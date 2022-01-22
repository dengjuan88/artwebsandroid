package cn.artwebs.control;

import cn.artwebs.R;
import cn.artwebs.object.BinList;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ArtMenuView extends LinearLayout {
	private Context context;
	private TableLayout menuTbl;
	
	protected BinList dataList=new BinList();
	protected int colnum=3;
	protected int padding_in_dp=15;
	private int height=95;
	private int width=95;
	private int padding=20;
	private int textColor=Color.BLACK;
	
	public static final int setNet_ID = Menu.FIRST;    
    public static final int EXIT_ID = Menu.FIRST + 1; 
	
	private ImageView oldimg;
	
	
    
    public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setPadding(int padding) {
		this.padding = padding;
	}
	
	
	public void addMenu(String name,int iconID,OnClickListener listener)
	{
		dataList.put(false, "name", name); 
    	dataList.put(true, "icon", iconID); 
    	dataList.put(true, "listener", listener); 
	}
	
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	
	public ArtMenuView(Context context) {
		super(context);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	
	public ArtMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.artmenu_layout, this);
		menuTbl = (TableLayout)findViewById(R.id.menuTbl);
	}
	
	public void commOnClick(View v)
	{
		if(oldimg!=null)oldimg.setBackgroundResource(0);
		ImageView img=(ImageView)v;
		img.setBackgroundResource(R.drawable.menu_default_icon_bg);
		oldimg=img;
	}
	
	public void singleOnClick(View v,boolean isOn)
	{
		ImageView img=(ImageView)v;
		if(isOn)
			img.setBackgroundResource(R.drawable.menu_default_icon_bg);
		else
			img.setBackgroundResource(0);
	}
	
    
    public void displayMenu()
    {
    	final float scale = getResources().getDisplayMetrics().density;
    	TableRow trImg = new TableRow(context);
    	menuTbl.addView(trImg);
		TableRow trText = new TableRow(context);
		menuTbl.addView(trText);
		int num=0;
		for(int i=0;i<dataList.size();i++)
		{
			if(num%colnum==0)
			{
				trImg = new TableRow(context);
			    			 
			    int padding_in_px = (int) (padding_in_dp * scale + 0.5f);					    
			    trImg.setPadding(0, padding_in_px, 0, 0);
			    menuTbl.addView(trImg);
				trText = new TableRow(context);
				menuTbl.addView(trText);
			}
			
			String name=dataList.getValue(i, "name").toString();
			OnClickListener listener=(OnClickListener)dataList.getValue(i, "listener");
			int icon =Integer.parseInt(dataList.getValue(i, "icon").toString());
			if(name.length()>5)
				name = name.substring(0,5);
			ImageView img= new ImageView(context);
//			img.setLayoutParams();
			img.setImageResource(icon);
//			if(listener==null)
//				img.setOnClickListener(context);
//			else
				img.setOnClickListener(listener);
			img.setId(i);
			img.setTag(name);
			img.setPadding((int)(padding*scale),0,(int)(padding*scale),0);
			if(dataList.containsKey("isSelected"))
				singleOnClick(img,Boolean.valueOf(dataList.getValue(i, "isSelected").toString()));
				
			trImg.addView(img);
			
			LayoutParams para = (LayoutParams) img.getLayoutParams();   
	        para.height = (int)(this.height*scale);  
	        para.width =  (int)(this.width*scale);  
	        img.setLayoutParams(para);
	        
			
			TextView text = new TextView(context);
			text.setText(name);
			text.setTextColor(textColor);
			text.setGravity(Gravity.CENTER);
			trText.addView(text);
			if("占位".equals(name))
			{
				img.setVisibility(View.INVISIBLE);
				text.setVisibility(View.INVISIBLE);
			}
			num++;
		}
    }

}
