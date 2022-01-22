package cn.artwebs.object;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BinMap extends IBinObject {
	private ArrayList item=new ArrayList();
	public ArrayList getItem() {
		return item;
	}
	public BinMap setItemByHashMap(HashMap hm){
		Iterator it= hm.keySet().iterator();
		Object key=new Object();
		Object value=new Object();
		while(it.hasNext())
		{			
			key=(Object)it.next();
			value=hm.get(key);
			this.put(key, value);
		}
		return this;
	}
	public Object getKey(int i) {		
		HashMap hm=(HashMap)item.get(i);
		Iterator it= hm.keySet().iterator();
		Object key=new Object();
		while(it.hasNext())
		{			
			key=(Object)it.next();
		}
		return key;
	}
	
	public String getKeyString(int i)
	{
		Object key=getKey(i);
		return key==null?"":key.toString();
	}
	
	public Object getKey(Object value) {
		Object key=new Object(); 
		for(int i=0;i<item.size();i++)
		{
		  HashMap hm=(HashMap)item.get(i);
		  Iterator it= hm.keySet().iterator();			
		  while(it.hasNext())
		  {			
			  Object tempkey=(Object)it.next();
			  if(value.equals(hm.get(tempkey)))key=tempkey;			
		  }
		}
		return key;
	}
	
	public String getKeyString(Object value)
	{
		Object key=getKey(value);
		return key==null?"":key.toString();
	}

	public Object getValue(int index) {
		HashMap hm=(HashMap)item.get(index);
		Iterator it= hm.keySet().iterator();
		Object key=new Object();
		Object value=new Object();
		while(it.hasNext())
		{			
			key=(Object)it.next();
			value=hm.get(key);
		}
		return value;
	}
	
	public String getValueString(int index)
	{
		Object value=getValue(index);
		return value==null?"":value.toString();
	}

    public String getAllValueSplitString(String sstr)
    {
        String rs="";
        for (int i = 0; i <size() ; i++) {
            if(i>0)rs+=sstr;
            rs+=getValueString(i);
        }
        return rs;
    }


	@SuppressWarnings("unchecked")
	public Object getValue(Object key) {
		Object value=null;
		for(int i=0;i<item.size();i++)
		{
		  HashMap hm=(HashMap)item.get(i);
		  Iterator it= hm.keySet().iterator();			
		  while(it.hasNext())
		  {			
			  Object tempkey=(Object)it.next();
			  if(key.equals(tempkey))value=hm.get(key);			
		  }
		}
		return value;
	}
	
	public String getValueString(Object key)
	{
		Object value=getValue(key);
		return value==null?"":value.toString();
	}

	public void setValue(Object key,Object value){
		for(int i=0;i<item.size();i++)
		{
		  HashMap hm=(HashMap)item.get(i);
		  Iterator it= hm.keySet().iterator();			
		  while(it.hasNext())
		  {			
			  Object tempkey=(Object)it.next();
			  if(key.equals(tempkey)){
				  hm.remove(key);
				  hm.put(key, value);			
			  }		
		  }
		}
	}
	
	public void setValue(int index,Object value){

		  HashMap hm=(HashMap)item.get(index);
		  Iterator it= hm.keySet().iterator();			
		  while(it.hasNext())
		  {			
			  Object tempkey=(Object)it.next();
			  hm.remove(tempkey);
			  hm.put(tempkey, value);		
		  }
	}

	@SuppressWarnings("unchecked")
	public void put(Object key,Object value)
	{
		HashMap hm=new HashMap();
	    hm.put(key, value);
	    item.add(hm);
	}
	public void put(ArrayList keys,ArrayList values)
	{
		HashMap hm=new HashMap();
		if(keys.size()==values.size())
		{
		    for(int i=0;i<keys.size();i++)
		    {
		    	hm.put(keys.get(i), values.get(i));			
		    }
		    item.add(hm);
		}		
	}

	public int size()
	{ 
		int size=item.size();
		return size;
	}
	public void remove(int index){
		item.remove(index);
	}
	public void remove(String key){
		for(int i=0;i<item.size();i++)
		{
		  HashMap hm=(HashMap)item.get(i);
		  Iterator it= hm.keySet().iterator();			
		  while(it.hasNext())
		  {			
			  Object tempkey=(Object)it.next();
			  if(key.equals(tempkey)){
				  hm.remove(key);		
			  }		
		  }
		}
	}
	public void clear()
	{
		if(item!=null)item.clear();		
	}
	public boolean containsKey(Object key)
	{
		boolean Flage=false;
		for(int i=0;i<item.size();i++)
		{
			HashMap hm=(HashMap)item.get(i);
			if(hm.containsKey(key))
			{
				Flage=true;
				break;				
			}	
		}
		return Flage;
	}
	public boolean containsValue(Object value)
	{
		boolean Flage=false;
		for(int i=0;i<item.size();i++)
		{
			HashMap hm=(HashMap)item.get(i);
			if(hm.containsValue(value))
			{
				Flage=true;
				break;				
			}	
		}
		return Flage;
	}
	
	public void addend(BinMap adder)
	{
		adder.getItem().addAll(0,this.item);
	}
	
	public JSONObject toJSONObject()
	{
		JSONObject obj=new JSONObject();
		for(int i=0;i<this.item.size();i++)
		{
			  HashMap hm=(HashMap)item.get(i);
			  Iterator it= hm.keySet().iterator();	
			   Object key=new Object();
				Object value=new Object();
				while(it.hasNext())
				{			
					key=(Object)it.next();
					value=hm.get(key);
					try {
						obj.put(key.toString(), value.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			  
		}
		return obj;
	}
	public static void main(String[] args) {
		BinMap pm=new BinMap();
		pm.put("1", "a");
		pm.put("2", "b");
		pm.setValue("2", "c");
//		String rs="";
//		System.out.println(pm.getValue("3"));
//		if(pm.getValue("2")!=null)rs=(String)pm.getValue("3");
//		System.out.println(pm.getItem());
		
		BinMap adder=new BinMap();
		adder.put("3", "d");
		adder.put("4", "e");
		adder.put("5", "f");
		
		pm.addend(adder);
		System.out.println(pm.getItem());
		

	}

}
