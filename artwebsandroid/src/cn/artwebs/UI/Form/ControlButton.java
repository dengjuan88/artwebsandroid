package cn.artwebs.UI.Form;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import cn.artwebs.object.BinList;
import cn.artwebs.object.BinMap;

public class ControlButton extends AbsControl {
	private Button button=null;
	private String tempValue="";
	private String tempKey="";
	public ControlButton(TableLayout layout, Activity activity, BinMap para) {
		super(layout, activity, para);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		TextView textView=new TextView(activity);
		textView.setWidth(100);
		textView.setGravity(Gravity.CENTER);
		textView.setText(this.para.getValue("CNAME").toString());
		this.strName=this.para.getValue("NAME").toString();
		tbrow.addView(textView);
		
		button=new Button(activity);
		if(!"".equals(this.para.getValue("DICVALUE").toString()))button.setText(this.para.getValue("DICVALUE").toString());
		button.setGravity(Gravity.CENTER);
		
		if(!this.para.containsKey("READONLY")&&!"".equals(this.para.getValue("VALUE")))
		{
			button.setEnabled(false);			
		}
		if("false".equals(this.para.getValue("DISPLAY")))tbrow.setVisibility(View.INVISIBLE);
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				Intent intent=new Intent();
//				intent.putExtra("conurl", ControlButton.this.para.getValue("CONURL").toString());
//				intent.setClass(activity, SelectActivity.class);
//				activity.startActivity(intent);
				
				ControlButton.this.showDialog(activity);
				
			}			 
						 
			  });
		tbrow.addView(button);
	}
	
	private void showDialog(Context context)
	{
		LinearLayout main=new LinearLayout(context);
        main.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        main.setOrientation(LinearLayout.VERTICAL);
        
        
        this.uiFactory.setTransmit(transmit);
        this.uiFactory.parseData(this.para.getValue("CONURL").toString().replace("#and", "&"));
        BinMap para=this.uiFactory.getMap();
        final BinList list=(BinList)para.getValue("rows");
        RadioGroup radioGroup=new RadioGroup(context);
        
        for(int i=0;i<list.size();i++)
        {
        	RadioButton radioButton=new RadioButton(context);
        	radioButton.setId(i);
        	radioButton.setText(list.getValue(i, "first").toString());
        	if(tempKey==list.getValue(i, "second").toString())radioButton.setSelected(true);
        	radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				tempValue=list.getValue(arg1, "first").toString();
				tempKey=list.getValue(arg1, "second").toString();
				RadioButton radioButton=(RadioButton)arg0.getChildAt(arg1);
				radioButton.setSelected(true);
			}});
        main.addView(radioGroup);
        
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);  
		builder.setCancelable(false);   
		builder.setTitle(this.para.getValue("CNAME").toString());  
		builder.setView(main);  
		builder.setPositiveButton("确认",  
		        new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int whichButton) {  
		                button.setText(tempValue);
		                ControlButton.this.strValue=tempKey;
		                ControlButton.this.strText=tempValue;
		            }  
		        });  
		builder.setNegativeButton("取消",  
		        new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int whichButton) {  
//		                setTitle("");  
		            }  
		        });  
		builder.show();  

        
	}
	

}
