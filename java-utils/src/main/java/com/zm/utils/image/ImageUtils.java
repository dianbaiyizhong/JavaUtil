package com.zm.utils.image;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ImageUtils {

    public static void main(String[] args) throws Exception {
//        BufferedImage image = ImageIO.read(new File("C:\\Users\\nntk\\Downloads\\ocr-test\\74cce33211fa4e608b523d21a74cb141.png"));
//
//        BufferedImage denoise = binaryImage(grayImage(image));
//
//        File file = new File("C:\\Users\\nntk\\Downloads\\ocr-test\\74cce33211fa4e608b523d21a74cb141-1.png");
//        ImageIO.write(denoise, "png", file);

//        ImgUtil.gray(FileUtil.file("C:\\Users\\nntk\\Downloads\\ocr-test\\74cce33211fa4e608b523d21a74cb141.png"),
//                FileUtil.file("C:\\Users\\nntk\\Downloads\\ocr-test\\74cce33211fa4e608b523d21a74cb141-1.png"));


        BufferedImage image = ImageIO.read(new File("C:\\Users\\nntk\\Downloads\\ocr-test\\1.png"));
        int w = image.getWidth();
        int h = image.getHeight();
        int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);

//        Arrays.sort(pixels);

        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] > 0) {
                System.out.println(pixels[i]);
            }
