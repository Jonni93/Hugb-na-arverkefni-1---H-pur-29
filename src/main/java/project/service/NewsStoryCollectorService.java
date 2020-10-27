package project.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Categories;
import project.persistence.entities.NewsStory;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsStoryCollectorService {

    List<NewsStory> newsStoryList;

    @Autowired
    public NewsStoryCollectorService(){
        newsStoryList = new ArrayList<>();
    }

    /*
    Á eftir að útfæra
     */
    public List<NewsStory> findStories(){
        collectFromVisir();
        collectFromDV();
        collectFromMBL();
        return newsStoryList;

    }
    //Einhverjar Jsoup þreifingar hér.Þetta virkar að mestu leyti
    private void collectFromVisir(){


        String url = "https://www.visir.is/f/frettir";

        try{
            Document document = Jsoup.connect(url).get();

            for(Element e : document.select("section.segment").select("article") ){
                String title = e.select(".article-item__title").text();
                String site = "Vísir";
                String timePosted = e.select("time.article-item__time").text();
                String link ="visir.is" + e.select(".article-item__title").select("a[href]").attr("href");
                Categories category = Categories.valueOf(e.select("article-item__meta").text());
                newsStoryList.add(new NewsStory(title, site, timePosted, link, category));


                System.out.println(e.select(".article-item__title").text());
                System.out.println(e.select(".article-item__title").select("a[href]").attr("href"));
                System.out.println(e.select("div.article-item__meta").select("a").text());
                System.out.println(e.select("time.article-item__time").text());
                System.out.println();

            }


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void collectFromMBL(){ }

    private void collectFromDV(){}



}
