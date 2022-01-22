package cn.artwebs.socket;

import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.os.Handler;
import android.util.Log;

public class ClientUDP extends Client {
	protected DatagramPacket packet;
	protected DatagramSocket socket;
	@Override
	public void getConnetion() {
		if(packet!=null)	
		{
			try {

				this.socket=new DatagramSocket();
				packet.setAddress(InetAddress.getByName(this.getHost()));
				packet.setPort(this.port);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String download(String meg) {
		String rs="";
		byte data[] =meg.getBytes();
		packet=new DatagramPacket(data,data.length);
		this.getConnetion();
		try {			
			this.socket.send(packet);
			Log.i("port", this.socket.getLocalPort()+"");
			
			byte rData[]=new byte[2048];
			DatagramPacket rPacket=new DatagramPacket(rData,rData.length);
			this.socket.receive(rPacket);
			rs=new String(rData,0,rPacket.getLength());			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnetion();			
		return rs;
	}



	@Override
	public int downFile(String meg, String path, String fileName) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	@Override
	public void closeConnetion() {
		this.socket.close();
		this.socket=null;		
	}

	@Override
	public int downFile(String meg, String path, String fileName,
			Handler handler) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InputStream downStream(String meg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String download(String msg, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String download(String msg, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] download(byte[] msg, int size) {
		byte rData[]=new byte[size];
		packet=new DatagramPacket(msg,msg.length);
		this.getConnetion();
		try {			
			this.socket.send(packet);
			Log.i("port", this.socket.getLocalPort()+"");
			DatagramPacket rPacket=new DatagramPacket(rData,rData.length);
			this.socket.receive(rPacket);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnetion();			
		return rData;
	}

}
