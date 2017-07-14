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
