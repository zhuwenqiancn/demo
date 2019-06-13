package com.demo.user.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.demo.user.bean.User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.demo.user.bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    final static String className = "com/demo/user/controller/UserController.java";


    //return page
    // used thymeleaf templates
    @RequestMapping("/list")
    public String userList(Model model){

        this.printMsg(Thread.currentThread().getStackTrace()[0].getMethodName().toString());
        // used to store data
        List<User> userList = new ArrayList<User>();

        for (int i = 0; i < 10; i++){
            userList.add(new User( i, Integer.toString(20+i), "Echo"+i, "address: shandong china"));
        }
        model.addAttribute("users",userList);
        return "/user/list";
    }

    @PostMapping
    @ResponseBody
    @RequestMapping(value = "/listpost", method = RequestMethod.POST)
    public List<User> userListJson(Model model){

        //this.printMsg(Thread.currentThread().getStackTrace()[0].getMethodName().toString());
        // used to store data
        List<User> userList = new ArrayList<User>();

        for (int i = 0; i < 10; i++){
            userList.add(new User( i, Integer.toString(20+i), "Echo"+i, "address: shandong china"));
        }
        model.addAttribute("users",userList);
        return userList;
    }

    @ResponseBody
    @RequestMapping("/manage")
    public User userManager(Integer id){

        //this.printMsg(Thread.currentThread().getStackTrace()[0].getMethodName());
        if(id == null || id < 0)
        {
            id = 1;
        }
        User user = new User( id, Integer.toString(20+id), "Echo"+id, "address: shandong china");

        return user;

    }

    //different site redirect
    @RequestMapping(value = "/webReurl")
    public String redirect1(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("into Redirect 1 funtion");
        String url = "http://www.datuodui.com";
        return "redirect:" + url;
    }

    // same site redirect
    @RequestMapping(value = "/ssReurl")
    public String redirect2(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("into Redirect 2 funtion");
        String url = "/user/manage";
        return "redirect:" + url;
    }

    //different site response redirect
    @RequestMapping(value = "/rspsReurl")
    public void rspsredirect1(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("into Redirect 1 funtion");
        String url = "http://www.datuodui.com";
        try{
            response.sendRedirect(url);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/forword")
    public void forward(HttpServletRequest request, HttpServletResponse response){
        String url = "/user/";
        try {
            request.getRequestDispatcher(url).forward(request,response);
        } catch (ServletException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("After the forwarding is completed, the code can also run.");
    }


    public void printMsg(String methodName){
        System.out.println("ClassName=\"" + this.getClass().getName() + "\"   :  mothod : \"" + methodName +"\"" );
    }
}
