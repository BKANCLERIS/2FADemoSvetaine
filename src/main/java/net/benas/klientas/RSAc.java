/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.benas.klientas;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;

/**
 *
 * @author Benas
 */
public class RSAc {
   private PublicKey pub;
    private PrivateKey pvt;
    
    public RSAc() {
       try {
           KeyPairGenerator kpg;
           
           kpg = KeyPairGenerator.getInstance("RSA");
           kpg.initialize(1024, new SecureRandom());
           KeyPair kp =kpg.generateKeyPair();
           pub = kp.getPublic();
           pvt = kp.getPrivate();
       } catch (NoSuchAlgorithmException ex) {
           Logger.getLogger(RSAc.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public String getPub(){
     byte[] publicKeyByte = pub.getEncoded();   
     return encode(publicKeyByte);
    }
    
    public void setPub(String pubkeystring){
        try {
            byte[] decodedpubkey= decode(pubkeystring);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            pub = (PublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(decodedpubkey));
                    } catch (InvalidKeySpecException ex) { 
           Logger.getLogger(RSAc.class.getName()).log(Level.SEVERE, null, ex);
       } catch (NoSuchAlgorithmException ex) {
           Logger.getLogger(RSAc.class.getName()).log(Level.SEVERE, null, ex);
       } 
    }
    

    
    public String encryptAESkey(String message) throws Exception{
    byte[] messageToBytes = message.getBytes();
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.ENCRYPT_MODE,pub);
    byte[] encryptedBytes = cipher.doFinal(messageToBytes);
    return encode(encryptedBytes);
}
    
    public String decrypt(String encryptedMessage) throws Exception{
    byte[] encryptedBytes = decode(encryptedMessage);
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.DECRYPT_MODE, pvt);
    byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
    
    return new String(decryptedMessage,"UTF8");
}

    private String encode(byte[] data) {
         return Base64.getEncoder().encodeToString(data);
    }
    
    private byte[] decode(String data){
    return Base64.getDecoder().decode(data);
}
    
}