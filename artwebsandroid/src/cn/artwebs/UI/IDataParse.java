package cn.artwebs.UI;

import cn.artwebs.object.BinMap;
import cn.artwebs.transmit.ITransmit;


public interface IDataParse {
	public BinMap parse();
	public void setDataStr(String dataStr);
	public void setTransmit(ITransmit transmit);
	public void setPara(BinMap para);
}
