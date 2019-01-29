package utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * 生成验证码图片工具类
 */
public class Validate {

    private int width=100; //验证码图片宽度
    private int height=30;  //验证码图片高度
    private String validWords="1234567890"; //验证码内容字符串
    public Map<String,Object> paint(){
        //在内存中生成图片
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //获取画笔对象
        Graphics2D graphics=(Graphics2D) bufferedImage.getGraphics();
        //设置画笔颜色
        graphics.setColor(Color.gray);
        //绘制填充矩形
        graphics.fillRect(0,0,width,height);
        //
        graphics.setColor(Color.black);
        //绘制边框
        graphics.drawRect(0,0,width-1,height-1);
        //随机获取字符
        Random random=new Random();
        //字符初始位置坐标
        int x=15;
        int y=20;
        //
        graphics.setColor(Color.red);
        //设置字符字体样式
        graphics.setFont(new Font("隶书",Font.BOLD,20));
        StringBuffer stringBuffer=new StringBuffer();
        for(int i=0;i<4;i++){
            //设置弧度
            int angle=random.nextInt(60)-30;
            double rad=angle*Math.PI/180;
            graphics.rotate(rad,x,y);
            int index=random.nextInt(validWords.length());
            char ch=validWords.charAt(index);
            stringBuffer.append(ch);
            graphics.drawString(""+ch,x,y);
            graphics.rotate(-rad,x,y);
            x+=20;
        }
        //设置干扰线
        int x1,y1,x2,y2;
        graphics.setColor(Color.black);
        for(int i=0;i<4;i++){
            x1=random.nextInt(width);
            y1=random.nextInt(height);
            x2=random.nextInt(width);
            y2=random.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",stringBuffer);
        map.put("image",bufferedImage);
        return map;
    }

}
