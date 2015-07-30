package bin;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description: TestViewPdf
 * Author: Zen
 * Update: Zen(2015-07-29 15:26)
 */
public class TestViewPdf {
    public static void main(String[] args) {


        new TestViewPdf().Pdf_Png(3);
    }

    public void Pdf_Png(int pageNumber) {
        int pagen = pageNumber;
        //File file = new File("/home/develop/xq/P9.pdf");
        File file = new File("d:/test.pdf");
        PDFFile pdffile = null;
        // set up the PDF reading
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0,
                    channel.size());
            pdffile = new PDFFile(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (pagen < pdffile.getNumPages()) {
//            return;
//        }

        // print 出该 pdf 文档的页数
        System.out.println(pdffile.getNumPages());

        // 设置将第 pagen 页生成 png 图片
        PDFPage page = pdffile.getPage(pagen);

        // create and configure a graphics object
        int width = (int) page.getBBox().getWidth();
        int height = (int) page.getBBox().getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // do the actual drawing
        PDFRenderer renderer = new PDFRenderer(page, g2, new Rectangle(0, 0, width, height), null, Color.WHITE);
        try {
            page.waitForFinish();
        } catch (Exception e) {
            e.printStackTrace();
        }
        renderer.run();
        g2.dispose();

        try {
            //ImageIO.write(img, "png", new File("/home/develop/xq/123.png"));
            ImageIO.write(img, "png", new File("d:/123.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
