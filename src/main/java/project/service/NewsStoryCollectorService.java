package project.service;


import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Categories;
import project.persistence.entities.NewsStory;

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
                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.INNLENT));
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

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.ERLENT));
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

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.SPORT));
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

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.VIDSKIPTI));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Tækni og vísindi
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/togt/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.TOV));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Matur
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/matur/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.MATUR));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Ferðalög
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/ferdalog/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.FERDALOG));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Börn
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/born/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.BORN));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Smartland
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/smartland/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.SMARTLAND));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Bílar
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/bill/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.BILAR));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

        //Fólk
        try {
            URL feedSource = new URL("https://www.mbl.is/feeds/folk/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for(SyndEntry entry: (List<SyndEntry>) feed.getEntries()){
                String title = entry.getTitle();
                String link = entry.getLink();
                Long pubDate = entry.getPublishedDate().getTime();

                newsStoryList.add(new NewsStory(title,"Mbl.is",pubDate,link,Categories.FOLK));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: "+ex.getMessage());
        }

    }

    private void collectFromDV(){}



}
