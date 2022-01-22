package cn.artwebs.UI.Form;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import cn.artwebs.object.BinMap;

public class ControlMeltiline extends AbsControl{
	private EditText meltlline;
	public ControlMeltiline(TableLayout layout, Activity activity, BinMap para) {
		super(layout, activity, para);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		TextView textView=new TextView(activity);
		textView.setWidth(100);
		textView.setGravity(Gravity.CENTER);
		textView.setText(this.para.getValue("CNAME").toString());
		tbrow.addView(textView);
		
		meltlline=new EditText(activity);
		meltlline.setText(this.para.getValue("VALUE").toString());		
		meltlline.setHeight(200);
		if(!this.para.containsKey("READONLY")&&!"".equals(this.para.getValue("VALUE")))
		{
			meltlline.setEnabled(false);
		}
		if("false".equals(this.para.getValue("DISPLAY")))tbrow.setVisibility(View.INVISIBLE);
		tbrow.addView(meltlline);
		
	}
	
	public String getStrValue() {
		strValue=meltlline.getText().toString();
		return strValue;
	}
	
	public String getStrText() {
		strText=meltlline.getText().toString();
		return strText;
	}

}
