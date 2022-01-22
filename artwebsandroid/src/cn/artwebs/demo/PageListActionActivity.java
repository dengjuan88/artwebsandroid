package cn.artwebs.demo;

import java.util.HashMap;

import cn.artwebs.UI.UIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

public class PageListActionActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);         
        LinearLayout main=new LinearLayout(this);
        main.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        main.setOrientation(LinearLayout.VERTICAL);
        this.setContentView(main);
        
		UIFactory factory=new UIFactory();
		factory.setTransmit(C.transmit.transObj);
		
		factory.setOnItemClickListener(new OnItemClickListener() {			 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {	
					String id=((HashMap)arg0.getAdapter().getItem(arg2)).get("id").toString();
					Intent intent=new Intent();
					intent.setClass(PageListActionActivity.this, InfoActionActivity.class);
					intent.putExtra("id", id);
					PageListActionActivity.this.startActivity(intent);
//						Toast.makeText(ListActionActivity.this, arg3+"Item clicked, position is:" + arg0.getAdapter().getItem(arg2),
//							       Toast.LENGTH_SHORT).show();
					
				}				 
			  });
			View view=factory.dranView(this, String.format(C.transmit.pagelist, 1,3));
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
			
			main.addView(view);
    }
}
