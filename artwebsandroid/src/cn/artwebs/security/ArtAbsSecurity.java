package cn.artwebs.security;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by artwebs on 14-7-22.
 */
public class ArtAbsSecurity {

    public static byte[] generateSecretKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(algorithm);
        return keygen.generateKey().getEncoded();
    }

    public static byte[] randomIVBytes(){
        return randomIVBytes(8);
    }

    public static byte[] randomIVBytes(int len) {
        Random ran = new Random();
        byte[] bytes = new byte[len];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = (byte) ran.nextInt(Byte.MAX_VALUE + 1);
        }
        return bytes;
    }

    public static  byte[] getKeyByte(String key)
    {
        return getKeyByte(key,24);
    }

    public static byte[] getKeyByte(String key,int len)
    {
        byte[] rs=new byte[len];
        byte[] keyByte=key.getBytes();
        for (int i = 0,j=0; i < len; i++,j++) {
            if (j >=keyByte.length)
                    j=0;
            rs[i]=keyByte[j];
        }
        System.out.println(new String(rs));
        return rs;
    }

    public static byte[] getIVBtye(String iv)
    {
        byte[] rs=new byte[8];
        byte[] ivByte=iv.getBytes();
        for (int i = 0,j=0; i < 8; i++,j++) {
            if (j>=ivByte.length)
                j=0;
            rs[i]=ivByte[j];

        }
        System.out.println(new String(rs));
        return rs;
    }

}
