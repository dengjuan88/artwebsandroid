package cn.artwebs.security;

import cn.artwebs.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by artwebs on 14-7-21.
 */
public class ArtSecurity3DES extends ArtAbsSecurity {

    private static final String MCRYPT_TRIPLEDES = "DESede";
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";


    public static String decode(String content,String key,String iv)
    {
        return  decode(content,getKeyByte(key),getIVBtye(iv));
    }

    public static String decode(String content,byte[] key,byte[] iv)
    {
        byte[] data=Base64.decode(content.getBytes());
        try {
            data=decrypt(data,key,iv);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
        }
        return new String(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, sec, IvParameters);
        return cipher.doFinal(data);
    }


    public static String encode(String content,String key,String iv)
    {
        return encode(content,getKeyByte(key),getIVBtye(iv));
    }

    public static String encode(String content,byte[] key,byte[] iv)
    {
        byte[] data=null;
        try {
            data=encrypt(content.getBytes(),key,iv);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return new String(Base64.encode(data));
    }

    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, sec, IvParameters);
        return cipher.doFinal(data);
    }




    public static void main(String args[]) throws Exception {
        String plainText = "a12*&1c中文";
        final byte[] secretBytes = ArtSecurity3DES.generateSecretKey(MCRYPT_TRIPLEDES);
//        System.out.println(secretBytes.length);
        final byte[] ivbytes = ArtSecurity3DES.randomIVBytes();
//        System.out.println("plain text: " + plainText);
//        byte[] encrypt = ArtSecurity3DES.encrypt(plainText.getBytes(), secretBytes, ivbytes);
//        System.out.println("cipher text: " + encrypt);
//        System.out.println("decrypt text: " + new String(ArtSecurity3DES.decrypt(encrypt, secretBytes, ivbytes), "UTF-8"));

        String key="www.zcline.net";
        String iv="artwebs";
        String rs;
//        String rs= ArtSecurity3DES.encode(plainText, secretBytes, ivbytes);
//        System.out.println(rs);
//        System.out.println(ArtSecurity3DES.decode(rs, secretBytes, ivbytes));

        rs= ArtSecurity3DES.encode(plainText, key, iv);
        System.out.println(rs);
        System.out.println(ArtSecurity3DES.decode(rs, key, iv));
//        System.out.println(ArtSecurity3DES.decode("bXgKYTR47dosKznX/32ARzoeuuBsdfIn", key, iv));

    }

}
