package cn.artwebs.utils;

public class ByteIntLong {
	 public final static byte[] getBytes(short s, boolean asc) {
	    byte[] buf = new byte[2];
	    if (asc)
	      for (int i = buf.length - 1; i >= 0; i--) {
	        buf[i] = (byte) (s & 0x00ff);
	        s >>= 8;
	      }
	    else
	      for (int i = 0; i < buf.length; i++) {
	        buf[i] = (byte) (s & 0x00ff);
	        s >>= 8;
	      }
	    return buf;
	  }
	  public final static byte[] getBytes(int s, boolean asc) {
	    byte[] buf = new byte[4];
	    if (asc)
	      for (int i = buf.length - 1; i >= 0; i--) {
	        buf[i] = (byte) (s & 0x000000ff);
	        s >>= 8;
	      }
	    else
	      for (int i = 0; i < buf.length; i++) {
	        buf[i] = (byte) (s & 0x000000ff);
	        s >>= 8;
	      }
	    return buf;
	  }
	  public final static byte[] getBytes(long s, boolean asc) {
	    byte[] buf = new byte[8];
	    if (asc)
	      for (int i = buf.length - 1; i >= 0; i--) {
	        buf[i] = (byte) (s & 0x00000000000000ff);
	        s >>= 8;
	      }
	    else
	      for (int i = 0; i < buf.length; i++) {
	        buf[i] = (byte) (s & 0x00000000000000ff);
	        s >>= 8;
	      }
	    return buf;
	  }
	  public final static short getShort(byte[] buf, boolean asc) {
	    if (buf == null) {
	      throw new IllegalArgumentException("byte array is null!");
	    }
	    if (buf.length > 2) {
	      throw new IllegalArgumentException("byte array size > 2 !");
	    }
	    short r = 0;
	    if (asc)
	      for (int i = buf.length - 1; i >= 0; i--) {
	        r <<= 8;
	        r |= (buf[i] & 0x00ff);
	      }
	    else
	      for (int i = 0; i < buf.length; i++) {
	        r <<= 8;
	        r |= (buf[i] & 0x00ff);
	      }
	    return r;
	  }
	  public final static int getInt(byte[] buf, boolean asc) {
	    if (buf == null) {
	      throw new IllegalArgumentException("byte array is null!");
	    }
	    if (buf.length > 4) {
	      throw new IllegalArgumentException("byte array size > 4 !");
	    }
	    int r = 0;
	    if (asc)
	      for (int i = buf.length - 1; i >= 0; i--) {
	        r <<= 8;
	        r |= (buf[i] & 0x000000ff);
	      }
	    else
	      for (int i = 0; i < buf.length; i++) {
	        r <<= 8;
	        r |= (buf[i] & 0x000000ff);
	      }
	    return r;
	  }
	  
	  public final static long getLong(byte[] buf, boolean asc) {
		    if (buf == null) {
		      throw new IllegalArgumentException("byte array is null!");
		    }
		    if (buf.length > 8) {
		      throw new IllegalArgumentException("byte array size > 8 !");
		    }
		    long r = 0;
		    if (asc)
		      for (int i = buf.length - 1; i >= 0; i--) {
		        r <<= 8;
		        r |= (buf[i] & 0x00000000000000ff);
		      }
		    else
		      for (int i = 0; i < buf.length; i++) {
		        r <<= 8;
		        r |= (buf[i] & 0x00000000000000ff);
		      }
		    return r;
	  }

	public static String bytesToHexString(byte[] src){
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
}
