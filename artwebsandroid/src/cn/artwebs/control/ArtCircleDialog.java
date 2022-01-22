package cn.artwebs.control;

import cn.artwebs.comm.DialogStyle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

public class ArtCircleDialog {
	protected  ProgressDialog progressDialog = null;
	private  Activity window;
	public ArtCircleDialog(Activity window)
	{
		this.window=window;
	}
	
	
	
	public void show(DialogStyle style)
	{
		this.show(style,null);
	}
	
	public void show(DialogStyle style,OnCancelListener cancel)
	{
		if(progressDialog==null)
		{
			if(style==DialogStyle.none)return;
			progressDialog = ProgressDialog.show(window,style.getTitle(), style.getContent(), true);
			progressDialog.setCancelable(true);
			progressDialog.setCanceledOnTouchOutside(false);
			if(cancel==null)
				progressDialog.setOnCancelListener(selfcancel);
			else
				progressDialog.setOnCancelListener(cancel);
			window.runOnUiThread(new Runnable(){

				@Override
				public void run() {
					progressDialog.show();
				}});
		}else
		{
			if(progressDialog.isShowing())
				this.close();
		}
			
		
	}
	
	public  void close()
	{
		if(window!=null){
			window.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if(progressDialog!=null)
					{
						progressDialog.dismiss();
						progressDialog=null;
					}
					
				}
			});
		}
			
		
	}
	
	private OnCancelListener selfcancel = new OnCancelListener() 
	{
		@Override
		public void onCancel(DialogInterface dialog) 
		{
			
		}
	};
}
