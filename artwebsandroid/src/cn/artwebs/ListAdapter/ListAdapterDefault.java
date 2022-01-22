package cn.artwebs.ListAdapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import cn.artwebs.R;
import cn.artwebs.object.BinMap;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by rsmac on 15/1/30.
 */
public class ListAdapterDefault<ViewHolderDefault> extends ListAdapter {

    public ListAdapterDefault(Activity activity) {
        super(activity);
    }

    public ListAdapterDefault(BinMap para, Activity activity) {
        super(para, activity);
    }

    @Override
    public int initLayoutID() {
        return R.layout.binlistitem;
    }

    @Override
    public ViewHolder initViewHolder() {
        return null;
    }


    @Override
    public void updateUI(View convertView, ViewHolder obj) {
        TextView firstView=(TextView)convertView.findViewById(R.id.first);
        firstView.setMaxLines(2);
        firstView.setText(obj.getString("text"));
    }

    public class ViewHolderDefault extends ViewHolder{

    }
}


