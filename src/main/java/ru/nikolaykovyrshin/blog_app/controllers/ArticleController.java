//package ru.nikolaykovyrshin.blog_app.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import ru.nikolaykovyrshin.blog_app.entity.NewsArticle;
//import ru.nikolaykovyrshin.blog_app.exception.NewsNotFoundException;
//import ru.nikolaykovyrshin.blog_app.service.NewsService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Controller("/full_text_article")
//public class ArticleController {
//
//    private final NewsService newsService;
//
//    public ArticleController(NewsService newsService) {
//        this.newsService = newsService;
//    }
//
//
//
//    @GetMapping
//    public String getFullTextPage() {
//        return "full_text_article";
//    }
//
//}
