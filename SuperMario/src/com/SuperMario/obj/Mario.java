package com.SuperMario.obj;

import com.SuperMario.util.BackGround;
import com.SuperMario.util.StaticValue;

import java.awt.image.BufferedImage;

public class Mario implements Runnable {
    //用于表示坐标
    private int x;
    private int y;
    //用于表示当前状态
    private String status;
    //用于显示当前状态对应的图像
    private BufferedImage show = null;
    //定义一个BackGround对象，用来获取障碍物信息
    private BackGround backGround = new BackGround();
    //用来实现马里奥动作
    private Thread thread = null;
    //移动速度
    private int xSpeed;
    //跳跃速度
    private int ySpeed;
    //定义一个索引
    private int index;
    //用来表示马里奥方向
    private boolean face_to = true;
    private int uptime=0;

    public Mario() {
    }

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.stand_R;
        this.status = "stand--right";
        thread = new Thread(this);
        thread.start();
    }
    public void leftMove(){
        //改变速度
        xSpeed = -5;
        //判断马里奥是否位于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "move--left";
        }
    }
    public void rightMove () {
        //改变速度
        xSpeed = 5;
        //判断马里奥是否位于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "move--right";
        }
    }
    public void leftStop () {
        //改变速度
        xSpeed = 0;
        //判断马里奥是否位于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "stop--left";
        }
    }
    public void rightStop () {
        //改变速度
        xSpeed = 0;
        //判断马里奥是否位于空中
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "stop--right";
        }
    }

    public void Jump()
    {
        if(status.indexOf("jump")==-1)
        {
            if(status.indexOf("left")!=-1)
            {
                status ="jemp--left";

            }
            else {
                status="jump--right";

            }
            ySpeed=-10;
             uptime=7;
        }
    }
    public void fall()
    {
        if(status.indexOf("left")!=-1)
        {
            status="jump--left";

        }
        else {
            status="jump--right";
        }
        ySpeed=10;

    }

    @Override
    public void run() {
        while (true) {
            boolean onObstacle=false;
            for (int i=0;i<backGround.getObstacleList().size();i++)
            {
                Obstacle ob=backGround.getObstacleList().get(i);
                if(ob.getY()==this.y+25&&(ob.getX()>this.x-30&&ob.getX()<this.x+25))
                {
                    onObstacle=true;
                }
                //jump
                if(onObstacle&&uptime==0)
                {
                    if(status.indexOf("left") !=-1)
                    {
                        if(xSpeed!=0)
                        {
                            status="move--left";
                        }
                        else{
                            status="stop--left";
                        }

                    }
                    else {
                        if(xSpeed!=0)
                        {
                            status="move--right";
                        }
                        else{
                            status="stop--right";
                        }
                    }
                }
                else{
                    if(uptime!=0)
                    {
                        uptime--;
                    }
                    else {
                        fall();

                    }
                    y+=ySpeed;
                }
            }
            if (xSpeed < 0 || xSpeed > 0) {
                x += xSpeed;
                //判断马里奥是否到达最左边
                if (x < 0) {
                    x = 0;
                }
            }
            //判断马里奥是否是移动状态
            if (status.contains("move")) {
                index = index == 0 ? 1 : 0;
            }
            //判断是否向左移动
            if ("move--left".equals(status)) {
                show = StaticValue.run_L.get(index);
            }
            //判断是否向右移动
            if ("move--right".equals(status)) {
                show = StaticValue.run_R.get(index);
            }
            //判断是否向左停止
            if ("stop--left".equals(status)) {
                show = StaticValue.stand_L;
            }
            //判断是否向右停止
            if ("stop--right".equals(status)) {
                show = StaticValue.stand_R;
            }
            if("jump--left".equals(status))
            {
                show=StaticValue.jump_L;
            }
            if("jump--right".equals(status))
            {
                show=StaticValue.jump_R;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getStatus() {
        return status;
    }

    public BufferedImage getShow() {
        return show;
    }

    public BackGround getBackGround() {
        return backGround;
    }

    public Thread getThread() {
        return thread;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public int getIndex() {
        return index;
    }

    public boolean isFace_to() {
        return face_to;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setFace_to(boolean face_to) {
        this.face_to = face_to;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
