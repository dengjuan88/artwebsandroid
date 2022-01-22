package cn.artwebs.UI.DataParseXML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.artwebs.object.BinList;

public class DataInfo extends AbsDataParse{
	private boolean newRow=false;
	private BinList rows=new BinList();
	
	@Override
	public void newInstance() {
		super.setTextElement();
		this.appendTextElement("keyvalue");
		
		this.contentHandler=new DefaultHandler(){
			private StringBuffer sb=new StringBuffer();
			public void startElement(String namespaceURI, String localName,
					String qName, Attributes attr) throws SAXException {
				sb.delete(0, sb.length());
			}

			public void endElement(String namespaceURI, String localName, String qName)
					throws SAXException {	
				if(localName.equals("item"))newRow=false;
				if(localName.equals("doc"))DataInfo.this.para.put("rows", rows);
				
				if (DataInfo.this.textElement.indexOf(localName)>=0)
					DataInfo.this.para.put(localName, sb.toString());
				else if(localName.equals("item"))
				{				
					rows.put(newRow, localName, sb.toString());
					newRow=true;
				}
			}
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				sb.append(new String(ch, start, length));
			}
		};
		
	}
	
}
