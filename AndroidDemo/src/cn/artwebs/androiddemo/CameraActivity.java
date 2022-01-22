package cn.artwebs.androiddemo;

import cn.artwebs.control.ArtCamera;
import cn.artwebs.control.ArtCamera.CAMERATYPE;
import cn.artwebs.control.ArtCamera.OPERATE;
import cn.artwebs.control.ArtCamera.SaveCallBack;
import cn.artwebs.utils.FileUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CameraActivity extends Activity implements SaveCallBack ,OnClickListener{
	private LinearLayout mainLayout;
	private ArtCamera artCamera;
	private TextView megTxt;
	private Button saveImageBtn;
	private Button saveVideoBtn;
	private Button saveAudioBtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mainLayout=(LinearLayout)getLayoutInflater().inflate(R.layout.camera, null);
		this.setContentView(mainLayout);
		artCamera=(ArtCamera)findViewById(R.id.artcamera);
		artCamera.setSavePath(new FileUtils().getSDPATH());
		artCamera.setSaveCallBack(this);
		artCamera.show(CAMERATYPE.BACK);
		megTxt=(TextView)findViewById(R.id.megTxt);
		saveImageBtn=(Button)findViewById(R.id.saveImageBtn);
		saveImageBtn.setOnClickListener(this);
		saveVideoBtn=(Button)findViewById(R.id.saveVideoBtn);
		saveVideoBtn.setOnClickListener(this);
		saveAudioBtn=(Button)findViewById(R.id.saveAudioBtn);
		saveAudioBtn.setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.saveImageBtn:
			artCamera.saveImage();
			break;
		case R.id.saveVideoBtn:
			artCamera.saveVideo();
			break;
		case R.id.saveAudioBtn:
			artCamera.saveAudio();
			break;
		}
		
	}


	@Override
	public void onGetFileName(String fileName,OPERATE opt) {
		// TODO Auto-generated method stub
		megTxt.setText(fileName);
	}


	@Override
	public void onGetError(Exception e) {
		// TODO Auto-generated method stub
	}
	
}
