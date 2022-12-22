package ru.nikolaykovyrshin.blog_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikolaykovyrshin.blog_app.entity.NewsArticle;

public interface NewsRepository extends JpaRepository<NewsArticle, Long> {
}
