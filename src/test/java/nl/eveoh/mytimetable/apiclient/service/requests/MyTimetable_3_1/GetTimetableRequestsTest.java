package nl.eveoh.mytimetable.apiclient.service.requests.MyTimetable_3_1;

import com.google.common.collect.Lists;
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

public class GetTimetableRequestsTest extends HttpResponseMockTestBase {

    private static final String EXPECTED_PATH = API_PATH + "v0.5/timetable";

    private static final int TEST_START_DATE_TIMESTAMP = 1499348000;
    private static final Date TEST_START_DATE = new Date(TEST_START_DATE_TIMESTAMP);

    private static final int TEST_END_DATE_TIMESTAMP = 1499368000;
    private static final Date TEST_END_DATE = new Date(TEST_END_DATE_TIMESTAMP);

    private static final String TEST_USERNAME = "username";


    @Test
    public void getNoUsername() throws IOException {
        catchException(service).getUserTimetable(null, null, null, 0, null, false);
        assertThat(caughtException(), instanceOf(IllegalArgumentException.class));

        verify(httpClient, never()).execute(any(HttpRequestBase.class));
    }

    @Test
    public void getForStartDate() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getUserTimetable(TEST_USERNAME, TEST_START_DATE, null, 0, null, false);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(2)));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_START_DATE, Integer.toString(TEST_START_DATE_TIMESTAMP)));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_EXCLUDE_RESOURCE_TIMETABLES, Boolean.FALSE.toString()));
    }

    @Test
    public void getForEndDate() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getUserTimetable(TEST_USERNAME, null, TEST_END_DATE, 0, null, false);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(2)));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_END_DATE, Integer.toString(TEST_END_DATE_TIMESTAMP)));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_EXCLUDE_RESOURCE_TIMETABLES, Boolean.FALSE.toString()));
    }

    @Test
    public void getForLimit() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getUserTimetable(TEST_USERNAME, null, null, 10, null, false);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(2)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_LIMIT, "10"));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_EXCLUDE_RESOURCE_TIMETABLES, Boolean.FALSE.toString()));
    }

    @Test
    public void getForTypes() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getUserTimetable(TEST_USERNAME, null, null, 0, Lists.newArrayList("module", "pos"), false);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(3)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, "module"));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, "pos"));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_EXCLUDE_RESOURCE_TIMETABLES, Boolean.FALSE.toString()));
    }

    @Test
    public void getForExcludeResourceTimetables() throws IOException {
        CloseableHttpResponse httpResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getUserTimetable(TEST_USERNAME, null, null, 0, null, true);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(1)));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam(QUERY_PARAM_EXCLUDE_RESOURCE_TIMETABLES, Boolean.TRUE.toString()));
    }
}
