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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.UrlEscapers;
import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import nl.eveoh.mytimetable.apiclient.exception.HttpException;
import nl.eveoh.mytimetable.apiclient.exception.InvalidConfigurationException;
import nl.eveoh.mytimetable.apiclient.exception.NoUsableMyTimetableApiUrlException;
import nl.eveoh.mytimetable.apiclient.model.*;
import nl.eveoh.mytimetable.apiclient.service.mapper.*;
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
import java.util.*;

/**
 * Implementation of the {@link MyTimetableService} interface.
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 */
public class MyTimetableServiceImpl implements MyTimetableService {

    private static final Logger log = LoggerFactory.getLogger(MyTimetableServiceImpl.class);

    private CloseableHttpClient client = null;

    private MyTimetableHttpClientBuilder clientBuilder = new MyTimetableHttpClientBuilderImpl();

    private ObjectMapper mapper = new ObjectMapper();

    private Configuration configuration;


    public MyTimetableServiceImpl(Configuration configuration, MyTimetableHttpClientBuilder clientBuilder) {
        this.configuration = configuration;

        if (clientBuilder != null) {
            this.clientBuilder = clientBuilder;
        }

        reinitializeHttpClient();

        // Make sure the Jackson ObjectMapper does not fail on other properties in the JSON response.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public MyTimetableServiceImpl(Configuration configuration) {
        this(configuration, null);
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        reinitializeHttpClient();
    }

    public void reinitializeHttpClient() {
        this.close();

        if (StringUtils.isBlank(configuration.getApiKey())) {
            log.error("API key cannot be empty.");
            throw new InvalidConfigurationException("API key cannot be empty.");
        }

        client = clientBuilder.build(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public List<Event> getTimetableByKey(String key, Date startDate, Date endDate, int limit) {
        HashMap<String, String> params = new HashMap<>();

        if (startDate != null) {
            params.put("startDate", Long.toString(startDate.getTime()));
        }
        if (endDate != null) {
            params.put("endDate", Long.toString(endDate.getTime()));
        }
        if (limit > 0) {
            params.put("limit", Integer.toString(limit));
        }

        String encodedKey = UrlEscapers.urlPathSegmentEscaper().escape(key);
        return performRequest(new EventListStreamMapper(mapper), "timetables/" + encodedKey, params, null);
    }

    @Override
    public List<Event> getTimetableByHostKey(String key, String type, Date startDate, Date endDate, int limit) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("fetchBy", "hostKey");
        params.put("type", type);

        if (startDate != null) {
            params.put("startDate", Long.toString(startDate.getTime()));
        }
        if (endDate != null) {
            params.put("endDate", Long.toString(endDate.getTime()));
        }
        if (limit > 0) {
            params.put("limit", Integer.toString(limit));
        }

        String encodedKey = UrlEscapers.urlPathSegmentEscaper().escape(key);
        return performRequest(new EventListStreamMapper(mapper), "timetables/" + encodedKey, params, null);
    }

    @Override
    public List<Timetable> getTimetables(String type, String d, String q, Map<String, TimetableFilterOption> filters, int limit, int offset) {
        return getTimetables(new TimetableListMapper(mapper), type, d, q, filters, limit, offset);
    }

    @Override
    public List<LocationTimetable> getLocationTimetables(String type, String d, String q, Map<String, TimetableFilterOption> filters, int limit, int offset) {
        return getTimetables(new LocationTimetableListMapper(mapper), type, d, q, filters, limit, offset);
    }

    private <T> T getTimetables(StreamMapper<T> streamMapper, String type, String d, String q, Map<String, TimetableFilterOption> filters, int limit, int offset) {
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("type", type);

        if (d != null) {
            params.put("d", d);
        }
        if (q != null) {
            params.put("q", q);
        }
        if (limit > 0) {
            params.put("limit", Integer.toString(limit));
        }
        if (offset > 0) {
            params.put("offset", Integer.toString(offset));
        }

        if (filters != null) {
            for (Map.Entry<String, TimetableFilterOption> filter : filters.entrySet()) {
                params.put(filter.getKey() + "Filter", filter.getValue().getValue());
            }
        }

        return performRequest(streamMapper, "timetables", params, null);
    }

    @Override
    public List<TimetableFilterType> getTimetableFilters(String type, String d, Map<String, TimetableFilterOption> filters) {
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("type", type);

        if (d != null) {
            params.put("d", d);
        }

        if (filters != null) {
            for (Map.Entry<String, TimetableFilterOption> filter : filters.entrySet()) {
                params.put(filter.getKey() + "Filter", filter.getValue().getValue());
            }
        }

        return performRequest(new TimetableFilterTypeListMapper(mapper), "timetablefilters", params, null);
    }

    @Override
    public List<Event> getUpcomingEvents(String username, int limit) {
        if (StringUtils.isBlank(username)) {
            log.error("Username cannot be empty.");
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        HashMap<String, String> params = new HashMap<String, String>();

        Date currentTime = new Date();
        params.put("startDate", Long.toString(currentTime.getTime()));
        params.put("limit", Integer.toString(limit));

        return performRequest(new EventListStreamMapper(mapper), "timetable", params, username);
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

    private <T> T performRequest(StreamMapper<T> mapper, String path, Map<String, String> params, String requestedAuth) {
        ArrayList<HttpUriRequest> requests = getApiRequests(path, params, requestedAuth);

        for (HttpUriRequest request : requests) {
            CloseableHttpResponse response = null;

            try {
                response = client.execute(request);

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream stream = entity.getContent();

                    try {
                        return mapper.map(stream);
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

        if (requests.isEmpty()) {
            throw new RuntimeException("No API request has been built.");
        } else {
            throw new HttpException("Could not fetch results from MyTimetable API.");
        }
    }

    /**
     * Creates a request for each MyTimetable API endpoint defined in the configuration.
     *
     * @param requestedAuth Username the fetch the upcoming events for.
     * @return List of {@link HttpUriRequest} objects, which should be executed in order, until a result is acquired.
     */
    private ArrayList<HttpUriRequest> getApiRequests(String path, Map<String, String> params, String requestedAuth) {
        ArrayList<HttpUriRequest> requests = new ArrayList<HttpUriRequest>();

        for (String uri : configuration.getApiEndpointUris()) {
            StringBuilder baseUrl = new StringBuilder(uri);
            if (!uri.endsWith("/")) {
                baseUrl.append('/');
            }
            baseUrl.append(path);

            try {
                URIBuilder uriBuilder = new URIBuilder(baseUrl.toString());
                for (Map.Entry<String, String> param : params.entrySet()) {
                    uriBuilder.addParameter(param.getKey(), param.getValue());
                }

                URI apiUri = uriBuilder.build();

                HttpGet request = new HttpGet(apiUri);
                request.addHeader("apiToken", configuration.getApiKey());
                if (StringUtils.isNotBlank(requestedAuth)) {
                    request.addHeader("requestedAuth", requestedAuth);
                }

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
            throw new NoUsableMyTimetableApiUrlException("No usable MyTimetable API url.");
        }

        return requests;
    }
}
