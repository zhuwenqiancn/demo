package com.demo.test;

import java.awt.Image;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.ImageIcon;

public class HttpImage {
    private Image image;
    private byte[]buf;
    private static HttpImage httpImage;
    private boolean stop=false;
    public HttpImage()
    {
        buf=new byte[1024*1024*3];
    }
    public static void main(String[] args) {
        
        
        
    }
    public static HttpImage getHttpImage()
    {
        if(httpImage==null)
            httpImage=new HttpImage();
        return httpImage;
    }
    public Image getImage(String addr)
    {
        stop = false;
        image = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        try {
            URL url = new URL(addr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10 * 1000);       // 10秒的连接超时
            conn.setReadTimeout(15 * 1000);          // 15秒的读取超时
            conn.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
            conn.setDoInput(true);
            conn.connect();
            in = conn.getInputStream();
            int readlen = 0;
            int contentlen = 0;
            do {
                // 一次读取10KB，如果返回值小于0可以认为一定读取完毕
                contentlen += readlen;
                readlen = in.read(buf, contentlen, 1024 * 10);
                if (stop) {
                    in.close();
                    conn.disconnect();
                    return null;
                }
            } while (readlen > 0);
            in.close();
            conn.disconnect();
        //  image = ImageIO.read(new ByteArrayInputStream(buf));
            image=(new ImageIcon(buf)).getImage();   //利用ImageIcon获取Image对象

        } catch (Exception e) {
            image = null;
            e.printStackTrace();
            try {
                in.close();
                conn.disconnect();
            } catch (Exception ex) {
            }
        }
        return image;
    }
    public void stop()
    {
        this.stop=true;
    }
    
}