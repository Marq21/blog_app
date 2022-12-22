package ru.nikolaykovyrshin.blog_app.service;

import org.springframework.stereotype.Service;
import ru.nikolaykovyrshin.blog_app.entity.NewsArticle;
import ru.nikolaykovyrshin.blog_app.exception.NewsNotFoundException;
import ru.nikolaykovyrshin.blog_app.repo.NewsRepository;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newRepository;

    public NewsServiceImpl(NewsRepository newRepository) {
        this.newRepository = newRepository;
    }

    @Override
    public void save(NewsArticle article) {
        newRepository.save(article);
    }

    @Override
    public List<NewsArticle> findAll() {
        return newRepository.findAll();
    }

    @Override
    public NewsArticle findById(long id) throws NewsNotFoundException {
        return newRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException(id));
    }

    @Override
    public void deleteById(long id) {
        newRepository.deleteById(id);
    }

    @Override
    public void update(NewsArticle article) {
    }
}
