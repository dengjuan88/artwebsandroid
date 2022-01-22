package cn.artwebs.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.artwebs.LHB;
import cn.artwebs.object.BinMap;



public class DbUtil extends LHB {
	public BinMap getNoExKey(BinMap para) {
		BinMap rsLm=this.getNoExKey(para);
		return rsLm;
	}
	
	public BinMap getNoExPara(BinMap para) {
		BinMap rsLm=this.getNoExPara(para);
		return rsLm;
	}
	
	public String getInsertPart(BinMap para) {
		String rsStr="";
		String fieldpart="";
		String valuepart="";		
		for(int i=0;i<para.size();i++){
			String key=(String)para.getKey(i);
			if(key.indexOf(".")>0){
				String ex=key.substring(key.indexOf(".")+1);
				String noex=key.substring(0,key.lastIndexOf("."));
				fieldpart+=noex+",";
				if(ex.equals("string")){
					valuepart+="'"+para.getValue(i)+"',";
				}else if(ex.equals("clob")){
					valuepart+="empty_clob(),";
				}else{
					valuepart+=para.getValue(i)+",";
				}
								
			}else{
				fieldpart+=key+",";
				valuepart+="'"+para.getValue(i)+"',";
			}
			
		}
		if(fieldpart!=""&&valuepart!=""){
			fieldpart=fieldpart.substring(0,fieldpart.lastIndexOf(','));
			valuepart=valuepart.substring(0,valuepart.lastIndexOf(','));
			rsStr="("+fieldpart+") values ("+valuepart+")";
		}
		return rsStr;
	}


	public String getSelectField(BinMap para) {
		String rsStr="";
		String regex="\\s+as\\s+";
		Pattern p=Pattern.compile(regex,Pattern.UNICODE_CASE);
		for(int i=0;i<para.size();i++){
			String key=(String)para.getKey(i);
			Matcher m=p.matcher(key);
			if(key.indexOf(".")>0&&!m.find()){
				String noex=key.substring(0,key.lastIndexOf("."));
				rsStr+=noex+",";
			}else{
				rsStr+=key+",";
			}
		}
		if(rsStr!=""){
			rsStr=rsStr.substring(0,rsStr.lastIndexOf(','));
		}
		return rsStr;
	}

	public String getUpdatePart(BinMap para) {
		String rsStr="";
		for(int i=0;i<para.size();i++){
			String key=(String)para.getKey(i);
			if(key.indexOf(".")>0){
				String ex=key.substring(key.indexOf(".")+1);
				String noex=key.substring(0,key.lastIndexOf("."));
				rsStr+=noex+"=";
				if(ex.equals("string")){
					rsStr+="'"+para.getValue(i)+"',";
				}else{
					rsStr+=para.getValue(i)+",";
				}
								
			}else{
				rsStr+=key+"=";
				rsStr+="'"+para.getValue(i)+"',";
			}
			
		}
		if(rsStr!=""){
			rsStr=rsStr.substring(0,rsStr.lastIndexOf(','));
		}
		return rsStr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DbUtil isf=new DbUtil();
		BinMap lm=new BinMap();
		lm.put("a.string", "1");
		lm.put("b.nextval as b1", "2");
		lm.put("c", "3");
		lm.put("d", "4");
//		
//		String i=isf.getInsertPart(lm);
//		String u=isf.getUpdatePart(lm);
//		String s=isf.getSelectField(lm);
		BinMap s=isf.getNoExKey(lm);
//		
//		System.out.println(i);
//		System.out.println(u);
//		System.out.println(s);
		System.out.println(s.getItem());

	}

}
