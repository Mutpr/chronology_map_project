package com.board.controller;

import com.board.model.UserDTO;
import com.board.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController{
    UserService userService;
    @Autowired
    public HomeController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/")
    public String printHello(ModelMap model, UserDTO userDTO, RedirectAttributes redirectAttributes,
                             @ModelAttribute("userNickname") String nickname,
                             @ModelAttribute("id") String id) {
        System.out.println(nickname);
        model.addAttribute("message", "Hello Spring MVC Framework!");
        model.addAttribute("id", id);
        model.addAttribute("nickname", nickname);
        return "index";
    }

}
