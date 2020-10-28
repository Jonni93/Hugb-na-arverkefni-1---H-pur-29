package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Categories;
import project.persistence.entities.NewsStory;
import project.persistence.repositories.NewsStoryRepository;
import project.service.NewsStoryService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class NewsStoryServiceImplementation implements NewsStoryService {

    NewsStoryRepository repository;

    @Autowired
    public NewsStoryServiceImplementation(NewsStoryRepository repository){
        this.repository = repository;
    }

    @Override
    public NewsStory save(NewsStory newsStory) {
        return repository.save(newsStory);
    }


    @Override
    public void save(List<NewsStory> newsStories){
        for(NewsStory ns : newsStories){
            save(ns);
        }
    }

    @Override
    public void delete(NewsStory newsStory) {
        repository.delete(newsStory);
    }

    @Override
    public List<NewsStory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<NewsStory> findAllChronological() {
        List<NewsStory> list = repository.findAll();
        Collections.sort(list, Comparator.comparingLong(NewsStory ::getTimePosted));
        Collections.reverse(list);
        return list;
    }

    @Override
    public NewsStory findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<NewsStory> findByTitle(String title){
        return repository.findByTitle(title);
    }

    @Override
    public List<NewsStory> findByCategoriesIn(List<String> categories) {
        return repository.findByCategoriesIn(categories);
    }
}
