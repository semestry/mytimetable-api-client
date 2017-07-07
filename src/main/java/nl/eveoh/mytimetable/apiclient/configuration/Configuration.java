/*
 * Copyright 2013 - 2014 Eveoh
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

package nl.eveoh.mytimetable.apiclient.configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Configuration object.
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 */
public class Configuration {

    public enum MyTimetable_Version {
        V3_0, V3_1
    }

    /**
     * Key used for communicating with the MyTimetable API.
     * <p/>
     * Key should have elevated access.
     */
    private String apiKey = "";

    /**
     * Endpoint URL of the MyTimetable API.
     * <p/>
     * Should be something like <tt>https://timetable.institution.ac.uk/api/v0/</tt>.
     */
    private List<String> apiEndpointUris = new ArrayList<String>();

    /**
     * Whether the SSL certificate CN should be verified when connecting to the MyTimetable API.
     * <p/>
     * Default to {@code true}.
     */
    private boolean apiSslCnCheck = true;

    /**
     * Timeout for connecting to the MyTimetable API, in milliseconds.
     * <p/>
     * Defaults to 1000 (1 second).
     */
    private int apiConnectTimeout = 1000;

    /**
     * Timeout for the socket waiting for data from the MyTimetable API.
     * <p/>
     * Defaults to 10000 (10 seconds).
     */
    private int apiSocketTimeout = 10000;

    /**
     * Maximum number of concurrent connections in the MyTimetable API connection pool.
     */
    private int apiMaxConnections = 20;

    /**
     * Enable Gzip compression
     * <p>
     * Defaults to true
     */
    private boolean apiEnableGzip = true;

    /**
     * Sets the MyTimetable version of the client.
     * <p>
     * Higher MyTimetable versions might return richer objects. Please refer to the model documentation for more
     * information.
     */
    private MyTimetable_Version myTimetableVersion = MyTimetable_Version.V3_1;


    public Configuration() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<String> getApiEndpointUris() {
        return apiEndpointUris;
    }

    public void setApiEndpointUris(List<String> apiEndpointUris) {
        this.apiEndpointUris = apiEndpointUris;
    }

    public boolean isApiSslCnCheck() {
        return apiSslCnCheck;
    }

    public void setApiSslCnCheck(boolean apiSslCnCheck) {
        this.apiSslCnCheck = apiSslCnCheck;
    }

    public int getApiConnectTimeout() {
        return apiConnectTimeout;
    }

    public void setApiConnectTimeout(int apiConnectTimeout) {
        this.apiConnectTimeout = apiConnectTimeout;
    }

    public int getApiSocketTimeout() {
        return apiSocketTimeout;
    }

    public void setApiSocketTimeout(int apiSocketTimeout) {
        this.apiSocketTimeout = apiSocketTimeout;
    }

    public int getApiMaxConnections() {
        return apiMaxConnections;
    }

    public void setApiMaxConnections(int apiMaxConnections) {
        this.apiMaxConnections = apiMaxConnections;
    }

    public boolean isApiEnableGzip() {
        return apiEnableGzip;
    }

    public void setApiEnableGzip(boolean apiEnableGzip) {
        this.apiEnableGzip = apiEnableGzip;
    }

    public MyTimetable_Version getMyTimetableVersion() {
        return myTimetableVersion;
    }

    public void setMyTimetableVersion(MyTimetable_Version myTimetableVersion) {
        this.myTimetableVersion = myTimetableVersion;
    }
}
