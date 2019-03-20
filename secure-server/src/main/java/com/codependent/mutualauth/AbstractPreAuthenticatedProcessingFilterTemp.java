package com.codependent.mutualauth;

import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * Created by e076103 on 21-08-2018.
 */
public class AbstractPreAuthenticatedProcessingFilterTemp extends
    AbstractPreAuthenticatedProcessingFilter {

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    System.out.println("SEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
    X509Certificate cert = this.extractClientCertificate(request);
    Object o = this.extractPrincipal(cert);
    System.out.println(o);
    return cert == null?null: o;
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    X509Certificate certificate = this.extractClientCertificate(request);
    System.out.println(certificate);
    String tempCert = "[\n"
        + "[\n"
        + "  Version: V3\n"
        + "  Subject: CN=codependent-client, OU=myorg, O=myorg, L=mycity, ST=mystate, C=es\n"
        + "  Signature Algorithm: SHA256withRSA, OID = 1.2.840.113549.1.1.11\n"
        + "\n"
        + "  Key:  Sun RSA public key, 2048 bits\n"
        + "  modulus: 20051655370683445321724799723906492162961120464400182310587559734459180876463318561863389902430206324216550209369198058950376737985971428527574970384936026268322289873354907285301121439438837788320950657371000447381000845593435530441773813200520091538512890288379654348769597042177352159680533385374004740897854951547737911452083233409809990116102397595806246071948783651101694712230224093668156064982412709575597400259171457840944907544935566838038897262832302915237196873450659370239928530705409862676618442590285564410874043641201289177307486366244525506405029654643226921370103005007635034450488697064388721501749\n"
        + "  public exponent: 65537\n"
        + "  Validity: [From: Tue Aug 21 09:52:51 IST 2018,\n"
        + "               To: Mon Nov 19 09:52:51 IST 2018]\n"
        + "  Issuer: CN=codependent-client, OU=myorg, O=myorg, L=mycity, ST=mystate, C=es\n"
        + "  SerialNumber: [    0fc11c8a]\n"
        + "\n"
        + "Certificate Extensions: 1\n"
        + "[1]: ObjectId: 2.5.29.14 Criticality=false\n"
        + "SubjectKeyIdentifier [\n"
        + "KeyIdentifier [\n"
        + "0000: 14 8F 14 C5 65 AC C7 E1   4B 6C 90 39 50 A5 53 B2  ....e...Kl.9P.S.\n"
        + "0010: A0 DC B8 04                                        ....\n"
        + "]\n"
        + "]\n"
        + "\n"
        + "]\n"
        + "  Algorithm: [SHA256withRSA]\n"
        + "  Signature:\n"
        + "0000: 9C 93 C1 15 1A AF 5B A2   43 6B 6A 63 B9 00 29 CD  ......[.Ckjc..).\n"
        + "0010: 18 51 7F 16 AC A5 9C A5   EA A4 57 03 20 48 57 6A  .Q........W. HWj\n"
        + "0020: 03 0D 38 A1 64 D6 D2 C6   E5 45 EF F5 24 23 25 4A  ..8.d....E..$#%J\n"
        + "0030: 4D F0 10 72 95 12 D7 BA   D0 D9 4B 7C 36 C3 FC 04  M..r......K.6...\n"
        + "0040: 31 F6 C9 BF 3B 6A 44 F0   AA 8D BE 44 41 BE 03 BE  1...;jD....DA...\n"
        + "0050: 89 08 6C CE BE 10 40 1D   AE F5 D4 D3 9D AD 4A 71  ..l...@.......Jq\n"
        + "0060: 4D 18 B7 C4 4B F0 78 22   07 AD AD A2 83 69 A0 30  M...K.x\".....i.0\n"
        + "0070: 18 B8 9D 45 1E 85 71 F2   21 6C 61 8A E0 2E 05 49  ...E..q.!la....I\n"
        + "0080: 96 90 7D F1 4C B0 C8 FC   03 BE 4E C6 A7 50 D1 CE  ....L.....N..P..\n"
        + "0090: BA 78 EE 2E 6A B4 67 75   16 FC 0B 83 EB 44 19 57  .x..j.gu.....D.W\n"
        + "00A0: 0F D1 F6 6E C3 93 F7 AC   21 2F 4D B8 76 BB 90 85  ...n....!/M.v...\n"
        + "00B0: BA 0D 1C B7 45 FE F3 05   7A F8 82 36 EC CF EC 03  ....E...z..6....\n"
        + "00C0: C5 D3 EA E9 F4 C2 85 A2   99 C4 52 3A 1A C6 AC C1  ..........R:....\n"
        + "00D0: FF 01 46 C2 60 E5 D4 F4   A5 1D EC 2D 7E 3A 49 85  ..F.`......-.:I.\n"
        + "00E0: F8 85 CB F0 5D 23 F8 8B   22 B9 78 35 CC 06 4B 56  ....]#..\".x5..KV\n"
        + "00F0: 3F 05 2E C1 F4 6E 6B FA   7B D5 4E CF D5 12 E5 46  ?....nk...N....F\n"
        + "\n"
        + "]";
    return null;
    //return certificate;
  }

  private X509Certificate extractClientCertificate(HttpServletRequest request) {
    X509Certificate[] certs = (X509Certificate[])((X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate"));
    if(certs != null && certs.length > 0) {
      if(this.logger.isDebugEnabled()) {
        this.logger.debug("X.509 client authentication certificate:" + certs[0]);
      }

      return certs[0];
    } else {
      if(this.logger.isDebugEnabled()) {
        this.logger.debug("No client certificate found in request.");
      }

      return null;
    }
  }

  public Object extractPrincipal(X509Certificate clientCert) {
    String subjectDN = clientCert.getSubjectDN().getName();
    System.out.println("Subject DN is '" + subjectDN + "'");

      return "kuch bhe";

  }

}
