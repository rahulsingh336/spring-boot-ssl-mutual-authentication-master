package com.codependent.mutualauth;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
/**
 * Created by e076103 on 21-08-2018.
 */
public class CertificateUtil {

  public static void main(String[] args) {
    CertificateDetails certDetails = getCertificateDetails();
    System.out.println(certDetails.getPrivateKey());
    System.out.println(certDetails.getX509Certificate());
    addCertificate();
  }

  public static CertificateDetails getCertificateDetails() {

    CertificateDetails certDetails = null;

    try {

      boolean isAliasWithCertificate = false;
      KeyStore keyStore = KeyStore.getInstance("JKS");

      // Provide location of Java Keystore and password for access
      keyStore.load(new FileInputStream("C:\\Project\\mutual_ssl\\server-truststore.jks"), "secret".toCharArray());
      //keyStore.load(new FileInputStream("C:\\Project\\mutual_ssl\\server-keystore.jks"), "secret".toCharArray());

      // iterate over all aliases
      Enumeration<String> es = keyStore.aliases();
      String alias = "";
      while (es.hasMoreElements()) {
        alias = (String) es.nextElement();
        // if alias refers to a private key break at that point
        // as we want to use that certificate
        if (isAliasWithCertificate = keyStore.isCertificateEntry(alias)) {
          break;
        }
      }

      if (isAliasWithCertificate) {

        Certificate certificate = keyStore.getCertificate(alias);
        certDetails = new CertificateDetails();

        //certDetails.setPrivateKey("nothing");
        certDetails.setX509Certificate((X509Certificate) certificate);


        /*KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias,
            new KeyStore.PasswordProtection(jksPassword.toCharArray()));

        PrivateKey myPrivateKey = pkEntry.getPrivateKey();

        // Load certificate chain
        Certificate[] chain = keyStore.getCertificateChain(alias);

        certDetails = new CertificateDetails();
        certDetails.setPrivateKey(myPrivateKey);
        certDetails.setX509Certificate((X509Certificate) chain[0]);*/

      }

    } catch (KeyStoreException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (CertificateException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return certDetails;
  }

  public static void addCertificate() {

    CertificateDetails certDetails = null;

    try {

      KeyStore keyStore = KeyStore.getInstance("JKS");
      CertificateFactory cf = CertificateFactory.getInstance("X.509");
      String certfile = "C:\\Project\\mutual_ssl\\add\\client-public.cer";
      InputStream certstream = fullStream (certfile);
      Certificate certs =  cf.generateCertificate(certstream);

      // Provide location of Java Keystore and password for access
      keyStore.load(new FileInputStream("C:\\Project\\mutual_ssl\\server-truststore.jks"),
          "secret".toCharArray());
      keyStore.setCertificateEntry("test", certs);
      System.out.println("keyStore.size()");
      System.out.println(keyStore.size());
      System.out.println("*************CHECK ENtries*****************");
      certDetails = getCertificateDetails();
      System.out.println(certDetails.getPrivateKey());
      System.out.println(certDetails.getX509Certificate());

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (CertificateException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (KeyStoreException e) {
      e.printStackTrace();
    }
  }

  private static InputStream fullStream ( String fname ) throws IOException {
    FileInputStream fis = new FileInputStream(fname);
    DataInputStream dis = new DataInputStream(fis);
    byte[] bytes = new byte[dis.available()];
    dis.readFully(bytes);
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    return bais;
  }

}
