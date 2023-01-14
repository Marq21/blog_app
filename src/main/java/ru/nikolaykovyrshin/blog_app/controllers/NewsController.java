package ru.nikolaykovyrshin.blog_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nikolaykovyrshin.blog_app.entity.NewsArticle;
import ru.nikolaykovyrshin.blog_app.exception.NewsNotFoundException;
import ru.nikolaykovyrshin.blog_app.service.NewsService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String getLastNews(Model model) {
        List<NewsArticle> articles = newsService.getLastThirteenNews();
        List<NewsArticle> topViewsArticles = newsService.getTopSixViewsNews();
        model.addAttribute("articlesList", articles);
        model.addAttribute("topViews", topViewsArticles);
        model.addAttribute("df", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return "news_main_page";
    }

    @GetMapping("/news")
    public String posts(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                        @RequestParam(value = "size", required = false, defaultValue = "12") int size, Model model) {
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        List<NewsArticle> topViewsArticlesOnPosts = newsService.getTopSixViewsNews();
        model.addAttribute("topViewsList", topViewsArticlesOnPosts);
        model.addAttribute("posts", newsService.getPage(pageNumber, size));
        return "posts";
    }

    @GetMapping("/{id}")
    public String getFullText(@PathVariable(value = "id") Long id, Model model) throws NewsNotFoundException {
        List<NewsArticle> topViewsArticles = newsService.getTopSixViewsNews();
        Optional<NewsArticle> optArticle = Optional.ofNullable(newsService.findById(id));
        NewsArticle incrNews = optArticle.orElseThrow();
        incrNews.setViews(incrNews.getViews()+1);
        newsService.save(incrNews);
        List<NewsArticle> list = new ArrayList<>();
        optArticle.ifPresent(list::add);
        model.addAttribute("fullTextArticle", list);
        model.addAttribute("topViewsList", topViewsArticles);
        return "full_text_article";
    }

    @GetMapping("/{id}/edit")
    public String newsEdit(@PathVariable(value = "id") Long id, Model model) throws NewsNotFoundException {
        Optional<NewsArticle> optArticle = Optional.ofNullable(newsService.findById(id));
        List<NewsArticle> editList = new ArrayList<>();
        optArticle.ifPresent(editList::add);
        model.addAttribute("editFullTextArticle", editList);
        return "article_edit";
    }

    @GetMapping("/create_article")
    public String newForm(Model model) {
        model.addAttribute("CreateNewForm", "newForm");
        List<NewsArticle> editArticles = newsService.findAll();
        model.addAttribute("articlesListForEdit", editArticles);
        model.addAttribute("df", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return "create_article";
    }

    @PostMapping("/create_article")
    public String newsSubmit(@RequestParam String name,
                             @RequestParam String img,
                             @RequestParam String fullDescription) {
        NewsArticle article = new NewsArticle();
        article.setName(name);
        article.setImageUri(img);
        article.setFullDescription(fullDescription);
        article.setCreated_at(LocalDateTime
                .now()
                .withNano(0));
        article.setViews(0L);
        newsService.save(article);
        return "success";
    }

    @PostMapping("/{id}/edit")
    public String updateNews(@PathVariable(value = "id") Long id,
                             @RequestParam String name,
                             @RequestParam String img,
                             @RequestParam String fullDescription) throws NewsNotFoundException {
        NewsArticle article = newsService.findById(id);
        article.setName(name);
        article.setImageUri(img);
        article.setFullDescription(fullDescription);
        article.setCreated_at(LocalDateTime
                .now()
                .withNano(0));
        newsService.save(article);
        return "success";
    }

    @PostMapping("/{id}/delete")
    public String deleteNews(@PathVariable(value = "id") Long id) {
        newsService.deleteById(id);
        return "success";
    }

    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }
}
