package cn.artwebs.UI;

import java.lang.reflect.Constructor;
import java.util.ArrayList;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;

import cn.artwebs.UI.Form.AbsControl;
import cn.artwebs.object.BinList;
import cn.artwebs.object.BinMap;


public class CodeUIForm extends CodeUI {
	private final static String tag="CodeUIForm";
	private TableLayout formLayout;
	private ArrayList<AbsControl> ctlList=new ArrayList<AbsControl>();

	
	@Override
	public View drawnView(Activity activity,Integer parentid,Integer id) {
		this.formLayout=new TableLayout(activity);
//		this.formLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
		this.formLayout.setColumnStretchable(1, true);
		this.drawnControl(activity);		
		return this.formLayout;
		
		
	}
	
	private void drawnControl(Activity activity)
	{
		if("1".equals(this.para.getValue("code").toString()))
		{
			if(para.containsKey("title"))
				activity.setTitle(this.para.getValue("title").toString());
			BinList rows=(BinList)(this.para.getValue("rows"));
			for(int i=0;i<rows.size();i++)
			{
				
				BinMap ctlpara= new BinMap();
				ctlpara.setItemByHashMap(rows.getItem(i));
				Log.i(tag,ctlpara.getValue("CONMETHOD").toString());
				Class<CodeUI> objclass=null;
				AbsControl ctlobj=null;
				String type=ctlpara.getValue("CONMETHOD").toString().toLowerCase();
				ctlobj=this.getControlObj(type, activity, ctlpara);
				ctlobj.setTransmit(this.transmit);
				ctlobj.setUiFactory(this.uiFactory);
				ctlobj.setCtlList(ctlList);
				ctlobj.create();
				ctlobj.display();
			}
		}
	}
	
	private AbsControl getControlObj(String type,Activity activity, BinMap para)
	{
		Class<CodeUI> objclass=null;
		AbsControl ctlobj=null;
		try{
			objclass=(Class<CodeUI>)Class.forName("com.artwebsandroid.UI.Form.Control"+type.substring(0,1).toUpperCase()+type.substring(1));
			Constructor constructor = objclass.getConstructor(TableLayout.class,Activity.class,BinMap.class); 
			ctlobj=(AbsControl)constructor.newInstance(this.formLayout,activity,para);
		}catch(Exception e)
		{
			try {
				objclass=(Class<CodeUI>)Class.forName("com.artwebsandroid.UI.Form.ControlTextbox");
				Constructor constructor = objclass.getConstructor(TableLayout.class,Activity.class,BinMap.class); 
				ctlobj=(AbsControl)constructor.newInstance(this.formLayout,activity,para);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}		
		return ctlobj;
		
	}

	
	


}
