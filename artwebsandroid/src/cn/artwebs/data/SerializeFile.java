package cn.artwebs.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

public class SerializeFile {
	private final static String tag="SerializeFile";
	private final static String rootPath="/";
	public static boolean saveObject(String path,String filename,Object obj)
	{
		boolean flag=false;
		FileOutputStream fs;
		try {
			File dir = new File(rootPath + path);
			dir.mkdirs();
			fs = new FileOutputStream(rootPath+path+filename);
			ObjectOutputStream os =  new ObjectOutputStream(fs);     
	        os.writeObject(obj);     
	        os.close(); 
	        flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
        return flag;  
	}
	
	public static boolean saveObject(Context context,String filename,Object obj)
	{
		String path=context.getApplicationContext().getFilesDir().getAbsolutePath();
		boolean flag=saveObject(path,filename,obj);
		return flag;
	}
	
	public static Object readObject(String path,String filename)
	{
		Object obj=null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			
			fis = new FileInputStream(rootPath+path+filename);
			Log.d(tag,"SerializeFile");
			ois = new ObjectInputStream(fis);
			
			obj = ois.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return obj;		
	}
	
	public static Object readObject(Context context,String filename)
	{
		String path=context.getApplicationContext().getFilesDir().getAbsolutePath();
		return readObject(path,filename);
	}
	
	public static boolean isFileExist(String path,String filename)
	{
		File file = new File(rootPath + path+filename);
		return file.exists();
	}
	
	public static boolean isFileExist(Context context,String filename)
	{
		String path=context.getApplicationContext().getFilesDir().getAbsolutePath();
		return isFileExist(path, filename);
	}
	
	public static void deleteFile(String path,String filename)
	{
		File file = new File(rootPath + path+filename);
		if(file.exists())file.delete();;
	}
	
	public static void deleteFile(Context context,String filename)
	{
		String path=context.getApplicationContext().getFilesDir().getAbsolutePath();
		deleteFile(path, filename);
	}
}
