package cn.artwebs.control;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.artwebs.R;
import cn.artwebs.comm.AppApplication;
import cn.artwebs.object.BinList;

/**
 * Created by rsmac on 15/3/13.
 */
public class ArtSettingView extends LinearLayout {
    private BinList list=new BinList();
    private Context context;
    private final static String fieldIco ="ico";
    private final static String fieldTitle ="title";
    private final static String fieldValue ="value";
    private final static String fieldListener ="listener";
    private final static String fieldHeight ="height";

    public ArtSettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void clearViews(){
        this.list.clear();
        this.removeAllViews();
    }

    public void reloadView(){
        this.removeAllViews();
        for(int i=0;i<list.size();i++){
            LayoutInflater inflater= LayoutInflater.from(AppApplication.getAppContext());
            View view = (View) inflater.inflate(R.layout.artsettingitem,null);
            LinearLayout titleLayout= (LinearLayout) view.findViewById(R.id.titleLayout);
            ImageView icoImageView= (ImageView) view.findViewById(R.id.icoImageView);
            ImageView lineImageView= (ImageView) view.findViewById(R.id.lineImageView);
            TextView titleTextView= (TextView) view.findViewById(R.id.titleTextView);
            TextView valueTextView= (TextView) view.findViewById(R.id.valueTextView);

            //隐藏最后一行分割线
            if(i==list.size()-1){
                lineImageView.setVisibility(View.INVISIBLE);
            }

            if(list.size()==1){
                ViewGroup.LayoutParams params=titleLayout.getLayoutParams();
                params.height=params.height+params.height/3;
                titleLayout.setLayoutParams(params);
            }

            if(list.containsKey(i, fieldIco)){
                icoImageView.setImageDrawable((Drawable) (list.getValue(i, fieldIco)));
                icoImageView.setVisibility(View.VISIBLE);
            }
            if(list.containsKey(i, fieldTitle))titleTextView.setText(list.getValueString(i, fieldTitle));
            if(list.containsKey(i, fieldValue))valueTextView.setText(list.getValueString(i, fieldValue));
            if(list.containsKey(i, fieldListener))view.setOnClickListener((OnClickListener) list.getValue(i, fieldListener));
            if(list.containsKey(i, fieldHeight)){
                ViewGroup.LayoutParams params=titleLayout.getLayoutParams();
                params.height=Integer.valueOf(list.getValueString(i, fieldHeight));
                titleLayout.setLayoutParams(params);
            }
            this.addView(view);
        }
    }

    public void append(String title,OnClickListener listener){
        append(null, title, null, 0 ,listener);
    }

    public void append(Drawable ico,String title,OnClickListener listener){
        append(ico, title, null, 0 ,listener);
    }

    public void append(Drawable ico,String title,String value,OnClickListener listener){
        append(ico, title, value, 0 ,listener);
    }

    public void append(Drawable ico,String title,String value,int height,OnClickListener listener){
        list.put(false,"index",list.size());
        if(ico !=null)list.put(true,fieldIco,ico);
        if(title !=null)list.put(true, fieldTitle, title);
        if(value !=null)list.put(true, fieldValue,value);
        if(listener !=null)list.put(true, fieldListener,listener);
        if(height !=0){
            height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, getResources()
                    .getDisplayMetrics());
            list.put(true, fieldHeight,height);
        }
    }

}
