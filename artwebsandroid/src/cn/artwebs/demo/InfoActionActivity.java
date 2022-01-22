package cn.artwebs.demo;

import cn.artwebs.UI.UIFactory;
import cn.artwebs.net.MobileNet;
import cn.artwebs.socket.ClientTCP;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class InfoActionActivity extends Activity {
	private final static String tag="InfoActionActivity"; 
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);  
//	        MobileNet net=new MobileNet(this);
//	        net.setEnable();
	        Log.d(tag,getApplicationContext().getFilesDir().getAbsolutePath());
	        String var="";

	        Intent intent=this.getIntent();
	        if(intent.hasExtra("id"))
	        {
		        var=intent.getStringExtra("id");
		        var=var.replace(",", "&");
	        }
	        
	        LinearLayout main=new LinearLayout(this);
	        main.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
	        main.setOrientation(LinearLayout.VERTICAL);
	        this.setContentView(main);
	        final UIFactory factory=new UIFactory();
	        factory.setTransmit(C.transmit.transObj);
			View view=factory.dranView(this,String.format(C.transmit.info, var));
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
			main.addView(view);
			
			Button button=new Button(this);
			button.setText("新增");
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					Intent intent=new Intent();
					intent.setClass(InfoActionActivity.this, UiActionActivity.class);
					intent.putExtra("session", factory.getMap().getValue("session").toString());
					InfoActionActivity.this.startActivity(intent);
					
				}});
			button.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
	        main.addView(button);
	 }
}
