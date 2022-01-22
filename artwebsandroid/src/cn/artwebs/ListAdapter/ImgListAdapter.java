package cn.artwebs.ListAdapter;

import java.util.HashMap;

import org.json.JSONArray;

import cn.artwebs.R;
import cn.artwebs.AsyncImageLoader.AsyncImageLoader;
import cn.artwebs.AsyncImageLoader.IAsyncImageLoader;
import cn.artwebs.object.BinList;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



public class ImgListAdapter extends ListAdapterDefault {

	protected IAsyncImageLoader loader = new AsyncImageLoader();
	public ImgListAdapter(Activity activity) {
		super(activity);
	}




	@Override
	public int initLayoutID() {
		return R.layout.imgbinlistitem;
	}

	@Override
	public ViewHolder initViewHolder() {
		return new ViewHolder();
	}



}
