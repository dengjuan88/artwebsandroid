package cn.artwebs.UI;

import cn.artwebs.ListAdapter.ListAdapter;
import cn.artwebs.ListAdapter.ListAdapterDefault;
import cn.artwebs.UI.DataParseXML.DataPagelist;
import cn.artwebs.object.BinList;
import cn.artwebs.object.BinMap;


import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;



public class CodeUIPagelist extends CodeUIList implements OnScrollListener {
	private final static String tag="CodeUIPagelist";
	private String next=null;
	private int visibleLastIndex = 0;   //���Ŀ���������    
    private int visibleItemCount;       // ��ǰ���ڿɼ�������
    private ListView listView;
    private LinearLayout loadMoreLayout ;
    private int datasize=0;
	@Override
	public View drawnView(final Activity activity,Integer parentid,Integer id) {
//		adapter=new ListAdapter(this.para,activity);	
//		ListView listView=(ListView)activity.findViewById(id);
		this.setMainActity(activity);
		loadMoreLayout = new LinearLayout(activity);
		loadMoreLayout.setOrientation(LinearLayout.HORIZONTAL);
		loadMoreLayout.setGravity(Gravity.CENTER);
					
		listView=new ListView(activity);
		listView.addFooterView(loadMoreLayout);
		if(adapter==null)this.setAdapter(new ListAdapterDefault(this.para,activity));
		listView.setAdapter(adapter);		
		datasize=adapter.getDataSize();
		listView.setOnItemClickListener(this.onItemClickListener);
		listView.setOnScrollListener(this);
		return listView;
	}
	
	private void loadMoreData()
	{
		if(next==null)next=this.para.getValue("next").toString();        
        IDataParse parseObj=new DataPagelist();
		String outStr=this.transmit.download(next);
        parseObj.setDataStr(outStr);
        BinMap para=parseObj.parse();
        
        if(!para.getValue("next").equals(next))
        {
        	adapter.appendItem((BinList)para.getValue("rows"));
        	next=para.getValue("next").toString();
        }
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.visibleItemCount = visibleItemCount;  
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;            
        //������еļ�¼ѡ�������ݼ����������Ƴ��б�ײ���ͼ  
        if(totalItemCount == datasize+1){  
            listView.removeFooterView(loadMoreLayout);  
            Toast.makeText(this.getMainActity(), "���ȫ��������!", Toast.LENGTH_LONG).show();  
        }  
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		int itemsLastIndex = adapter.getCount()-1;  //��ݼ����һ�������    
        int lastIndex = itemsLastIndex + 1;  
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE  
                && visibleLastIndex == lastIndex &&adapter.getCount()<datasize) {  
        	loadMoreData();
			adapter.notifyDataSetChanged();
        }  
	}
}
