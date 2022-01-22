package cn.artwebs.androiddemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by artwebs on 14-10-9.
 */
public class OtherAppActivity extends Activity {
    private LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout=(LinearLayout)getLayoutInflater().inflate(R.layout.activity_main, null);
        this.setContentView(mainLayout);
        Button btn=new Button(this);
        btn.setText("点击调用");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("net.zcline.zctechdemo","net.zcline.zctechdemo.MainActivity"));
                startActivity(intent);
            }
        });
        mainLayout.addView(btn);
    }
}
