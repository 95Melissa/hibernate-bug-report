package com.example.bugreport.repositories;

import com.example.bugreport.domain.Author;
import com.example.bugreport.domain.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeAll
    void beforeAll() {
        bookRepository.saveAndFlush(getLotr());
    }

    @Test
    void saveEmptyAuthorsUpdateGetter() {
        Book invalid = bookRepository.findById(1L).get();
        invalid.getAuthors().clear();
        invalid.getAuthors().addAll(new HashSet<>());

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("authors", getPropertyPaths(e).get(0));
    }

    @Test
    void saveEmptyAuthorsUpdateSetter() {
        Book invalid = bookRepository.findById(1L).get();
        invalid.setAuthors(new HashSet<>());

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("authors", getPropertyPaths(e).get(0));
    }

    @Test
    void saveEmptyAuthorsGetter() {
        Book invalid = getGot();
        invalid.getAuthors().clear();
        invalid.getAuthors().addAll(new HashSet<>());

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("authors", getPropertyPaths(e).get(0));
    }

    @Test
    void saveEmptyAuthorsSetter() {
        Book invalid = getGot();
        invalid.setAuthors(new HashSet<>());

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("authors", getPropertyPaths(e).get(0));
    }

    @Test
    void saveInvalidAuthorsUpdateGetter() {
        Author invalidAuthor = new Author();
        invalidAuthor.setFirstName("");

        Book invalid = bookRepository.findById(1L).get();
        invalid.getAuthors().clear();
        invalid.getAuthors().addAll(Collections.singletonList(invalidAuthor));

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(2, e.getConstraintViolations().size());
        assertTrue(getPropertyPaths(e).containsAll(Arrays.asList("firstName", "lastName")));
    }

    @Test
    void saveInvalidAuthorsUpdateSetter() {
        Author invalidAuthor = new Author();
        invalidAuthor.setFirstName("");

        Book invalid = bookRepository.findById(1L).get();
        invalid.setAuthors(new HashSet<>(Collections.singletonList(invalidAuthor)));

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(2, e.getConstraintViolations().size());
        assertTrue(getPropertyPaths(e).containsAll(Arrays.asList("firstName", "lastName")));
    }

    @Test
    void saveInvalidAuthorsGetter() {
        Author invalidAuthor = new Author();
        invalidAuthor.setFirstName("");

        Book invalid = getGot();
        invalid.getAuthors().clear();
        invalid.getAuthors().addAll(Collections.singletonList(invalidAuthor));

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(2, e.getConstraintViolations().size());
        assertTrue(getPropertyPaths(e).containsAll(Arrays.asList("firstName", "lastName")));
    }

    @Test
    void saveInvalidAuthorsSetter() {
        Author invalidAuthor = new Author();
        invalidAuthor.setFirstName("");

        Book invalid = getGot();
        invalid.setAuthors(new HashSet<>(Collections.singletonList(invalidAuthor)));

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(2, e.getConstraintViolations().size());
        assertTrue(getPropertyPaths(e).containsAll(Arrays.asList("firstName", "lastName")));
    }

    @Test
    void saveNullAuthorsUpdate() {
        Book invalid = bookRepository.findById(1L).get();
        invalid.setAuthors(null);

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("authors", getPropertyPaths(e).get(0));
    }

    @Test
    void saveNullAuthors() {
        Book invalid = getGot();
        invalid.setAuthors(null);

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("authors", getPropertyPaths(e).get(0));
    }

    @Test
    void saveNullTitleUpdate() {
        Book invalid = bookRepository.findById(1L).get();
        invalid.setTitle(null);

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("title", getPropertyPaths(e).get(0));
    }

    @Test
    void saveNullTitle() {
        Book invalid = getGot();
        invalid.setTitle(null);

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("title", getPropertyPaths(e).get(0));
    }

    @Test
    void saveEmptyTitleUpdate() {
        Book invalid = bookRepository.findById(1L).get();
        invalid.setTitle("");

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("title", getPropertyPaths(e).get(0));
    }

    @Test
    void saveEmptyTitle() {
        Book invalid = getGot();
        invalid.setTitle("");

        ConstraintViolationException e = assertThrows(ConstraintViolationException.class, () -> bookRepository.saveAndFlush(invalid));
        assertEquals(1, e.getConstraintViolations().size());
        assertEquals("title", getPropertyPaths(e).get(0));
    }

    private Book getLotr() {
        Author tolkien = new Author();
        tolkien.setFirstName("John");
        tolkien.setLastName("Tolkien");

        Book lotr = new Book();
        lotr.setTitle("The Lord of the Rings");
        lotr.setAuthors(new HashSet<>(Collections.singletonList(tolkien)));
        return lotr;
    }

    private Book getGot() {
        Author martin = new Author();
        martin.setFirstName("George");
        martin.setLastName("Martin");

        Book got = new Book();
        got.setTitle("A Game of Thrones");
        got.setAuthors(new HashSet<>(Collections.singletonList(martin)));
        return got;
    }

    private List<String> getPropertyPaths(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(ConstraintViolation::getPropertyPath)
                .map(Path::toString)
                .collect(Collectors.toList());
    }

}
