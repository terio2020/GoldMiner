package com.terio;

import java.awt.*;
import java.net.URL;


public class Bg {

    // 总分
    static int count=0;
    // 药水数量
    static int waterNum = 3;
    // 药水使用状态
    static boolean isUesd = false;
    // 关卡数
    static int level = 1;
    // 每关目标得分
    int goal = level * level * 10;
    // 开始时间
    long startTime;
    // 当前时间
    long curTime;
    // 药水价格
    int price = (level-1) * 10;
    // 是否进入商店 购买
    boolean isBuy = false;

    URL url = GameWin.class.getResource("imgs/bg.jpg");
    Image bg = Toolkit.getDefaultToolkit().getImage(url);
    URL url1 = GameWin.class.getResource("imgs/bg1.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage(url1);
    URL url2 = GameWin.class.getResource("imgs/peo.png");
    Image peo = Toolkit.getDefaultToolkit().getImage(url2);
    URL url3 = GameWin.class.getResource("imgs/water.png");
    Image water = Toolkit.getDefaultToolkit().getImage(url3);

    public void paintSelf(Graphics g) {
        g.drawImage(bg1, 0, 0, null);
        g.drawImage(bg, 0, 200, null);
        g.drawImage(peo, 310, 50, null);
        switch (GameWin.gameState) {
            case 0:
                drawStr(g, 80, Color.WHITE, "准备开始游戏", 140, 400);
                drawStr(g, 40, Color.WHITE, "点击鼠标右键开始", 200, 500);
                break;
            case 1:
                g.drawImage(water, 550, 40, null);
                // 绘制字符串
                drawStr(g, 30, Color.BLACK, "积分 ： " + count, 30, 150);
                drawStr(g, 30, Color.GREEN, " * " + waterNum, 610, 100);
                drawStr(g, 30, Color.GRAY, "第 "+level+" 关", 30, 60);
                drawStr(g, 30, Color.GRAY, "目标积分 ： " + goal, 30, 110);
                // 时间组件
                curTime = System.currentTimeMillis();
                long tim = 20 - (curTime - startTime) / 1000;
                drawStr(g, 30, Color.CYAN, "时间 ： "+tim, 550, 150);
                break;
            case 2:
                g.drawImage(water, 180, 400, null);
                drawStr(g, 30, Color.GREEN, "药水价格：" + price , 300, 450);
                drawStr(g, 30, Color.GREEN, "是否购买？", 300, 550);
                drawStr(g, 20, Color.GREEN, "左键购买 右键下一关", 280, 600);
                if (isBuy && count >= price) {
                    count = count - price;
                    waterNum++;
                    isBuy=false;
                    startTime = System.currentTimeMillis();
                }
                g.drawImage(water, 550, 40, null);
                drawStr(g, 30, Color.GREEN, " * " + waterNum, 610, 100);
                drawStr(g, 30, Color.GRAY, "第 "+level+" 关", 30, 60);
                drawStr(g, 30, Color.BLACK, "积分:  " + count, 30, 150);
                break;
            case 3:
                drawStr(g, 70, Color.RED, "游戏失败", 240, 500);
                drawStr(g, 60, Color.RED, "积分: " + count, 240, 550);
                break;
            case 4:
                drawStr(g, 70, Color.GREEN, "游戏成功", 240, 500);
                drawStr(g, 60, Color.GREEN, "积分: " + count, 240, 550);
                break;
            default:
        }

    }

    public boolean gameTime(){
        long tim = (curTime - startTime) / 1000;
        if (tim > 20) {
            return true;
        }
        return false;
    }

    // 重置游戏
    public void reStart(){
        level = 1;
        goal = level * 25;
        count = 0;
        waterNum = 3;
        isUesd = false;
    }

    public static void drawStr(Graphics g, int size, Color color, String str, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("仿宋", Font.BOLD, size));
        g.drawString(str, x, y);
    }
}
