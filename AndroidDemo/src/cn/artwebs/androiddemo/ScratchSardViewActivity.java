package cn.artwebs.androiddemo;

import cn.artwebs.control.ScratchSardView;
import cn.artwebs.control.ScratchSardView.ArtOnTuchListener;
import cn.artwebs.utils.AndroidUtils;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ScratchSardViewActivity extends Activity implements OnClickListener,ArtOnTuchListener {
	private LinearLayout mainLayout;
	private ScratchSardView ssView;
	private Button reBtn;
	private int count=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mainLayout=(LinearLayout)getLayoutInflater().inflate(R.layout.scratch_card, null);
		this.setContentView(mainLayout);
		ssView=(ScratchSardView) findViewById(R.id.ssView);
		ssView.setArtOnTuchListener(this);
		reBtn=(Button) findViewById(R.id.reBtn);
		reBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ssView.init();
		ssView.setText("刷新"+count++);
	}

	@Override
	public void toBengin() {
		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				AndroidUtils.toastShow(ScratchSardViewActivity.this, "开始刮了");
				
			}});
		
	}

	
	
}
