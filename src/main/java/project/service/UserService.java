package project.service;

import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.NewsStory;
import project.persistence.entities.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(User user);

    List<User> findAll();

    @Query(value = "SELECT p FROM PostitNote p WHERE p.id = ?1")
    User findOne(Long id);

    User findByUName(String uName);

    User login(User user);
}
