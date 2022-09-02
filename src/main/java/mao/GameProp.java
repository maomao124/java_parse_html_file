package mao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Project name(项目名称)：解析html文件
 * Package(包名): mao
 * Class(类名): GameProp
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 21:42
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class GameProp
{
    /**
     * url
     */
    private static final String url;
    /**
     * hot div id
     */
    private static final String hotDivId;

    static
    {
        String url1;
        String hotDivId1;
        InputStream inputStream = GameProp.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try
        {
            properties.load(inputStream);
            url1 = properties.getProperty("url");
            hotDivId1 = properties.getProperty("hot.div.id");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            url1 = "https://bbs.3dmgame.com/forum.php";
            hotDivId1 = "category_441";
        }

        url = url1;
        hotDivId = hotDivId1;
    }

    /**
     * 获取url
     *
     * @return {@link String}
     */
    public static String getUrl()
    {
        return url;
    }

    /**
     * getHotDivId
     *
     * @return {@link String}
     */
    public static String getHotDivId()
    {
        return hotDivId;
    }
}
