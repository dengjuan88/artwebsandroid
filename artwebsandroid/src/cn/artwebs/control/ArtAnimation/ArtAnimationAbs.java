package cn.artwebs.control.ArtAnimation;

import android.os.Handler;

public class ArtAnimationAbs<T> {
	private boolean bool;
	private ArtAnimationNumberDelegate<T> callObj;
    private T currentValue;
    private T startValue;
    private T tagValue;

    public void setStartValue(T startValue) {
        this.currentValue=startValue;
        this.startValue = startValue;
    }

    public void setTagValue(T tagValue) {
        this.tagValue = tagValue;
    }

    public ArtAnimationAbs(ArtAnimationNumberDelegate obj){
        callObj=obj;
    }

	public ArtAnimationAbs(ArtAnimationNumberDelegate obj, T startValue, T tagValue)
	{
		callObj=obj;
        this.currentValue=startValue;
        this.startValue=startValue;
        this.tagValue=tagValue;
	}


	
	public void start()
	{
		bool = true;
		handler.postDelayed(task, 50);
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
//			if (bool&&(tagValue>startValue?(currentValue<tagValue):(currentValue>tagValue))) {
            if(callObj!=null)
			if (bool&&callObj.isFinish(currentValue, startValue, tagValue)) {
				handler.postDelayed(this, 50);
//				currentValue+=(tagValue-startValue)/100;
				currentValue=callObj.intervalValue(currentValue,startValue,tagValue);
				callObj.toRetrun(currentValue);
			}
		}
	};
	
	public interface ArtAnimationNumberDelegate<T>
	{
        public boolean isFinish(T currentValue, T startValue, T tagValue);
        public T intervalValue(T currentValue,T startValue,T tagValue);
		public void toRetrun(T f);
	}
	

}
