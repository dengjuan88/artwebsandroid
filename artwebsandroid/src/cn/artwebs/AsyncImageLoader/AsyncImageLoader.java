package cn.artwebs.AsyncImageLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.artwebs.transmit.ITransmit;
import cn.artwebs.utils.Base64;
import cn.artwebs.utils.FileUtils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

//该类的主要作用是实现图片的异步加载
public class AsyncImageLoader implements IAsyncImageLoader {
	private static final String tag="AsyncImageLoader";
	//图片缓存对象
	//键是图片的URL，值是一个SoftReference对象，该对象指向一个Drawable对象
	private FileUtils fileutil=new FileUtils();
	private String path="";

		
	private ITransmit trans;
	
	public void setRootPath(String path)
	{
		fileutil=new FileUtils(path);
	}

	//实现图片的异步加载
	public Drawable loadDrawable(final String imageUrl,final ImageCallback callback,ITransmit trans){
		this.trans=trans;
		String filename=Base64.encode(imageUrl)+".jpg";
		Log.d(tag,filename);
		if(fileutil.isFileExist(filename))		
		{
			try {
				callback.imageLoaded(Drawable.createFromStream(new FileInputStream(fileutil.getFile(filename)), filename));
			  return null;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		final Handler handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.obj!=null)
					callback.imageLoaded((Drawable) msg.obj);
			}
		};
		//新开辟一个线程，该线程用于进行图片的下载
		new Thread(){
			public void run() {
				Drawable drawable=loadImageFromUrl(imageUrl);
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			};
		}.start();
		return null;
	}
	//该方法用于根据图片的URL，从网络上下载图片
	protected Drawable loadImageFromUrl(String imageUrl) {
		String imgFileName=Base64.encode(imageUrl)+".jpg";
		try {
			InputStream inputStream;
			if(this.trans!=null)
				inputStream=this.trans.downStream(imageUrl);
			else{
				URL url = new URL(imageUrl);
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				inputStream = urlConn.getInputStream();
			}
			//根据图片的URL，下载图片，并生成一个Drawable对象
			BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = 4;
			Bitmap bm;
			File file = fileutil.creatSDFile(imgFileName);
			FileOutputStream output = new FileOutputStream(file);
			bm=BitmapFactory.decodeStream(inputStream,null,bitmapOptions);
			if(bm.compress(Bitmap.CompressFormat.JPEG, 50,output))
			{
				output.close();
				output.flush();
			}
			if (bm != null && !bm.isRecycled())
				bm.recycle();
			inputStream.close();
			inputStream=null;
			if(fileutil.isFileExist(imgFileName))
//			fileutil.write2SDFromInput(this.path, imgFileName, inputStream);
				return Drawable.createFromStream(new FileInputStream(fileutil.getFile(imgFileName)), this.path+"/"+imgFileName);
			else
				return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{}
	}
	



}
