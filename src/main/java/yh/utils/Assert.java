package yh.utils;

public class Assert {
     public static void isEmpty(String data, String msg){
         if(StringUtils.isEmpty(data)){
             throw new RuntimeException(msg);
         }
     }
}
