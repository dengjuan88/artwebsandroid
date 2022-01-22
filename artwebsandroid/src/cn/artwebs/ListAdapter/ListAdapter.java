package cn.artwebs.ListAdapter;

import java.util.HashMap;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.artwebs.object.BinList;
import cn.artwebs.object.BinMap;

import cn.artwebs.R;
import cn.artwebs.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ListAdapter<T extends ListAdapter.ViewHolder> extends BaseAdapter {
	private final String tag="ListAdapter";
	protected BinMap para=new BinMap();
	protected BinList list=new BinList();
	protected Activity activity=null;
	protected int dataSize=0;
	
	public ListAdapter(Activity activity)
	{
		this.activity=activity;
	}
	
	public void setList(BinList list)
	{
		dataSize=list.size();
		this.list=list;
	}
	public ListAdapter(BinMap para,Activity activity)
	{
		this.para=para;
		if(this.para.containsKey("rows"))this.list=(BinList)this.para.getValue("rows");
		dataSize=Integer.parseInt(this.para.getValue("count").toString());
		this.activity=activity;
	}
	
	public void appendItem(BinList list)
	{
		Log.d(tag,"item"+this.list.getItem().toString());
		Log.d(tag,"adder"+list.getItem().toString());
		this.list.addend(list);
		Log.d(tag,"appendItem"+this.list.getItem().toString());
	}
	
	public void clearItem()
	{
		this.list.clear();
	}
	
	public void removeItem(int index)
	{
		this.list.remove(index);
	}
	
	public int getDataSize() {
		return dataSize;
	}

	@Override
	public int getCount() {
		return this.list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.list.getItem(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T obj;
		if(convertView==null){
			convertView=initLayout(initLayoutID());
			obj=initViewHolder();
			convertView.setTag(obj);
		}else {
			obj= (T) convertView.getTag();
		}
		obj.setMap((HashMap) this.getItem(position));
		updateUI(convertView, obj);
		return convertView;
	}

	public abstract int initLayoutID();
	public abstract T initViewHolder();

	public View initLayout(int layoutid){
		return this.activity.getLayoutInflater().inflate(layoutid, null);
	}


	public abstract void updateUI(View convertView,T obj);

	public class ViewHolder{
		private HashMap map;

		public String getString(String key){
			return Utils.getJSONString(getJson(),key);
		}

		public BinList getList(String key){
			return  new BinList().appendFromJSONArray(Utils.getJSONArray(getJson(),key));
		}

		public JSONObject getJson() {
			return new BinMap().setItemByHashMap(this.map).toJSONObject();
		}


		public void setMap(HashMap map){
			this.map=map;
		}

		public void setValue(String key,Object value){
			if(this.map!=null){
				this.map.put(key,value);
			}
		}



	}

}
