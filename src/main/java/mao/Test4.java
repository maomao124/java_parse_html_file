package mao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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


    public static void main(String[] args) throws IOException
    {
        URL url = new URL("https://bbs.3dmgame.com/forum.php");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        assert inputStream != null;
        String s = load(inputStream);
        System.out.println(s);
        //Document document = Jsoup.parse();
    }
}
