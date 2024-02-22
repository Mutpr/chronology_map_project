package com.board.controller;

import com.board.model.CharacterDTO;
import com.board.model.ContentsDTO;
import com.board.model.ImageDTO;
import com.board.service.CharacterService;
import com.board.service.ContentsService;
import com.board.service.ImageService;
import jdk.jfr.ContentType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.CharacterTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    @PostMapping("insert")
    public String insertCharacter(ModelMap model, CharacterDTO character) {
        if (!characterService.insertCharacterInfo(character)) {
            model.addAttribute("insertCharacterFailMessage", "추가에 실패했습니다!");
        } return "redirect:/";
    }

    //캐릭터에 이미지랑 컨텐츠 최초 생성했을때
    @PostMapping("imageAndContentsInsert?userid={user_id}/image_id?={image_id}/character_id?={character_id}/{x}/{y}/{title}/{description}/{path}/{name}")
    public String insertImageAndContentsInfo(@PathVariable int user_id,
                                             @PathVariable int x, @PathVariable int y,
                                             @PathVariable String title, @PathVariable String description,
                                             ContentsDTO contentsDTO,
                                             CharacterDTO characterDTO, @PathVariable String character_id, @PathVariable String path){
        System.out.println("characterDTO"+ characterDTO);
        if(characterDTO.getName() != null){
            contentsDTO.setX(x);
            contentsDTO.setY(y);
            contentsDTO.setTitle(title);
            contentsDTO.setDescription(description);
            System.out.println(contentsDTO);
            characterService.insertCharacterInfo(characterDTO);
            contentsService.insertContentsInfo(contentsDTO);
            return "redirect:/";
        }
        return "redirect:/";
    }

    //캐릭터에 이미 존재하는 이미지에 콘텐츠 생성할때

//    public String insertContentsToCharacter(){
//        characterService.selectAllByCharacterId()
//    }

    //전체 콘텐츠 읽어들일때
    @GetMapping("selectContentsByCharacterId/{character_Id}")
    public String selectContentsByCharacterId(@PathVariable int character_Id){
        List<ContentsDTO> result = characterService.selectAllByCharacterId(character_Id);
        System.out.println(result);
        return "";
    }


    //콘텐츠만 업로드 할때
    @PostMapping("contentsInsert?userid={user_id}/image_id?={image_id}/character_id?={character_id}/{x}/{y}/{title}/{description}")
    public String insertContents(@PathVariable String user_id, @PathVariable String image_id, @PathVariable String character_id,
                                 @PathVariable int x, @PathVariable int y, @PathVariable String title, @PathVariable String description,
                                 ContentsDTO contentsDTO){
            contentsDTO.setX(x);
            contentsDTO.setY(y);
            contentsDTO.setTitle(title);
            contentsDTO.setDescription(description);
            System.out.println(contentsDTO);
            contentsService.insertContentsInfo(contentsDTO);
            return "redirect:/";
    }
}
