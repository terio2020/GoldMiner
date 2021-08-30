package com.terio;

import java.awt.*;
import java.net.URL;


public class Gold extends Object {

    public Gold() {
        this.x = (int) (Math.random()*700);
        this.y = (int) (Math.random()*550+300);
        this.width = 36;
        this.heigth = 36;
        this.flag = false;
        this.m = 15;
        this.score = 5;
        this.type = 1;
        URL url = Gold.class.getResource("imgs/gold0.gif");
        this.img = Toolkit.getDefaultToolkit().getImage(url);
    }
}

class GoldMid extends Gold{
    public GoldMid() {
        this.width = 52;
        this.heigth = 52;
        this.m = 30;
        this.score = 15;
        this.type = 1;
        URL url = Gold.class.getResource("imgs/gold1.gif");
        this.img = Toolkit.getDefaultToolkit().getImage(url);
    }
}

class GoldPlus extends Gold{
    public GoldPlus() {
        this.x = (int) (Math.random()*650);
        this.width = 105;
        this.heigth = 105;
        this.m = 60;
        this.score = 30;
        this.type = 1;
        URL url = Gold.class.getResource("imgs/gold2.gif");
        this.img = Toolkit.getDefaultToolkit().getImage(url);
    }
}