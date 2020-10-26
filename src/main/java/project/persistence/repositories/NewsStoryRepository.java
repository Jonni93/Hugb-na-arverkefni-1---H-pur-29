package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Categories;
import project.persistence.entities.NewsStory;

import java.util.List;

public interface NewsStoryRepository extends JpaRepository<NewsStory, Long> {

    NewsStory save(NewsStory newsStory);

    void delete(NewsStory newsStory);

    List<NewsStory> findAll();

    // Instead of the method findAllReverseOrder() in PostitNoteService.java,
    // We could have used this method by adding the words
    // ByOrderByIdDesc, which mean: Order By Id in a Descending order
    //
    List<NewsStory> findAllByOrderByIdDesc();

    @Query(value = "SELECT p FROM NewsStory p WHERE p.id = ?1")
    NewsStory findOne(Long id);

    List<NewsStory> findByTitle(String title);

    List<NewsStory> findByCategoriesIn(List<Categories> categories);
}