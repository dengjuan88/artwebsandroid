package cn.artwebs.UI;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.artwebs.object.BinList;

public class CodeUIInfo extends CodeUI {
	
	
	@Override
	public View drawnView(Activity activity,Integer parentid,Integer id) {
		 TextView text=new TextView(activity);
		 if(id!=null)text.setId(id);
		 Log.i("Info",this.para.getItem().toString());
		 if("-1".equals(this.para.getValue("code").toString()))
		 {
			 text.setText(this.para.getValue("message").toString());			 
		 }
		 else if(Integer.parseInt(this.para.getValue("count").toString())>0)
		 {
			 text.setText(((BinList)(this.para.getValue("rows"))).getValue(0, "item").toString());	
		 }
		 else
		 {
			 text.setText("��ѯ���Ϊ��");
		 }
		 
		 
		return text;
	}


}
