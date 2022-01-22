package cn.artwebs.UI.Form;

import java.util.ArrayList;

import cn.artwebs.UI.UIFactory;
import cn.artwebs.control.CircleAsyncTask;
import cn.artwebs.object.BinMap;
import cn.artwebs.utils.Utils;


import android.R.color;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class ControlSubmit extends AbsControl {
	private Button button;
	protected CircleAsyncTask syncTask;
	
	public ControlSubmit(TableLayout layout, Activity activity, BinMap para) {
		super(layout, activity, para);
		this.setSelfOnClickListener(new SelfOnClickListener());
	}

	@Override
	public void create() {
		tbrow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
		LinearLayout row=new LinearLayout(this.activity);
		row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
		row.setGravity(Gravity.CENTER);
		
		button=new Button(activity);
		button.setGravity(Gravity.CENTER);
		button.setText(this.para.getValue("CNAME").toString());
		this.strName=this.para.getValue("NAME").toString();
		button.setWidth(160);
		button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
		button.setOnClickListener(this.selfOnClickListener);
		
		row.addView(button);
		
		TableRow.LayoutParams rowvar=new TableRow.LayoutParams(row.getLayoutParams());
		rowvar.span=3;
		row.setLayoutParams(rowvar);
		
		tbrow.addView(row);		
	}
	
	
	class SelfOnClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			syncTask=new CircleAsyncTask(ControlSubmit.this.activity,""){

				@Override
				public BinMap doRun() {
					StringBuilder sb=new StringBuilder();
					sb.append(ControlSubmit.this.para.getValue("CONURL").toString().replace("#and", "&"));
					ArrayList<AbsControl> ctls=ControlSubmit.this.ctlList;
					
					for(int i=0;i<ControlSubmit.this.ctlList.size();i++)
					{
						sb.append("&");
						sb.append(ctls.get(i).getStrName());
						sb.append("=");
						sb.append(Utils.UrlEncode(ctls.get(i).getStrValue(), "utf-8"));
					}
					
					UIFactory factory=new UIFactory();
					factory.setTransmit(ControlSubmit.this.transmit);
					factory.dranView(ControlSubmit.this.activity,sb.toString());
					return factory.getMap();
				}
				
				@Override
				public void doUpdate(BinMap para)
				{
					Toast toast=Toast.makeText(ControlSubmit.this.activity, para.getValue("message").toString(), Toast.LENGTH_LONG);
					toast.show();
					this.getProgressDialog().dismiss();
				}
				
			};
			syncTask.start();
			
		}
		
	}
	

}
