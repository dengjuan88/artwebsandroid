package cn.artwebs.androiddemo;

import cn.artwebs.control.SubmitView;
import cn.artwebs.object.BinList;
import cn.artwebs.object.BinMap;
import cn.artwebs.utils.AndroidUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SubmitDataActivity extends Activity {
	private Activity window;
	private LinearLayout mainLayout;
	private SubmitView submitView;
	private Button submitBtn;
	private Button reloadBtn;
	private BinList para;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		window=this;
		mainLayout=(LinearLayout)getLayoutInflater().inflate(R.layout.submit_layout, null);
		this.setContentView(mainLayout);
		submitView=(SubmitView)findViewById(R.id.submitView);
		submitBtn=(Button)findViewById(R.id.submitBtn);
		reloadBtn=(Button)findViewById(R.id.reloadBtn);
		para=getPara();
//		SubmitView.buildData(para, this.getData(), 0);
		submitView.setSource(para);
		submitView.reLoad();
		
		submitBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AndroidUtils.toastShow(window, submitView.isChanged()+"", Toast.LENGTH_LONG);
				AndroidUtils.toastShow(window, submitView.getResult().getItem().toString(), Toast.LENGTH_LONG);
			}
		});
		
		reloadBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				window.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						para.remove(0);
						submitView.setSource(para);
						submitView.reLoad();
					}
				});
				
			}
		});
		
	}
	
	
	private BinList getPara()
	{
		BinList para=new BinList();
		//para.put(true, "type", "textBox"); 		
		para.put(false, "name", "businessScope"); 		para.put(true, "cname", "业务范围："); 		
		para.put(false, "name", "equipmentCode"); 		para.put(true, "cname", "设备编码："); 		 		para.put(true, "readonly", "true");
		para.put(false, "name", "equipmentName"); 		para.put(true, "cname", "设备名称："); 		
		para.put(false, "name", "countryCode"); 		para.put(true, "cname", "国家局编码："); 		        para.put(true, "display", "false");
		para.put(false, "name", "abcType"); 			para.put(true, "cname", "ABC标识："); 		
		para.put(false, "name", "productModel"); 		para.put(true, "cname", "型号："); 			
		para.put(false, "name", "oldEquipmentCode"); 	para.put(true, "cname", "原机编号："); 		
		para.put(false, "name", "dateProduction"); 		para.put(true, "cname", "出厂日期："); 		
		para.put(false, "name", "manufacturer"); 		para.put(true, "cname", "制造厂商："); 		
		para.put(false, "name", "costCenter"); 			para.put(true, "cname", "成本中心："); 		
		para.put(false, "name", "placeId"); 			para.put(true, "cname", "存放地点："); 		
		para.put(false, "name", "useType"); 			para.put(true, "cname", "使用状态："); 		
		para.put(false, "name", "orgnValue"); 			para.put(true, "cname", "累计购置价值："); 	
		para.put(false, "name", "mpsjnl"); 				para.put(true, "cname", "铭牌设计能力："); 	
		para.put(false, "name", "unitDescription"); 	para.put(true, "cname", "生产能力单位："); 	
		para.put(false, "name", "dimensions"); 			para.put(true, "cname", "外型尺寸："); 		
		para.put(false, "name", "capacity"); 			para.put(true, "cname", "装机容量："); 		
		para.put(false, "name", "weight"); 				para.put(true, "cname", "重量："); 				para.put(true, "unit", "吨");
		para.put(false, "name", "specs"); 				para.put(true, "cname", "规格："); 			
		para.put(false, "name", "zrcbzx"); 				para.put(true, "cname", "责任成本中心："); 	
		para.put(false, "name", "stdUser"); 			para.put(true, "cname", "资产使用人："); 		
		para.put(false, "name", "shebeiDescription"); 	para.put(true, "cname", "国家局管设备："); 	
		para.put(false, "name", "equipmentType"); 		para.put(true, "cname", "设备种类："); 		
		para.put(false, "name", "equipmentTypeName"); 	para.put(true, "cname", "设备类别名称："); 	
		para.put(false, "name", "supplierName"); 		para.put(true, "cname", "供应商名称："); 		
		para.put(false, "name", "rcDate"); 				para.put(true, "cname", "入厂日期："); 		
		para.put(false, "name", "stdDate"); 			para.put(true, "cname", "资本化日期："); 		
		para.put(false, "name", "stdNumber"); 			para.put(true, "cname", "资产编码："); 		
		para.put(false, "name", "plantNo"); 			para.put(true, "cname", "车间机号："); 		
		para.put(false, "name", "hdSize"); 				para.put(true, "cname", "硬盘容量："); 		
		para.put(false, "name", "cpuType"); 			para.put(true, "cname", "CPU型号："); 		
		para.put(false, "name", "memorySize"); 			para.put(true, "cname", "内存容量："); 		
		para.put(false, "name", "displayNo"); 			para.put(true, "cname", "显示器编号："); 		
		para.put(false, "name", "displayType"); 		para.put(true, "cname", "显示器规格："); 		
		para.put(false, "name", "plateNo"); 			para.put(true, "cname", "车辆牌号："); 		
		para.put(false, "name", "notes"); 				para.put(true, "cname", "备注："); 			
		return para;
		
	}
	
	private BinList getData()
	{
		BinList data=new BinList();
		data.put(true, "businessScope", "col_1"); 
		data.put(true, "equipmentCode", "col_2"); 
		data.put(true, "equipmentName", "col_3"); 
		data.put(true, "countryCode", "col_4"); 
		data.put(true, "abcType", "col_5"); 
		data.put(true, "productModel", "col_6"); 
		data.put(true, "oldEquipmentCode", "col_7"); 
		data.put(true, "dateProduction", "col_8"); 
		data.put(true, "manufacturer", "col_9"); 
		data.put(true, "costCenter", "col_10"); 
		data.put(true, "placeId", "col_11"); 
		data.put(true, "useType", "col_12"); 
		data.put(true, "orgnValue", "col_13"); 
		data.put(true, "mpsjnl", "col_14"); 
		data.put(true, "unitDescription", "col_15"); 
		data.put(true, "dimensions", "col_16"); 
		data.put(true, "capacity", "col_17"); 
		data.put(true, "weight", "col_18"); 
		data.put(true, "specs", "col_19"); 
		data.put(true, "zrcbzx", "col_20"); 
		data.put(true, "stdUser", "col_21"); 
		data.put(true, "shebeiDescription", "col_22"); 
		data.put(true, "equipmentType", "col_23"); 
		data.put(true, "equipmentTypeName", "col_24"); 
		data.put(true, "supplierName", "col_25"); 
		data.put(true, "rcDate", "col_26"); 
		data.put(true, "stdDate", "col_27"); 
		data.put(true, "stdNumber", "col_28"); 
		data.put(true, "plantNo", "col_29"); 
		data.put(true, "hdSize", "col_30"); 
		data.put(true, "cpuType", "col_31"); 
		data.put(true, "memorySize", "col_32"); 
		data.put(true, "displayNo", "col_33"); 
		data.put(true, "displayType", "col_34"); 
		data.put(true, "plateNo", "col_35"); 
		data.put(true, "notes", "col_36"); 
		return data;
	}
}
