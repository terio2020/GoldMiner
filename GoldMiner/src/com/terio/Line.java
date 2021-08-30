package com.terio;

import java.awt.*;
import java.net.URL;

public class Line {
    // 原点坐标
    int orgx=380;
    int orgy=180;
    // 终点坐标
    int endx=500;
    int endy=500;
    // 线长
    double length = 100;
    double MIN_length = 100;
    double MAX_length = 750;
    double theta = 0;
    // 方向
    int dir = 1;
    // 状态 0:摇摆；1:抓取 2:收回 3:抓取返回
    int state = 0;

    // 爪子
    URL url = Line.class.getResource("imgs/hook.png");
    Image hook = Toolkit.getDefaultToolkit().getImage(url);

    GameWin frame;

    public Line(GameWin frame) {
        this.frame = frame;
    }


    // 碰撞检测
    public void logic(){
        for (Object gold : this.frame.objectList) {
            if (endx+25 > gold.x && endx-25 < gold.x+ gold.width && endy > gold.y && endy < gold.y+gold.heigth){
                state=3;
                gold.flag=true;
            }
            if (endx < -40 || endx > 800) {
                state=2;
            }
        }
    }

    // 绘制线
    public void lines(Graphics g) {
        endx = (int) (orgx + length*Math.cos(theta * Math.PI));
        endy = (int) (orgy + length*Math.sin(theta * Math.PI));
        g.setColor(Color.RED);
        g.drawLine(orgx-1, orgy-1, endx-1, endy-1);
        g.drawLine(orgx, orgy, endx, endy);
        g.drawLine(orgx+1, orgy+1, endx+1, endy+1);
        g.drawImage(hook, endx-36, endy-2, null);
    }

    public void paintSelf(Graphics g) {
        logic();
        switch (state) {
            case 0:
                if (theta < 0.1) {
                    dir = 1;
                } else if (theta > 0.9) {
                    dir = -1;
                }
                theta = theta + 0.005 * dir;
                lines(g);
                break;
            case 1:
                if (length <= MAX_length) {
                    length = length + 15;    // 抓取速度
                    lines(g);
                } else {
                    state = 2;
                }
                break;
            case 2:
                if (length>=MIN_length) {
                    length = length - 15;
                    lines(g);
                } else {
                    state = 0;
                }
                break;
            case 3:
                int m = 1;  // 接收质量
                if (length >= MIN_length) {
                    length = length - 10;
                    lines(g);
                    for (Object gold : this.frame.objectList) {
                        if (gold.flag) {
                            m = gold.m;
                            gold.x = endx - gold.getWidth()/2;
                            gold.y = endy;
                            if (length <= MIN_length) {
                                gold.x=-150;
                                gold.y=-150;
                                gold.flag=false;
                                Bg.count+=gold.score;       // 计分
                                Bg.isUesd = false;
                                state = 0;
                            }
                            if (Bg.isUesd) {
                                if (gold.type==1) {
                                    m = 1;
                                } else if (gold.type == 2) {
                                    gold.x = -150;
                                    gold.y = -150;
                                    gold.flag = false;
                                    Bg.isUesd = false;
                                    state = 2;
                                }
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(m);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void reStart() {
        length = 100;
        theta = 0;
    }
}
