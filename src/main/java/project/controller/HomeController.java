package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.User;
import project.service.Implementation.NewsStoryServiceImplementation;
import project.service.Implementation.UserServiceImplementation;

import javax.servlet.http.HttpSession;

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

        //Ef notandi er skráður inn þá á að birta filteraðan fréttalista
        if(sessionUser  != null){
            model.addAttribute("newsStories", newsStoryService.findByCategoriesIn(sessionUser.getCategories()));
            return "Index";
        }

        //Notandi er ekki skráður inn. Birta allar fréttir
        model.addAttribute("newsStories", newsStoryService.findAllChronological() );

        return "Index";
    }
}
