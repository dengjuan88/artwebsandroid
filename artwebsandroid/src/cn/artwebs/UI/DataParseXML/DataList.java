package cn.artwebs.UI.DataParseXML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.artwebs.object.BinList;

public class DataList extends AbsDataParse{
	String tagName;
	boolean newRow=false;
	protected BinList rows=new BinList();
	
	@Override
	public void newInstance() {
		super.setTextElement();
		this.contentHandler=new DefaultHandler(){
			private StringBuffer sb=new StringBuffer();
			
			public void startElement(String namespaceURI, String localName,
					String qName, Attributes attr) throws SAXException {
				tagName = localName;
				sb.delete(0, sb.length());
			}

			public void endElement(String namespaceURI, String localName, String qName)
					throws SAXException {	
				if(localName.equals("item"))newRow=false;
				if(localName.equals("doc"))DataList.this.para.put("rows", rows);
			}
			
			
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				sb.append(new String(ch, start, length));
				if (DataList.this.textElement.indexOf(tagName)>=0)
					DataList.this.para.put(tagName,sb.toString());
				else if(tagName.equals("text")||tagName.equals("id")||tagName.equals("findkey"))
				{			
					rows.put(newRow, tagName, sb.toString());
					newRow=true;
				}			
				
			}
		};
		
	}

}
