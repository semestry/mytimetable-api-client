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

package nl.eveoh.mytimetable.apiclient.service.requests;

import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import nl.eveoh.mytimetable.apiclient.service.MyTimetableService;
import nl.eveoh.mytimetable.apiclient.service.MyTimetableServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractHttpResponseMockTestBase {

    private static final String API_BASE_URL = "https://demo.eveoh.nl";
    protected static final String API_PATH = "/api/";
    protected static final String API_ENDPOINT = API_BASE_URL + API_PATH;

    protected static final String API_KEY = "test-api-key";

    private static final String BODY_EMPTY_ELEMENT = "{}";
    private static final String BODY_EMPTY_COLLECTION = "[]";

    protected static final String QUERY_PARAM_DS = "d";
    protected static final String QUERY_PARAM_END_DATE = "endDate";
    protected static final String QUERY_PARAM_EXCLUDE_RESOURCE_TIMETABLES = "excludeResourceTimetables";
    protected static final String QUERY_PARAM_FETCH_BY = "fetchBy";
    protected static final String QUERY_PARAM_LIMIT = "limit";
    protected static final String QUERY_PARAM_OFFSET = "offset";
    protected static final String QUERY_PARAM_QUERY = "q";
    protected static final String QUERY_PARAM_START_DATE = "startDate";
    protected static final String QUERY_PARAM_TYPE = "type";

    protected CloseableHttpClient httpClient;

    protected MyTimetableService service;


    @Before
    public void setUp() throws IOException {
        MyTimetableHttpClientBuilderMockImpl mockClientBuilder = new MyTimetableHttpClientBuilderMockImpl();

        this.service = new MyTimetableServiceImpl(getConfiguration(), mockClientBuilder);

        this.httpClient = mockClientBuilder.getMockHttpClient();
    }

    @After
    public void tearDown() throws Exception {
        this.service.close();
    }

    protected abstract Configuration getConfiguration();

    protected CloseableHttpResponse createResponse(int httpStatus, String content) {
        CloseableHttpResponse httpResponse = mock(CloseableHttpResponse.class);
        HttpEntity entity = mock(HttpEntity.class);

        try {
            when(httpResponse.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, httpStatus, null));
            when(entity.getContent()).thenReturn(
                    new ByteArrayInputStream(Charset.forName("UTF-8").encode(content).array()));
            when(httpResponse.getEntity()).thenReturn(entity);
        } catch (IOException e) {
            // Doesn't happen on mocks
        }

        return httpResponse;
    }

    protected CloseableHttpResponse createEmptyCollectionResponse(int httpStatus) {
        return createResponse(httpStatus, BODY_EMPTY_COLLECTION);
    }

    public CloseableHttpResponse createEmptyElementResponse(int httpStatus) {
        return createResponse(httpStatus, BODY_EMPTY_ELEMENT);
    }
}
