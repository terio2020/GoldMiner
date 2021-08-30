package com.terio;

import java.awt.*;

/**
 * Created by Terto_MY on 2021-08-30 15:21
 */
public class Object {
    // 坐标
    int x;
    int y;
    // 宽高
    int width;
    int heigth;
    // 图片
    Image img;
    // 标记:是否能移动
    boolean flag;
    // 物体质量
    int m;
    // 积分
    int score;
    // 抓取物体的标记
    int type;

    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public int getWidth() {
        return width;
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, width, heigth);
    }
}
