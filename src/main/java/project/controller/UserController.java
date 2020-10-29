package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.User;
import project.service.Implementation.UserServiceImplementation;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    UserServiceImplementation userService;

    @Autowired
    public UserController(UserServiceImplementation userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGET(User user){
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpPOST(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "signup";
        }
        User exists = userService.findByUName(user.getuName());
        if(exists == null){
            userService.save(user);
        }
        //model.addAttribute("newsStories", newsStoryService.findAll());
        return "redirect:/";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersGET(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }
        //model.addAttribute("newsStories",newsStoryService.findAll());
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", user);
            return "redirect:/";
        }
        //Hér þarf að returna síðu sem segir að notandanafn sé ekki til
        return "redirect:/";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        //model.addAttribute("newsStories",newsStoryService.findAll());
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("loggedinuser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/";
    }

    //Fall til að testa EYÐIST
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filter(HttpSession session,User user){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            List<String> categories = new ArrayList<>();
            categories.add("Íþróttir");
            sessionUser.setCategories(categories);
            return "redirect:/";
        }
        return "redirect:/";
    }



}
