package com.board.controller;


import com.board.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
public class ImageController {

    ImageService imageService;
    @Autowired
    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }
    public String showImage(){
        return "showImage";
    }
}
