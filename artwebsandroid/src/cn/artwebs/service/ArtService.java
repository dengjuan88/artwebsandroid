package cn.artwebs.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

public abstract class ArtService extends Service {

	private static LocalBroadcastManager broadcaster;

	private static String SERVICE_RESULT="cn.artwebs.service.ArtService";
	public final static String RESULT_TAG="tag";
	private static boolean isSendbroad=false;
	private static ArtService self;


	
	public static void setServiceResult(String tag)
	{
		SERVICE_RESULT=tag;
		if(SERVICE_RESULT==null)isSendbroad=false;
		
	}
	
	public static void setBroadcaster(LocalBroadcastManager bdcaster)
	{
		broadcaster=bdcaster;
	}
	
	public  static String getserviceSign()
	{
		return SERVICE_RESULT;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		serviceRun();
	}
	
	public abstract void serviceRun();
	
	public void sendResult(String message)
	{
		 Intent intent = new Intent(SERVICE_RESULT);
		 if(message != null&&isSendbroad)
		        intent.putExtra(RESULT_TAG,message);
		    if(broadcaster!=null)
		    	broadcaster.sendBroadcast(intent);
	}
	
	class ToClass {
		
		public Class getToClass()
		{
			return this.getClass();
		}
	}

}
