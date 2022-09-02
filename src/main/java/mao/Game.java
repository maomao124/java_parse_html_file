package mao;

/**
 * Project name(项目名称)：解析html文件
 * Package(包名): mao
 * Class(类名): Game
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/2
 * Time(创建时间)： 21:15
 * Version(版本): 1.0
 * Description(描述)： 无
 */


public class Game
{
    /**
     * 游戏名称
     */
    private String gameName;
    /**
     * 热度
     */
    private int hot;

    /**
     * Instantiates a new Game.
     */
    public Game()
    {

    }

    /**
     * Instantiates a new Game.
     *
     * @param gameName the game name
     * @param hot      the hot
     */
    public Game(String gameName, int hot)
    {
        this.gameName = gameName;
        this.hot = hot;
    }

    /**
     * Gets game name.
     *
     * @return the game name
     */
    public String getGameName()
    {
        return gameName;
    }

    /**
     * Sets game name.
     *
     * @param gameName the game name
     */
    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }

    /**
     * Gets hot.
     *
     * @return the hot
     */
    public int getHot()
    {
        return hot;
    }

    /**
     * Sets hot.
     *
     * @param hot the hot
     */
    public void setHot(int hot)
    {
        this.hot = hot;
    }

    @Override
    @SuppressWarnings("all")
    public String toString()
    {
        final StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("gameName：").append(gameName).append('\n');
        stringbuilder.append("hot：").append(hot).append('\n');
        return stringbuilder.toString();
    }
}
