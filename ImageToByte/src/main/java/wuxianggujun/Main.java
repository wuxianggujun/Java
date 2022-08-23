package wuxianggujun;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        String base = "无相孤君";
        //图片路径
        InputStream path = Thread.currentThread().getContextClassLoader().getResourceAsStream("ljn.jpg");
        BufferedImage image = ImageIO.read(path);
        for (int i = 0; i < image.getHeight(); i++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int color = image.getRGB(x, i);//图片缓冲区自带的方法，可以得到当前点的颜色值，返回值是int类型
                int r = (color >> 16) & 0xff;
                int g = (color >> 8) & 0xff;
                int b = color & 0xff;
                float gray = 0.299f * r + 0.578f * g + 0.114f * b;//灰度值计算公式，固定比例，无需理解
                int index = Math.round(gray * (base.length()) / 255);
                if (index >= base.length()) {
                    System.out.print(" ");//白色的地方打空格，相当于白色背景，这样图片轮廓比较明显
                } else {
                    System.out.print(base.charAt(index));//有颜色的地方打字符
                }
            }
            System.out.println();//一行打完，换行
        }
    }

}
