package com.codependent.mutualauth;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by e076103 on 21-08-2018.
 */

@Data
@Component
@ToString
@Slf4j
public class SSLConfiguration {

  @Value("${ssl.key.password:}")
  String sslKeyPassword;

  @Value("${ssl.keystore.location:}")
  String sslKeystoreLocation;

  @Value("${ssl.keystore.password:}")
  String sslKeystorePassword;

  @Value("${ssl.truststore.location:}")
  String sslTruststoreLocation;

  @Value("${ssl.truststore.password:}")
  String sslTruststorePassword;

  @Value("${spring.profiles.active:UNKNOWN}")
  String activeSpringProfile;

  @Value("${ssl.keystore.alias:}")
  String keyStoreAlias;

  @Autowired
  private Environment env;

  @PostConstruct
  public void init() {
    log.info("current active spring profile: [{}]", activeSpringProfile);
    if (Arrays.asList("dev", "stage", "mtf", "prod").contains(activeSpringProfile)) {
      log.info("overriding ssl key/trust-store configs");
      this.sslKeyPassword = System.getProperty("javax.net.ssl.keyStorePassword");
      this.sslKeystoreLocation = System.getProperty("javax.net.ssl.keyStore");
      this.sslKeystorePassword = System.getProperty("javax.net.ssl.keyStorePassword");
      this.sslTruststoreLocation = System.getProperty("javax.net.ssl.trustStore");
      this.sslTruststorePassword = System.getProperty("javax.net.ssl.trustStorePassword");
      this.keyStoreAlias = env.getProperty("synapse.ssl.keystore.client.alias");
    }
  }

}
