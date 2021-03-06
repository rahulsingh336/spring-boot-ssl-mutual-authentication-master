package com.codependent.mutualauth.web;

/**
 * Created by e076103 on 20-08-2018.
 */
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class RestClientCertTestConfiguration {

  private String allPassword = "secret";

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {

    SSLContext sslContext = SSLContextBuilder
        .create()
        .loadKeyMaterial(ResourceUtils.getFile("classpath:client-keystore.jks"), allPassword.toCharArray(), allPassword.toCharArray())
        .loadTrustMaterial(ResourceUtils.getFile("classpath:client-truststore.jks"), allPassword.toCharArray())
        .build();

    HttpClient client = HttpClients.custom()
        .setSSLContext(sslContext)
        .build();

    return builder
        .requestFactory(new HttpComponentsClientHttpRequestFactory(client))
        .build();
  }
}