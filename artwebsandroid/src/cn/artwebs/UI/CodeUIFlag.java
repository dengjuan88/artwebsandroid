package cn.artwebs.UI;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class CodeUIFlag extends CodeUI {

	@Override
	public View drawnView(Activity activity, Integer parentid, Integer id) {
		TextView text=new TextView(activity);
		text.setText(this.para.getValue("message").toString());
		return text;
	}
	
	
}
