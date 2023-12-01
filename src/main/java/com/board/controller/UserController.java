package com.board.controller;

import com.board.model.UserDTO;
import com.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("user/")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("selectAll")
    public String selectAllUser(){
        List<UserDTO> list = userService.selectAll();
        System.out.println(list);
        return "redirect:/";
    }

    @PostMapping("login")
    public String userLogin(UserDTO userDTO, ModelMap model,RedirectAttributes redirectAttributes, HttpSession session){
        UserDTO result = userService.selectOne(userDTO);
        if(result != null){
            redirectAttributes.addAttribute("id", result.getId());
            redirectAttributes.addAttribute("nickname", result.getNickname());
            session.setAttribute("nickname", result.getNickname());
            System.out.println(result);
        }else{
            redirectAttributes.addAttribute("nickname", null);
            redirectAttributes.addAttribute("id", null);
            redirectAttributes.addFlashAttribute("message", "로그인에 실패햇숴");
        }
        return "redirect:/";

    }

    @PostMapping("register")
    public String userRegister(UserDTO user, ModelMap model, HttpSession session){
        if (!userService.userRegister(user)) {
            model.addAttribute("message", "register failed!");
        }
        return "redirect:/";
    }
}
