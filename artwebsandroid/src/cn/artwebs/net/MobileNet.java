package cn.artwebs.net;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

public class MobileNet {
	private boolean netStatus=false;
	private Context act;
	public MobileNet(Context act)
	{
		this.act=act;
	}
	
	public void setWiFiEnable()
	{
		WifiManager wifiManager = (WifiManager) act.getSystemService(Context.WIFI_SERVICE);  
	    if (wifiManager.isWifiEnabled()) {  
	        wifiManager.setWifiEnabled(false);  
        } else {  
        	wifiManager.setWifiEnabled(true);  
        }
	}
	
	public boolean isMobileNetEnable()
	{
		Object[] arg = null;
  	  	try {
  	  		netStatus = invokeMethod("getMobileDataEnabled", arg);
  	  	} catch (Exception e) {
  	  		e.printStackTrace();
  	  	}  	 
        return netStatus;
	}
	
	public void setEnable(){
		Object[] arg = null;
  	  	try {
  	  		boolean isMobileDataEnable = invokeMethod("getMobileDataEnabled", arg);
  	  		if(!isMobileDataEnable){
  	  			invokeBooleanArgMethod("setMobileDataEnabled", true);
  	  		}
  	  	} catch (Exception e) {
  	  		e.printStackTrace();
  	  	}  	  	
  	 } 
	
	public void setDisable(){
		Object[] arg = null;
  	  	try {
  	  		boolean isMobileDataEnable = invokeMethod("getMobileDataEnabled", arg);
  	  		if(isMobileDataEnable){
  	  			invokeBooleanArgMethod("setMobileDataEnabled", false);
  	  		}
  	  	} catch (Exception e) {
  	  		e.printStackTrace();
  	  	}  	  	
  	 } 

  	public boolean invokeMethod(String methodName,
  	            Object[]  arg) throws Exception {  	     
  	     ConnectivityManager mConnectivityManager = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
  	     Class ownerClass = mConnectivityManager.getClass();
  	        Class[]  argsClass = null;
  	        if (arg != null) {
  	            argsClass = new Class[1];
  	            argsClass[0] = arg.getClass();
  	        }
  	        Method method = ownerClass.getMethod(methodName, argsClass);  	       
  	        Boolean isOpen = (Boolean) method.invoke(mConnectivityManager, arg);
  	        return isOpen;
  	}
  	    
    public Object invokeBooleanArgMethod(String methodName,
                boolean value) throws Exception {  	     
      ConnectivityManager mConnectivityManager = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
            Class ownerClass = mConnectivityManager.getClass();
            Class[]  argsClass = new Class[1];
                argsClass[0] = boolean.class;
            Method method = ownerClass.getMethod(methodName,argsClass);
            return method.invoke(mConnectivityManager, value);
    }
	
}
