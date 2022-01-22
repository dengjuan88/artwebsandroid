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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UiActionActivity extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
			
//			MobileNet net=new MobileNet(this);
//	        net.setEnable();
	        
	        LinearLayout main=new LinearLayout(this);
	        main.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
	        
	        this.setContentView(main);
	        
	        String var="";

	        Intent intent=this.getIntent();
	        if(intent.hasExtra("session"))
	        {
		        var=intent.getStringExtra("session");
		        var=var.replace(",", "&");
	        }
	        
			UIFactory factory=new UIFactory();
			factory.setTransmit(new TransmitExample("test/"));
			View view=factory.dranView(this,"LHBSystem_1/index.php?mod=examplexml&act=ui&session="+var);
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
			main.addView(view);
			
	        
	 }

}
