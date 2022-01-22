package cn.artwebs.UI.Form;

import android.R;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TableLayout;

import cn.artwebs.control.CircleAsyncTask;
import cn.artwebs.object.BinList;
import cn.artwebs.object.BinMap;
import cn.artwebs.utils.AndroidUtils;


public class ControlRefreshtextbox extends ControlTextbox implements OnClickListener{
	protected CircleAsyncTask syncTask;
	protected ImageButton button;
	private final static String tag="ControlRefreshtextbox";
	public ControlRefreshtextbox(TableLayout layout, Activity activity,
			BinMap para) {
		super(layout, activity, para);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		super.create();
		button=new ImageButton(this.activity);
		button.setImageResource(R.drawable.ic_popup_sync);
		button.setOnClickListener(this);
		tbrow.addView(button);

	}

	@Override
	public void onClick(View v) {
		syncTask=new CircleAsyncTask(this.activity,"正在同步..."){

			@Override
			public BinMap doRun() {
				Log.d(tag,ControlRefreshtextbox.this.para.getValue("CONURL").toString().replace("#and", "&"));
				ControlRefreshtextbox.this.uiFactory.setTransmit(transmit);
				ControlRefreshtextbox.this.uiFactory.parseData(ControlRefreshtextbox.this.para.getValue("CONURL").toString().replace("#and", "&"));
				return ControlRefreshtextbox.this.uiFactory.getMap();
			}
			
			@Override
			public void doUpdate(BinMap para){
				if("1".equals(para.getValue("code")))
					editText.setText(para.getValue("rstext").toString());
				else
					AndroidUtils.commDialog(ControlRefreshtextbox.this.activity, "", para.getValue("message").toString());
				this.getProgressDialog().dismiss();
			}
			
		};
		syncTask.start();
	}

}
