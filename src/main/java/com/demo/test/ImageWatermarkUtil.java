package com.demo.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageWatermarkUtil {

    public void testImageWatermarkUtil(){
        
    }
    public static void main(String[] args) {

        try {
            
            ImageWatermarkUtil imgOR = new ImageWatermarkUtil();
            imgOR.cropAndCompressImage(15, 20, "http://127.0.0.1:8080/image/2.jpg", "http://127.0.0.1:8080/image/w2.png");
            
            System.out.println("图片己处理完成red");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println(e1.toString());
        }
        finally{
            
        }
    }

    /*
    * author:  zhuwenqian
    * Date:    2019-5-24
    * description:
    * 实际应用中，再把两个图片地址写为参数，返回输出文件。
    * int x : 水印距离底图左边间距
    × int y : 水印距离底图上边间距
    * String waterFile:水印是址：
    */
    private void cropAndCompressImage(int x, int y, String imageFile, String waterFile) {
        
        BufferedImage image;
        BufferedImage waterImg;
        try {
            
            Image img = HttpImage.getHttpImage().getImage(imageFile);
            image = new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.createGraphics();
            graphics.drawImage(img, 0, 0, null);
            graphics.dispose();

            //未加水印图读取完成，输出一下图片测试
            //ImageIO.write(image, "JPG", new File("/Users/echo/Desktop/t1.jpg")); //测试完注释掉
            
            //后面处理加水印

            float alpha = 1.0f;

            //获取层图
            Image waterWebImg = HttpImage.getHttpImage().getImage("http://127.0.0.1:8080/image/w2.png");
            waterImg = new BufferedImage(waterWebImg.getWidth(null), waterWebImg.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
            graphics = waterImg.createGraphics();
            graphics.drawImage(waterWebImg, 0, 0, null);
            graphics.dispose();

            //上面处理透明图层时，会把透明图层变为黑色，改为直接读取，或许有其它处理方式
            //waterBuffImg = ImageIO.read(new File(waterFile));
            // 创建Graphics2D对象，用在底图对象上绘图
            Graphics2D g2d = image.createGraphics();
            int waterImgWidth = waterImg.getWidth();// 获取层图的宽cn.
            int waterImgHeight = waterImg.getHeight();// 获取层图的高度
            // 在图形和图像中实现混合和透明效果
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            // 绘制
            g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
            //释放图形上下文使用的系统资源
            g2d.dispose();
            //输出叠加后的图片
            ImageIO.write(image, "JPG", new File("/home/echo/Desktop/wt1.jpg"));

        } catch (Exception e) {
            // error 等异常处理...
            //TODO 
            System.out.println(e.toString());
        }
        finally{
            image = null;
            waterImg = null;
        }
    }

}