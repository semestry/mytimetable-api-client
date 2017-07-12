package nl.eveoh.mytimetable.apiclient.service.requests.MyTimetable_3_0;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationTest extends HttpResponseMockTestBase {

    @Test
    public void testElevatedAuthentication() throws IOException {
        CloseableHttpResponse emptyCollectionResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(emptyCollectionResponse);

        String username = "ldaptest12";
        service.getUpcomingEvents(username, 5, false);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, atLeastOnce()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertTrue(actualHttpRequest.containsHeader("apiToken"));
        Assert.assertTrue(actualHttpRequest.containsHeader("requestedAuth"));

        Assert.assertEquals(1, actualHttpRequest.getHeaders("apiToken").length);
        Assert.assertEquals(1, actualHttpRequest.getHeaders("requestedAuth").length);

        Assert.assertEquals(API_KEY, actualHttpRequest.getFirstHeader("apiToken").getValue());
        Assert.assertEquals(username, actualHttpRequest.getFirstHeader("requestedAuth").getValue());
    }

    @Test
    public void testKeyAuthentication() throws IOException {
        CloseableHttpResponse emptyCollectionResponse = createEmptyCollectionResponse(HttpStatus.SC_OK);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(emptyCollectionResponse);

        service.getTimetableByHostKey("hostkey", "module", null, null, 0);

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, atLeastOnce()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertTrue(actualHttpRequest.containsHeader("apiToken"));
        Assert.assertFalse(actualHttpRequest.containsHeader("requestedAuth"));

        Assert.assertEquals(1, actualHttpRequest.getHeaders("apiToken").length);
        Assert.assertEquals(API_KEY, actualHttpRequest.getFirstHeader("apiToken").getValue());
    }
}
