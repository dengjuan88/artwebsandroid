package cn.artwebs.demo;

import cn.artwebs.utils.FileUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class InstallApkActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransmitExample trans=new TransmitExample("test/");
        int rsInt=trans.downFile("LHBSystem_1/UpFiles/artcode.apk", "", "artcode.apk");
		Log.i("Install","���ؽ��:"+rsInt);
		FileUtils fileUtils=new FileUtils();
		Log.i("Install",fileUtils.getSDPATH()+"artcode.apk");
		fileUtils.installApk(this, fileUtils.getSDPATH()+"artcode.apk");
	}
}
