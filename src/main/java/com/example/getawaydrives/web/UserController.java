package com.example.getawaydrives.web;

import com.example.getawaydrives.entities.DocumentType;
import com.example.getawaydrives.entities.User;
import com.example.getawaydrives.entities.Vehicle;
import com.example.getawaydrives.repositories.DocumentRepository;
import com.example.getawaydrives.repositories.UserRepository;
import com.example.getawaydrives.service.UserServiceImpl;
import com.example.getawaydrives.service.UserServiceImpl;
import com.example.getawaydrives.utility.CommonMethods;
import com.example.getawaydrives.utility.PasswordAuthentication;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private DocumentRepository docRepo;

    @PostMapping("/login")
    public String login(Model model, User loginCred, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        try {
            User user = UserServiceImpl.loginUser(loginCred, repository);
            session.setAttribute("user", user);
            //model.addAttribute("user",user);
        } catch (Exception e) {
            System.out.println(e.getMessage()); // TODO show this message
        }
        return "LoggedIn";

    }

    @GetMapping(path = "/testlogin")
    public String testLog(Model model, @RequestParam(name = "email", defaultValue = "")
    String email,@RequestParam(name = "password", defaultValue = "")
    String password) {
//
//        if(password.equals("abc")){
//            return "LoggedIn";
//        }
//        else
            return "LoggedIn";

    }


    @GetMapping("/logout")
    public String logout(Model model, User user, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        session.invalidate();
        return "redirect:index";
    }

    @GetMapping("/verify")
    public String verifyLogin(@Valid User user, BindingResult result) {
        //
        if (result.hasErrors()) {
            return "byBuild";
        } else {
            return "redirect:index";
        }
    }

    @PostMapping(path = "/register")
    public String register(Model model, @Valid User user, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "index";
        } else {
            User u = UserServiceImpl.registerUser(user, repository);
            if(u != null && user.getFile() != null){
                CommonMethods.createDocument(docRepo, user.getFile(), u.getId(), null,
                        DocumentType.DL.getId());
            }
            return "redirect:index";
        }

    }

    @PostMapping("/editUser")
    public String editUser(Long id, Model model, @Valid User userRequest, BindingResult
            bindingResult, ModelMap mm, HttpSession session){
        User user = repository.findById(String.valueOf(id)).orElse(null);
        userRequest.setId(user.getId());
        UserServiceImpl.editUser(userRequest, repository);
        if(userRequest.getFile() != null){
            CommonMethods.createDocument(docRepo, user.getFile(), user.getId(), null,
                    DocumentType.DL.getId());
        }
        return "userProfile";
    }

    @GetMapping(path = "/Register")
    public String regPage(Model model){
        return "Register";
    }
    @GetMapping(path = "/userProfile")
    public String userProf(Model model){
        return "userProfile";
    }

    @GetMapping(path = "/editProfile")
    public String editProf(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute("user"));
        return "editProfile";
    }
    @GetMapping(path = "/loginPage")
    public String loginPage(Model model){
        return "loginForm";
    }

    @InitBinder     /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
