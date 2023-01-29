package ru.nikolaykovyrshin.blog_app.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nikolaykovyrshin.blog_app.entity.sec.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);
}
