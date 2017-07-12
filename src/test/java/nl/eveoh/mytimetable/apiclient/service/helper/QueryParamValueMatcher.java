package nl.eveoh.mytimetable.apiclient.service.helper;

import org.apache.http.client.methods.HttpUriRequest;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class QueryParamValueMatcher extends TypeSafeMatcher<HttpUriRequest> {

    protected final String queryParam;
    protected final String queryParamValue;


    public QueryParamValueMatcher(final String queryParam, final String queryParamValue) {
        this.queryParam = queryParam;
        this.queryParamValue = queryParamValue;
    }

    @Override
    protected boolean matchesSafely(HttpUriRequest item) {
        return item.getURI().getQuery().equals(queryParam + "=" + queryParamValue) ||
                item.getURI().getQuery().startsWith(queryParam + "=" + queryParamValue + "&") ||
                item.getURI().getQuery().endsWith("&" + queryParam + "=" + queryParamValue) ||
                item.getURI().getQuery().contains("&" + queryParam + "=" + queryParamValue + "&");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a HttpUriRequest containing a queryParam ")
                .appendText(queryParam)
                .appendText("=")
                .appendText(queryParamValue);
    }

    /**
     * Creates a matcher that matches if the examined {@link HttpUriRequest} contains the specified
     * query parameter and value anywhere.
     * <p/>
     * For example:
     * <pre>assertThat(myHttpUriRequest, containsQueryParam("foo", "bar"))</pre>
     *
     * @param queryParam      the query parameter that the returned matcher will expect to find within a HttpUriRequest
     * @param queryParamValue the query parameter value that the returned matcher will expect to find within a
     *                        HttpUriRequest
     */
    @Factory
    public static Matcher<HttpUriRequest> containsQueryParam(String queryParam, String queryParamValue) {
        return new QueryParamValueMatcher(queryParam, queryParamValue);
    }
}
