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

@Controller
public class UserController {
    UserServiceImplementation userService;

    @Autowired
    public UserController(UserServiceImplementation userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGET(){
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpPOST(@Valid User user, BindingResult result){
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
    public String loginPOST(@Valid User user, BindingResult result, HttpSession session){
        if(result.hasErrors()) {
            return "login";
        }
        User exists = userService.login(user);
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("loggedinUser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/";
    }

    //Þegar búið er að velja checkbox og ýta á submit er kallað á þetta fall
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String home(@Valid User user, BindingResult result, Model model,HttpSession session){
        User sessionUser = (User)session.getAttribute("LoggedInUser");
        sessionUser.setCategories(user.getCategories());
        userService.save(sessionUser);

        return "redirect:/";
    }

}
