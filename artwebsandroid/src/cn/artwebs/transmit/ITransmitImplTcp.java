package cn.artwebs.transmit;

import java.io.InputStream;

import cn.artwebs.socket.ClientTCP;
import cn.artwebs.transmit.ITransmit;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class ITransmitImplTcp implements ITransmit {
	private final static String tag="ITransmitImplTcp";
	private String skip="";
	private ClientTCP sok;
	private String host="";
	private int prot=3456;

	
	public ITransmitImplTcp(String host,String skip)
	{
		this.host=host;
		this.skip=skip;
	}
	
	public ITransmitImplTcp(String host,int port,String skip)
	{
		this.host=host;
		this.prot=port;
		this.skip=skip;
		sok=new ClientTCP(host,prot);	
	}


	
	@Override
	public void setSkip(String skip) {
		this.skip=skip;
	}

	@Override
	public String getSkip() {
		return this.skip;
	}

	
	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}


	@Override
	public String download(String commend) {
		Log.i("trans",commend);
		String rs= sok.download(this.skip+commend);
		Log.i(tag,"rs=>"+rs);
		return rs;
	}

	@Override
	public int downFile(String commend, String path, String fileName) {
		Log.i("Trans",this.skip+commend);
		return sok.downFile(this.skip+commend, path, fileName);
	}

	@Override
	public int downFile(String commend, String path, String fileName,
			Handler handler) {
		return sok.downFile(this.skip+commend, path, fileName,handler);
	}

	@Override
	public InputStream downStream(String commend) {
		return sok.downStream(this.skip+commend);
	}

	@Override
	public String download(String commend, int size) {
		Log.i("trans","commend="+commend);
		Log.i("trans","size="+size);
		String rs=sok.download(commend, size);
		Log.i(tag,"rs=>"+rs);
		return rs;
	}

	@Override
	public String download(String commend, String end) {
		Log.i("trans","commend="+commend);
		Log.i("trans","end="+end);
		String rs=sok.download(commend, end);
		Log.i(tag,"rs=>"+rs);
		return rs;
	}

	@Override
	public void close() {
		if(sok!=null)sok.closeConnetion();
	}

	@Override
	public byte[] download(byte[] commend, int size) {
		return sok.download(commend, size);
	}

	
}
