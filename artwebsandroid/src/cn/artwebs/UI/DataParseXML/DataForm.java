package cn.artwebs.UI.DataParseXML;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import cn.artwebs.object.BinList;

public class DataForm extends AbsDataParse{
	private boolean newRow=false;
	private boolean newItem=false;
	private BinList rows=new BinList();
	private BinList items=new BinList();
	private ArrayList<String> rowElement=new ArrayList<String>();
	private ArrayList<String> itemElement=new ArrayList<String>();
	
	@Override
	public void newInstance() {
		super.setTextElement();
		this.appendTextElement("title");
		this.rowElement.add("NAME");
		this.rowElement.add("CNAME");
		this.rowElement.add("VALUE");
		this.rowElement.add("DICVALUE");
		this.rowElement.add("DISPLAY");
		this.rowElement.add("MATCHE");
		this.rowElement.add("CONMETHOD");
		this.rowElement.add("CONURL");
		
		this.itemElement.add("text");
		this.itemElement.add("id");
		this.itemElement.add("findkey");
		
		this.contentHandler=new DefaultHandler(){
			private StringBuffer sb=new StringBuffer();
			
			public void startElement(String namespaceURI, String localName,
					String qName, Attributes attr) throws SAXException {
				sb.delete(0, sb.length());
			}

			public void endElement(String namespaceURI, String localName, String qName)
					throws SAXException {	
				if(localName.equals("item"))newItem=false;
				if(localName.equals("row"))newRow=false;	
				if(localName.equals("ITEMS"))
				{
					rows.put(newRow, "ITEMS", items);
					items=new BinList();
				}
				
				
				if (DataForm.this.textElement.indexOf(localName)>=0)
					DataForm.this.para.put(localName, sb.toString());
				else if(DataForm.this.itemElement.indexOf(localName)>=0)
				{
					items.put(newItem, localName, sb.toString());
					newItem=true;
				}else if(DataForm.this.rowElement.indexOf(localName)>=0)
				{
					rows.put(newRow, localName, sb.toString());
					newRow=true;
				}
				
				if(localName.equals("doc"))DataForm.this.para.put("rows", rows);
			}
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				sb.append(new String(ch, start, length));
			}
		};
		
	}

}
