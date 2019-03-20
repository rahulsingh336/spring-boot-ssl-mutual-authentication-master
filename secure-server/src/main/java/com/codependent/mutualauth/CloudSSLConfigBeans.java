package com.codependent.mutualauth;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by e076103 on 21-08-2018.
 */
//@Configuration
@Slf4j
public class CloudSSLConfigBeans extends AbstractSSLConfiguration{

  @Bean
  public SSLConfiguration sslConfiguration() {
    return new SSLConfiguration();
  }

  @Bean
  public SSLConnectionSocketFactory sslConnectionSocketFactory(SSLConfiguration sslConfiguration)  {
    return buildSslConnectionSocketFactory(sslConfiguration);
  }

}
