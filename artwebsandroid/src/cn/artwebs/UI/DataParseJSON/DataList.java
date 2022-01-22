package cn.artwebs.UI.DataParseJSON;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import cn.artwebs.object.BinMap;

public class DataList extends AbsDataParse {

	@Override
	public BinMap parse() {
		JSONObject jsonObject;
		try {
			JSONTokener jsonParser = new JSONTokener(this.dataStr);  
		    jsonObject = (JSONObject) jsonParser.nextValue();  
			this.para.put("code", jsonObject.getString("code").toString());
			String message=jsonObject.getString("message").toString();
			this.para.put("message",message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{}
		return this.para;
	}
}
