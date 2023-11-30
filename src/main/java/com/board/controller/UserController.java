package com.board.controller;

import com.board.model.UserDTO;
import com.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @GetMapping("login")
//    public String userLogin(UserDTO userDTO, RedirectAttributes redirectAttributes, HttpSession session){
//        UserDTO result = userService.selectOne(userDTO);
//        System.out.println(result);
//        return "redirect:/";
//    }

//    @GetMapping("register")
//    public String userRegister(UserDTO user, ModelMap model){
//        if (!userService.userRegister(user)) {
//            model.addAttribute("message", "register failed!");
//        }
//        return "redirect:/";
//    }
}
