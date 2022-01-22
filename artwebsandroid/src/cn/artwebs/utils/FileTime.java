package cn.artwebs.utils;

import java.util.Date;

public class FileTime {
	 
	   /**
	     * Unix 时间 1970-01-01 00:00:00 与 Win32 FileTime 时间 1601-01-01 00:00:00
	     * 毫秒数差 
	     */
	    public final static long UNIX_FILETIME_DIFF = 11644473600000L;
	     
	    /**
	     * Win32 FileTime 采用 100ns 为单位的，定义 100ns 与 1ms 的倍率
	     */
	    public final static int MILLISECOND_MULTIPLE = 10000;
	    
	   /**
	     * 将 Win32 的 FileTime 结构转为 Java 中的 Date 类型
	     * @param fileTime
	     * @return
	     */
	    public static Date fileTime2Date(long fileTime) {
	        return new Date(fileTime / MILLISECOND_MULTIPLE - UNIX_FILETIME_DIFF);
	    }
	    
	    /**
	     * 将 Java 中的 Date 类型转为 Win32 的 FileTime 结构
	     * @param date
	     * @return
	     */
	    public static long date2FileTime(Date date) {
	        return (UNIX_FILETIME_DIFF + date.getTime()) * MILLISECOND_MULTIPLE;
	    }
}
