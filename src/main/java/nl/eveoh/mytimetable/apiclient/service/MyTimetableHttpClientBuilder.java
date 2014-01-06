package nl.eveoh.mytimetable.apiclient.service;

import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author Erik van Paassen
 */
public interface MyTimetableHttpClientBuilder {

    /**
     * Creates a thread-safe HttpClient instance.
     *
     * @param configuration {@link Configuration} object containing the configuration data to configure the HttpClient with.
     * @return Thread-safe HttpClient.
     */
    public CloseableHttpClient build(Configuration configuration);
}
