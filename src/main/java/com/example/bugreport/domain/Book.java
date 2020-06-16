package com.example.bugreport.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @NotEmpty
    private Set<Author> authors = new HashSet<>();

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
        if (authors != null) {
            authors.forEach(author -> author.getBooks().add(this));
        }
    }

}
