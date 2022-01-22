package cn.artwebs.UI.Form;

import java.util.HashMap;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.artwebs.object.BinList;

import cn.artwebs.R;

public class SelectAdapter extends BaseAdapter {
	private HashMap<Integer,View> rowViews=new HashMap<Integer,View>();
	private BinList list=new BinList();
	private Activity activity=null;
	
	public SelectAdapter(BinList list,Activity activity)
	{
		this.list=list;
		this.activity=activity;
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
		View rowView=rowViews.get(position);
		if(rowView==null)
		{
			rowView=(LinearLayout)this.activity.getLayoutInflater().inflate(R.layout.diclistitem, null);
			TextView firstView=(TextView)rowView.findViewById(R.id.text1);
			firstView.setMaxLines(2);
			HashMap<Object, Object> row=(HashMap<Object, Object>)this.getItem(position);
			firstView.setText(row.get("first").toString());
			rowViews.put(position, rowView);
		}
		return rowView;
	}
}
