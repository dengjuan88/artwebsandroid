package cn.artwebs.demo;

import cn.artwebs.transmit.ITransmit;
import cn.artwebs.transmit.ITransmitImplHttp;

public final class C {
	public final static class transmit	{
		public final static String host="http://psdemo.sinaapp.com";
		public final static String skip="";
		public static String addCmd="";
		public static ITransmit transObj=new ITransmitImplHttp(host,skip,addCmd);
		
		
		
		public final static String list="/user/userAllList";
		public final static String pagelist="/user/userPageList/%s/%s";
		public final static String info="/user/userInfo/%s";
		
	}
	
}
