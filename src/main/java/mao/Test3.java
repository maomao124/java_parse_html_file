package mao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Project name(项目名称)：解析html文件
 * Package(包名): mao
 * Class(类名): Test3
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 20:13
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test3
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


    public static void main(String[] args) throws IOException
    {
        InputStream inputStream = Test3.class.getClassLoader().getResourceAsStream("forum.html");
        assert inputStream != null;
        Document document = Jsoup.parse(load(inputStream));
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
                System.out.println(gameName + "\t\t" + hot);
            }
        }
    }
}
