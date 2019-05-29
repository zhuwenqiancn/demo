package com.demo.user;

import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    public String listUser(Model model){
        // used to store data
        List<User> userList = new ArrayList<User>();

        for (int i = 0; i < 10; i++){
            userList.add(new User( i, "Echo"+i, Integer.toString(20+i), "shandong china"));
        }
        model.addAttribute("users",userList);
        return "/user/list";
    }
}
