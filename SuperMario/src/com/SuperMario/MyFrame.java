package com.SuperMario;

import com.SuperMario.obj.Mario;
import com.SuperMario.util.BackGround;
import com.SuperMario.util.StaticValue;
import com.SuperMario.obj.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;;

public class MyFrame extends JFrame implements KeyListener,Runnable{

    private List<BackGround> allBg = new ArrayList<>();
    private BackGround nowBg = new BackGround();
    private Image offScreenImage = null;
    private Mario mario  = new Mario();
    private Thread thread=new Thread(this);
    public MyFrame(){
        //设置窗口的大小为800*600
        this.setSize(800,600);
        //设置窗口居中显示
        this.setLocationRelativeTo(null);
        //设置窗口的可见性
        this.setVisible(true);
        //设置点击窗口上的关闭键，结束程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口大小不可变
        this.setResizable(false);
        //向窗口对象添加键盘监听器
        this.addKeyListener(this);
        //设置窗口名称
        this.setTitle("SuperMario");

        //初始化所有图片
        StaticValue.init();
        //初始化马里奥
        mario = new Mario(10,355);
        //创建全部场景
        for(int i = 1;i <= 3;i++){
            allBg.add(new BackGround(i,i == 3 ? true : false));
        }
        //将第一个背景设为当前场景
        nowBg = allBg.get(0);
        repaint();
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
       if(offScreenImage == null){
           offScreenImage = createImage(800,600);
       }
       Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0,0,800,600);

        //绘制背景
        graphics.drawImage(nowBg.getBgImage(),0,0,this);
        //绘制马里奥
        graphics.drawImage(mario.getShow(),mario.getX(),mario.getY(),this);

        //绘制障碍物
        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(),ob.getX(),ob.getY(),this);
        }
        graphics.drawImage(nowBg.getTower(),620,270,this);
        graphics.drawImage(nowBg.getGan(),500,220,this);

        //将绘制背景添加到窗口中
        g.drawImage(offScreenImage,0,0,this);
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //向右移动
        if (e.getKeyCode() == 39) {
            mario.rightMove();
        }
        //向左移动
        if (e.getKeyCode() == 37) {
            mario.leftMove();
        }
        //jump
        if (e.getKeyCode()==38)
        {
            mario.Jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //向左停止
        if (e.getKeyCode() == 37) {
            mario.leftStop();
        }
        //向右停止
        if (e.getKeyCode() == 39) {
            mario.rightStop();
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(50);
                //判断马里奥是否到达下一关
                if (mario.getX() >= 775) {
                    nowBg = allBg.get(nowBg.getSort());
                    mario.setBackGround(nowBg);
                    mario.setX(10);
                    mario.setY(395);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
