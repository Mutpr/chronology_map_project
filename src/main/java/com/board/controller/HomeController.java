package com.board.controller;

import com.board.model.CharacterDTO;
import com.board.model.UserDTO;
import com.board.service.CharacterService;
import com.board.service.UserService;
import com.google.protobuf.Message;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController{
    UserService userService;
    CharacterService characterService;
    @Autowired
    public HomeController(UserService userService, CharacterService characterService){
        this.userService = userService;
        this.characterService = characterService;
    }
    @GetMapping("/")
    public String printHello(){
        return "index";
    }
    @GetMapping("selectOneCharacter/{id}")
    public String characterSelect(@PathVariable int id, Model model){
        CharacterDTO result = characterService.selectedOneChar(id);
        System.out.println(result);
        model.addAttribute("result", result);
        return "index";
    }


}
