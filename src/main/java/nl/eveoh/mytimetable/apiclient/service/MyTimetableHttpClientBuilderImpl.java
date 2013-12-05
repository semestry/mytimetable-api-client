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

package nl.eveoh.mytimetable.apiclient.service;

import nl.eveoh.mytimetable.apiclient.configuration.Configuration;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Default {@link MyTimetableHttpClientBuilder} implementation.
 *
 * @author Erik van Paassen
 */
public class MyTimetableHttpClientBuilderImpl implements MyTimetableHttpClientBuilder {

    private final Configuration configuration;



    public MyTimetableHttpClientBuilderImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    public CloseableHttpClient build() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", createSslSocketFactory())
                .build();

        // Create the Connection manager.
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(configuration.getApiMaxConnections());
        connectionManager.setDefaultMaxPerRoute(configuration.getApiMaxConnections());

        // Create the HttpClient.
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    private SSLConnectionSocketFactory createSslSocketFactory() {
        X509HostnameVerifier verifier;
        if (configuration.isApiSslCnCheck()) {
            verifier = SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        } else {
            verifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        }

        return new SSLConnectionSocketFactory(SSLContexts.createSystemDefault(), verifier);
    }
}