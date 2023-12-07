package com.board.service;


import com.board.model.ImageDTO;
import com.board.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ImageService {

    private final String NAMESPACE = "mapper.imageMapper";
    //userid, imageid
    public void getImageInfo(HashMap<String, Object> params, UserDTO userDTO, ImageDTO imageDTO){

    }

}
