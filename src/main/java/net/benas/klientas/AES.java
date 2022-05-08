/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.benas.klientas;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Benas
 */
public class AES {
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static final int AES_KEY_BIT = 256;
    private  SecretKey secret;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    // AES-GCM needs GCMParameterSpec
    
    public AES(){
        secret=null;
    }
    
    
    public static byte[] encrypt(byte[] pText,SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] encryptedText = cipher.doFinal(pText);
        return encryptedText;

    }

    // prefix IV length + IV bytes to cipher text
    public String encryptWithPrefixIV(String pText) throws Exception {
        byte[] iv = getRandomNonce(IV_LENGTH_BYTE);
        byte[] cipherText = encrypt(pText.getBytes(), secret, iv);

        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
                .put(iv)
                .put(cipherText)
                .array();
        return Base64.getEncoder().encodeToString(cipherTextWithIv);

    }

    public static String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws Exception {

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] plainText = cipher.doFinal(cText);
        return new String(plainText, UTF_8);

    }

    public String decryptWithPrefixIV(String cText) throws Exception {

        ByteBuffer bb = ByteBuffer.wrap(Base64.getDecoder().decode(cText));

        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        String plainText = decrypt(cipherText, secret, iv);
        return plainText;

    }
   
   
   public String SecretKeyToString(){
       String EncodedSkey = Base64.getEncoder().encodeToString(secret.getEncoded());
       return EncodedSkey;
   }
   
   public SecretKey StringToSecretkey(String StringEncKey){
       byte[] decodedKey = Base64.getDecoder().decode(StringEncKey);
       SecretKey originalkey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
       return originalkey;
   }
   public SecretKey getSecretKey(){
       return secret;
   }
   
   public void setSecretKey(String secretkeyString){
        byte[] decodedKey = Base64.getDecoder().decode(secretkeyString);
       secret = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
   }
   
   public byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }
   
}
