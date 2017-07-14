/*
 * Copyright 2013 - 2017 Eveoh
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
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.net.UrlEscapers;
import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import nl.eveoh.mytimetable.apiclient.exception.HttpException;
import nl.eveoh.mytimetable.apiclient.exception.InvalidConfigurationException;
import nl.eveoh.mytimetable.apiclient.exception.NoUsableMyTimetableApiUrlException;
import nl.eveoh.mytimetable.apiclient.model.DataSource;
import nl.eveoh.mytimetable.apiclient.model.Event;
import nl.eveoh.mytimetable.apiclient.model.LocationTimetable;
import nl.eveoh.mytimetable.apiclient.model.Timetable;
import nl.eveoh.mytimetable.apiclient.model.TimetableFilterOption;
import nl.eveoh.mytimetable.apiclient.model.TimetableFilterType;
import nl.eveoh.mytimetable.apiclient.model.TimetableType;
import nl.eveoh.mytimetable.apiclient.service.mapper.DataSourceDetailsListStreamMapper;
import nl.eveoh.mytimetable.apiclient.service.mapper.DataSourceListStreamMapper;
import nl.eveoh.mytimetable.apiclient.service.mapper.EventListStreamMapper;
import nl.eveoh.mytimetable.apiclient.service.mapper.LocationTimetableListMapper;
import nl.eveoh.mytimetable.apiclient.service.mapper.StreamMapper;
import nl.eveoh.mytimetable.apiclient.service.mapper.TimetableFilterTypeListMapper;
import nl.eveoh.mytimetable.apiclient.service.mapper.TimetableListMapper;
import nl.eveoh.mytimetable.apiclient.service.mapper.TimetableTypeDetailsListStreamMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the {@link MyTimetableService} interface.
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 */
public class MyTimetableServiceImpl implements MyTimetableService {

    private static final Logger log = LoggerFactory.getLogger(MyTimetableServiceImpl.class);

    private CloseableHttpClient client;

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
    public List<Event> getUserTimetable(String username, Date startDate, Date endDate, int limit, List<String> types,
                                        boolean excludeResourceTimetables) {
        if (StringUtils.isBlank(username)) {
            log.error("Username cannot be empty.");

            throw new IllegalArgumentException("Username cannot be empty.");
        }

        Multimap<String, String> params = HashMultimap.create();

        if (this.configuration.getMyTimetableVersion() == Configuration.MyTimetable_Version.V3_1) {
            params.put("excludeResourceTimetables", Boolean.toString(excludeResourceTimetables));
        }

        if (startDate != null) {
            params.put("startDate", Long.toString(startDate.getTime()));
        }

        if (endDate != null) {
            params.put("endDate", Long.toString(endDate.getTime()));
        }

        if (limit > 0) {
            params.put("limit", Integer.toString(limit));
        }

        if (types != null) {
            for (String type : types) {
                params.put("type", type);
            }
        }

        return performRequest(new EventListStreamMapper(mapper), "v0.5/timetable", params, username);
    }

    @Override
    public List<Event> getTimetableByKey(String key, Date startDate, Date endDate, int limit) {
        assertParamNotNull(key, "key");

        Multimap<String, String> params = HashMultimap.create();

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
        return performRequest(new EventListStreamMapper(mapper), "v0.5/timetables/" + encodedKey, params, null);
    }

    @Override
    public List<Event> getTimetableByHostKey(String key, String type, Date startDate, Date endDate, int limit) {
        assertParamNotNull(key, "key");
        assertParamNotNull(type, "type");

        Multimap<String, String> params = HashMultimap.create();
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
        return performRequest(new EventListStreamMapper(mapper), "v0.5/timetables/" + encodedKey, params, null);
    }

    @Override
    public List<Timetable> getTimetables(String type, String d, String q, Map<String, TimetableFilterOption> filters,
                                         int limit, int offset) {
        return getTimetables(new TimetableListMapper(mapper), type, d, q, filters, limit, offset);
    }

