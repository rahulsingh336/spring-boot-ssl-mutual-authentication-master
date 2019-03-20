package com.codependent.mutualauth;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by e076103 on 21-08-2018.
 */
@Slf4j
public class AbstractSSLConfiguration {

  private static final String PROTOCOL_HTTPS = "https";

  SSLConnectionSocketFactory buildSslConnectionSocketFactory(SSLConfiguration sslConfiguration) {
    log.info("building SSLConnectionSocketFactory");
    try {
      KeyStore keyStore = loadJksFile(sslConfiguration.getSslKeystoreLocation(), sslConfiguration.getSslKeystorePassword());
      KeyStore trustStore = loadJksFile(sslConfiguration.getSslTruststoreLocation(), sslConfiguration.getSslTruststorePassword());
      SSLConnectionSocketFactory sslConnectionSocketFactory = createSslConnectionSocketFactory(sslConfiguration, keyStore, trustStore);
      log.info("SSLConnectionSocketFactory built successfully");
      return sslConnectionSocketFactory;
    } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException |
        UnrecoverableKeyException | KeyManagementException e) {
      log.error("Unable to build SSLConnectionSocketFactory required to make SSL http rest calls", e);
    }
    return null;
  }

  private SSLConnectionSocketFactory createSslConnectionSocketFactory(SSLConfiguration sslConfiguration, KeyStore keyStore, KeyStore trustStore)
      throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException, IOException {
    log.info("alias configured is [{}]", sslConfiguration.getKeyStoreAlias());
    SSLContext sslcontext = SSLContexts.custom().useProtocol("TLS").build();
    sslcontext.init(null, new X509TrustManager[]{new CustomTrustManager()}, new SecureRandom());
    SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
    SSLContext build = sslContextBuilder
        .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
        .loadKeyMaterial(keyStore, sslConfiguration.getSslKeystorePassword().toCharArray(),
            (map, socket) -> sslConfiguration.getKeyStoreAlias()).build();
    build.init(null, new X509TrustManager[]{new CustomTrustManager()}, new SecureRandom());
    SSLServerSocketFactory sf = build.getServerSocketFactory();
    SSLServerSocket ss = (SSLServerSocket)sf.createServerSocket(8443);
    ss.setNeedClientAuth(true);
    return new SSLConnectionSocketFactory(
        build,
        NoopHostnameVerifier.INSTANCE);
  }

  private KeyStore loadJksFile(final String fileLocation, final String filePwd) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    log.info("loading keys from {} file", fileLocation);
    final File file = new File(readFilePath(fileLocation));
    System.out.println(file.exists());
    try (InputStream fileInputStream = new FileInputStream(file)) {
      keyStore.load(fileInputStream, filePwd.toCharArray());
    }
    return keyStore;
  }

  RestTemplate buildRestTemplateSsl(ClientHttpRequestFactory clientHttpRequestFactory) throws IOException {
    log.info("building a rest template with ClientHttpRequestFactory instance - {}", clientHttpRequestFactory);
    return new RestTemplate(clientHttpRequestFactory);

  }

  /*@Bean
  public ClientHttpRequestFactory clientHttpRequestFactory(SSLConnectionSocketFactory sslConnectionSocketFactory) throws IOException {
    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
        .<ConnectionSocketFactory>create().register(PROTOCOL_HTTPS, sslConnectionSocketFactory)
        .build();
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    connectionManager.setMaxTotal(120);
    connectionManager.setDefaultMaxPerRoute(120);

    RequestConfig config = RequestConfig.custom()
        .setConnectTimeout(10000)
        .setSocketTimeout(10000)
        .build();
    try (CloseableHttpClient httpClient = HttpClientBuilder.create()
        .setSSLSocketFactory(sslConnectionSocketFactory)
        .setConnectionManager(connectionManager)
        .setConnectionManagerShared(true)
        .setDefaultRequestConfig(config).build()) {
      return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
  }*/

  protected String readFilePath(final String filePath) {
    if (null == filePath) {
    log.error("unbale to load file");
    }
    return filePath;
  }

}
