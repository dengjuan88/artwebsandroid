package cn.artwebs.comm;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 
 * @author artwebs
 * IMSI：international mobiles subscriber identity国际移动用户号码标识，
	这个一般大家是不知道，GSM必须写在卡内相关文件中；
	MSISDN:mobile subscriber ISDN用户号码，这个是我们说的139，136那个号码；
	ICCID:ICC identity集成电路卡标识，这个是唯一标识一张卡片物理号码的；
	IMEI：international mobile Equipment identity手机唯一标识码；
 *
 */
public class Phone{
	private String imsi="";
	private String imei="";
	private String msisdn="";
	private String iccid="";
	private static Phone obj;
	
	public String getImsi() {
		return imsi==null?"":imsi;
	}

	public String getImei() {
		return imei==null?"":imei;
	}

	public String getMsisdn() {
		return msisdn==null?"":msisdn;
	}

	public String getIccid() {
		return iccid==null?"":iccid;
	}

	private Phone()
	{
		TelephonyManager tm=((TelephonyManager) AppApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE));
		imsi=tm.getSubscriberId();
		imei=tm.getDeviceId();
		msisdn=tm.getLine1Number();
		iccid=tm.getSimSerialNumber();
	}
	
	public synchronized static Phone instance()
	{
		if(obj==null)
			obj=new Phone();
		return obj;
	}
	
}