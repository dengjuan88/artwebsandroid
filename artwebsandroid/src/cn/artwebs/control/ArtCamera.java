package cn.artwebs.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.artwebs.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Bitmap.CompressFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArtCamera extends RelativeLayout {
	private final static String tag="ArtCamera";
	private int cammeraIndex;
	private Button mVideoStartBtn;
	private SurfaceView mSurfaceview;
	private ImageView mimageview;
	private MediaRecorder mMediaRecorder;
	private SurfaceHolder mSurfaceHolder;
	private File mRecVedioPath;
	private File mRecAudioFile;
	private TextView timer;
	private int hour = 0;
	private int minute = 0;
	private int second = 0;
	private boolean bool;
	
	private Camera camera;
	private boolean isPreview;
	private SurfaceHolder holder;
	private Context context;
	private String savePath;
	private SaveCallBack callBackObj;
	private boolean isRecording = true;
	private int displayRotate=0;
	private Camera c;
	
	public enum CAMERATYPE{FRONT,BACK}
	public enum OPERATE{IMAGE,VIDEO,AUDIO}
	private CAMERATYPE cameraType;
	public ArtCamera(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	
	public ArtCamera(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.artcamera, this);
		mSurfaceview=(SurfaceView)findViewById(R.id.video_view);
		mimageview=(ImageView)findViewById(R.id.image_view);
		timer=(TextView)findViewById(R.id.video_timer);
		timer.setText("");
		// ??????????????????
		holder = mSurfaceview.getHolder();
		
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		timer.setVisibility(View.VISIBLE);
	}
	
	
	
	public String getSavePath() {
		if(savePath==null)savePath="/";
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
		// ??????????????????
		mRecVedioPath = new File(savePath+ "/temp/");
		if (!mRecVedioPath.exists()) {
			mRecVedioPath.mkdirs();
		}
	}
	
	public void setSaveCallBack(SaveCallBack obj)
	{
		this.callBackObj=obj;
	}
	
	@SuppressLint("NewApi")
	public void getCamera()
	{
		if(camera!=null)
		{
			camera.stopPreview();
			camera.release();
			camera = null;
		}
		try {
			camera = Camera.open(cammeraIndex);
			setCameraDisplayOrientation ();
			Camera.Parameters parameters = camera.getParameters();
			parameters.setPreviewFrameRate(5); // ??????5???
			parameters.setPictureFormat(PixelFormat.JPEG);// ???????????????????????????
			parameters.set("jpeg-quality", 85);// ????????????
			camera.setParameters(parameters);
			camera.setPreviewDisplay(mSurfaceHolder);
			camera.startPreview();
			isPreview = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void show(CAMERATYPE type,OPERATE opt)
	{
		switch(opt)
		{
		case IMAGE:
			mSurfaceview.setVisibility(View.VISIBLE);
			break;
		case VIDEO:
			mSurfaceview.setVisibility(View.VISIBLE);
			break;
		case AUDIO:
			mimageview.setVisibility(View.VISIBLE);
			break;
		}
		if(type==CAMERATYPE.FRONT)
		{
			cammeraIndex=FindFrontCamera();
		}
		if(cammeraIndex==-1)cammeraIndex=FindBackCamera();
		
		holder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if (camera != null) {
					if (isPreview) {
						camera.stopPreview();
						isPreview = false;
					}
					camera.release();
					camera = null; // ????????????
				}
				mSurfaceview = null;
				mSurfaceHolder = null;
				mMediaRecorder = null;
			}

			@SuppressLint("NewApi")
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				try {
					camera = Camera.open(cammeraIndex);
					setCameraDisplayOrientation ();
					Camera.Parameters parameters = camera.getParameters();
					parameters.setPreviewFrameRate(5); // ??????5???
					parameters.setPictureFormat(PixelFormat.JPEG);// ???????????????????????????
					parameters.set("jpeg-quality", 85);// ????????????
					camera.setParameters(parameters);
					camera.setPreviewDisplay(holder);
					camera.startPreview();
					isPreview = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				mSurfaceHolder = holder;
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				mSurfaceHolder = holder;
			}
		});
	}

	public void show(CAMERATYPE type)
	{
		show(type,OPERATE.IMAGE);
	}
	
	
	public void saveImage()
	{
		
		if (camera != null) {
			camera.autoFocus(null);
			camera.takePicture(null, null, new PictureCallback() {
				@Override
				public void onPictureTaken(byte[] data, Camera camera) {
					BitmapFactory.Options opts = new BitmapFactory.Options();  
					opts.inJustDecodeBounds = true;  
					BitmapFactory.decodeByteArray(data, 0, data.length, opts);
					  
					opts.inSampleSize = computeSampleSize(opts, -1, 1024*1024);  
					opts.inJustDecodeBounds = false;  
					Bitmap bitmap =BitmapFactory.decodeByteArray(data, 0, data.length, opts);
					Matrix matrix = new Matrix();
					// ????????????
					if(cameraType==CAMERATYPE.FRONT)
						matrix.postRotate(360-displayRotate);
					else
						matrix.postRotate(displayRotate);
					matrix.postScale(1f, 1f);
					bitmap = Bitmap.createBitmap(bitmap, 0, 0,
							bitmap.getWidth(), bitmap.getHeight(),
							matrix, true);

					final String fileName = new SimpleDateFormat(
							"yyyyMMddHHmmss").format(new Date())
							+ ".jpg";
					File out = new File(savePath);
					if (!out.exists()) {
						out.mkdirs();
					}
					out = new File(savePath, fileName);
					try {
						FileOutputStream outStream = new FileOutputStream(
								out);
						bitmap.compress(CompressFormat.JPEG, 100,
								outStream);
						outStream.close();
						camera.startPreview();
						if (bitmap != null && !bitmap.isRecycled())
				            bitmap.recycle();
						if(callBackObj!=null)callBackObj.onGetFileName(savePath+ fileName,OPERATE.IMAGE);
					} catch (Exception e) {
						e.printStackTrace();
						if(callBackObj!=null)callBackObj.onGetError(e);
					}finally{
						
					}
				}
			}); // ??????
		}
	}
	
	@SuppressLint("NewApi")
	public void saveVideo()
	{
		if (isRecording) {
			/*
			 * ??????????????????
			 */
			if (isPreview) {
				camera.stopPreview();
				camera.release();
				camera = null;
			}
			second = 0;
			minute = 0;
			hour = 0;
			bool = true;
			if (mMediaRecorder == null)
				mMediaRecorder = new MediaRecorder();
			else
				mMediaRecorder.reset();
			c = Camera.open(cammeraIndex);
            c.setDisplayOrientation(displayRotate);
            c.unlock();
            mMediaRecorder.setCamera(c);
			mMediaRecorder.setPreviewDisplay(mSurfaceHolder
					.getSurface());
			mMediaRecorder
					.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			mMediaRecorder
					.setAudioSource(MediaRecorder.AudioSource.MIC);
			mMediaRecorder
					.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			mMediaRecorder
					.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
			mMediaRecorder
					.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			mMediaRecorder.setVideoSize(320, 240);
			mMediaRecorder.setVideoFrameRate(15);
			// ????????????
			if(cameraType==CAMERATYPE.FRONT)
				mMediaRecorder.setOrientationHint(360-displayRotate);
			else
				mMediaRecorder.setOrientationHint(displayRotate);
			try {
				mRecAudioFile = File.createTempFile("Vedio", ".3gp",
						mRecVedioPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mMediaRecorder.setOutputFile(mRecAudioFile
					.getAbsolutePath());
			try {
				mMediaRecorder.prepare();
				timer.setVisibility(View.VISIBLE);
				handler.postDelayed(task, 1000);
				mMediaRecorder.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
//			showMsg("????????????");
//			mVideoStartBtn.setBackgroundDrawable(iconStop);
			isRecording = !isRecording;
		} else {
			/*
			 * ????????????
			 */
			try {
				bool = false;
				mMediaRecorder.stop();
				timer.setText(format(hour) + ":" + format(minute) + ":"
						+ format(second));
				mMediaRecorder.release();
				mMediaRecorder = null;
				videoRename(OPERATE.VIDEO);
				c.stopPreview();
				c.release();
				c = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			isRecording = !isRecording;
//			mVideoStartBtn.setBackgroundDrawable(iconStart);
			
//			showMsg("????????????????????????");
			timer.setVisibility(View.INVISIBLE);
			getCamera();
		}
	}
	
	
	public void saveAudio()
	{
		if (isRecording) {
			/*
			 * ??????????????????
			 */
			
			second = 0;
			minute = 0;
			hour = 0;
			bool = true;
			if (mMediaRecorder == null)
				mMediaRecorder = new MediaRecorder();
			else
				mMediaRecorder.reset();
			 mMediaRecorder = new MediaRecorder();
	         
		    /* setAudioSource/setVedioSource*/
	        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//???????????????
	         
	        /* ??????????????????????????????THREE_GPP/MPEG-4/RAW_AMR/Default
	         * THREE_GPP(3gp?????????H263??????/ARM????????????)???MPEG-4???RAW_AMR(???????????????????????????????????????AMR_NB)
	         */
	        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
	          
	         /* ??????????????????????????????AAC/AMR_NB/AMR_MB/Default */
	         mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			try {
				mRecAudioFile = File.createTempFile("Vedio", ".amr",
						mRecVedioPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mMediaRecorder.setOutputFile(mRecAudioFile
					.getAbsolutePath());
			try {
				mMediaRecorder.prepare();
				timer.setVisibility(View.VISIBLE);
				handler.postDelayed(task, 1000);
				mMediaRecorder.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
//			showMsg("????????????");
//			mVideoStartBtn.setBackgroundDrawable(iconStop);
			isRecording = !isRecording;
		} else {
			/*
			 * ????????????
			 */
			try {
				bool = false;
				mMediaRecorder.stop();
				timer.setText(format(hour) + ":" + format(minute) + ":"
						+ format(second));
				mMediaRecorder.release();
				mMediaRecorder = null;
				videoRename(OPERATE.AUDIO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			isRecording = !isRecording;
//			mVideoStartBtn.setBackgroundDrawable(iconStart);
			
//			showMsg("????????????????????????");

		}
	}
	
	@TargetApi(10)  
    private int FindFrontCamera(){  
        int cameraCount = 0;  
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();  
        cameraCount = Camera.getNumberOfCameras(); // get cameras number  
                
        for ( int camIdx = 0; camIdx < cameraCount;camIdx++ ) {  
            Camera.getCameraInfo( camIdx, cameraInfo ); // get camerainfo  
            if ( cameraInfo.facing ==Camera.CameraInfo.CAMERA_FACING_FRONT ) {  
                // ????????????????????????????????????????????????????????????CAMERA_FACING_FRONT?????????CAMERA_FACING_BACK??????  
               return camIdx;  
            }  
        }  
        return -1;  
    } 
	
    @TargetApi(10)  
    private int FindBackCamera(){  
        int cameraCount = 0;  
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();  
        cameraCount = Camera.getNumberOfCameras(); // get cameras number  
                
        for ( int camIdx = 0; camIdx < cameraCount;camIdx++ ) {  
            Camera.getCameraInfo( camIdx, cameraInfo ); // get camerainfo  
            if ( cameraInfo.facing ==Camera.CameraInfo.CAMERA_FACING_BACK ) {  
                // ????????????????????????????????????????????????????????????CAMERA_FACING_FRONT?????????CAMERA_FACING_BACK??????  
               return camIdx;  
            }  
        }  
        return -1;  
    }  

	public void onBackPressed() {
		if (mMediaRecorder != null) {
			mMediaRecorder.stop();
			mMediaRecorder.release();
			mMediaRecorder = null;
		}
	}
	@TargetApi(10)  
	private void setCameraDisplayOrientation () {
	     android.hardware.Camera.CameraInfo info =
	              new android.hardware.Camera.CameraInfo();
	     android.hardware.Camera.getCameraInfo ( cammeraIndex , info );
	      int rotation = ((Activity)context).getWindowManager ().getDefaultDisplay ().getRotation ();
	      int degrees = 0 ;
	      switch ( rotation ) {
	          case Surface.ROTATION_0 : degrees = 0 ; break ;
	          case Surface.ROTATION_90 : degrees = 90 ; break ;
	          case Surface.ROTATION_180 : degrees = 180 ; break ;
	          case Surface.ROTATION_270 : degrees = 270 ; break ;
	      }
	 
	      int result ;
	      if ( info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT ) {
	    	 cameraType=CAMERATYPE.FRONT;
	         result = ( info.orientation + degrees ) % 360 ;
	         result = ( 360 - result ) % 360 ;   // compensate the mirror
	      } else {   // back-facing
	         result = ( info.orientation - degrees + 360 ) % 360 ;
	      }
	     displayRotate=result;
	     camera.setDisplayOrientation ( result );
	     Log.d(tag, "setCameraDisplayOrientation="+result);
	  }

	
	public interface SaveCallBack
	{
		public void onGetFileName(String fileName,OPERATE opt);
		public void onGetError(Exception e);
	}
	
	/*
	 * ??????video????????????
	 */
	protected void videoRename(OPERATE opt) {
		String ex="";
		switch(opt)
		{
		case VIDEO:
			ex=".3gp";
			break;
		case AUDIO:
			ex=".amr";
			break;
		default:
			ex=".3gp";
			break;
		}
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss")
		.format(new Date()) + ex;
		File out = new File(savePath);
		if (!out.exists()) {
			out.mkdirs();
		}
		out = new File(savePath, fileName);
		if (mRecAudioFile.exists())
		{
			mRecAudioFile.renameTo(out);
			if(callBackObj!=null)callBackObj.onGetFileName(savePath+ fileName,OPERATE.VIDEO);
			timer.setVisibility(View.VISIBLE);
		}
			
	}

	
	/*
	 * ??????????????????????????????
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
				timer.setText(format(hour) + ":" + format(minute) + ":"
						+ format(second));
			}
		}
	};
	
	/*
	 * ???????????????
	 */
	private String format(int i) {
		String s = i + "";
		if (s.length() == 1) {
			s = "0" + s;
		}
		return s;
	}
	
	public static int computeSampleSize(BitmapFactory.Options options,  
	        int minSideLength, int maxNumOfPixels) {  
	    int initialSize = computeInitialSampleSize(options, minSideLength,maxNumOfPixels);  
	  
	    int roundedSize;  
	    if (initialSize <= 8 ) {  
	        roundedSize = 1;  
	        while (roundedSize < initialSize) {  
	            roundedSize <<= 1;  
	        }  
	    } else {  
	        roundedSize = (initialSize + 7) / 8 * 8;  
	    }  
	  
	    return roundedSize;  
	}  
	  
	private static int computeInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {  
	    double w = options.outWidth;  
	    double h = options.outHeight;  
	  
	    int lowerBound = (maxNumOfPixels == -1) ? 1 :  
	            (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));  
	    int upperBound = (minSideLength == -1) ? 128 :  
	            (int) Math.min(Math.floor(w / minSideLength),  
	            Math.floor(h / minSideLength));  
	  
	    if (upperBound < lowerBound) {  
	        // return the larger one when there is no overlapping zone.  
	        return lowerBound;  
	    }  
	  
	    if ((maxNumOfPixels == -1) &&  
	            (minSideLength == -1)) {  
	        return 1;  
	    } else if (minSideLength == -1) {  
	        return lowerBound;  
	    } else {  
	        return upperBound;  
	    }  
	}  
	
}
