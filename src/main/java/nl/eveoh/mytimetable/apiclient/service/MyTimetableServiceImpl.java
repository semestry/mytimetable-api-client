/*
 * Copyright 2013 - 2014 Eveoh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.eveoh.mytimetable.apiclient.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import nl.eveoh.mytimetable.apiclient.configuration.ConfigurationChangeListener;
import nl.eveoh.mytimetable.apiclient.exception.LocalizableException;
import nl.eveoh.mytimetable.apiclient.model.Event;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the MyTimetableService interface.
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 * @see MyTimetableService
 */
public class MyTimetableServiceImpl implements MyTimetableService, ConfigurationChangeListener {

    private static final Logger log = LoggerFactory.getLogger(MyTimetableServiceImpl.class);

    private CloseableHttpClient client = null;

    private MyTimetableHttpClientBuilder clientBuilder = new MyTimetableHttpClientBuilderImpl();

    private ObjectMapper mapper = new ObjectMapper();

    private Configuration configuration;


    public MyTimetableServiceImpl(Configuration configuration, MyTimetableHttpClientBuilder clientBuilder) {
        this.configuration = configuration;
        if (clientBuilder != null)
            this.clientBuilder = clientBuilder;

        reinitializeHttpClient();

        // Make sure the Jackson ObjectMapper does not fail on other properties in the JSON response.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public MyTimetableServiceImpl(Configuration configuration) {
        this(configuration, null);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        this.configuration = configuration;
        reinitializeHttpClient();
    }

    public void reinitializeHttpClient() {
        this.close();
        client = clientBuilder.build(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public List<Event> getUpcomingEvents(String username) {
        ArrayList<HttpUriRequest> requests = getApiRequests(username);

        for (HttpUriRequest request : requests) {
            CloseableHttpResponse response = null;

            try {
                response = client.execute(request);

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream stream = entity.getContent();

                    try {
                        return mapper.readValue(stream, mapper.getTypeFactory().constructCollectionType(List.class, Event.class));
                    } catch (JsonParseException e) {
                        log.error("Could not fetch results from MyTimetable API.", e);
                    } catch (JsonMappingException e) {
                        log.error("Could not fetch results from MyTimetable API.", e);
                    } finally {
                        stream.close();
                    }
                }
            } catch (ClientProtocolException e) {
                log.error("Could not fetch results from MyTimetable API.", e);
            } catch (IOException e) {
                log.error("Could not fetch results from MyTimetable API.", e);
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        log.warn("Error while closing HttpResponse.", e);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public void close() {
        if (client != null) {
            try {
                client.close();
                client = null;
            } catch (IOException e) {
                log.warn("Could not close HttpClient", e);
            }
        }
    }

    /**
     * Creates a request for each MyTimetable API endpoint defined in the configuration.
     *
     * @param username Username the fetch the upcoming events for.
     * @return List of {@link HttpUriRequest} objects, which should be executed in order, until a result is acquired.
     */
    private ArrayList<HttpUriRequest> getApiRequests(String username) {
        if (StringUtils.isBlank(username)) {
            log.error("Username cannot be empty.");
            throw new LocalizableException("Username cannot be empty.", "notLoggedIn");
        }

        if (StringUtils.isBlank(configuration.getApiKey())) {
            log.error("API key cannot be empty.");
            throw new LocalizableException("API key cannot be empty.");
        }

        // Prefix the username, for example when MyTimetable is used in a domain.
        String domainPrefix = configuration.getUsernameDomainPrefix();
        if (domainPrefix != null && !domainPrefix.isEmpty()) {
            username = domainPrefix + '\\' + username;
        }

        // Postfix the username, for example when usernames need a domain postfix
        String postfix = configuration.getUsernamePostfix();
        if (postfix != null && !postfix.isEmpty()) {
            username = username + postfix;
        }

        // build request URI
        Date currentTime = new Date();

        ArrayList<HttpUriRequest> requests = new ArrayList<HttpUriRequest>();

        for (String uri : configuration.getApiEndpointUris()) {
            String baseUrl;

            if (uri.endsWith("/")) {
                baseUrl = uri + "timetable";
            } else {
                baseUrl = uri + "/timetable";
            }

            try {
                URIBuilder uriBuilder = new URIBuilder(baseUrl);
                uriBuilder.addParameter("startDate", Long.toString(currentTime.getTime()));
                uriBuilder.addParameter("limit", Integer.toString(configuration.getMaxNumberOfEvents()));

                for (String type : configuration.getTimetableTypes()) {
                    uriBuilder.addParameter("type", type);
                }

                URI apiUri = uriBuilder.build();

                HttpGet request = new HttpGet(apiUri);
                request.addHeader("apiToken", configuration.getApiKey());
                request.addHeader("requestedAuth", username);

                // Configure request timeouts.
                RequestConfig requestConfig = RequestConfig.custom()
                        .setSocketTimeout(configuration.getApiSocketTimeout())
                        .setConnectTimeout(configuration.getApiConnectTimeout())
                        .build();

                request.setConfig(requestConfig);

                requests.add(request);
            } catch (URISyntaxException e) {
                log.error("Incorrect MyTimetable API url syntax.", e);
            }
        }

        if (requests.isEmpty()) {
            log.error("No usable MyTimetable API url.");
            throw new LocalizableException("No usable MyTimetable API url.");
        }

        return requests;
    }
}