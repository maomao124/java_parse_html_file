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

import java.util.*;

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

        Element div = document.getElementById(GameProp.getHotDivId());
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
     * 映射到列表
     *
     * @param map map集合
     * @return {@link List}<{@link Game}>
     */
    public static List<Game> mapToList(Map<String, String> map)
    {
        List<Game> list = new LinkedList<>();
        for (String key : map.keySet())
        {
            String value = map.get(key);
            list.add(new Game(key, Integer.parseInt(value)));
        }
        return list;
    }

    /**
     * 排序
     *
     * @param list 列表
     */
    public static void sort(List<Game> list)
    {
        list.sort(new Comparator<Game>()
        {
            @Override
            public int compare(Game o1, Game o2)
            {
                return o2.getHot() - o1.getHot();
            }
        });
    }


    /**
     * 打印
     *
     * @param list 列表
     */
    public static void print(List<Game> list)
    {
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|  游戏热度\t\t\t游戏名称                                 |");
        System.out.println("+-----------------------------------------------------------+");
        for (Game game : list)
        {
            int hot = game.getHot();
            String gameName = game.getGameName();
            //System.out.println(hot + "\t\t\t" + gameName);
            //System.out.printf("|%5d\t\t\t%-30s|\n", hot, gameName);
            String format = String.format("|%6d\t\t\t%-36s", hot, gameName);
            //46
            System.out.println(format);
        }
        System.out.println("+-----------------------------------------------------------+");
    }


    public static void main(String[] args) throws IOException
    {
        Map<String, String> map = parse(GameProp.getUrl());
        //System.out.println(map);
        List<Game> list = mapToList(map);
        sort(list);
        //System.out.println(list);
        print(list);

    }
}
