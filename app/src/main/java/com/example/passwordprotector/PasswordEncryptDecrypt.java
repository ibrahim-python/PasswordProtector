package com.example.passwordprotector;


import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptDecrypt  {



    protected static byte[] generateKey(String myKey) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = myKey.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        return key;
    }

    protected static byte[] encrypt(byte[] raw, byte[] clear) throws Exception{
        byte[] encrypted = null;

        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encrypted = cipher.doFinal(clear);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypted;
    }

    protected static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception{
        byte[] decrypted=null;

        try {
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            decrypted = cipher.doFinal(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }




}
