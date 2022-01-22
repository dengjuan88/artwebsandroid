package cn.artwebs.UI.DataParseJSON;

import java.io.InputStream;

import cn.artwebs.object.BinMap;
import cn.artwebs.socket.ClientTCP;
import cn.artwebs.transmit.ITransmit;


import android.content.Context;
import android.os.Handler;
import android.test.AndroidTestCase;
import android.util.Log;

public class DataParseTest extends AndroidTestCase{
	private ITransmit transmit=new ITransmit(){
		private String skip="test";
		private ClientTCP sok;
				
		@Override
		public void setSkip(String skip) {
			this.skip=skip;
		}

		@Override
		public String getSkip() {
			return this.skip;
		}

		@Override
		public String download(String commend) {
			sok=new ClientTCP("10.0.2.2",3456);		
			return sok.download(this.skip+commend);
		}

		@Override
		public int downFile(String commend, String path, String fileName) {
			sok=new ClientTCP("10.0.2.2",3456);	
			Log.i("Trans",this.skip+commend);
			return sok.downFile(this.skip+commend, path, fileName);
		}

		@Override
		public String getHost() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setHost(String host) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public InputStream downStream(String commend) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int downFile(String commend, String path, String fileName,
				Handler handler) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String download(String commend, int size) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String download(String commend, String end) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void close() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public byte[] download(byte[] commend, int size) {
			// TODO Auto-generated method stub
			return null;
		}

	

	};
	
	public void testParseList()
	{
		AbsDataParse parse=new DataList();
		String in=this.transmit.download("/movie/movie");
		parse.setDataStr(in);
		BinMap rs=parse.parse();
		
		Log.i("parse",in);
		Log.i("parse", rs.getItem().toString());
	}
}
