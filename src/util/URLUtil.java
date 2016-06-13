package util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * URLUtil
 *
 * @author crystalChen
 * @date 16/6/11 11:42
 */
public class URLUtil {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static String getContentFromURL(String _url, String encoding) {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = null;
        try {
            //构建一URL对象
            URL url = new URL(_url);
            //使用openStream得到一输入流并由此构造一个BufferedReader对象
            in = new BufferedReader(new InputStreamReader(url
                    .openStream(), encoding));
            String line;
            //读取www资源
            System.out.println("====== ========= readLine......");
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }


//            char[]   cs   =   new   char[1024 *   16];
//            while(true) {
//                int count = in.read(cs);
//                if( -1 == count) {
//                    break;
//                } else {
//                    sb.append(cs, 0, count);
//                }
//            }
            in.close();
            latch.countDown();
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            try {
                if(null != in)
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }




}
