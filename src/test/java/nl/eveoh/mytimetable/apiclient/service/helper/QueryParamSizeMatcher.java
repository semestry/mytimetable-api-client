package nl.eveoh.mytimetable.apiclient.service.helper;

import org.apache.http.client.methods.HttpUriRequest;
import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class QueryParamSizeMatcher extends FeatureMatcher<HttpUriRequest, Integer> {

    public QueryParamSizeMatcher(Matcher<? super Integer> sizeMatcher) {
        super(sizeMatcher, "an array with size", "array size");
    }


    @Override
    protected Integer featureValueOf(HttpUriRequest actual) {
        if (actual.getURI().getQuery() == null) {
            return 0;
        }

        return actual.getURI().getQuery().length() - actual.getURI().getQuery().replace("=", "").length();
    }


    /**
     * Creates a matcher that matches if the number of query parameters in the examined {@link HttpUriRequest} satisfies
     * the specified matcher
     * <p/>
     * For example:
     * <pre>assertThat(myHttpUriRequest, queryParamSize(is(2)))</pre>
     *
     * @param sizeMatcher a matcher for the number of query parameters in the examined {@link HttpUriRequest}
     */
    @Factory
    public static Matcher<HttpUriRequest> queryParamSize(org.hamcrest.Matcher<? super java.lang.Integer> sizeMatcher) {
        return new QueryParamSizeMatcher(sizeMatcher);
    }
}
