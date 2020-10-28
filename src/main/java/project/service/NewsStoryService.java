package project.service;

import project.persistence.entities.Categories;
import project.persistence.entities.NewsStory;

import java.util.List;

public interface NewsStoryService {


    NewsStory save(NewsStory newsStory);

    void save (List<NewsStory> newsStories);


    void delete(NewsStory newsStory);


    List<NewsStory> findAll();


    List<NewsStory> findAllChronological();

    NewsStory findOne(Long id);


    List<NewsStory> findByTitle(String title);

    List<NewsStory> findByCategoriesIn(List<String> newsCategories);

}