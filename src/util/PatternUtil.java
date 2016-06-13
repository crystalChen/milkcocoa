package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * PatternUtil
 *
 * @author crystalChen
 * @date 16/6/11 12:00
 */
public class PatternUtil {
    public static List<String> getURLs(String text) {
        String url = "href=\"http://www\\.milk.*?>";
        Pattern pattern = Pattern.compile(url);
        Matcher m = pattern.matcher(text);
        List<String> urlList = new ArrayList<>(64);
        String _url = null;
        while (m.find( )) {
            _url = m.group().replaceAll("href=|>|\"", "").split(" ")[0];
            urlList.add(_url);
//            System.out.println("Found value: " + _url);
        }
        return urlList;
    }

    public static List<String> getPictrueURLs(String text) {
        String url = "src=[\"\'](http.+\\.(jpg|gif|png))[\"\']";
        Pattern pattern = Pattern.compile(url);
        Matcher m = pattern.matcher(text);
        List<String> urlList = new ArrayList<>(64);
        String _url = null;
        while (m.find( )) {
            _url = m.group().replaceAll("src=\"|\"", "");
            urlList.add(_url);
            System.out.println("Found pictrue: " + _url);
        }
        return urlList;
    }

}
