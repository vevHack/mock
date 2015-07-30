//package org.sudojs.utils;
//
///**
// * Description: ConvertUtils
// * Author: Fluss
// * Update: Zen(2015-07-23 10:35)
// */
//public class ConvertUtils {
//    public static void main(String[] args) throws ConnectException {
//        // TODO Auto-generated method stub
//        File inputFile = new File("Resume.doc");
//        File outputFile = new File("document.pdf");
//
//        // connect to an OpenOffice.org instance running on port 8100
//        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
//        connection.connect();
//
//        // convert
//        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
//        converter.convert(inputFile, outputFile);
//
//        // close the connection
//        connection.disconnect();
//    }
//}
