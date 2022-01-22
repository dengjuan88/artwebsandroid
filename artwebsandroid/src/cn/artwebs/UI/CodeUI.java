package cn.artwebs.UI;

import java.util.ArrayList;

import org.xml.sax.ContentHandler;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import cn.artwebs.object.BinMap;
import cn.artwebs.transmit.ITransmit;


public abstract class CodeUI{
	protected BinMap para=new BinMap();
	private View viewObj;
	private Activity mainActity;
	public static String ERROR_CODE="-1";
	public static String RIGHT_CODE="00";
	protected OnItemClickListener onItemClickListener;
	protected ArrayList<String> textElement=new ArrayList<String>();
	protected ITransmit transmit;	
	protected IUIFactory uiFactory;
	
	public BinMap getPara() {
		return para;
	}


	public void setPara(BinMap para) {
		this.para = para;
	}

	public View getViewObj() {
		return viewObj;
	}

	public void setViewObj(View viewObj) {
		this.viewObj = viewObj;
	}

	public Activity getMainActity() {
		return mainActity;
	}

	public void setMainActity(Activity mainActity) {
		this.mainActity = mainActity;
	}
		
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}	

	public void setTransmit(ITransmit transmit) {
		this.transmit = transmit;
	}


	public void setUiFactory(IUIFactory uiFactory) {
		this.uiFactory = uiFactory;
	}


	public CodeUI()
	{
		
	}
	public abstract View drawnView(Activity activity,Integer parentid,Integer id);

	
	public static BinMap setErrorPara(String msg)
	{
		BinMap para=new BinMap();
		para.put("return", ERROR_CODE);
		para.put("returnflag", msg);
		return para;
	}
	
	
}
