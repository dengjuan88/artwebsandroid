package cn.artwebs.control;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import cn.artwebs.control.submititem.SubmitItem;
import cn.artwebs.control.submititem.SubmitItem.ItemKey;
import cn.artwebs.control.submititem.SubmitItem.ItemValueType;
import cn.artwebs.object.BinList;
import cn.artwebs.object.BinMap;

import java.lang.reflect.Constructor;

public class SubmitView extends ScrollView {
	private Context context;
	private BinList source;
	private BinMap ctlMap;
	private TableLayout tblLayout;
    private SubmitItem.SubmitItemDelegate itemDelegate=null;
	
	
	public void setSource(BinList source) {
		this.source = source;
	}

    public void setItemDelegate(SubmitItem.SubmitItemDelegate itemDelegate) {
        this.itemDelegate = itemDelegate;
    }

    public SubmitView(Context context) {
		super(context,null);
	}

	
	public SubmitView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		
		ctlMap=new BinMap();
		tblLayout=new TableLayout(this.context);
		tblLayout.setColumnStretchable(1, true);
		this.addView(tblLayout);
	}
	
	public void reLoad()
	{
		if(source!=null)
		{
			ctlMap.clear();
			tblLayout.removeAllViews();
			for(int i=0;i<source.size();i++)
			{
				BinMap map=new BinMap();
				map.setItemByHashMap(source.getItem(i));
				
				
				SubmitItem itemObj=getItemObj(map);
				TableRow rowLayout=itemObj.buildItem(context, map);
				ctlMap.put(map.getValue(ItemKey.name.toString()).toString(), itemObj);
				if(map.containsKey(ItemKey.display.toString()))
					if("false".equals(map.getValue(ItemKey.display.toString())))continue;
				tblLayout.addView(rowLayout);
			}
			
		}
		
	}
	
	private SubmitItem getItemObj(BinMap map)
	{
		String type=ItemValueType.textBox.toString();
		if(map.containsKey(ItemKey.type.toString()))
		{
			type=map.getValue(ItemKey.type.toString()).toString();
		}
		
		Class<SubmitItem> objclass=null;
		SubmitItem itemobj=null;
		try{
			objclass=(Class<SubmitItem>)Class.forName("cn.artwebs.control.submititem.SubmitItem2"+type.substring(0,1).toUpperCase()+type.substring(1));
			Constructor constructor = objclass.getConstructor(); 
			itemobj=(SubmitItem)constructor.newInstance();
		}catch(Exception e)
		{
			try {
				objclass=(Class<SubmitItem>)Class.forName("cn.artwebs.control.submititem.SubmitItem2TextBox");
				Constructor constructor = objclass.getConstructor(); 
				itemobj=(SubmitItem)constructor.newInstance();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}	
		return itemobj;
	}
	
	
	public static void buildData(BinList para,BinList data ,int index)
	{
		if(data==null)return;
		if(index>data.size())return;
		BinMap mapData=new BinMap();
		mapData.setItemByHashMap(data.getItem(index));
		for(int i=0;i<para.size();i++)
		{
			for(int j=0;j<mapData.size();j++)
			{
				if(para.getValue(i, ItemKey.name.toString()).equals(mapData.getKey(j)))
				{
					para.put(i, ItemKey.value.toString(), mapData.getValue(j));
					mapData.remove(j);
					continue;
				}
			}
		}
		
	}
	
	public boolean isChanged() {
		BinMap rsMap=new BinMap();
		boolean isChanged=false;
		for(int i=0;i<ctlMap.size();i++)
		{
			SubmitItem item=(SubmitItem) ctlMap.getValue(i);
			if(item.isChanged())isChanged=true;
		}
		return isChanged;
	}
	
	
	public BinMap getResult()
	{
		BinMap rsMap=new BinMap();
		for(int i=0;i<ctlMap.size();i++)
		{
			SubmitItem item=(SubmitItem) ctlMap.getValue(i);
			rsMap.put(item.getName(), item.getValue());
		}
		return rsMap;
	}
	
	public BinMap getResult(boolean isChanged)
	{
		BinMap rsMap=new BinMap();
		for(int i=0;i<ctlMap.size();i++)
		{
			SubmitItem item=(SubmitItem) ctlMap.getValue(i);
			if(item.isChanged()!=isChanged)continue;
			rsMap.put(item.getName(), item.getValue());
		}
		return rsMap;
	}
	
	
}
