package mao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;

import java.util.Map;

/**
 * Project name(项目名称)：解析html文件
 * Package(包名): mao
 * Class(类名): Test4
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 20:24
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test4
{
    /**
     * 加载
     *
     * @param inputStream 输入流
     * @return {@link String}
     * @throws IOException IOException
     */
    public static String load(InputStream inputStream) throws IOException
    {
        if (inputStream == null)
        {
            return null;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuffer = new StringBuilder();
        String str = null;
        while ((str = bufferedReader.readLine()) != null)
        {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    /**
     * 解析
     *
     * @param urlString url字符串
     * @return map集合，key为游戏名称，值为热度值
     * @throws IOException IOException
     */
    public static Map<String, String> parse(String urlString) throws IOException
    {
        if (urlString == null)
        {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        //设置请求头，让服务器识别为电脑端
        urlConnection.setRequestProperty("sec-ch-ua-platform", "Windows");
        urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.102 Safari/537.36 Edg/104.0.1293.70");
        InputStream inputStream = urlConnection.getInputStream();
        assert inputStream != null;
        String s = load(inputStream);
        //System.out.println(s);
        Document document = Jsoup.parse(s);
        //System.out.println(document);

        Element div = document.getElementById("category_441");
        //System.out.println(div);
        assert div != null;
        Elements tr = div.getElementsByTag("tr");
        for (Element element : tr)
        {
            //System.out.println(element);
            Elements td = element.getElementsByTag("td");
            for (Element element1 : td)
            {
                //System.out.println(element1);
                Element dt = element1.getElementsByTag("dt").first();
                //System.out.println(dt);
                assert dt != null;
                Element a = dt.getElementsByTag("a").first();
                assert a != null;
                String gameName = a.html();
                Element em = dt.getElementsByTag("em").first();
                assert em != null;
                String hot = em.html();
                hot = hot.substring(1, hot.length() - 1);
                //System.out.println(gameName + "\t\t" + hot);
                map.put(gameName, hot);
            }
        }
        return map;
    }


    /**
     * 打印
     *
     * @param map map集合
     */
    public static void print(Map<String, String> map)
    {
        System.out.println("+--游戏热度--|----游戏名称------");
        for (String key : map.keySet())
        {
            String value = map.get(key);
            System.out.println("|--" + value + "--|" + key);
        }
    }


    public static void main(String[] args) throws IOException
    {
        Map<String, String> map = parse("https://bbs.3dmgame.com/forum.php");
        //System.out.println(map);
        print(map);
    }
}
