package mao;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;


/**
 * Project name(项目名称)：解析html文件
 * Package(包名): mao
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 19:03
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
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
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("test.html");
        assert inputStream != null;
        Document document = Jsoup.parse(load(inputStream));
        Elements html = document.getElementsByTag("html");
        //System.out.println(html);
        Elements body = html.select("body");
        //System.out.println(body);
        Elements tr = body.select("tr");
        //System.out.println(tr);
        for (Element element : tr)
        {
            //System.out.println(element+"\n");
            Elements td = element.getElementsByTag("td");
            //System.out.println(td);
            for (Element element1 : td)
            {
                //System.out.println(element1 + "\n");
                String s = element1.html();
                System.out.print(s + "\t\t");
            }
            System.out.println();
        }
    }
}
