package cn.artwebs.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.util.Log;

/**
 * 文件http上传类
 * @author artwebs
 * 
 * 
 *
 */
public class FileHttpUpload {
	private static String tag="FileHttpUpload";
	private String urlStr="";
	public FileHttpUpload(String urlStr)
	{
		this.urlStr=urlStr;
	}
	
	public String fileNameFormat(int index)
	{
		return "file"+index;
	}
	
	/**
	 * 发起文件上传方法
	 * @param list 文件上传路径
	 * @return
	 */
	
	public String upload(List<String> list){   
		String rs="";
        try {   
            String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线   
            URL url = new URL(urlStr);   
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();   
            // 发送POST请求必须设置如下两行   
            conn.setDoOutput(true);   
            conn.setDoInput(true);   
            conn.setUseCaches(false);   
            conn.setRequestMethod("POST");   
            conn.setRequestProperty("connection", "Keep-Alive");   
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");   
            conn.setRequestProperty("Charsert", "UTF-8");    
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);   
               
            OutputStream out = new DataOutputStream(conn.getOutputStream());   
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线   
            int leng = list.size();   
            for(int i=0;i<leng;i++){   
                String fname = list.get(i);   
                File file = new File(fname);   
                Log.e(tag, "上传的文件大小：" + file.length());
                StringBuilder sb = new StringBuilder();     
                sb.append("--");     
                sb.append(BOUNDARY);     
                sb.append("\r\n");     
                sb.append("Content-Disposition: form-data;name=\""+fileNameFormat(i)+""+"\";filename=\""+ file.getName() + "\"\r\n");
                // sb.append("Content-Disposition: form-data;name=\"file"+i+"\";filename=\""+ file.getName() + "\"\r\n");
                sb.append("Content-Type:application/octet-stream\r\n\r\n");     
                   
                byte[] data = sb.toString().getBytes();   
                out.write(data);   
                DataInputStream in = new DataInputStream(new FileInputStream(file));   
                int bytes = 0;   
                byte[] bufferOut = new byte[1024];   
                while ((bytes = in.read(bufferOut)) != -1) {   
                    out.write(bufferOut, 0, bytes);   
                }   
                out.write("\r\n".getBytes()); //多个文件时，二个文件之间加入这个   
                in.close();   
            }   
            out.write(end_data);   
            out.flush();     
            out.close();    
               
            // 定义BufferedReader输入流来读取URL的响应   
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));   
            String line = "";   
            while ((line = reader.readLine()) != null) {   
                rs+=line;
            }   
  
        } catch (Exception e) {   
            System.out.println("发送POST请求出现异常！" + e);   
            e.printStackTrace();   
        }  
        System.out.println("发送POST请求出现异常！" + rs);   
        return rs;
    } 
}
