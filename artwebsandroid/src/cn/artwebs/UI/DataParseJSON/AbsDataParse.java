package cn.artwebs.UI.DataParseJSON;

import cn.artwebs.UI.IDataParse;
import cn.artwebs.object.BinMap;
import cn.artwebs.transmit.ITransmit;


public abstract class AbsDataParse implements IDataParse {
	protected String dataStr;
	protected ITransmit transmit;
	protected BinMap para=new BinMap();
	
	@Override
	public void setPara(BinMap para) {
		this.para=para;
		
	}
	
	@Override
	public void setDataStr(String dataStr) {
		this.dataStr=dataStr;
		
	}

	@Override
	public void setTransmit(ITransmit transmit) {
		this.transmit=transmit;
		
	}
}
