package com.example.passwordprotector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class SecureMasterPassword {

    protected static byte[] getSalt()
    {
        byte[] salt = new byte[16];

        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            sr.nextBytes(salt);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        catch(NoSuchProviderException e){
            e.printStackTrace();

        }
        return salt;

    }

    protected static String getSecuredpassword(String unSecuredPassword, byte[] salt){
        String securedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(unSecuredPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            securedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return securedPassword;
    }

}
