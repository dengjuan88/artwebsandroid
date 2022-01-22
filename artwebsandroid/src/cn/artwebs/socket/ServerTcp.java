package cn.artwebs.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerTcp {
	private int port;
	private ServerSocket server=null;
	private ExecutorService executorService;
	private int poolSize=2;
	
	public ServerTcp()
	{
		this(9000);
	}
	
	public ServerTcp(int port)
	{
		this.port=port;
		executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*poolSize);
		
	}
	
	
	public void startup()
	{
		new Thread()
		{
			@Override
			public void run() {
				try {
					server=new ServerSocket(port);
					while(true){     
						Socket socket=null;     
			            try {     
			                //���տͻ�����,ֻҪ�ͻ�����������,�ͻᴥ��accept();�Ӷ�������     
			                socket=server.accept();     
			                executorService.execute(getRunnable(socket));  
			            } catch (Exception e) {     
			                e.printStackTrace();     
			            }     
			        } 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}.start();
		
	}
	
	public void shutdown(){
		executorService.shutdownNow();
	}
	
	public Runnable getRunnable(Socket socket){
		return new Handler(socket);
	}
	
	class Handler implements Runnable{     
	    private Socket socket;
	    protected OutputStream socketOut;
	    protected InputStream socketIn;
	    public Handler(Socket socket){     
	        this.socket=socket;     
	        try {
				socketOut=socket.getOutputStream();
				socketIn=socket.getInputStream();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }     
	    private PrintWriter getWriter(Socket socket) throws IOException{     
	        return new PrintWriter(socketOut,true);     
	    }     
	    private BufferedReader getReader(Socket socket) throws IOException{     
	        return new BufferedReader(new InputStreamReader(socketIn));     
	    }     
	    public String echo(String msg){     
	        return "echo:"+msg;     
	    }     
	    public void run(){     
	        try {     
	            System.out.println("New connection accepted "+socket.getInetAddress()+":"+socket.getPort());     
	            BufferedReader br=getReader(socket);     
	            PrintWriter pw=getWriter(socket);     
	            String msg=null;     
	            while((msg=br.readLine())!=null){     
	                System.out.println(msg);     
	                pw.println(echo(msg));     
	                if(msg.equals("bye"))     
	                    break;     
	            }     
	        } catch (IOException e) {     
	            e.printStackTrace();     
	        }finally{     
	            try {     
	                if(socket!=null)     
	                    socket.close();     
	            } catch (IOException e) {     
	                e.printStackTrace();     
	            }     
	        }     
	    }     
	}   
}
