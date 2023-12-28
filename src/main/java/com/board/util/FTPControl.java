package com.board.util;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSCommand;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

class FTPControl{


    public HashMap<String, String> setSFTPInfo(String resources) throws IOException {
        Properties properties = new Properties();
        HashMap<String, String> info = new HashMap<>();

        InputStream reader = getClass().getResourceAsStream(resources);
        properties.load(reader);

        final String SFTP_HOST = (String) properties.get("SFTP_HOST");
        final String SFTP_USER = (String) properties.get("SFTP_USER");
        final String SFTP_PASSWORD = (String) properties.get("SFTP_PASSWORD");
        final String SFTP_PORT = (String) properties.get("SFTP_PORT");

        info.put("host", SFTP_HOST);
        info.put("user", SFTP_USER);
        info.put("password", SFTP_PASSWORD);
        info.put("port", SFTP_PORT);

        return info;
    }

    public void FTPUploader(String host, int port, String user, String pwd, String revFile) throws IOException {
        FTPClient ftpClient = new FTPClient();
        String resources = "resources/linux.properties";
        HashMap<String, String> info = setSFTPInfo(resources);
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
