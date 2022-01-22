package cn.artwebs.transmit;

import java.io.IOException;
import java.io.InputStream;



import android.os.Handler;
import android.util.Log;
import cn.artwebs.transmit.ITransmit;
import cn.artwebs.utils.HttpDownloader;



public class ITransmitImplHttp implements ITransmit {
	private String host;
	private String skip;
	private String addCmd="";
	private HttpDownloader client=new HttpDownloader();
	private final static String tag="ITransmitImplHttp";	
	public ITransmitImplHttp(String host,String skip)
	{
		this.host=host;
		this.skip=skip;
	}
	public ITransmitImplHttp(String host,String skip,String addCmd)
	{
		this.host=host;
		this.skip=skip;
		this.addCmd=addCmd;
	}

	@Override
	public int downFile(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int downFile(String arg0, String arg1, String arg2, Handler arg3) {
		String inStr=this.host+arg0;
		Log.i(tag,inStr);
		Log.i(tag,"path:"+arg1);
		Log.i(tag,"filename:"+arg2);
		Log.i(tag,inStr);		
		int rsInt=client.downFile(inStr, arg1, arg2,arg3);
		Log.i(tag,"rsInt:"+rsInt);
		return rsInt;
	}

	@Override
	public InputStream downStream(String arg0) {	
		String inStr=this.host+arg0;
		Log.i(tag,inStr);
		try {
			client.openConn(inStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client.getInputStream();
	}

	@Override
	public String download(String arg0) {
		String inStr=this.host+arg0.replace("#and", "&")+this.addCmd;
		Log.i(tag,inStr);
		String rs=client.download(inStr);
		Log.i(tag,"rs=>"+rs);
		return rs;
	}
	

	
	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSkip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHost(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSkip(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String download(String commend, int size) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String download(String commend, String end) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public byte[] download(byte[] commend, int size) {
		// TODO Auto-generated method stub
		return null;
	}



}
