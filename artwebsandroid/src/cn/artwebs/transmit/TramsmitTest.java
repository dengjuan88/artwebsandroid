package cn.artwebs.transmit;

import cn.artwebs.demo.TransmitExample;

import android.test.AndroidTestCase;
import android.util.Log;

public class TramsmitTest extends AndroidTestCase {
	private TransmitExample trans=new TransmitExample("test/");
	public void testDownFile()
	{
		int rsInt=trans.downFile("LHBSystem_1/UpFiles/artcode.apk", "", "artcode.apk");
		Log.i("Trans","���ؽ��:"+rsInt);
	}
	
	
}
