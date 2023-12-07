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
    public String printHello(HttpSession session, Model model,
                             @ModelAttribute("userNickname") String nickname,
                             @ModelAttribute("id") String id,
                             @ModelAttribute("message") String message) {
        try {
            int idParsing = Integer.parseInt(id);
            System.out.println(idParsing);
            List<CharacterDTO> characterList = characterService.selectCharactersById(idParsing);
            System.out.println(characterList);
            model.addAttribute("characterList",characterList);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(nickname);
        System.out.println(message);
        System.out.println(id);
        session.getAttribute("nickname");
        return "index";
    }
    @GetMapping("selectOneCharacter/{id}")
    public String characterSelect(@PathVariable int id, Model model){
        CharacterDTO result = characterService.selectedOneChar(id);
        System.out.println(result);
        model.addAttribute("result", result);
        return "index";
    }

    @PostMapping("contentsUpload/{userId}/{characterId}")
    public String contentsUpload(@PathVariable int userId, @PathVariable int characterId){
        return "redirect:/";
    }
}
