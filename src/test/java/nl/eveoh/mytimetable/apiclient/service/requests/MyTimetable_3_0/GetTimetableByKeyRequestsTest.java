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
import java.util.Date;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static nl.eveoh.mytimetable.apiclient.service.helper.QueryParamValueMatcher.containsQueryParam;
import static nl.eveoh.mytimetable.apiclient.service.helper.QueryParamSizeMatcher.queryParamSize;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetTimetableByKeyRequestsTest extends HttpResponseMockTestBase {

    private static final String TEST_TIMETABLE_KEY = "testKey";
    private static final int TEST_DATE_TIMESTAMP = 1499348000;
    private static final Date TEST_DATE = new Date(TEST_DATE_TIMESTAMP);

    private static final String EXPECTED_PATH = API_PATH + "v0.5/timetables/" + TEST_TIMETABLE_KEY;


    @Test
    public void getNoKey() throws IOException {
        catchException(service).getTimetableByKey(null, null, null, 0);
        assertThat(caughtException(), instanceOf(IllegalArgumentException.class));

        verify(httpClient, never()).execute(any(HttpRequestBase.class));
    }

    @Test
    public void getKey() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetableByKey(TEST_TIMETABLE_KEY, null, null, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
    }

    @Test
    public void getForStartDate() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetableByKey(TEST_TIMETABLE_KEY, TEST_DATE, null, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());

        Assert.assertThat(actualHttpRequest, queryParamSize(is(1)));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_START_DATE, Integer.toString(TEST_DATE_TIMESTAMP)));
    }

    @Test
    public void getForEndDate() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetableByKey(TEST_TIMETABLE_KEY, null, TEST_DATE, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(1)));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_END_DATE, Integer.toString(TEST_DATE_TIMESTAMP)));
    }

    @Test
    public void getForLimit() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetableByKey(TEST_TIMETABLE_KEY, null, null, 10);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(1)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_LIMIT, "10"));
    }
}
