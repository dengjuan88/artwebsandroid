package cn.artwebs.AsyncImageLoader;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CallbackImpl implements ImageCallback{
	private ImageView imageView ;
	
	public CallbackImpl(ImageView imageView) {
		super();
		this.imageView = imageView;
	}

	@Override
	public void imageLoaded(Drawable imageDrawable) {
		imageView.setBackgroundDrawable(imageDrawable);
	}

}
