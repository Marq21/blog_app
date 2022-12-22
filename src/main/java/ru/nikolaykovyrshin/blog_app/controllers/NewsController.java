package ru.nikolaykovyrshin.blog_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/fresh_news")
    public String getAllNews(Model model) {
        List<NewsArticle> articles = newsService.findAll();
        model.addAttribute("articlesList", articles);
        model.addAttribute("df", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return "fresh_news";
    }

    @GetMapping("fresh_news/{id}")
    public String getFullText(@PathVariable(value = "id") Long id, Model model) throws NewsNotFoundException {
        Optional<NewsArticle> optArticle = Optional.ofNullable(newsService.findById(id));
        List<NewsArticle> list = new ArrayList<>();
        optArticle.ifPresent(list::add);
        model.addAttribute("fullTextArticle", list);
        return "full_text_article";
    }

    @GetMapping("/create_article")
    public String newForm(Model model) {
        model.addAttribute("CreateNewForm", "newForm");
        return "create_article";
    }

    @PostMapping("/create_article")
    public String newsSubmit(@RequestParam String name, @RequestParam String fullDescription) {
        NewsArticle article = new NewsArticle();
        article.setName(name);
        article.setFullDescription(fullDescription);
        article.setCreated_at(LocalDateTime
                .now()
                .withNano(0));
        newsService.save(article);
        return "redirect:success";
    }

//    @DeleteMapping("{newsId}")
//    public void deleteNews(@PathVariable("newsId") Long id) {
//        newsService.deleteById(id);
//    }

    @GetMapping("/success")
    public String showSuccess() {
        return "success";
    }

}
