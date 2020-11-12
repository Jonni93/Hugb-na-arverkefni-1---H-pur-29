package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Categories;
import project.persistence.entities.User;
import project.service.Implementation.NewsStoryServiceImplementation;
import project.service.Implementation.UserServiceImplementation;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class HomeController {
    NewsStoryServiceImplementation newsStoryService;
    UserServiceImplementation userService;

    @Autowired
    public HomeController(NewsStoryServiceImplementation newsStoryService,UserServiceImplementation userService) {
        this.newsStoryService = newsStoryService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        User sessionUser = (User) session.getAttribute("LoggedInUser");

        //Ef notandi er skráður inn þá á að birta filteraðan fréttalista. Checkbox til að velja flokka eru sýnileg.
        if(sessionUser  != null){
            //Bý til User hlutinn loggedInUser hér sem hálfgert skítamix til að skipta út sessionUser hlutnum.
            //Ef ég nota sessionUser.getCategories fæ ég eftirfarandi villu:
            //"failed to lazily initialize a collection of role: no session or session was closed"
            //Vantar eflaust bara eitthvað annotation einhver staðar en eins og er virkar loggedInUser
            User loggedInUser = userService.findByUName(sessionUser.getuName());
            model.addAttribute("user", loggedInUser);
            model.addAttribute("categories", Arrays.asList(Categories.values()));
            model.addAttribute("newsStories", newsStoryService.findByCategoriesIn(loggedInUser.getCategories()));
            return "Index";
        }

        //Notandi er ekki skráður inn. Birta allar fréttir. Checkbox fyrir flokka eru ekki sýnileg
        model.addAttribute("newsStories", newsStoryService.findAllChronological() );

        return "Index";
    }
    /*
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String home(@Valid User user,HttpSession session){
        User sessionUser = (User)session.getAttribute("LoggedInUser");
        sessionUser.setCategories(user.getCategories());
        userService.save(sessionUser);

        return "redirect:/";
    }

     */
}
