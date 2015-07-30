package bin;

import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;

/**
 * Description: ExtractArchive
 * Author: Zen
 * Update: Zen(2015-07-30 16:04)
 */
public class ExtractArchive {
    public static void main(String[] args) {
//        if (args.length == 2) {
//        extractArchive("E:\\demos\\demo.rar", "E:\\bin");
        unZipFiles("E:\\demos\\TPNDEMO.zip", "E:\\bin");
//        } else {
//            System.out.println("usage: java -jar extractArchive.jar <thearchive> <the destination directory>");
//        }
    }

    public static void extractArchive(String archive, String destination) {
        if (archive == null || destination == null) {
            throw new RuntimeException("archive and destination must me set");
        }
        File arch = new File(archive);
        System.out.println("arch.............");
        System.out.println(arch);
        if (!arch.exists()) {
            throw new RuntimeException("the archive does not exit: " + archive);
        }
        File dest = new File(destination);
        if (!dest.exists() || !dest.isDirectory()) {
            throw new RuntimeException("the destination must exist and point to a directory: " + destination);
        }
        ExtractArchive.extractArchive(arch, dest);
    }

    public static void extractArchive(File archive, File destination) {
        com.github.junrar.extract.ExtractArchive extractArchive = new com.github.junrar.extract.ExtractArchive();
        extractArchive.setLogger(LogFactory.getLog(ExtractArchive.class.getName()));
        extractArchive.extractArchive(archive, destination);
    }

    public static void unZipFiles(String zipfile, String descDir) {
        try {
            ZipFile zf = new ZipFile(new File(zipfile));
            for (Enumeration entries = zf.getEntries(); entries
                    .hasMoreElements(); ) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String zipEntryName = entry.getName();
                InputStream in = zf.getInputStream(entry);
                OutputStream out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
                //System.out.println("解压缩完成.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
