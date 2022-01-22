package cn.artwebs.object;

public class ResultObject extends IBinObject {
	private String code;
	private String message;
	private BinMap args;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public BinMap getArgs() {
		return args;
	}
	public void setArgs(BinMap args) {
		this.args = args;
	}
	
	
}
