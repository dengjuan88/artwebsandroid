package cn.artwebs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.artwebs.comm.AppApplication;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class FileUtils {
	private static final String tag="FileUtils";
	private String SDPATH="";

	public String getSDPATH() {
		return SDPATH;
	}
	
	public FileUtils() {
		//得到当前外部存储设备的目录
		// /SDCARD
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			SDPATH =Environment.getExternalStorageDirectory() + "/";
		else
			SDPATH=AppApplication.getAppContext().getApplicationContext().getFilesDir().getAbsolutePath()+"/";
		
			
	}
	
	public FileUtils(String path)
	{
		this();
		this.creatSDDir(path);
		SDPATH=SDPATH+path+"/";
		Log.d(tag,"FileUtils="+SDPATH);
	}
	
	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public File creatSDFile(String fileName) throws IOException {
		Log.d(tag,"creatSDFile="+SDPATH+fileName);
		File file = new File(SDPATH , fileName);
		file.createNewFile();
		return file;
	}
	
	public void deleteSDFile(String fileName)
	{
		Log.d(tag,"deleteSDFile="+SDPATH+fileName);
		File file = new File(SDPATH,fileName);
		file.delete();
	}
	
	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 */
	public File creatSDDir(String dirName) {
		File dir = new File(SDPATH,dirName);
		if(!dir.exists())dir.mkdirs();
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public boolean isFileExist(String fileName){
		Log.d(tag,"isFileExist:"+SDPATH+ fileName);
		File file = new File(SDPATH+ fileName);
		return file.exists();
	}
	
	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public File getFile(String fileName){
		Log.d(tag,"getFile="+SDPATH+fileName);
		File file = new File(SDPATH+fileName);
		return file;
	}
	
	public long getFileSize(String fileName){//取得文件大小
        long s=0;
        File file = new File(SDPATH+fileName);
        try {
	        if (file.exists()) {
	           FileInputStream fis = null;
			   fis = new FileInputStream(file);
	           s= fis.available();
	        } else {
	        	file.createNewFile();
	            System.out.println("文件不存在");
	        }
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{}
        return s;
    }
	
	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String fileName,InputStream input){
		File file = null;
		OutputStream output = null;
		try{	
			file = creatSDFile(fileName);
			output = new FileOutputStream(file);
			byte buffer [] = new byte[1024];			
			int count=0;
			while((count=input.read(buffer))!=-1)
			{
				output.write(buffer, 0, count);
			}
			output.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				output.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return file;
	}
	
	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String fileName,InputStream input,Handler handler){
		File file = null;
		OutputStream output = null;
		try{	
			file = creatSDFile(fileName);
			output = new FileOutputStream(file);
			byte buffer [] = new byte[1024];			
			int count=0;
			while((count=input.read(buffer))!=-1)
			{
				
				output.write(buffer, 0, count);
				handler.sendMessage(handler.obtainMessage(1, count));
			}
			handler.sendEmptyMessage(2);
			output.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				output.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public void installApk(ContextWrapper activity,String filename)
	{
		File file=new File(filename);
		Intent intent=new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		String type="application/vnd.android.package-archive";
		intent.setDataAndType(Uri.fromFile(file), type);
		activity.startActivity(intent);
//		file.delete();
	}

}