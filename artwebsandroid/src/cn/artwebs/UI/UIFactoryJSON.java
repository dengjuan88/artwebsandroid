package cn.artwebs.UI;

public class UIFactoryJSON extends UIFactory {
	public void uiIntance()
	{
		super.uiIntance();
		this.uiFactory=new UIFactoryJSON();
	}
	
	public void parseInstance()
	{
		Class<IDataParse> objclass=null;
		try {
			objclass=(Class<IDataParse>)Class.forName("com.artwebsandroid.UI.DataParseJSON.Data"+this.type.substring(0,1).toUpperCase()+this.type.substring(1));
			parseobj=(IDataParse)objclass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
}
