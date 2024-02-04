package com.board.controller;

import org.apache.commons.io.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

@Controller
public class FileController {

    @PostMapping("fileUpload")
    public @ResponseBody void fileUpload(MultipartFile file){
        String uploadFolder = "/var/lib/tomcat9/work/Catalina/localhost/ROOT";
        File uploadPath = new File(uploadFolder, getFolder());

        if(!uploadPath.exists()) {
            uploadPath.mkdirs();	//경로에 폴더가없으면 생성시킴
        }

        String uploadName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();

        uploadName = uuid+"_"+uploadName;

        File saveFile = new File(uploadPath, uploadName);

        try{
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        // 현재날짜를받아옴
        Date date = new Date();

        String str = sdf.format(date);

        return str.replace("-", File.separator);
        // "-" 를 운영체제에 맞게 / 또는 \\ 으로 변경한다.
    }

    public static HashMap<String, String> setSFTPInfo(String resources) throws IOException {
        Properties properties = new Properties();
        HashMap<String, String> info = new HashMap<>();

        InputStream reader = FileController.class.getResourceAsStream(resources);
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
}
