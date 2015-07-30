package bin;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description: PdfUtils
 * Author: Zen
 * Update: Zen(2015-07-29 10:19)
 */
public class PdfUtils {

    public static void setup() throws IOException {

        //load a pdf from a byte buffer
        File file = new File("D:\\Personal\\Downloads\\test.pdf");
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdffile = new PDFFile(buf);
        System.out.println(pdffile.getNumPages());
        // draw the first page to an image
        PDFPage page = pdffile.getPage(4);
        //get the width and height for the doc at the default zoom
        Rectangle rect = new Rectangle(0,0,
                (int)page.getBBox().getWidth(),
                (int)page.getBBox().getHeight());

        //generate the image
        Image img = page.getImage(
                rect.width, rect.height, //width & height
                rect, // clip rect
                null, // null for the ImageObserver
                true, // fill background with white
                true  // block until drawing is done
        );

        //show the image in a frame
        JFrame frame = new JFrame("PDF Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    PdfUtils.setup();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
