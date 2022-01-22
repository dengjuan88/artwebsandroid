package cn.artwebs.control;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import cn.artwebs.ListAdapter.ListAdapter;
import cn.artwebs.ListAdapter.ListAdapterDefault;
import cn.artwebs.R;
import cn.artwebs.object.BinList;

import java.util.HashMap;

/**
 * Created by rsmac on 15/2/11.
 */
public class ArtTabPager extends RelativeLayout {
    private BinList list=new BinList();
    private Context context;
    private ViewPager tabPager;
    private GridView tabGridView;
    private ListAdapter adapter;
    private int colorDown;  //tab为选中时颜色
    private int colorUp;    //tab选中是颜色
    private int colorWhite; //白色

    public ArtTabPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public ArtTabPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ArtTabPager(Context context) {
        super(context);
        init(context, null);
    }

    protected void init(Context context, AttributeSet attrs){
        this.context=context;
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(initTabPageLayoutID(), this);
        tabPager= (ViewPager) findViewById(R.id.tabPager);
        tabGridView= (GridView) findViewById(R.id.tabGridView);

        colorDown=0xff009688;
        colorUp=0xff868686;
        colorWhite=0xffffffff;
    }

    public void appendItem(String title,Fragment fragment){
        list.put(false,"title",title);
        list.put(true,"selected",list.size()==1?"true":"false");
        list.put(true,"pager",fragment);
    }

    public void refresh(ActionBarActivity window){
        adapter=new TabAdapter(window);
        tabGridView.setAdapter(adapter);
        tabPager.setOnPageChangeListener(pageChangeListener);
        tabGridView.setOnItemClickListener(tabOnItemClicker);

        tabPager.setOffscreenPageLimit(list.size()); //设置缓存
        tabGridView.setNumColumns(list.size());
        adapter.appendItem(list);
        adapter.notifyDataSetChanged();
        tabPager.setAdapter(new TabFragmentPagerAdapter(window.getSupportFragmentManager()));
    }

    private AdapterView.OnItemClickListener tabOnItemClicker =new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setSelectItem(position);
        }
    };

    protected void setSelectItem(int index){
        for(int i=0;i<adapter.getCount();i++){
            HashMap map= (HashMap) adapter.getItem(i);
            if(i==index)
                map.put("selected","true");
            else
                map.put("selected","false");
        }
        tabPager.setCurrentItem(index);
        adapter.notifyDataSetChanged();
    }

    private class TabAdapter extends ListAdapterDefault {

        public TabAdapter(Activity activity) {
            super(activity);
        }

        @Override
        public int initLayoutID() {
            return intTabPageLayoutItemID();
        }

        @Override
        public ViewHolder initViewHolder() {
            return new TabView();
        }

        @Override
        public void updateUI(View convertView, ViewHolder obj) {
            TabView vObj=(TabView)obj;
            TextView titleText= (TextView) convertView.findViewById(R.id.titleText);
            ImageView lineImageView= (ImageView) convertView.findViewById(R.id.lineImageView);
            Boolean selected=Boolean.valueOf(vObj.getString("selected"));
            titleText.setText(vObj.getString("title"));
            if(selected){
                titleText.setTextColor(colorDown);
                lineImageView.setBackgroundColor(colorDown);
            }
            else{
                titleText.setTextColor(colorUp);
                lineImageView.setBackgroundColor(colorWhite);
            }

        }

        class TabView extends ViewHolder{

        }
    }

    public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg) {
            HashMap map= (HashMap) adapter.getItem(arg);
            return (Fragment) map.get("pager");
        }

        @Override
        public int getCount() {
            return adapter.getCount();
        }
    }

    private ViewPager.OnPageChangeListener pageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {


        }

        @Override
        public void onPageSelected(int i) {
            setSelectItem(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    protected int initTabPageLayoutID(){
        return R.layout.arttabpager;
    }

    protected int intTabPageLayoutItemID(){
        return R.layout.arttabpager_tab_item;
    }

}
