package cn.artwebs.UI.DataParseXML;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import cn.artwebs.UI.IDataParse;
import cn.artwebs.object.BinMap;
import cn.artwebs.transmit.ITransmit;
import cn.artwebs.utils.Utils;


public abstract class AbsDataParse implements IDataParse {
	private final static String tag="AbsDataParse";
	protected BinMap para=new BinMap();
	protected ContentHandler contentHandler =new DefaultHandler(){
		private StringBuffer sb=new StringBuffer();
		public void startElement(String namespaceURI, String localName,
				String qName, Attributes attr) throws SAXException {
			sb.delete(0, sb.length());
			if(localName.equals("doc"))
			AbsDataParse.this.para.put("type", attr.getValue("type"));
		}

		public void endElement(String namespaceURI, String localName, String qName)
				throws SAXException {	
			
			if (AbsDataParse.this.textElement.indexOf(localName)>=0)
				AbsDataParse.this.para.put(localName, sb.toString());
		}
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			sb.append(new String(ch, start, length));
		}
	};
	protected ArrayList<String> textElement=new ArrayList<String>();
	protected String dataStr;
	private ITransmit transmit;
	
	public AbsDataParse()
	{
		this.setTextElement();
	}
	
	public AbsDataParse(String dataStr)
	{
		this.setTextElement();
		this.dataStr=dataStr;
	}
		
	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}

	public ITransmit getTransmit() {
		return transmit;
	}

	public void setTransmit(ITransmit transmit) {
		this.transmit = transmit;
	}

	
	
	public void setPara(BinMap para) {
		this.para = para;
	}

	public void setTextElement()
	{
		textElement.add("count");
		textElement.add("code");
		textElement.add("message");
		textElement.add("type");
		textElement.add("rsid");
		textElement.add("rstext");
	}
	
	public void appendTextElement(String name)
	{
		textElement.add(name);
	}
	
	public abstract void newInstance();
	
	@Override
	public BinMap parse()
	{
		this.newInstance();
		Utils.parseXMLBySAX(this.dataStr, this.contentHandler);
		Log.d(tag,this.para.getItem().toString());
		return this.para;
	}

}
