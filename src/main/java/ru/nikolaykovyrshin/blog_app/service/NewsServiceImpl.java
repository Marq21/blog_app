package ru.nikolaykovyrshin.blog_app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nikolaykovyrshin.blog_app.entity.NewsArticle;
import ru.nikolaykovyrshin.blog_app.entity.paging.Paged;
import ru.nikolaykovyrshin.blog_app.entity.paging.Paging;
import ru.nikolaykovyrshin.blog_app.exception.NewsNotFoundException;
import ru.nikolaykovyrshin.blog_app.repo.NewsRepository;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

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
    public Paged<NewsArticle> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<NewsArticle> postPage = newRepository.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

    @Override
    public List<NewsArticle> getLastThirteenNews() {
        return newRepository.findTop14ByOrderByIdDesc();
    }

    @Override
    public List<NewsArticle> getTopSixViewsNews() {
        return newRepository.findTop6ByOrderByViewsDesc();
    }
}
