package project.service;


import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Categories;
import project.persistence.entities.NewsStory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

    private void collectFromVisir(){
        //Var búinn að skipta úr Jsoup yfir í ROME. Geymi þetta hér til ef ske kynni að þetta kæmi að gagni.
        /*
        catch (MalformedURLException ue){
        System.out.println("Malformed URL");
        }
        catch (IOException ioe){
        System.out.println("Something went wrong reading the contents");
        } catch (FeedException e) {
            e.printStackTrace();
        }
        */
        /*

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

         */

    }

    private void collectFromMBL(){
        //Innlendar fréttir
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/innlent/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();
                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,"Innlent"));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Erlendar fréttir
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/erlent/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,"Erlent"));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Íróttafréttir
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/sport/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,"Íþróttir"));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Viðskiptafréttir
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/vidskipti/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,"Viðskipti"));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }
    }

    private void collectFromDV(){}



}
