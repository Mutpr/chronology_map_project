package com.board.controller;

import com.board.model.CharacterDTO;
import com.board.service.CharacterService;
import com.board.service.ContentsService;
import com.board.service.ImageService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("character/")
public class CharacterController {

    CharacterService characterService;
    ContentsService contentsService;
    ImageService imageService;
    @Autowired
    public CharacterController(CharacterService characterService, ContentsService contentsService, ImageService imageService){
        this.characterService = characterService;
        this.contentsService = contentsService;
        this.imageService = imageService;
    }


    //캐릭터 최초 생성시
    @PostMapping("insert/{user_id}/{name}")
    public String insertCharacter(ModelMap model, @PathVariable String user_id, @PathVariable String name, CharacterDTO character) {
        if (!characterService.insertCharacterInfo(character)) {
            return "redirect:/";
        }else{
            model.addAttribute("insertCharacterFailMessage", "추가에 실패했습니다!");
            return "redirect:/";
        }
    }

    //캐릭터에 이미지랑 컨텐츠 최초 생성했을때
    public String insertImageAndContentsInfo(){

    }

    //캐릭터에 이미 존재하는 이미지에 콘텐츠 생성할때
    public String insertContentsToCharacter(){

    }

    //전체 콘텐츠 읽어들일때
    public String selectContentsByCharacterId(){

    }
}
