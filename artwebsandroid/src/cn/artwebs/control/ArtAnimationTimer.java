package cn.artwebs.control;

import android.os.Handler;

public class ArtAnimationTimer {
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private boolean bool;
	private ArtAnimationTimerDelegate callObj;
	
	public ArtAnimationTimer(ArtAnimationTimerDelegate obj)
	{
		callObj=obj;
	}
	
	public void start()
	{
		bool = true;
		handler.postDelayed(task, 1000);
	}
	
	public void stop()
	{
		bool = false;
	}
	
	/*
	 * 定时器设置，实现计时
	 */
	private Handler handler = new Handler();
	private Runnable task = new Runnable() {
		public void run() {
			if (bool) {
				handler.postDelayed(this, 1000);
				second++;
				if (second >= 60) {
					minute++;
					second = second % 60;
				}
				if (minute >= 60) {
					hour++;
					minute = minute % 60;
				}
				if(callObj!=null)callObj.timerRetrun(format(hour) + ":" + format(minute) + ":"
						+ format(second));
			}
		}
	};
	
	public interface ArtAnimationTimerDelegate
	{
		public void timerRetrun(String timeStr);
	}
	
	/*
	 * 格式化时间
	 */
	private String format(int i) {
		String s = i + "";
		if (s.length() == 1) {
			s = "0" + s;
		}
		return s;
	}
}
