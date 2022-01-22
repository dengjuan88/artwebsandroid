package cn.artwebs.androiddemo;

import android.util.Log;
import cn.artwebs.service.ArtService;

/**
 * @author artwebs
 * <service android:name="cn.artwebs.androiddemo.ArtServiceTest"></service>
 * ArtServiceTest.start(this);
 */
public class ArtServiceTest extends ArtService {
	private final static String tag="ArtServiceTest";
	@Override
	public void serviceRun() {
		Log.w(tag, "ArtServiceTest=>run");
	}

}
