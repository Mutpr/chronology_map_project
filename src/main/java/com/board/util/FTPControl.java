package com.board.util;

import com.board.controller.FileController;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

class FTPControl{

    public FTPControl(){

    }

    public void FTPUploader(String revFile) throws IOException {
        String host;
        int port;
        String user;
        String pwd;

        FTPClient ftpClient = new FTPClient();

        String resources = "resources/linux.properties";
        HashMap<String, String> info = FileController.setSFTPInfo(resources);

        host = info.get("host");
        port = Integer.parseInt(info.get("port"));
        user = info.get("user");
        pwd = info.get("password");

        try{
            ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(revFile)));
            int reply = 0;

            ftpClient.connect(host,port);
            reply = ftpClient.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)){
                ftpClient.disconnect();
            }else{
                Date date = new Date();
                SimpleDateFormat nowDateHHmmss = new SimpleDateFormat("HHmmss");
                SimpleDateFormat nowDateymd = new SimpleDateFormat("yyyyMMdd");
                String nowDate = nowDateHHmmss.format(date);
                String nowYear = nowDateymd.format(date);

                ftpClient.login(user, pwd);
                showServerReply(ftpClient);

                ftpClient.makeDirectory("/VOL1/personalApi/"+nowYear);
                showServerReply(ftpClient);

                ftpClient.makeDirectory("VOL1/personalApi"+nowYear+"/"+nowDate);
                showServerReply(ftpClient);
                System.out.println(ftpClient.printWorkingDirectory());

                ftpClient.enterLocalPassiveMode();
                showServerReply(ftpClient);

                FileInputStream fileInputStream = new FileInputStream(revFile);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                boolean isSuccess = ftpClient.storeFile(revFile, fileInputStream);

                if(isSuccess){
                    System.out.println("업로드 성공!!");
                }
            }
        }catch (FileNotFoundException | SocketException e){
            e.printStackTrace();
        }
    }

    public static void showServerReply(FTPClient ftpClient){
        String [] replies = ftpClient.getReplyStrings();
        if(replies != null){
            for(String reply: replies){
                System.out.println(reply);
            }
        }
    }
}
