package cn.artwebs.socket;

import junit.framework.Assert;


import android.test.AndroidTestCase;
public class SocketTest extends AndroidTestCase {
	public void testTCP()
	{
		ClientTCP client=new ClientTCP();
		client.setHost("116.55.248.21");
		client.setPort(6788);
		String rs=client.download("�������");
		Assert.assertEquals("111", rs);
	}
	
	public void testUDP()
	{
		ClientUDP client=new ClientUDP();
		client.setHost("116.55.248.21");
		client.setPort(6789);
		String rs=client.download("�������");
		Assert.assertEquals("111", rs);
	}
}
