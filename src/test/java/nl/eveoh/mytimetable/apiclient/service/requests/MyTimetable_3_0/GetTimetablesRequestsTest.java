package nl.eveoh.mytimetable.apiclient.service.requests.MyTimetable_3_0;

import nl.eveoh.mytimetable.apiclient.model.TimetableFilterOption;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

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

public class GetTimetablesRequestsTest extends HttpResponseMockTestBase {

    private static final String EMPTY_GET_TIMETABLE_RESPONSE = "{\"timetable\":[]}";

    private static final String EXPECTED_PATH = API_PATH + "v0/timetables";

    private static final String TEST_TIMETABLE_TYPE = "module";
    private static final String TEST_DS = "2017";


    @Test
    public void getNoType() throws IOException {
        catchException(service).getTimetables(null, null, null, null, 0, 0);
        assertThat(caughtException(), instanceOf(IllegalArgumentException.class));

        verify(httpClient, never()).execute(any(HttpRequestBase.class));
    }

    @Test
    public void getForType() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_OK, EMPTY_GET_TIMETABLE_RESPONSE);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetables(TEST_TIMETABLE_TYPE, null, null, null, 0, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(1)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, TEST_TIMETABLE_TYPE));
    }

    @Test
    public void getForTypeAndDataSource() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_OK, EMPTY_GET_TIMETABLE_RESPONSE);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetables(TEST_TIMETABLE_TYPE, TEST_DS, null, null, 0, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(2)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, TEST_TIMETABLE_TYPE));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_DS, TEST_DS));
    }

    @Test
    public void getForTypeAndQuery() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_OK, EMPTY_GET_TIMETABLE_RESPONSE);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetables(TEST_TIMETABLE_TYPE, null, "test", null, 0, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(2)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, TEST_TIMETABLE_TYPE));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_QUERY, "test"));
    }

    @Test
    public void getForTypeAndTimetableFilterOptions() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_OK, EMPTY_GET_TIMETABLE_RESPONSE);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        TimetableFilterOption option = new TimetableFilterOption();
        option.setValue("646ADCA666D4A88402CA46C26A73803C");

        Map<String, TimetableFilterOption> filterOptionMap = new TreeMap<>();
        filterOptionMap.put("department", option);

        service.getTimetables(TEST_TIMETABLE_TYPE, null, null, filterOptionMap, 10, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(3)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, TEST_TIMETABLE_TYPE));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_LIMIT, "10"));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_LIMIT, "10"));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam("departmentFilter", "646ADCA666D4A88402CA46C26A73803C"));
    }

    @Test
    public void getForTypeAndMultipleTimetableFilterOptions() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_OK, EMPTY_GET_TIMETABLE_RESPONSE);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        TimetableFilterOption option = new TimetableFilterOption();
        option.setValue("646ADCA666D4A88402CA46C26A73803C");

        Map<String, TimetableFilterOption> filterOptionMap = new TreeMap<>();
        filterOptionMap.put("department", option);
        filterOptionMap.put(TEST_TIMETABLE_TYPE, option);

        service.getTimetables(TEST_TIMETABLE_TYPE, null, null, filterOptionMap, 10, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(4)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, TEST_TIMETABLE_TYPE));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_LIMIT, "10"));
        Assert.assertThat(actualHttpRequest,
                containsQueryParam("departmentFilter", "646ADCA666D4A88402CA46C26A73803C"));
        Assert.assertThat(actualHttpRequest, containsQueryParam("moduleFilter", "646ADCA666D4A88402CA46C26A73803C"));
    }

    @Test
    public void getForTypeAndLimit() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_OK, EMPTY_GET_TIMETABLE_RESPONSE);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetables(TEST_TIMETABLE_TYPE, null, null, null, 10, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(2)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, TEST_TIMETABLE_TYPE));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_LIMIT, "10"));
    }

    @Test
    public void getForTypeAndOffset() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_OK, EMPTY_GET_TIMETABLE_RESPONSE);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTimetables(TEST_TIMETABLE_TYPE, null, null, null, 0, 10);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(2)));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_TYPE, TEST_TIMETABLE_TYPE));
        Assert.assertThat(actualHttpRequest, containsQueryParam(QUERY_PARAM_OFFSET, "10"));
    }
}