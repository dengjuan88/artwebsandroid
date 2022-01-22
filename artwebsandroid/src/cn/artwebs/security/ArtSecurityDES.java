package cn.artwebs.security;

import cn.artwebs.utils.Base64;
import cn.artwebs.utils.Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by artwebs on 14-7-22.
 */
public class ArtSecurityDES extends ArtAbsSecurity {
    public final static String remoteEncoding = "GBK";
    public final static String LocalEncoding = "UTF-8";

    private static Cipher encodeCipher=null;
    private static Cipher decodeCipher=null;
    private static String mode="DESede";


    public static String encode(String content,String key) throws Exception{
        return encode(content,getKeyByte(key));
    }

    public static String encode(String content,byte[] key) throws Exception{
        String result="";
        if (!Utils.isValidString(content))
            return result;
        try
        {
            if (encodeCipher==null)
            {
                SecretKey secretKey = new SecretKeySpec(key,mode);
                encodeCipher= Cipher.getInstance("DESede");
                encodeCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            }
            byte[] encodeByte=encodeCipher.doFinal(content.getBytes(remoteEncoding));
            result= new String(Base64.encode(encodeByte));
//            result= Base64.encodeToString(encodeByte, Base64.DEFAULT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("编码失败!");
        }
        return result;
    }

    public static String decode(String content,String key) throws Exception
    {
        return decode(content,getKeyByte(key));
    }

    //解码
    public static String decode(String content,byte[] key) throws Exception
    {
        String result="";
        if (!Utils.isValidString(content))
            return result;
        try
        {
            if (decodeCipher==null)
            {
                SecretKey secretKey = new SecretKeySpec(key,mode);
                decodeCipher= Cipher.getInstance("DESede");
                decodeCipher.init(Cipher.DECRYPT_MODE, secretKey);
            }
            byte[] encodeByte=Base64.decode(content.getBytes());
            byte[] decodeByte=decodeCipher.doFinal(encodeByte);
            result = new String(decodeByte,LocalEncoding);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("解码失败!");
        }
        return result;
    }

    public static void main(String[] args) {
        String key="www.zcline.net";
        try {
            String rs=ArtSecurityDES.encode("1103010900000013",key);
            System.out.println(rs);
            System.out.println(ArtSecurityDES.decode(rs,key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

}
