package cn.artwebs.AsyncImageLoader;

import cn.artwebs.transmit.ITransmit;

import android.content.Context;
import android.graphics.drawable.Drawable;


public interface IAsyncImageLoader {
	public void setRootPath(String path);
	public Drawable loadDrawable(final String imageUrl,final ImageCallback callback,ITransmit trans);
}
