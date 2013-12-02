/*
 * Eveoh MyTimetable, Web interface for timetables.
 *
 * Copyright (c) 2010 - 2013 Eveoh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, see src/main/webapp/license/gpl-3.0.txt.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package nl.eveoh.mytimetable.apiclient.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import nl.eveoh.mytimetable.apiclient.exception.LocalizableException;
import nl.eveoh.mytimetable.apiclient.model.Event;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
 * TODO: Implement SSL CN check skip, if configured.
 *
 * @see MyTimetableService
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 */
public class MyTimetableServiceImpl implements MyTimetableService {

    private static final Logger log = LoggerFactory.getLogger(MyTimetableServiceImpl.class);

    private static final CloseableHttpClient client = HttpClients.createDefault();

    private ObjectMapper mapper = new ObjectMapper();



    public MyTimetableServiceImpl() {
        // Make sure the Jackson ObjectMapper does not fail on other properties in the JSON response.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public List<Event> getUpcomingEvents(String username, Configuration configuration) {
        ArrayList<HttpUriRequest> requests = getApiRequests(username, configuration);

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

    /**
     * Creates a request for each MyTimetable API endpoint defined in the configuration.
     *
     * @param username      Username the fetch the upcoming events for.
     * @param configuration Configuration object.
     * @return              List of {@link HttpUriRequest} objects, which should be executed in order, until a result is acquired.
     */
    private ArrayList<HttpUriRequest> getApiRequests(String username, Configuration configuration) {
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
                uriBuilder.addParameter("limit", Integer.toString(configuration.getNumberOfEvents()));

                URI apiUri = uriBuilder.build();

                HttpUriRequest request = new HttpGet(apiUri);
                request.addHeader("apiToken", configuration.getApiKey());
                request.addHeader("requestedAuth", username);

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