package cn.artwebs.UI.Form;

import java.util.HashMap;

import android.R;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import cn.artwebs.object.BinList;
import cn.artwebs.object.BinMap;
import cn.artwebs.utils.AndroidUtils;


public class ControlDropdownlist extends AbsControl {
	private Spinner spinner;
	private EditText editText;
	public ControlDropdownlist(TableLayout layout, Activity activity,
			BinMap para) {
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
		if(!this.para.containsKey("READONLY")&&!"".equals(this.para.getValue("VALUE")))
		{
			editText=new EditText(activity);
			editText.setText(this.para.getValue("DICVALUE").toString());
			editText.setSingleLine();					
			AndroidUtils.setEditTextReadOnly(editText);
			strValue=this.para.getValue("VALUE").toString();
			strText=this.para.getValue("DICVALUE").toString();
			tbrow.addView(editText);	
		}
		else
		{
			spinner=new Spinner(activity);
			BinList items=(BinList)this.para.getValue("ITEMS");
			
			SimpleAdapter adapter=new SimpleAdapter(activity,items.getItem(),android.R.layout.simple_spinner_item,new String[]{"text"},new int[]{R.id.text1});
			SimpleAdapter.ViewBinder viewBinder = new SimpleAdapter.ViewBinder() { 
				 
		        public boolean setViewValue(View view, Object data, 
		                String textRepresentation) { 
		            TextView textView = (TextView) view; 
		            textView.setText(textRepresentation); 
		            return true; 
		        } 
		    }; 
		    adapter.setViewBinder(viewBinder); 
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
			
			spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
				private final static String tag="OnItemSelectedListener";
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Log.i(tag,((HashMap)arg0.getAdapter().getItem(arg2)).toString());
					strValue=((HashMap)arg0.getAdapter().getItem(arg2)).get("id").toString();
					strText=((HashMap)arg0.getAdapter().getItem(arg2)).get("text").toString();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}});
			tbrow.addView(spinner);
			if(!this.para.containsKey("READONLY")&&!"".equals(this.para.getValue("VALUE")))
			{
				spinner.setEnabled(false);
			}
		}					
		if("false".equals(this.para.getValue("DISPLAY")))tbrow.setVisibility(View.INVISIBLE);		
		
	}
	

}
