package cn.artwebs.socket;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import cn.artwebs.utils.FileUtils;

import android.os.Handler;
import android.util.Log;

public class ClientTCP extends Client {
	private static String tag="ClientTCP";
	protected Socket socket;
	private int timeOut=1000*10;
	
	public ClientTCP(){}
	
	public ClientTCP(String host,Integer port){
		super(host, port);
	}
	
	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void getConnetion() {
		if(this.socket==null)
		{
			try {
				this.socket=new Socket(this.host,this.port);		
				this.socket.setSoTimeout(timeOut);
				Log.d(tag,"create Client");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.i(tag,e.toString());
				e.printStackTrace();
				if(socket!=null)
				{
					try {
						socket.close();
						socket=null;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
	}

	@Override
	public String download(String meg) {
		this.getConnetion();
		String rs="";
		try {
			BufferedReader in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			OutputStream outputstream =this.socket.getOutputStream();
			outputstream.write(meg.getBytes());
			String line=null;
			while((line=in.readLine())!=null)
			{
				rs=rs+line+"\n";
			}
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 该函数返回整形 -1：代表下载文件出错 0：代表下载文件成功 1：代表文件已经存在
	 */
	public int downFile(String meg, String path, String fileName) {		
				
		InputStream inputStream = null;
		try {
			FileUtils fileUtils = new FileUtils(path);
			
			if (fileUtils.isFileExist(fileName)) {
				return 1;
			} else {
				this.getConnetion();
				inputStream = this.socket.getInputStream();
				OutputStream outputstream =this.socket.getOutputStream();
				outputstream.write(meg.getBytes());
				File resultFile = fileUtils.write2SDFromInput(fileName, inputStream);
				if (resultFile == null) {
					return -1;
				}
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			
		}
		return 0;		
	}
	
	@Override
	public void closeConnetion() {
		if(this.socket!=null)
		try {
			this.socket.close();
			this.socket=null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.socket=null;
		
	}

	@Override
	public int downFile(String meg, String path, String fileName,
			Handler handler) {
		InputStream inputStream = null;
		try {
			FileUtils fileUtils = new FileUtils(path);
			
			if (fileUtils.isFileExist(fileName)) {
				return 1;
			} else {
				this.getConnetion();
				inputStream = this.socket.getInputStream();
				OutputStream outputstream =this.socket.getOutputStream();
				outputstream.write(meg.getBytes());
				File resultFile = fileUtils.write2SDFromInput(fileName, inputStream,handler);
				if (resultFile == null) {
					return -1;
				}
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			
		}
		return 0;
	}

	@Override
	public InputStream downStream(String meg) {
		InputStream inputStream = null;
		try {
			
				this.getConnetion();
				inputStream = this.socket.getInputStream();
				OutputStream outputstream =this.socket.getOutputStream();
				outputstream.write(meg.getBytes());
				return inputStream;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}

	@Override
	public String download(String msg, int size) {
		this.getConnetion();
		String rs="";
		try {
			byte[] b = new byte[size];
			OutputStream outputstream =this.socket.getOutputStream();
			outputstream.write(msg.getBytes());
			int len = this.socket.getInputStream().read(b);
		    ByteArrayOutputStream bais = new ByteArrayOutputStream();
		    bais.write(b, 0, len);
		    bais.flush();
		    b = bais.toByteArray();
		    bais.close();
		    rs=new String(b);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	@Override
	public byte[] download(byte[] msg, int size) {
		this.getConnetion();
		byte[] b = new byte[size];
		try {
			
			OutputStream outputstream =this.socket.getOutputStream();
			outputstream.write(msg);
			int len = this.socket.getInputStream().read(b);
		    ByteArrayOutputStream bais = new ByteArrayOutputStream();
		    bais.write(b, 0, len);
		    bais.flush();
		    b = bais.toByteArray();
		    bais.close();
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public String download(String msg, String end) {
		this.getConnetion();
		String rs="";
		try {
			OutputStream outputstream =this.socket.getOutputStream();
			outputstream.write(msg.getBytes());
			String line="";
			while(true)
			{
				byte[] b = new byte[1024];
				int len = this.socket.getInputStream().read(b);
			    ByteArrayOutputStream bais = new ByteArrayOutputStream();
			    bais.write(b, 0, len);
			    bais.flush();
			    b = bais.toByteArray();
			    bais.close();
			    line=new String(b);
				rs=rs+line;
				if(rs.indexOf(end)>0)break;
			}
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
