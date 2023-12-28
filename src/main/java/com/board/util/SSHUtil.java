package com.board.util;

import com.board.service.ImageService;
import com.jcraft.jsch.*;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public class SSHUtil {
    private static final int SFTP_PORT = 22;

    public HashMap<String, String> setSFTPInfo(String resources) throws IOException {
        Properties properties = new Properties();
        HashMap<String, String> info = new HashMap<>();

        InputStream reader = getClass().getResourceAsStream(resources);
        properties.load(reader);

        final String SFTP_HOST = (String) properties.get("SFTP_HOST");
        final String SFTP_USER = (String) properties.get("SFTP_USER");
        final String SFTP_PASSWORD = (String) properties.get("SFTP_PASSWORD");
        final String SFTP_REMOTE_DIR = "";

        info.put("host", SFTP_HOST);
        info.put("user", SFTP_USER);
        info.put("password", SFTP_PASSWORD);
        info.put("dir", SFTP_REMOTE_DIR);

        return info;
    }

    public void showFile(Model model) throws IOException {
        String resources = "resources/linux.properties";
        HashMap<String, String> info = setSFTPInfo(resources);

        String user = info.get("user");
        String host = info.get("host");
        String password = info.get("password");
        String dir = info.get("dir");

        // SFTP 연결 및 파일 다운로드
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, SFTP_PORT);
            session.setPassword(password);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            String fileName = "your_file.txt"; // 원하는 파일명으로 변경
            String localFilePath = "/path/on/local/machine/" + fileName;
            String remoteFilePath = dir + fileName;

            // 파일 다운로드
            channel.get(remoteFilePath, localFilePath);

            channel.disconnect();
            session.disconnect();

            // 모델에 파일 경로 추가
            model.addAttribute("filePath", localFilePath);

        } catch (JSchException | SftpException e) {
            e.printStackTrace();
            // 에러 처리 로직 추가
        } // 뷰 이름
    }
    public void sendFile(byte[] data , String filename) throws IOException {
        //data =
        String resources = "resources/linux.properties";
        HashMap<String, String> info = setSFTPInfo(resources);

        String user = info.get("user");
        String host = info.get("host");
        String password = info.get("password");
        String dir = info.get("dir");
        JSch jsch = new JSch();
        try{

            Session session = jsch.getSession(user, host, SFTP_PORT);
            session.setPassword(password);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect(3000);
            Channel channel = session.openChannel("sftp");

            channel.connect();
            ChannelSftp channelSftp = (ChannelSftp) channel;
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            channelSftp.cd(dir);
            channelSftp.put(in, filename);

            channel.disconnect();
            session.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
