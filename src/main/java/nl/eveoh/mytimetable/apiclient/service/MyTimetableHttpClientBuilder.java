package nl.eveoh.mytimetable.apiclient.service;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author Erik van Paassen
 */
public interface MyTimetableHttpClientBuilder {

    /**
     * Creates a thread-safe HttpClient instance.
     *
     * @return              Thread-safe HttpClient.
     */
    public CloseableHttpClient build();
}
