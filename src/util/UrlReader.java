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
 * UrlReader
 *
 * @author crystalChen
 * @date 16/6/12 08:31
 */
public class UrlReader implements Runnable {

    private final String url;
    private final String encoding;

    private volatile String pageContext;

    private BufferedReader in;

    public UrlReader(String _url, String _encoding) {
        url = _url;
        encoding = _encoding;
    }

    public void read() {
//        ReaderThread readerThread = new ReaderThread();
//        Thread listener = new Thread(readerThread);
//        listener.setDaemon(true);
//        listener.start();
//        boolean success = false;
//        try {
//            success = latch.await(20000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//        }
    }

    public String get_pageContext() {
        return pageContext;
    }

    public void closeBufferedReader() {
        if( null != in ) {
            try {
                in.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();

        try {
            //构建一URL对象
            URL _url = new URL(url);
            //使用openStream得到一输入流并由此构造一个BufferedReader对象
            in = new BufferedReader(new InputStreamReader(_url
                    .openStream(), encoding));
            String line;
            //读取www资源
            System.out.println("====== ========= readLine......");
            while ((line = in.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            pageContext = sb.toString();
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
        } catch (Exception ex) {
            System.err.println("流被中断：" + ex);
        } finally {
            try {
                if(null != in)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
