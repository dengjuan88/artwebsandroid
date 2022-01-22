package cn.artwebs.control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.artwebs.R;
import cn.artwebs.object.BinList;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SingleChoiceView extends RelativeLayout {
	private final static String tag="SingleChoiceView";
	private Context context;
	private String sourceStr;
	private String keyField;
	private String valueField;
	private String key;
	private String value;
	private BinList list;
	private TextView titleView;
	private RadioGroup itemGroup;
	
	
	
	public String getKeyField() {
		return keyField;
	}

	public String getValueField() {
		return valueField;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public TextView getTitleView() {
		return titleView;
	}

	public void setSource(String title,String itemSource) {
		this.titleView.setText(title);
		this.sourceStr = itemSource;
	}

	public SingleChoiceView(Context context) {
		super(context);
		this.context=context;
		list=new BinList();
		// TODO Auto-generated constructor stub
	}
	
	public SingleChoiceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.singlechoice_view, this);
		titleView=(TextView) findViewById(R.id.titleTextView);
		itemGroup=(RadioGroup) findViewById(R.id.itemGroup);
		itemGroup.setOnCheckedChangeListener(onCheckedChangeListener);
		list=new BinList();
	}
	
	
	
	public void init(String kField,String vField)
	{
		this.keyField=kField;
		this.valueField=vField;
	}
	
	public void show()
	{
//		Log.d(tag, "sourceStr="+sourceStr);
		list.clear();
		itemGroup.removeAllViews();
		try {
			JSONArray jarr=new JSONArray(this.sourceStr);
			for(int i=0;i<jarr.length()&&list.size()<20;i++)
			{
				JSONObject jobj=(JSONObject)jarr.get(i);
				String jkey=jobj.getString(this.keyField);
				String jvalue=jobj.getString(this.valueField);
				list.put(false, this.keyField, jkey);
				list.put(true, this.valueField, jvalue);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{}
		
		for(int i=0;i<list.size();i++)
        {
        	RadioButton radioButton=buildRadioButton();
        	radioButton.setId(i);
        	radioButton.setText(list.getValue(i, this.valueField).toString());
        	if(key==list.getValue(i, this.keyField).toString())
        		radioButton.setSelected(true);
        	itemGroup.addView(radioButton);
        }
	}
	
	
	protected RadioButton buildRadioButton()
	{
		RadioButton radioButton=new RadioButton(this.context);
		return radioButton;
	}
	
	OnCheckedChangeListener onCheckedChangeListener=new OnCheckedChangeListener(){

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			value=list.getValue(checkedId, getValueField()).toString();
			key=list.getValue(checkedId, getKeyField()).toString();
			RadioButton radioButton=(RadioButton)group.getChildAt(checkedId);
			radioButton.setSelected(true);
		}};

}
