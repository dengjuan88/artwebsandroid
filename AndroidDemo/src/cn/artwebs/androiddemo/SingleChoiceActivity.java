package cn.artwebs.androiddemo;

import cn.artwebs.control.SingleChoiceView;
import cn.artwebs.utils.AndroidUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SingleChoiceActivity extends Activity {
	private SingleChoiceView sigleChoiceView;
	private Button scBtn;
	//[{"rvalue":"不满意","rkey":"0"},{"rvalue":"一般","rkey":"1"},{"rvalue":"满意","rkey":"2"},{"rvalue":"很满意","rkey":"3"}]
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singlechoice_layout);
		sigleChoiceView=(SingleChoiceView) findViewById(R.id.scView); 
		scBtn=(Button) findViewById(R.id.scBtn); 
		sigleChoiceView.init("rkey", "rvalue");
		sigleChoiceView.setSource("应用使用是否满意", "[{\"rvalue\":\"不满意\",\"rkey\":\"0\"},{\"rvalue\":\"一般\",\"rkey\":\"1\"},{\"rvalue\":\"满意\",\"rkey\":\"2\"},{\"rvalue\":\"很满意\",\"rkey\":\"3\"}]");
		sigleChoiceView.show();
		scBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AndroidUtils.toastShow(SingleChoiceActivity.this, sigleChoiceView.getKey()+"=>"+sigleChoiceView.getValue());
				
			}
		});
	}
	
	
}
