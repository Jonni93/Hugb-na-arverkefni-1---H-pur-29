package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Categories;
import project.persistence.entities.NewsStory;
import project.persistence.entities.User;
import project.service.Implementation.NewsStoryServiceImplementation;
import project.service.Implementation.UserServiceImplementation;
import project.service.NewsStoryCollectorService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class HomeController {

    NewsStoryCollectorService collectorService;
    NewsStoryServiceImplementation newsStoryService;
    UserServiceImplementation userService;

    @Autowired
    public HomeController(NewsStoryCollectorService collectorService,NewsStoryServiceImplementation newsStoryService,UserServiceImplementation userService) {
        this.collectorService = collectorService;
        this.newsStoryService = newsStoryService;
        this.userService = userService;
    }

    // Request mapping is the path that you want to map this method to
    // In this case, the mapping is the root "/", so when the project
    // is running and you enter "localhost:8080" into a browser, this
    // method is called
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){


        List<NewsStory> newsStories  = collectorService.findStories();
        newsStoryService.save(newsStories);

        User sessionUser = (User) session.getAttribute("LoggedInUser");
        //Ef notandi er skráður inn þá á að birta filteraðan fréttalist
        if(sessionUser  != null){
            model.addAttribute("newsStories", newsStoryService.findByCategoriesIn(sessionUser.getCategories()));
            return "Index";
        }


        //Notandi er ekki skráður inn. Birta allar fréttir
        model.addAttribute("newsStories", newsStoryService.findAllChronological() );


        return "Index";
    }

    /**
     * Development fall til að debugga. Eyðist
     * @param
     * @return
     */
    @RequestMapping(value = "/viewNewsData", method = RequestMethod.GET)
    public String viewNewsData(Model model){
        model.addAttribute("newsStories", newsStoryService.findAll());
        return "viewNewsData";
    }

    //Develpoment fall. EYÐIST
    @RequestMapping("/makedata")
    public String makeData(Model model){

        /*
        List<Categories> categories = new ArrayList<>();
        //categories.add("Erlent");
        categories.add("Sport");
        categories.add("Innlent");
        for (int i = 0; i < 3; i++) {
            this.newsStoryService.save(new NewsStory("Innlend frétt", "Visir", "kl ble", "httpble", Categories.INNLENT ));
        }
        for (int i = 0; i < 3; i++) {
            this.newsStoryService.save(new NewsStory("Erlend frétt", "Visir", "kl ble", "httpble", Categories.ERLENT ));
        }
        for (int i = 0; i < 3; i++) {
            this.newsStoryService.save(new NewsStory("sportfrétt", "Visir", "kl ble", "httpble", Categories.SPORT ));
        }

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        User tempUser = new User("Karl Jóhann","pass123");
        tempUser.setCategories(categories);
        this.userService.save(tempUser);
        /*
        try {
            rentalLogService.save(new RentalLog(tempMovie.get(0),tempUser,sdf.parse("21/12/2012"),sdf.parse("31/12/2013") ));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<NewsStory> newsStories = newsStoryService.findByCategoriesIn(tempUser.getCategories());
        model.addAttribute("newsStories", newsStories );
        return "Index";*/
        return "Index";
    }
}
