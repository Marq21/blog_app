package ru.nikolaykovyrshin.blog_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikolaykovyrshin.blog_app.entity.NewsArticle;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsArticle, Long> {

    List<NewsArticle> findTop14ByOrderByIdDesc();

    List<NewsArticle> findTop6ByOrderByViewsDesc();
}
