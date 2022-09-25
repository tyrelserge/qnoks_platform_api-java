package com.qnoks.qnoksplatformapi;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Util
{

  private static Util util;

  public static Util getInstance() {
    if (util == null) {
      util = new Util();
    }
    return util;
  }

  public static String getUUID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

  public static  String thousandSeparator(int n)
   {
      String str=n+"";
      StringBuilder sb=new StringBuilder();
      for(int i=str.length()-1;i>=0;){
         sb.append(str.charAt(i));
         i--;
         if(i==-1)break;
         sb.append(str.charAt(i));
         i--;
         if(i==-1)break;
         sb.append(str.charAt(i));
         i--;
         if(i==-1)break;
         sb.append(" ");
      }
      return sb.reverse().toString();
   }

}