    @Override
    public List<LocationTimetable> getLocationTimetables(String type, String d, String q,
                                                         Map<String, TimetableFilterOption> filters, int limit,
                                                         int offset) {
        return getTimetables(new LocationTimetableListMapper(mapper), type, d, q, filters, limit, offset);
    }

    private <T> T getTimetables(StreamMapper<T> streamMapper, String type, String d, String q,
                                Map<String, TimetableFilterOption> filters, int limit, int offset) {
        assertParamNotNull(type, "type");

        Multimap<String, String> params = HashMultimap.create();

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
                params.put(filter.getKey() + "Filter", filter.getValue().getId());
            }
        }

        return performRequest(streamMapper, "v0/timetables", params, null);
    }

    @Override
    public List<TimetableFilterType> getTimetableFilters(String type, String d,
                                                         Map<String, TimetableFilterOption> filters) {
        assertParamNotNull(type, "type");

        Multimap<String, String> params = HashMultimap.create();

        params.put("type", type);

        if (d != null) {
            params.put("d", d);
        }

        if (filters != null) {
            for (Map.Entry<String, TimetableFilterOption> filter : filters.entrySet()) {
                params.put(filter.getKey() + "Filter", filter.getValue().getId());
            }
        }

        return performRequest(new TimetableFilterTypeListMapper(mapper), "v0/timetablefilters", params, null);
    }

    @Override
    @Deprecated
    public List<Event> getUpcomingEvents(String username, int limit) {
        return getUpcomingEvents(username, limit, false);
    }

    @Override
    public List<Event> getUpcomingEvents(String username, int limit, boolean excludeResourceTimetables) {
        return getUserTimetable(username, new Date(), null, limit, null, excludeResourceTimetables);
    }

    @Override
    public List<DataSource> getDataSources() {
        if (this.configuration.getMyTimetableVersion() == Configuration.MyTimetable_Version.V3_0) {
            return performRequest(new DataSourceListStreamMapper(mapper), "v0/databases", null, null);
        } else {
            return performRequest(new DataSourceDetailsListStreamMapper(mapper), "v0/databasedetails", null, null);
        }
    }

    @Override
    public List<TimetableType> getTimetableTypes() {
        return performRequest(new TimetableTypeDetailsListStreamMapper(mapper), "v0/timetabletypesdetails", null, null);
    }

    @SuppressWarnings("AssignmentToNull")
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

    private <T> T performRequest(StreamMapper<T> mapper, String path, Multimap<String, String> params,
                                 String requestedAuth) {
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
     * @param path          endpoint to call, will be automatically prefixed with the API base URL
     * @param params        {@link Multimap} containing all query parameters
     * @param requestedAuth username of the user to impersonate, or {@code null} if no impersonation is required
     *
     * @return List of {@link HttpUriRequest} objects, which should be executed in order, until a result is acquired.
     */
    private ArrayList<HttpUriRequest> getApiRequests(String path, Multimap<String, String> params,
                                                     String requestedAuth) {
        ArrayList<HttpUriRequest> requests = new ArrayList<>();

        for (String uri : configuration.getApiEndpointUris()) {
            StringBuilder baseUrl = new StringBuilder(uri);
            if (!uri.endsWith("/")) {
                baseUrl.append('/');
            }
            baseUrl.append(path);

            try {
                URIBuilder uriBuilder = new URIBuilder(baseUrl.toString());

                if (params != null) {
                    for (Map.Entry<String, Collection<String>> param : params.asMap().entrySet()) {
                        for (String v : param.getValue()) {
                            uriBuilder.addParameter(param.getKey(), v);
                        }
                    }
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

    private void assertParamNotNull(Object param, String paramDescription) {
        if (param == null) {
            throw new IllegalArgumentException('\'' + paramDescription + "' must not be null");
        }
    }
}
