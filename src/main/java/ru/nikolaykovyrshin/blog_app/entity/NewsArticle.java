package ru.nikolaykovyrshin.blog_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class NewsArticle {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(
            nullable = false,
            columnDefinition="TEXT"
    )
    private String fullDescription;
    private LocalDateTime created_at;
}
