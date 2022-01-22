package cn.artwebs.control;

import cn.artwebs.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ScratchSardView extends TextView {
	private final static String tag="ScratchSardView";
	private int widget, height;

	private Context mContext;
	private Paint mPaint;
	private Canvas tempCanvas;
	private Bitmap mBitmap;
	private float x, y, ox, oy;
	private Path mPath;
	Handler mHandler;
	MyThread mThread;
	int messageCount;
	int[] pixels;
	int color = Color.rgb(0xD6,0xD6,0xD6);
	private ArtOnTuchListener listener;
	private boolean isBegin=false;

	public ScratchSardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		
	}

	/**
	 * 再一次抽奖
	 */
	public void againLotter() {
//		Color.rgb(red, green, blue)
		isBegin=false;
		messageCount = 0;
		tempCanvas.drawColor(color);
		setGravity(Gravity.CENTER);
		setBackgroundColor(Color.WHITE);
	}
	
	public void setArtOnTuchListener(ArtOnTuchListener listener)
	{
		this.listener=listener;
	}
	

	public void init() {

		widget=this.getWidth();
		height=this.getHeight();
		// 初始化路径
		mPath = new Path();

		// 初始化画笔
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setAlpha(0);
		mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(10);//画笔宽度

		// 初始化Bitmap并且锁定到临时画布上
		mBitmap = Bitmap.createBitmap(widget, height, Bitmap.Config.ARGB_4444);
		tempCanvas = new Canvas();
		tempCanvas.setBitmap(mBitmap);
		againLotter();

		// 在字线程中创建Handler接收像素消息
		mThread = new MyThread();
		mThread.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(tempCanvas==null)init();
		// 将处理过的bitmap画上去
		canvas.drawBitmap(mBitmap, 0, 0, null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			touchDown(event);
			break;
		case MotionEvent.ACTION_MOVE:
			touchMove(event);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}

	/**
	 *  移动的时候
	 * @param event
	 */
	private void touchMove(MotionEvent event) {
		if(!isBegin&&listener!=null)
		{
			listener.toBengin();
			isBegin=true;
		}
		x = event.getX();
		y = event.getY();
		// 二次贝塞尔，实现平滑曲线；oX, oY为操作点 x,y为终点 
		mPath.quadTo((x + ox) / 2, (y + oy) / 2, x, y);
		tempCanvas.drawPath(mPath, mPaint);
		ox = x;
		oy = y;
		invalidate();
		computeScale();
	}
	/**
	 * 第一次按下来
	 * 
	 * @param event
	 */
	private void touchDown(MotionEvent event) {
		ox = x = event.getX();
		oy = y = event.getY();
		mPath.reset();
		mPath.moveTo(ox, oy);
	}
	/**
	 * 计算百分比
	 */
	private void computeScale() {
		Message msg = mHandler.obtainMessage(0);
		msg.obj = ++messageCount;
		mHandler.sendMessage(msg);
	}
	
	/**
	 * 异步线程，作用是创建handler接收处理消息。
	 * @author Administrator
	 *
	 */
	class MyThread extends Thread {

		public MyThread() {
		}

		@Override
		public void run() {
			/*
			 * 创建 handler前先初始化Looper.
			 */
			Looper.prepare();

			mHandler = new Handler() {
				@Override
				public void dispatchMessage(Message msg) {
					super.dispatchMessage(msg);
					// 只处理最后一次的百分比
					if ((Integer) (msg.obj) != messageCount) {
						return;
					}
					// 取出像素点
					synchronized (mBitmap) {
						if (pixels == null) {
							pixels = new int[mBitmap.getWidth()
									* mBitmap.getHeight()];
						}
						mBitmap.getPixels(pixels, 0, widget, 0, 0, widget,
								height);
					}

					int sum = pixels.length;
					int num = 0;
					for (int i = 0; i < sum; i++) {
						if (pixels[i] == 0) {
							num++;
						}
					}
				}
			};
			/*
			 * 启动该线程的消息队列
			 */
			Looper.loop();
		}
	}
	
	public interface ArtOnTuchListener{
		public void toBengin();
	}
	
}
