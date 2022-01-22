package cn.artwebs.control;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public abstract class ArtOnTouchListener implements OnTouchListener {

	//计算点击的次数
	private int count;
	//第一次点击的时间 long型
	private long firstClick;
	//最后一次点击的时间
	private long lastClick;
	private int firstId;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			//如果第二次点击 距离第一次点击时间过长   那么将第二次点击看为第一次点击
			if(firstClick!=0 && firstId!=0 && System.currentTimeMillis()-firstClick>300){
				count = 0;
				firstId = 0;
			}
			count++;
			if(count==1){
				firstClick = System.currentTimeMillis();
				//记录第一次点得按钮的id
				firstId = v.getId();
			}else if(count==2){
				lastClick = System.currentTimeMillis();
				//两次点击小于300ms 也就是连续点击
				if(lastClick-firstClick<300){
					this.onDoubleClick();
				}
				
				clear();
			}
		}
		return false;
	}
	//清空状态
	private void clear(){
		count = 0;
		firstClick = 0;
		lastClick = 0;
		firstId = 0;
	}
	
	public abstract void onDoubleClick();

}
