package cn.artwebs.comm;

import java.util.List;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;

public class Gsm {
	private int lac;
	private int cellid;
	private int bsss;
	
	public Gsm(int lac, int cellid, int bsss) {
		this.lac = lac;
		this.cellid = cellid;
		this.bsss = bsss;
	}
	
	public static Gsm instance()
	{
		TelephonyManager tm=((TelephonyManager) AppApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE));
		List<NeighboringCellInfo> infoLists = tm.getNeighboringCellInfo();  
	    System.out.println("infoLists:"+infoLists+"     size:"+infoLists.size());  
	    int l=0,c=0,b=0;
	    for (NeighboringCellInfo info : infoLists) {  
	        b+=(-133+2*info.getRssi());// 获取邻区基站信号强度    
	        l=info.getLac();// 取出当前邻区的LAC   
	        c=info.getCid();// 取出当前邻区的CID   
	    } 
		return new Gsm(l,c,b);
	}

	public int getLac() {
		return lac;
	}

	public int getCellid() {
		return cellid;
	}

	public int getBsss() {
		return bsss;
	}
	
	
	
	
}
