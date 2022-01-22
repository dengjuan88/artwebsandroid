package cn.artwebs.UI.DataParseXML;

public class DataPagelist extends DataList{
	
	@Override
	public void newInstance() {
		this.appendTextElement("next");
		super.newInstance();
	}
}
