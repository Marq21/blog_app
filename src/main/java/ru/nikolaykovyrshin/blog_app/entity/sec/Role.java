package ru.nikolaykovyrshin.blog_app.entity.sec;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
