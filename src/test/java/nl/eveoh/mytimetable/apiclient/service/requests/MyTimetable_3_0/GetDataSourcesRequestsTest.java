package nl.eveoh.mytimetable.apiclient.service.requests.MyTimetable_3_0;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static nl.eveoh.mytimetable.apiclient.service.helper.QueryParamSizeMatcher.queryParamSize;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetDataSourcesRequestsTest extends HttpResponseMockTestBase {

    private final CloseableHttpResponse TEST_HTTP_RESPONSE = createResponse(HttpStatus.SC_OK, "{\"database\":[]}");

    private static final String EXPECTED_PATH = API_PATH + "v0/databases";


    @Test
    public void get() throws IOException {
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(TEST_HTTP_RESPONSE);

        service.getDataSources();

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(0)));
    }
}
