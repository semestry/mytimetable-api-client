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

package nl.eveoh.mytimetable.apiclient.service.requests.MyTimetable_3_2;

import nl.eveoh.mytimetable.apiclient.exception.ErrorResponseException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static nl.eveoh.mytimetable.apiclient.service.helper.QueryParamSizeMatcher.queryParamSize;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetDataSourcesRequestsTest extends HttpResponseMockTestBase {

    private static final String EMPTY_GET_TERMS_RESPONSE = "{\"terms\":[]}";

    private static final String EXPECTED_PATH = API_PATH + "v0/terms";


    @Test
    public void get() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_OK, EMPTY_GET_TERMS_RESPONSE);
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        service.getTerms();

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(0)));
    }

    @Test
    /**
     * A 404 is returned when the terms endpoint does not exist (i.e. when MyTimetable 3.2 has not been patched).
     */
    public void getNotPatched() throws IOException {
        CloseableHttpResponse httpResponse = createResponse(HttpStatus.SC_NOT_FOUND, "");
        when(httpClient.execute(any(HttpRequestBase.class))).thenReturn(httpResponse);

        catchException(service).getTerms();

        ErrorResponseException exception = caughtException();
        assertThat(exception, instanceOf(ErrorResponseException.class));
        Assert.assertNotNull(exception.getErrorMessage());
        Assert.assertEquals("404", exception.getErrorMessage().getType());
        Assert.assertNull(exception.getErrorMessage().getMessage());

        ArgumentCaptor<HttpUriRequest> captor = ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient, only()).execute(captor.capture());

        HttpUriRequest actualHttpRequest = captor.getValue();

        Assert.assertEquals(HttpGet.METHOD_NAME, actualHttpRequest.getMethod());
        Assert.assertEquals(EXPECTED_PATH, actualHttpRequest.getURI().getPath());
        Assert.assertThat(actualHttpRequest, queryParamSize(is(0)));
    }
}
