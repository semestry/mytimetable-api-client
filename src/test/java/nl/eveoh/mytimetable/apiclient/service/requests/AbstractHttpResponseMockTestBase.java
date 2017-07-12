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

    public static final String API_BASE_URL = "https://demo.eveoh.nl";
    public static final String API_PATH = "/api/";
    public static final String API_ENDPOINT = API_BASE_URL + API_PATH;

    public static final String API_KEY = "test-api-key";

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

    public CloseableHttpClient httpClient;

    public MyTimetableService service;


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

    public abstract Configuration getConfiguration();

    public CloseableHttpResponse createResponse(int httpStatus, String content) {
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

    public CloseableHttpResponse createEmptyCollectionResponse(int httpStatus) {
        return createResponse(httpStatus, BODY_EMPTY_COLLECTION);
    }

    public CloseableHttpResponse createEmptyElementResponse(int httpStatus) {
        return createResponse(httpStatus, BODY_EMPTY_ELEMENT);
    }
}
