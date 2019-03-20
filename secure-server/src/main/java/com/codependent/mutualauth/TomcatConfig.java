package com.codependent.mutualauth;

import java.io.File;
import javax.annotation.Resource;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by e076103 on 21-08-2018.
 */
@Configuration
public class TomcatConfig {

  /*@Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() {
    return new EmbeddedServletContainerCustomizer() {
      @Override
      public void customize(ConfigurableEmbeddedServletContainer container) {
        if (container instanceof TomcatEmbeddedServletContainerFactory) {
          TomcatEmbeddedServletContainerFactory containerFactory =
              (TomcatEmbeddedServletContainerFactory) container;

          Connector connector = new Connector(TomcatEmbeddedServletContainerFactory.DEFAULT_PROTOCOL);
          connector.setPort(8449);
          connector.setScheme("https");
          containerFactory.addAdditionalTomcatConnectors(connector);
        }
      }
    };*/
  @Bean
  EmbeddedServletContainerCustomizer containerCustomizer(
      @Value("${ssl.keystore.location}") String keystoreFile,
      @Value("${ssl.truststore.location}") String trustFile,
      @Value("${ssl.keystore.password}") String keystorePass) throws Exception {

    File f = new File(keystoreFile);
    String absoluteKeystoreFile = f.getAbsolutePath();
    File ftrustFile = new File(trustFile);
    String atrustFile = ftrustFile.getAbsolutePath();

    return (ConfigurableEmbeddedServletContainer container) -> {

      if (container instanceof TomcatEmbeddedServletContainerFactory) {

        TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
        tomcat.addConnectorCustomizers(
            (Connector connector) -> {
              connector.setPort(8449);
              connector.setSecure(true);
              connector.setScheme("https");
              Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
              proto.setSSLEnabled(true);
              proto.setKeystoreFile(absoluteKeystoreFile);
              proto.setKeystorePass(keystorePass);
              proto.setTruststoreType("JKS");
              proto.setTruststoreFile(atrustFile);
              proto.setTruststorePass(keystorePass);
              proto.setKeystoreType("JKS");
              proto.setClientAuth("true");
              proto.setKeyAlias("secure-server");
            }
        );
      }
    };
  }



}
