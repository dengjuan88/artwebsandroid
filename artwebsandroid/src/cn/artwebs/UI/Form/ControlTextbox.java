package cn.artwebs.UI.Form;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import cn.artwebs.object.BinMap;
import cn.artwebs.utils.AndroidUtils;


public class ControlTextbox extends AbsControl {
	protected EditText editText;
	public ControlTextbox(TableLayout layout, Activity activity, BinMap para) {
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
		editText=new EditText(activity);
		editText.setText(this.para.getValue("DICVALUE").toString());
		
		editText.setSingleLine();
		if(!this.para.containsKey("READONLY")&&!"".equals(this.para.getValue("VALUE")))
		{
			AndroidUtils.setEditTextReadOnly(editText);
		}
		if("false".equals(this.para.getValue("DISPLAY")))tbrow.setVisibility(View.INVISIBLE);
		tbrow.addView(editText);	
	}

	
	public String getStrValue() {
		strValue=editText.getText().toString();
		return strValue;
	}
	
	
	public String getStrText() {
		strText=editText.getText().toString();
		return strText;
	}
}
