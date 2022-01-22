package cn.artwebs.utils;

import junit.framework.Assert;
import android.test.AndroidTestCase;
import android.util.Log;

public class UtilsTest extends AndroidTestCase  {
	public void testDownload()
	{
		HttpDownloader hdl=new HttpDownloader();
		String rs=hdl.download("http://www.jycw.net/LHBSystem_1/index.php?mod=examplexml&act=glist&source=1");
		Log.i("log", rs);
		Assert.assertEquals("<?xml version=\"1.0\" encoding=\"utf-8\"?><root type=\"list\"><count>3</count><return>00</return><value><row><first>���� ��2012-03-16</first><second>id=1,sex=1</second></row><row><first>���� ��2012-03-16</first><second>id=3,sex=1</second></row><row><first>1 ��</first><second>id=5,sex=1</second></row></value><redirect id=\"info\" text=\"��ϸ��ѯ\">/LHBSystem_1/index.php?act=info#ANDmod=examplexml</redirect></root>", rs);
	}
}
