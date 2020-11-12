package project.persistence.entities;

import javax.persistence.*;

@Entity
public class NewsStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String site;
    private Long timePosted; //currentMillis
    private String link;
    private Categories categories;

    public NewsStory(){

    }

    public NewsStory(String title, String site, Long timePosted, String link, Categories categories){
        this.title = title;
        this.site = site;
        this.timePosted = timePosted;
        this.link = link;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Long getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Long timePosted) {
        this.timePosted = timePosted;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
