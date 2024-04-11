//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.board.controller;

import com.board.model.CharacterDTO;
import com.board.model.UserDTO;
import com.board.service.CharacterService;
import com.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"user/"})
public class UserController {
    UserService userService;
    CharacterService characterService;

    @Autowired
    public UserController(UserService userService, CharacterService characterService) {
        this.userService = userService;
        this.characterService = characterService;

    }

    @GetMapping({"selectAll"})
    public String selectAllUser() {
        List<UserDTO> list = this.userService.selectAll();
        System.out.println(list);
        return "redirect:/";
    }

    @PostMapping({"login"})
    public String userLogin(UserDTO userDTO, ModelMap model, RedirectAttributes redirectAttributes, HttpSession session) {
        UserDTO result = this.userService.selectOne(userDTO);
        List<CharacterDTO> characterDTOList = characterService.selectCharactersById(result.getId());
        session.setAttribute("id", result.getId());
        session.setAttribute("nickname", result.getNickname());
        session.setAttribute("characterList", characterDTOList);
        session.setAttribute("nickname", result.getNickname());
        System.out.println(result);

        return "redirect:/";
    }

    @PostMapping({"register"})
    public String userRegister(UserDTO user, ModelMap model, HttpSession session) {
        if (!this.userService.userRegister(user)) {
            model.addAttribute("message", "register failed!");
        }

        return "redirect:/";
    }
}
