package cn.artwebs;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.artwebs.object.BinMap;
import cn.artwebs.utils.Base64;
import cn.artwebs.utils.CnToSpell;



public class LHB {
	public String GetEnoughLenStr(int num,String basestr){
		String rs=basestr;
		int baseint=rs.length();
		if(baseint<num){
			for(int i=baseint;i<num;i++)
			{
				rs="0"+rs;
			}
		}
		return rs;
	}
	public String putOffEnoughLenStr(int num,String basestr){
		String rs=basestr;
		int baseint=rs.length();
		if(baseint<num){
			baseint=num-baseint;
			rs=basestr.substring(baseint+1);
		}
		return rs;
	}
	public String UrlEncode(String code, String charset)
	  {
	    String rs = "";
	    try {
	      rs = URLEncoder.encode(code, charset);
	    }
	    catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    }
	    return rs;
	  }

	  public String UrlDecode(String code, String charset) {
	    String rs = "";
	    try {
	      rs = URLDecoder.decode(code, charset);
	    }
	    catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    }
	    return rs;
	  }
	  public String getContentCode(String strUrl, String charset)
	  {
	    URL url;
	    try
	    {
	      url = new URL(strUrl);	    
	      URLConnection urlc = url.openConnection();
	      urlc.setConnectTimeout(20000);
	      urlc.setReadTimeout(20000);
	      BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), charset));
	      String s = "";
	      StringBuffer sb = new StringBuffer("");
	      while ((s = br.readLine()) != null)
	      {
	        sb.append(s + "\r\n");
	      }
	      br.close();
	      //System.out.println("=="+Method.UrlDecode(strUrl, charset));
	      return sb.toString();
	    }
	    catch (Exception e) {
	      e.printStackTrace(); }
	    return "error";
	  }
	  
	  public String getMarkString(String Str, String LeftMark, String RightMark) {
		    String tmpreturn = Str;
		    if (tmpreturn.indexOf(LeftMark, 0) != -1)
		    {
		      int LeftMarkPoint = tmpreturn.indexOf(LeftMark, 0) + LeftMark.length();
		      int RightMarkPoint = tmpreturn.indexOf(RightMark, LeftMarkPoint);
		      if (RightMarkPoint != -1)
		      {
		        int ValueLength = RightMarkPoint;
		        tmpreturn = tmpreturn.substring(LeftMarkPoint, ValueLength);
		      }
		    }
		    if (tmpreturn == Str)
		    {
		      return "";
		    }

		    return tmpreturn;
		  }

		  public ArrayList getMarkStringList(String Str, String LeftMark, String RightMark)
		  {
		    ArrayList al = new ArrayList();
		    String tempstr = new String(Str);
		    String tempv = "";
		    while (!(getMarkString(tempstr, LeftMark, RightMark).equals(""))) {
		      tempv = getMarkString(tempstr, LeftMark, RightMark);
		      if (tempv.equals("")) break;
		      al.add(new String(tempv));
		      tempstr = tempstr.replace(LeftMark + tempv + RightMark, "");
		    }
		    return al;
		  }
		  public String Base64Encode(String para) {
			    String rs = "";
			    try {
			      rs = Base64.encode(para);
			    }
			    catch (Exception e) {
			      e.printStackTrace();
			    }
			    return rs;
			  }

	public String Base64Decode(String para) {
			    String rs = "";
			    try {
			      rs = Base64.decode(para);
			    }
			    catch (Exception e) {
			      e.printStackTrace();
			    }
			    return rs; 
			    }
	   	  
	   public String systemdate(){
		    String rs="";
			Calendar cal = new GregorianCalendar();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour=cal.get(Calendar.HOUR_OF_DAY);
			int m=cal.get(Calendar.MINUTE);
			int s=cal.get(Calendar.SECOND);
			rs=year+"-"+month+"-"+day+" "+hour+":"+m+":"+s;
			return rs;
	   }

	   /**
	    * ������ת��Ϊȫƴ����ƴ
	    * @param instr �����ַ�
	    * @param flag trueΪ��ƴ  falseΪȫƴ
	    * @return
	    */
	   public String getSpell(String instr, boolean flag){
		   String rsstr="";
		   rsstr=CnToSpell.getSpell(instr, flag);
		   return rsstr;
	   }
	   public String getBeforeAfterDate(String date,int n){
		    
			 Calendar cal=Calendar.getInstance();
			 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			 if(date.equals(""))date=df.format(cal.getTime());
			 try {
				cal.setTime(df.parse(date));				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+n);
			date=df.format(cal.getTime());
			return date;		
		}
	   //ʱ���
	   public Long getDateSub(String first,String second){
		   Long rs=null;
		   Date  df=new Date();
		   Date  dc=new Date();
		   SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd");
		   try {
			   df=dft.parse(first);
			   dc=dft.parse(second);
		   } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		   rs=(df.getTime()-dc.getTime())/(1000 * 60 * 60 * 24);	   
		   return rs;		   
	   }
	   /**
	    * ��ȡ��ǰʱ��
	    * @param type yyyy MM dd ww hh mm ss
	    * @return
	    */
	   public String getNowStr(String type){
		   	String rsStr=type;
		      Calendar ca = Calendar.getInstance(); 
		      String year = this.GetEnoughLenStr(4, ""+ca.get(Calendar.YEAR));//��ȡ��� 
		      String month=this.GetEnoughLenStr(2, ""+(ca.get(Calendar.MONTH)+1));//��ȡ�·�  
		      String day=this.GetEnoughLenStr(2, ""+ca.get(Calendar.DATE));//��ȡ�� 
		      String minute=this.GetEnoughLenStr(2, ""+ca.get(Calendar.MINUTE));//��  
		      String hour=this.GetEnoughLenStr(2, ""+ca.get(Calendar.HOUR_OF_DAY));//Сʱ  
		      String second=this.GetEnoughLenStr(2, ""+ca.get(Calendar.SECOND));//�� 
		      String WeekOfYear =this.GetEnoughLenStr(2, ""+ca.get(Calendar.DAY_OF_WEEK));  
		      rsStr=rsStr.replace("yyyy", year+"");
		      rsStr=rsStr.replace("MM", month+"");
		      rsStr=rsStr.replace("dd", day+"");
		      rsStr=rsStr.replace("ww", WeekOfYear+"");
		      rsStr=rsStr.replace("hh", hour+"");
		      rsStr=rsStr.replace("mm", minute+"");
		      rsStr=rsStr.replace("ss", second+"");		      
		      return rsStr;
	   }
	   
	   /**
	    * 
	    * @param first
	    * @param second
	    * @param type yyyy-MM-dd
	    * @param time day,hour,min,s
	    * @return
	    */
	   public Long getDateSub(String first,String second,String type,String time){
		   Long rs=null;
		   Date  df=new Date();
		   Date  dc=new Date();
		   SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd");
		   try {
			   df=dft.parse(first);
			   dc=dft.parse(second);
		   } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		   
		    if(time.equals("day"))rs=(df.getTime()-dc.getTime())/(1000 * 60 * 60 * 24);	
		    if(time.equals("hour"))rs=(df.getTime()-dc.getTime())/(1000 * 60 * 60 );	 
		    if(time.equals("min"))rs=(df.getTime()-dc.getTime())/(1000 * 60 );	 
		    if(time.equals("s"))rs=(df.getTime()-dc.getTime())/1000;
		   return rs;		   
	   }
	   /**
	    * ʱ���ʽת��
	    * @param strDate 
	    * @param btype ԭ���ĸ�ʽ
	    * @param etype �����ĸ�ʽ
	    * @return
	    */
	   public String convertDateMode(String strDate,String btype,String etype){
		   String rs="";
		   Date date = new Date();
           
           SimpleDateFormat format = new SimpleDateFormat();
           format.applyPattern(btype);
           
           try {
			date=format.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       format.applyPattern(etype);
	       rs = format.format(date);
		   return rs;
	   }
	   
	   /**
	    * ʱ���ʽת��
	    * @param strDate 
	    * @param btype ԭ���ĸ�ʽ
	    * @param etype �����ĸ�ʽ
	    *  Localized ��Locale.US);//   Ĭ��Locale���й����������Ӣ����ת�����˵�
	    * @return
	    */
	   public String convertDateMode(String strDate,String btype,String etype,Locale local ){
		   String rs="";
		   Date date = new Date();
           
           SimpleDateFormat format = new SimpleDateFormat(btype,local);
           try {
			date=format.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       format.applyPattern(etype);
	       rs = format.format(date);
		   return rs;
	   }
	
	   
	   /**
	    * ʱ���ʽת��
	    * @param strDate 
	    * @param etype �����ĸ�ʽ
	    * @return
	    */
	   public String convertDateMode(String strDate,String etype){
		   String rs="";
		   Date date = new Date();
           
           SimpleDateFormat format = new SimpleDateFormat();

	       format.applyPattern(etype);
	       rs = format.format(date);
		   return rs;
	   }
	  
	  public String getRandomNumber(){
		  String rs="";
		  Random r=new Random();
		  rs=r.nextInt()+"";
		  return rs;
	  }
	  public String getRandomNumber(int num){
		  String rs="";
		  Random r=new Random();
		  rs=r.nextInt(num)+"";
		  return rs;
	  }
	  
	  public String getGuid(){
		  String rs="";
		  UUID uuid = UUID.randomUUID();
		  rs=uuid.toString();
		  return rs;  
	  }
	 
	  public String submitMessage(String urlAddr,String method){
		  String rsStr="";	
		  method=method.toUpperCase();
		  try {
			URL url = new URL(urlAddr.substring(0,urlAddr.indexOf("?")));   
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);    
			conn.setRequestMethod(method);    
			conn.setUseCaches(false);    
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");    
			conn.setRequestProperty("Content-Length", String.valueOf(urlAddr.substring(urlAddr.indexOf("?")+1).length()));    
			conn.setDoInput(true);    
			conn.connect();    
  
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");    
			out.write(urlAddr.substring(urlAddr.indexOf("?")+1));    
			out.flush();    
			out.close();    
			
		    InputStream is=conn.getInputStream();
		    DataInputStream dis=new DataInputStream(is);
		    byte d[]=new byte[dis.available()];
		    dis.read(d);
		    rsStr=new String(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		  return rsStr;
	  }
	  public String submitMessage(String urlnopara,BinMap params,String method){
		  String rsStr="";	
		  method=method.toUpperCase();
		  StringBuffer psb=new StringBuffer();
		  for(int i=0;i<params.size();i++){
			  psb.append(params.getKey(i));
			  psb.append("=");
			  psb.append(params.getValue(i));
			  psb.append("&");
		  }
		  psb.append("");
		  try {
			URL url = new URL(urlnopara);   
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);    
			conn.setRequestMethod(method);    
			conn.setUseCaches(false);    
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");    
			conn.setRequestProperty("Content-Length", String.valueOf(psb.length()));    
			conn.setDoInput(true);    
			conn.connect();    
  
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");    
			out.write(psb.toString());    
			out.flush();    
			out.close();    
			
		    InputStream is=conn.getInputStream();
		    DataInputStream dis=new DataInputStream(is);
		    byte d[]=new byte[dis.available()];
		    dis.read(d);
		    rsStr=new String(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		  return rsStr;
	  }
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
