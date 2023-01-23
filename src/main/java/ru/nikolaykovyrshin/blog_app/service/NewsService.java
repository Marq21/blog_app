package ru.nikolaykovyrshin.blog_app.service;

import ru.nikolaykovyrshin.blog_app.entity.NewsArticle;
import ru.nikolaykovyrshin.blog_app.entity.paging.Paged;
import ru.nikolaykovyrshin.blog_app.exception.NewsNotFoundException;

import java.util.List;

public interface NewsService {

    void save(NewsArticle article);

    List<NewsArticle> findAll();

    NewsArticle findById(long id) throws NewsNotFoundException;

    void deleteById(long id);

    Paged<NewsArticle> getPage(int pageNumber, int size);

    List<NewsArticle> getLastThirteenNews();

    List<NewsArticle> getTopSixViewsNews();

}
