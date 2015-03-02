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

package nl.eveoh.mytimetable.apiclient.service;

import com.google.common.base.Splitter;
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

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Default {@link MyTimetableHttpClientBuilder} implementation.
 *
 * @author Erik van Paassen
 */
public class MyTimetableHttpClientBuilderImpl implements MyTimetableHttpClientBuilder {

    public CloseableHttpClient build(Configuration configuration) {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", createSslSocketFactory(configuration))
                .build();

        // Create the Connection manager.
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(configuration.getApiMaxConnections());
        connectionManager.setDefaultMaxPerRoute(configuration.getApiMaxConnections());

        // Create the HttpClient.
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }

    private SSLConnectionSocketFactory createSslSocketFactory(Configuration configuration) {
        X509HostnameVerifier verifier;
        if (configuration.isApiSslCnCheck()) {
            verifier = SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
        } else {
            verifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        }

        // Remove SSLv2Hello and SSLv3 from the allowed ciphers list
        // Java 7u75 disabled them anyway, so usually the MyTimetable server will not support these protocols
        SSLContext sslContext = SSLContexts.createSystemDefault();
        Set<String> enabledProtocols = new HashSet<String>();
        for (String s : sslContext.getDefaultSSLParameters().getProtocols()) {
            if (s.equals("SSLv3") || s.equals("SSLv2Hello")) {
                continue;
            }
            enabledProtocols.add(s);
        }

        return new SSLConnectionSocketFactory(sslContext,
                enabledProtocols.toArray(new String[enabledProtocols.size()]), null, verifier);
    }
}