//            String hex = String.format("#%06X", (0xFFFFFF & pixels[i]));
//            if (hex.equalsIgnoreCase("#aeb8d2") || hex.equalsIgnoreCase("#a2a9d1") || hex.equalsIgnoreCase("#aeb9ce")) {
////                pixels[i] = white;
//            }

            if (Math.abs(pixels[i]) < 9000000) {
                pixels[i] = -1;
            }
        }
        image.setRGB(0, 0, w, h, pixels, 0, w);
        File file = new File("C:\\Users\\nntk\\Downloads\\ocr-test\\output2.png");
        ImageIO.write(image, "png", file);
    }

    public static BufferedImage binaryImage(BufferedImage image) throws Exception {
        int w = image.getWidth();
        int h = image.getHeight();
        float[] rgb = new float[3];
        double[][] zuobiao = new double[w][h];
        int black = new Color(0, 0, 0).getRGB();
        int white = new Color(255, 255, 255).getRGB();
        BufferedImage bi = new BufferedImage(w, h,
                BufferedImage.TYPE_BYTE_BINARY);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = image.getRGB(x, y);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                float avg = (rgb[0] + rgb[1] + rgb[2]) / 3;
                zuobiao[x][y] = avg;

            }
        }
        //这里是阈值，白底黑字还是黑底白字，大多数情况下建议白底黑字，后面都以白底黑字为例
        double SW = 192;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (zuobiao[x][y] < SW) {
                    bi.setRGB(x, y, black);
                } else {
                    bi.setRGB(x, y, white);
                }
            }
        }
        return bi;
    }

    public static BufferedImage grayImage(BufferedImage bufferedImage) throws Exception {

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        BufferedImage grayBufferedImage = new BufferedImage(width, height, bufferedImage.getType());
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                final int color = bufferedImage.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                int newPixel = colorToRGB(255, gray, gray, gray);
                grayBufferedImage.setRGB(i, j, newPixel);
            }
        }

        return grayBufferedImage;

    }

    /**
     * 颜色分量转换为RGB值
     *
     * @param alpha
     * @param red
     * @param green
     * @param blue
     * @return
     */
    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    /**
     * 降噪，以1个像素点为单位（实际使用中可以循环降噪，或者把单位可以扩大为多个像素点）
     *
     * @param image
     * @return
     */
    public static BufferedImage denoise(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        int white = new Color(255, 255, 255).getRGB();

        if (isWhite(image.getRGB(1, 0)) && isWhite(image.getRGB(0, 1)) && isWhite(image.getRGB(1, 1))) {
            image.setRGB(0, 0, white);
        }
        if (isWhite(image.getRGB(w - 2, 0)) && isWhite(image.getRGB(w - 1, 1)) && isWhite(image.getRGB(w - 2, 1))) {
            image.setRGB(w - 1, 0, white);
        }
        if (isWhite(image.getRGB(0, h - 2)) && isWhite(image.getRGB(1, h - 1)) && isWhite(image.getRGB(1, h - 2))) {
            image.setRGB(0, h - 1, white);
        }
        if (isWhite(image.getRGB(w - 2, h - 1)) && isWhite(image.getRGB(w - 1, h - 2)) && isWhite(image.getRGB(w - 2, h - 2))) {
            image.setRGB(w - 1, h - 1, white);
        }

        for (int x = 1; x < w - 1; x++) {
            int y = 0;
            if (isBlack(image.getRGB(x, y))) {
                int size = 0;
                if (isWhite(image.getRGB(x - 1, y))) {
                    size++;
                }
                if (isWhite(image.getRGB(x + 1, y))) {
                    size++;
                }
                if (isWhite(image.getRGB(x, y + 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x - 1, y + 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x + 1, y + 1))) {
                    size++;
                }
                if (size >= 5) {
                    image.setRGB(x, y, white);
                }
            }
        }
        for (int x = 1; x < w - 1; x++) {
            int y = h - 1;
            if (isBlack(image.getRGB(x, y))) {
                int size = 0;
                if (isWhite(image.getRGB(x - 1, y))) {
                    size++;
                }
                if (isWhite(image.getRGB(x + 1, y))) {
                    size++;
                }
                if (isWhite(image.getRGB(x, y - 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x + 1, y - 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x - 1, y - 1))) {
                    size++;
                }
                if (size >= 5) {
                    image.setRGB(x, y, white);
                }
            }
        }

        for (int y = 1; y < h - 1; y++) {
            int x = 0;
            if (isBlack(image.getRGB(x, y))) {
                int size = 0;
                if (isWhite(image.getRGB(x + 1, y))) {
                    size++;
                }
                if (isWhite(image.getRGB(x, y + 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x, y - 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x + 1, y - 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x + 1, y + 1))) {
                    size++;
                }
                if (size >= 5) {
                    image.setRGB(x, y, white);
                }
            }
        }

        for (int y = 1; y < h - 1; y++) {
            int x = w - 1;
            if (isBlack(image.getRGB(x, y))) {
                int size = 0;
                if (isWhite(image.getRGB(x - 1, y))) {
                    size++;
                }
                if (isWhite(image.getRGB(x, y + 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x, y - 1))) {
                    size++;
                }
                //斜上下为空时，去掉此点
                if (isWhite(image.getRGB(x - 1, y + 1))) {
                    size++;
                }
                if (isWhite(image.getRGB(x - 1, y - 1))) {
                    size++;
                }
                if (size >= 5) {
                    image.setRGB(x, y, white);
                }
            }
        }

        //降噪，以1个像素点为单位
        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                if (isBlack(image.getRGB(x, y))) {
                    int size = 0;
                    //上下左右均为空时，去掉此点
                    if (isWhite(image.getRGB(x - 1, y))) {
                        size++;
                    }
                    if (isWhite(image.getRGB(x + 1, y))) {
                        size++;
                    }
                    //上下均为空时，去掉此点
                    if (isWhite(image.getRGB(x, y + 1))) {
                        size++;
                    }
                    if (isWhite(image.getRGB(x, y - 1))) {
                        size++;
                    }
                    //斜上下为空时，去掉此点
                    if (isWhite(image.getRGB(x - 1, y + 1))) {
                        size++;
                    }
                    if (isWhite(image.getRGB(x + 1, y - 1))) {
                        size++;
                    }
                    if (isWhite(image.getRGB(x + 1, y + 1))) {
                        size++;
                    }
                    if (isWhite(image.getRGB(x - 1, y - 1))) {
                        size++;
                    }
                    if (size >= 8) {
                        image.setRGB(x, y, white);
                    }
                }
            }
        }

        return image;
    }

    public static boolean isBlack(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 300) {
            return true;
        }
        return false;
    }

    public static boolean isWhite(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() > 300) {
            return true;
        }
        return false;
    }

    public static int isBlack(int colorInt, int whiteThreshold) {
        final Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= whiteThreshold) {
            return 1;
        }
        return 0;
    }

}
