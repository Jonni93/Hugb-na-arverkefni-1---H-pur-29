package project.persistence.entities;

import project.service.NewsStoryService;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class NewsStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String site;
    private Long timePosted; //currentMillis
    private String link;
    /*
    @ElementCollection(targetClass=Categories.class)
    @Column(name="categories", nullable=false)
    @CollectionTable(name="newsStory_categories", joinColumns= {@JoinColumn(name="newsStory_id")})
     */
    private String categories;

    public NewsStory(){

    }

    public NewsStory(String title, String site, Long timePosted, String link, String categories){
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
