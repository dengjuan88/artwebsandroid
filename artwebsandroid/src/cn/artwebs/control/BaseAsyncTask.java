package cn.artwebs.control;

import android.content.Context;
import android.os.AsyncTask;

import cn.artwebs.object.BinMap;
import cn.artwebs.utils.AndroidUtils;


public abstract class BaseAsyncTask {
	protected Context context;
	protected AsyncTask<Void,Void,BinMap> task=new AsyncTask<Void,Void,BinMap>(){

		@Override
		protected BinMap doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return doRun();
		}
		@Override
		protected void onPostExecute(BinMap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);	
			doUpdate(result);
		}
	};
	
	public abstract BinMap  doRun();
	
	public void  doUpdate(BinMap result)
	{
		AndroidUtils.commDialog(context, "",result.getValue("message").toString());
		this.hide();
	}
	
	public abstract void start();
	
	public abstract void show();
	
	public abstract void hide();
	
	public void stop()
	{
		this.task.cancel(true);
	}
}
