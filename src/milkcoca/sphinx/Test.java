package milkcoca.sphinx;

import util.PatternUtil;
import util.URLUtil;
import util.UrlReader;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Test
 *
 * @author crystalChen
 * @date 16/6/13 18:40
 */
public class Test {

    public static void main(String[] args) {
        String url = "http://www.milkcocoa.co.kr/";
        String s = URLUtil.getContentFromURL(url, "euc-kr");
        PatternUtil.getURLs(s);
        Deque<String> deque = new ArrayDeque<>(2048);
        deque.addLast(url);
        Set<String> visited_set = new HashSet<>(256);
        Set<String> unvisit_set = new HashSet<>(2048);
        int count = 0;
        while( !deque.isEmpty() ) {
            System.out.println("===========   " + count++);
            System.out.println("===========deque: " + deque.size());
            System.out.println("===========visited_set: " + visited_set.size());
            System.out.println("===========visiting url: " + deque.getFirst());
//            while (visited_set.contains(deque.getFirst())) {
//                deque.pop();
//                System.out.println(" ===== ======     pop     ====== ===== =====");
//            }
            String first_url = deque.getFirst();
            System.out.println("==== ====  getContentFromURL ............");
//            String content = getContentFromURL(first_url, "utf-8");
            String content = null;
            UrlReader urlReader = new UrlReader(first_url, "euc-kr");
            Thread thread = new Thread(urlReader);
            thread.start();
            long start = System.currentTimeMillis();
            long end = start;
            try {
                while( (end - start) < 5000 && null == (content=urlReader.get_pageContext()) ) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    end = System.currentTimeMillis();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /** 如果读取流readLine()  */
            if(content == null) {
                System.out.println("====== == =======  ************   ==== ==== =======");
                urlReader.closeBufferedReader();
                continue;
            }

            List<String> urlList = PatternUtil.getURLs(content);
            List<String> pictrueURLsList = PatternUtil.getPictrueURLs(content);
            //1.若未访问，入队列
            for(String _url : urlList) {
                if ( !visited_set.contains(_url) && !unvisit_set.contains(_url)) {
                    deque.addLast(_url);
                    unvisit_set.add(_url);
                }
            }

            //2.访问完毕，出队列
            deque.pop();
            //3.添加到已访问set
            visited_set.add(first_url);

        }
        System.out.println(visited_set.size());
    }
}
