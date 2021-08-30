package com.terio;

import java.awt.*;
import java.net.URL;

/**
 * Created by Terto_MY on 2021-08-30 15:56
 */
public class Rock extends Object {
    public Rock() {
        this.x = (int) (Math.random()*700);
        this.y = (int) (Math.random()*550+300);
        this.width = 71;
        this.heigth = 71;
        this.flag = false;
        this.m = 45;
        this.score = Math.max(1, (int) (Math.random()*5));
        this.type = 2;
        URL url = Rock.class.getResource("imgs/rock1.png");
        this.img = Toolkit.getDefaultToolkit().getImage(url);
    }

}
