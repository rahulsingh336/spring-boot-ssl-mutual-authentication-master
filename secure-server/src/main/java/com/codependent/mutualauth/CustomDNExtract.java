package com.codependent.mutualauth;

import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;

/**
 * Created by e076103 on 21-08-2018.
 */
public class CustomDNExtract implements X509PrincipalExtractor {

  @Override
  public Object extractPrincipal(X509Certificate clientCert) {
    String subjectDN = clientCert.getSubjectDN().getName();
    System.out.println("subjectDN");
    System.out.println(subjectDN);
      return "whatever";

  }
}
