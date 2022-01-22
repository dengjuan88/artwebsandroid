package cn.artwebs.demo;

import java.io.InputStream;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import cn.artwebs.socket.ClientTCP;
import cn.artwebs.transmit.ITransmit;


public class TransmitExample implements ITransmit {
	private String skip;
	private ClientTCP sok;
	
	public TransmitExample(String skip)
	{
		this.setSkip(skip);
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
	public String download(String commend) {
		sok=new ClientTCP("116.55.248.21",8000);		
		return sok.download(this.skip+commend);
	}

	@Override
	public int downFile(String commend, String path, String fileName) {
		sok=new ClientTCP("116.55.248.21",8000);	
		Log.i("Trans",this.skip+commend);
		return sok.downFile(this.skip+commend, path, fileName);
	}

	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHost(String host) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public InputStream downStream(String commend) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int downFile(String commend, String path, String fileName,
			Handler handler) {
		// TODO Auto-generated method stub
		return 0;
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
