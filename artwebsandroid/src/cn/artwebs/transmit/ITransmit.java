package cn.artwebs.transmit;

import java.io.InputStream;

import android.content.Context;
import android.os.Handler;

public interface ITransmit {
	public void setSkip(String skip);
	
	public String getSkip();
	
	public String getHost();
	
	public void setHost(String host);
	
	public void close();
	
	public String download(String commend);
	public String download(String commend,int size);
	public byte[] download(byte[] commend,int size);
	public String download(String commend,String end);
	public int downFile(String commend, String path, String fileName);
	public int downFile(String commend, String path, String fileName,Handler handler);
	
	public InputStream downStream(String commend);
	
	public static final class Staus
	{
		public static final int DOWN_SUCCEE=0;
		public static final int DOWN_FAIL=-1;
		public static final int DOWN_EXIST=1;
	}
	
}
