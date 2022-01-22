package cn.artwebs.UI;


import cn.artwebs.UI.DataParseXML.DataDefault;
import cn.artwebs.object.BinMap;
import cn.artwebs.transmit.ITransmit;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class UIFactory implements IUIFactory {
	private final static String tag="UIFactory";
	protected CodeUI uiobj;
	private String dataStr;
	private Activity mainActity;
	protected String type=null;
	private OnItemClickListener onItemClickListener;
	private ITransmit transmit;
	protected IDataParse parseobj;
	private BinMap para;
	protected IUIFactory uiFactory;
	
	public UIFactory()
	{}
	public UIFactory(ITransmit transmit)
	{
		this.transmit=transmit;
	}
	public UIFactory(String type)
	{
		this.type=type;
	}
	
	public UIFactory(String type,ITransmit transmit)
	{
		this.type=type;
		this.transmit=transmit;
	}
	
	public CodeUI getUiobj() {
		return uiobj;
	}

	public void setUiobj(CodeUI uiobj) {
		this.uiobj = uiobj;
	}

	public String getDataStr() {
		return dataStr;
	}
	public void setDataStr(String xmlStr) {
		this.dataStr = xmlStr;
	}
	
	public Activity getMainActity() {
		return mainActity;
	}
	public void setMainActity(Activity mainActity) {
		this.mainActity = mainActity;
	}
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}
		
	public void setTransmit(ITransmit transmit) {
		this.transmit = transmit;
	}
	
	
	@SuppressWarnings("unchecked")
	public void uiIntance()
	{
		Log.i(tag,"com.artwebsandroid.UI.CodeUI"+this.type.substring(0,1).toUpperCase()+this.type.substring(1));
		this.uiFactory=new UIFactory(); 
		Class<CodeUI> objclass=null;
		try {
			objclass=(Class<CodeUI>)Class.forName("com.artwebsandroid.UI.CodeUI"+this.type.substring(0,1).toUpperCase()+this.type.substring(1));
			uiobj=(CodeUI)objclass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}	
	}
	
	public void parseInstance()
	{
		Class<IDataParse> objclass=null;
		try {
			objclass=(Class<IDataParse>)Class.forName("com.artwebsandroid.UI.DataParseXML.Data"+this.type.substring(0,1).toUpperCase()+this.type.substring(1));
			parseobj=(IDataParse)objclass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	
	public void parseData()
	{	
		parseobj=new DataDefault();
		if(this.type==null)
		{
			this.parseobj.setDataStr(dataStr);
			this.para=this.parseobj.parse();
			this.type=this.para.getValue("type").toString();
		}			
		if(this.type==null)
		{		
			this.parseobj.setPara(CodeUI.setErrorPara("��ȡ���ʧ��"));
			return;
		}
			
		this.parseInstance();
		this.parseobj.setDataStr(this.dataStr);
		this.parseobj.setTransmit(this.transmit);
		this.para=this.parseobj.parse();
	}
	
	public void parseData(String commend)
	{
		if(transmit!=null)
		{
			this.dataStr=transmit.download(commend);
		}	
		this.parseData();
	}
	
	public BinMap getMap()
	{
		return this.para;
	}
	
	public View dranView(Activity activity)
	{
		return this.drawnView(activity,null,null);
	}
	
	public View dranView(Activity activity,String commend)
	{
		return this.drawnView(activity,commend,null,null);
	}
	
	public View drawnView(Activity activity,String commend,Integer parentid,Integer id)
	{
		this.parseData(commend);
		this.uiIntance();
		this.uiobj.setTransmit(this.transmit);
		this.uiobj.setUiFactory(this.uiFactory);
		this.uiobj.setPara(this.para);
		this.uiobj.setOnItemClickListener(onItemClickListener);
		return this.uiobj.drawnView(activity,parentid,id);
	}
	
	public View drawnView(Activity activity,Integer parentid,Integer id)
	{
		this.parseData();
		this.uiIntance();
		this.uiobj.setTransmit(this.transmit);
		this.uiobj.setUiFactory(this.uiFactory);
		this.uiobj.setPara(this.para);
		this.uiobj.setOnItemClickListener(onItemClickListener);
		return this.uiobj.drawnView(activity,parentid,id);
	}
	
}
