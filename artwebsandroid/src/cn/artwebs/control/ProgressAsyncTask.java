package cn.artwebs.control;


import android.R.attr;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

public abstract class ProgressAsyncTask extends BaseAsyncTask {
	private ProgressBar progressBar=null;

	public ProgressBar getProgressBar() {
		return progressBar;
	}
	
	public ProgressAsyncTask(Context context)
	{
		this.context=context;
		this.progressBar=new ProgressBar(this.context);
		this.progressBar.setScrollBarStyle(attr.progressBarStyleSmall);
	}
	
	public void start()
	{
		this.show();
		this.task.execute();
	}

	@Override
	public void show() {
		this.progressBar.setVisibility(View.VISIBLE);
		
	}

	@Override
	public void hide() {
		this.progressBar.setVisibility(View.GONE);
	}
	
	
}
