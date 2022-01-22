package cn.artwebs.service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class ArtServiceManager {
	private static Activity activity;
	private static Intent serviceIntent;
	private static AlarmManager manager;
	private static PendingIntent pendingIntent;
	private static long intervalTime=15*1000;
	
	public static void start(Activity obj,Class tagClass)
	{
		start(obj,tagClass,0,null);
	}
	
	public static void start(Activity obj,Class tagClass,long iTime)
	{
		start(obj,tagClass,iTime,null);
	}
	
	public static void start(Activity obj,Class tagClass,long iTime,String serviceSign)
	{
		if(activity==null)
		{
			activity=obj;
			ArtService.setServiceResult(serviceSign);
			ArtService.setBroadcaster(LocalBroadcastManager.getInstance(activity));
			if(iTime!=0)intervalTime=iTime;
			serviceIntent=new Intent(obj,tagClass);
    		if(manager==null)manager=(AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
     		if(pendingIntent==null)pendingIntent=PendingIntent.getService(activity, 0, serviceIntent, 0);
     		manager.setRepeating(AlarmManager.RTC, 0, intervalTime,pendingIntent);
    		
		}
	}
	
	public static void stop()
	{
		if(activity!=null)
		{
			manager.cancel(pendingIntent);
			activity.stopService(serviceIntent);
			activity=null;
		}
	}
	
	
}
