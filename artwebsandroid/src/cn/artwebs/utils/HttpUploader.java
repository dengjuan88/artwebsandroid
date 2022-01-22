package cn.artwebs.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;


public class HttpUploader {
	private final static String tag="HttpUploader";
//	public static String upload(String url,HashMap<String, ContentBody> para) throws Exception
//	{
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpPost httpost = new HttpPost(url);
//		MultipartEntity entity = new MultipartEntity();
//		Iterator<Entry<String, ContentBody>> iter = para.entrySet().iterator(); 
//		while (iter.hasNext()) { 
//		    Map.Entry<String, ContentBody> entry = (Map.Entry<String, ContentBody>) iter.next(); 
//		    String key = entry.getKey(); 
//		    ContentBody val = entry.getValue(); 
//		    entity.addPart(key, val);
//		} 
//		httpost.setEntity(entity);
//		HttpResponse response = null;
//		response = httpClient.execute(httpost);
//		Log.d(tag, "response="+response);
//		return response.toString();
//		
//	}
}
