package com.SuperMario.util;

import com.SuperMario.obj.Obstacle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    private BufferedImage bgImage = null;

    private int sort;
    private boolean flag;
    private List<Obstacle> obstacleList = new ArrayList<>();
    private BufferedImage gan = null;
    //用于存放城堡
    private BufferedImage tower = null;
    public BackGround()
    {

    }
    public BackGround(int sort,boolean flag)
    {
        this.sort=sort;
        this.flag=flag;
        if (flag) {
            bgImage = StaticValue.bg2;
        } else {
            bgImage = StaticValue.bg;
        }
        //第一关
        if(sort==1)
        {
            //绘制地面
            for(int i=0;i<27;i++)
            {
                obstacleList.add(new Obstacle(i*30,420,1,this));
            }
            for (int j=0;j<=120;j+=30)
            {
                for (int i=0;i<27;i++)
                {
                    obstacleList.add(new Obstacle(i*30,570-j,2,this));
                }

            }
            //绘制砖块A
            for (int i=120;i<=150;i+=30)
            {
                obstacleList.add(new Obstacle(i,300,7,this));
            }
            //绘制砖块B-F
            for (int i=300;i<=570;i+=30)
            {
                if (i==360||i==390||i==480||i==510||i==540)
                {
                    obstacleList.add(new Obstacle(i,300,7,this));
                }
                else {
                    obstacleList.add(new Obstacle(i,300,0,this));
                }
            }

            //绘制砖块G
            for (int i=420;i<=450;i+=30)
            {
                obstacleList.add(new Obstacle(i,240,7,this));
            }

            //绘制水管
            for (int i = 360;i<=600;i+=25)
            {
                if (i==360)
                {
                    obstacleList.add(new Obstacle(620,i,3,this));
                    obstacleList.add(new Obstacle(645,i,4,this));
                }
                else {
                    obstacleList.add(new Obstacle(620,i,5,this));
                    obstacleList.add(new Obstacle(645,i,6,this));
                }

            }

        }
        //判断第二关
        if(sort == 2){
            //绘制地面
            for(int i=0;i<27;i++)
            {
                obstacleList.add(new Obstacle(i*30,420,1,this));
            }
            for (int j=0;j<=120;j+=30)
            {
                for (int i=0;i<27;i++)
                {
                    obstacleList.add(new Obstacle(i*30,570-j,2,this));
                }

            }
            //绘制第一根水管
            for(int i = 360;i <= 600;i += 25){
                if(i == 360){
                    obstacleList.add(new Obstacle(60,i,3,this));
                    obstacleList.add(new Obstacle(85,i,4,this));
                }
                else{
                    obstacleList.add(new Obstacle(60,i,5,this));
                    obstacleList.add(new Obstacle(85,i,6,this));
                }
            }
            //绘制第二根水管
            for(int i = 330;i <= 600;i += 25){
                if(i == 330){
                    obstacleList.add(new Obstacle(620,i,3,this));
                    obstacleList.add(new Obstacle(645,i,4,this));
                }
                else{
                    obstacleList.add(new Obstacle(620,i,5,this));
                    obstacleList.add(new Obstacle(645,i,6,this));
                }
            }
            //绘制砖块C
             obstacleList.add(new Obstacle(300,330,0,this));
            //绘制砖块B,E,G
            for(int i = 270;i <= 330;i += 30){
                if(i == 270 || i == 330){
                    obstacleList.add(new Obstacle(i,360,0,this));
                }
                else{
                    obstacleList.add(new Obstacle(i,360,7,this));
                }
            }
            //绘制砖块A,D,F,H,I
            for(int i = 240;i <= 360;i += 30){
                if(i == 240 || i == 360){
                    obstacleList.add(new Obstacle(i,390,0,this));
                }
                else{
                    obstacleList.add(new Obstacle(i,390,7,this));
                }
            }
            //绘制妨碍砖块1
            obstacleList.add(new Obstacle(240,300,0,this));

            //绘制空1-4砖块
            for(int i = 360;i <= 540;i += 60){
                obstacleList.add(new Obstacle(i,270,7,this));
            }
        }
        //绘制第三关
        if (sort==3)
        {
            for (int i =0;i<27;i++){
                obstacleList.add(new Obstacle(i*30,420,1,this));
            }
            for (int j =0;j<=120;j+=30){
                for (int i = 0;i<27;i++){
                    obstacleList.add(new Obstacle(i*30,570-j,2,this));
                }
            }
            int temp=290;
            //三角形；
            for (int i=390;i>=270;i-=30)
            {
                for (int j=temp;j<=410;j+=30)
                {
                    obstacleList.add(new Obstacle(j,i,7,this));
                }
                temp+=30;
            }
            temp = 60;
            for (int i=390;i>360;i-=30)
            {
                for (int j=temp;j<=90;j+=30)
                {
                    obstacleList.add(new Obstacle(j,i,7,this));


                }
                temp+=30;
            }
            gan=StaticValue.gan;
            tower=StaticValue.tower;
            obstacleList.add(new Obstacle(515,220,8,this));
        }
    }
    public BufferedImage getBgImage() {
        return bgImage;
    }
    public boolean isFlag() {
        return flag;
    }

    public int getSort() {
        return sort;
    }
    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getGan() {
        return gan;
    }

    public BufferedImage getTower() {
        return tower;
    }
}


