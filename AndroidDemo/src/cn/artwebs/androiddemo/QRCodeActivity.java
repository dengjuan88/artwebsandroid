package cn.artwebs.androiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QRCodeActivity extends Activity {
	private LinearLayout mainLayout;
//	private ArtQrCodeView artqrcode;
	private TextView megTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mainLayout=(LinearLayout)getLayoutInflater().inflate(R.layout.qrcode, null);
		this.setContentView(mainLayout);
		megTxt=(TextView)findViewById(R.id.megTxt);
//		artqrcode=(ArtQrCodeView)findViewById(R.id.artqrcode);
	}
}
