package com.codependent.mutualauth;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 * Created by e076103 on 21-08-2018.
 */
public class CustomTrustManager implements X509TrustManager {

  @Override
  public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
      throws CertificateException {
    System.out.println("coming here");
    System.out.println("checkClientTrusted");
  }

  @Override
  public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
      throws CertificateException {
    System.out.println("coming here");
    System.out.println("checkServerTrusted");
  }

  @Override
  public X509Certificate[] getAcceptedIssuers() {
    System.out.println("coming here");
    System.out.println("getAcceptedIssuers");
    return new X509Certificate[0];
  }
}
