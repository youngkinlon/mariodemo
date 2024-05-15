package com.SuperMario.obj;

import com.SuperMario.util.BackGround;
import com.SuperMario.util.StaticValue;

import java.awt.image.BufferedImage;

public class Obstacle  {
    private int x;
    private int y;
    private int type;
    private BufferedImage show = null;
    //定义当前背景对象
    private BackGround bg = null;

    public Obstacle() {
    }

    public Obstacle(int x, int y, int type, BackGround bg)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.obstacle.get(type);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BackGround getBg() {
        return bg;
    }

    public BufferedImage getShow() {
        return show;
    }
}

