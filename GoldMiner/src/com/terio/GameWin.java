package com.terio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWin extends JFrame {

    // 0 等待 1 游戏中 2 商店 3 失败 4 胜利
    static int gameState;

    // 多个金块
    List<Object> objectList = new ArrayList<Object>();
    {
        boolean isPlace = true;
        for (int i = 0; i < 3*Bg.level+8; i++) {
            Gold gold;
            double random = Math.random();
            if (random<0.2) {
                gold = new GoldMid();
            } else if (random > 0.8) {
                gold = new GoldPlus();
            } else {
                gold = new Gold();
            }
            for (Object obj : objectList) {
                if (gold.getRec().intersects(obj.getRec())) {
                    isPlace = false;
                }
            }
            if (isPlace) {
                objectList.add(gold);
            } else {
                isPlace = true;
                i--;
            }
        }

        isPlace = true;
        for (int i = 0; i < Bg.level+2; i++) {
            Rock rock = new Rock();
            for (Object obj : objectList) {
                if (rock.getRec().intersects(obj.getRec())) {
                    isPlace = false;
                }
            }
            if (isPlace) {
                objectList.add(rock);
            } else {
                isPlace = true;
                i--;
            }
        }
    }


    Bg bg = new Bg();
    Line line = new Line(this);

    // 定义画布
    Image offScreenImage;

    public void launch() {
        this.setVisible(true);
        this.setSize(768, 1000);
        this.setLocationRelativeTo(null);
        this.setTitle("黄金矿工——TMY");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (gameState) {
                    case 0:
                        if (e.getButton() == 3) {
                            gameState = 1;
                            bg.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 1:
                        if (e.getButton() == 1 && line.state==0) {
                            line.state=1;
                        }

                        if (e.getButton() == 3 && line.state == 3 && Bg.waterNum > 0) {
                            Bg.waterNum--;
                            Bg.isUesd=true;
                        }
                        break;
                    case 2:
                        if (e.getButton() == 1) {
                            bg.isBuy = true;
                        } else if (e.getButton() == 3) {
                            gameState = 1;
                            bg.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 3:
                    case 4:
                        if (e.getButton() == 1) {
                            gameState = 0;
                            bg.reStart();
                            line.reStart();
                        }
                        break;
                    default:
                }
            }
        });

        while (true) {
            repaint();
            nextLevel();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void nextLevel() {
        if (bg.gameTime() && gameState == 1) {
            if (Bg.count >= bg.goal) {
                if (Bg.level >= 5) {
                    gameState = 4;
                } else {
                    gameState = 2;
                    Bg.level++;
                }
            } else {
                gameState = 3;
            }

            dispose();
            GameWin new_gameWin = new GameWin();
            new_gameWin.launch();
        }

    }

    @Override
    public void paint(Graphics g) {
        offScreenImage = this.createImage(768, 1000);
        Graphics gImage = offScreenImage.getGraphics();
        bg.paintSelf(gImage);

        if (gameState == 1){
            for(Object obj : objectList){
                obj.paintSelf(gImage);
            }
            line.paintSelf(gImage);
        }
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void main(String[] args) {
        new GameWin().launch();
    }
}
