package com.sram.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-21 上午10:41:13
 */
public class MathUtil {
	private  static	Map<String, String> map=new HashMap<String, String>();
	/**
	 * <p>功能:获取指定范围之内的不重复随机数</p> 
	 * @author 杨楷
	 * @date 2015-3-21 上午10:41:38 
	 * @param min 最小值
	 * @param max 最大值
	 * @param n 生成个数
	 * @return
	 */
	public static int[] randomCommon(int min, int max, int n){  
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;  
	}  
	
	/**
	 * <p>功能:根据难易度百分比算出每种难易度的题目数量</p> 
	 * 四舍五入得到题目数量有+1-1的误差
	 * 有+1误差的时候题目数最大的-1
	 * 有-1误差的时候题目数最大的+1
	 * @author 杨楷
	 * @date 2015-3-23 下午04:11:10 
	 * @param count
	 * @param easy
	 * @param normal
	 * @param hard
	 * @return
	 */
	public static int[] calcCount(int count,double easy,double normal,double hard){
		int easyCount=(int)Math.rint(count*easy);
		int normalCount=(int)Math.rint(count*normal);
		int hardCount=(int)Math.rint(count*hard);
		int[] array={easyCount,normalCount,hardCount};
		if (easyCount+normalCount+hardCount>count) {
			int max=Math.max(Math.max(easyCount, normalCount), hardCount);
			if (easyCount==max) {
				array[0]=max-1;
			}else if (normalCount==max) {
				array[1]=max-1;
			}else if (hardCount==max) {
				array[2]=max-1;
			}
			return array;
		}
		if (easyCount+normalCount+hardCount<count) {
			int min=Math.min(Math.min(easyCount, normalCount), hardCount);
			if (easyCount==min) {
				array[0]=min+1;
			}else if (normalCount==min) {
				array[1]=min+1;
			}else if (hardCount==min) {
				array[2]=min+1;
			}
			return array;
		}
		return array;
	}
	
	/**
	 * 
	 * <p>功能:</p> 
	 * @author 杨楷
	 * @date 2015-3-26 上午10:06:13 
	 * @param str
	 * @param type 快速智能,考点专项
	 * @return
	 */
	public static String generateOrder(String str,String type) {
		if (map.containsKey(str+"-"+type)) {
			String temp = map.get(str+"-"+type);
			temp=temp.substring(temp.lastIndexOf("-"));
			String strDate=temp.substring(0,7);
			String cunrrentDate = new SimpleDateFormat("yyMMdd").format(new Date());
			if(!strDate.equals(cunrrentDate)){
				strDate=cunrrentDate;
			}
			String strOrder=temp.substring(7);
			Long orderNumber=Long.valueOf(strOrder)+1;
			StringBuilder sb = new StringBuilder(""+orderNumber);
			
			while(sb.length() < 8){
				sb.insert(0, "0");
			}
			map.put(str+"-"+type, str+"-"+type+"-"+strDate+sb);
		}else{
			String cunrrentDate = new SimpleDateFormat("yyMMdd").format(new Date());
			map.put(str+"-"+type, str+"-"+type+"-"+cunrrentDate+"00000001");
		}
		
		return map.get(str+"-"+type);
	}
	
	/**
	 * 进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue
	 * <p>功能:</p> 
	 * @author 杨楷
	 * @date 2015-1-15 下午02:45:03 
	 * @param str
	 * @return
	 */
   public static String MD5Generator(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
  
            messageDigest.reset();  
  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString().toUpperCase();  
    } 
}
