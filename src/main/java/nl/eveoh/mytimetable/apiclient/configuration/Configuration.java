/*
 * Eveoh MyTimetable, Web interface for timetables.
 *
 * Copyright (c) 2010 - 2013 Eveoh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program, see src/main/webapp/license/gpl-3.0.txt.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package nl.eveoh.mytimetable.apiclient.configuration;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Generic Configuration object.
 *
 * @author Marco Krikke
 * @author Erik van Paassen
 */
public class Configuration {

    private static final String API_ENDPOINT_URIS = "apiEndpointUris";
    private static final String API_KEY = "apiKey";
    private static final String SSL_CN_CHECK = "sslCnCheck";
    private static final String APPLICATION_URI = "applicationUri";
    private static final String APPLICATION_TARGET = "applicationTarget";
    private static final String USERNAME_DOMAIN_PREFIX = "usernameDomainPrefix";
    private static final String NUMBER_OF_EVENTS = "numberOfEvents";

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
    private ArrayList<String> apiEndpointUris = new ArrayList<String>();

    /**
     * Whether the SSL certificate CN should be verified when connecting to the MyTimetable API.
     * <p/>
     * Default to {@code true}.
     */
    private boolean sslCnCheck = true;

    /**
     * URL to the full MyTimetable application.
     * <p/>
     * Should be something like <tt>https://timetable.institution.ac.uk/</tt>.
     */
    private String applicationUri = null;

    /**
     * Target of the full application link.
     * <p/>
     * Should be <tt>_self</tt>, <tt>_blank</tt>, <tt>_parent</tt>, or <tt>_top</tt>. Defaults to <tt>_blank</tt>.
     */
    private String applicationTarget = "_blank";

    /**
     * Number of events to.
     * <p/>
     * Defaults to 5.
     */
    private int numberOfEvents = 5;

    /**
     * Domain to prefix usernames with.
     * <p/>
     * Defaults to {@code null}.
     */
    private String usernameDomainPrefix;



    public Configuration() {}

    public Configuration(Properties properties) {
        apiKey = properties.getProperty(API_KEY);

        String uris = properties.getProperty(API_ENDPOINT_URIS);
        if (uris != null) {
            for (String uri : uris.split("\n")) {
                uri = uri.trim();

                if (!uri.isEmpty()) {
                    apiEndpointUris.add(uri);
                }
            }
        }

        sslCnCheck = Boolean.parseBoolean(properties.getProperty(SSL_CN_CHECK));
        applicationUri = properties.getProperty(APPLICATION_URI);
        applicationTarget = properties.getProperty(APPLICATION_TARGET);
        usernameDomainPrefix = properties.getProperty(USERNAME_DOMAIN_PREFIX);
        numberOfEvents = Integer.parseInt(properties.getProperty(NUMBER_OF_EVENTS));
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ArrayList<String> getApiEndpointUris() {
        return apiEndpointUris;
    }

    public void setApiEndpointUris(ArrayList<String> apiEndpointUris) {
        this.apiEndpointUris = apiEndpointUris;
    }

    public boolean isSslCnCheck() {
        return sslCnCheck;
    }

    public void setSslCnCheck(boolean sslCnCheck) {
        this.sslCnCheck = sslCnCheck;
    }

    public String getApplicationUri() {
        return applicationUri;
    }

    public void setApplicationUri(String applicationUri) {
        this.applicationUri = applicationUri;
    }

    public String getApplicationTarget() {
        return applicationTarget;
    }

    public void setApplicationTarget(String applicationTarget) {
        this.applicationTarget = applicationTarget;
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    public String getUsernameDomainPrefix() {
        return usernameDomainPrefix;
    }

    public void setUsernameDomainPrefix(String usernameDomainPrefix) {
        this.usernameDomainPrefix = usernameDomainPrefix;
    }

    /**
     * Creates a {@link Properties} object containing the configuration values.
     *
     * @return  Properties object
     */
    public Properties toProperties() {
        Properties ret = new Properties();

        if (applicationUri != null) {
            ret.setProperty(APPLICATION_URI, applicationUri);
        }

        if (applicationTarget != null) {
            ret.setProperty(APPLICATION_TARGET, applicationTarget);
        }

        StringBuilder uris = new StringBuilder();
        for (String apiEndpointUri : apiEndpointUris) {
            if (uris.length() > 0) {
                uris.append('\n');
            }

            uris.append(apiEndpointUri);
        }
        ret.setProperty(API_ENDPOINT_URIS, uris.toString());

        ret.setProperty(SSL_CN_CHECK, String.valueOf(sslCnCheck));

        if (apiKey != null) {
            ret.setProperty(API_KEY, apiKey);
        }

        ret.setProperty(NUMBER_OF_EVENTS, String.valueOf(numberOfEvents));

        if (usernameDomainPrefix != null) {
            ret.setProperty(USERNAME_DOMAIN_PREFIX, usernameDomainPrefix);
        }

        return ret;
    }
}
