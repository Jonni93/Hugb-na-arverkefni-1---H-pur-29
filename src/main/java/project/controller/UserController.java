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
        if(result.hasErrors()) {
            return "login";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            return "redirect:/";
        }
        //Hér þarf að returna síðu sem segir að notandanafn sé ekki til
        return "redirect:/";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("loggedinuser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/";
    }

    /*
    Testfall. EKKI PARTUR AF LOKAFORRITI.
    Hér er hægt að búa til notanda með stillt categories
     */
    @RequestMapping(value = "/makeUser", method = RequestMethod.GET)
    public String makeUser(Model model){
        User newUser = new User("user", "password");
        List<String> categories = new ArrayList<>();
        categories.add("Íþróttir");
        categories.add("Viðskipti");
        newUser.setCategories(categories);
        userService.save(newUser);
        model.addAttribute("user", newUser);

        return "makeUser";
    }
}
