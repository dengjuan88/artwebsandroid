package cn.artwebs.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.R.integer;

public abstract class ArtPoolSingleFixed implements Runnable {
	private ExecutorService pool;
	private boolean isRun=false;
	private final Integer LOCK=1;
	
	
	public boolean isRun() {
		if(pool==null)pool=Executors.newFixedThreadPool(1);
		synchronized (LOCK) {
			return isRun;
		}
	}

	public void setRun(boolean isRun) {
		synchronized (LOCK) {
			this.isRun = isRun;
		}
	}

	public void startPool()
	{
		if(pool==null)pool=Executors.newFixedThreadPool(1);
		setRun(true);
		pool.submit(this);
	}
	
	public void stopPool()
	{
		setRun(false);
		if(pool!=null)
		{
			pool.shutdownNow();
			pool=null;
		}
	}
}
