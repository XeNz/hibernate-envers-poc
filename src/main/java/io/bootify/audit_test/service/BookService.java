package io.bootify.audit_test.service;

import io.bootify.audit_test.domain.Author;
import io.bootify.audit_test.domain.Book;
import io.bootify.audit_test.model.BookDTO;
import io.bootify.audit_test.repos.AuthorRepository;
import io.bootify.audit_test.repos.BookRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(final BookRepository bookRepository,
                       final AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll(Sort.by("id"))
                .stream()
                .map(book -> mapToDTO(book, new BookDTO()))
                .collect(Collectors.toList());
    }

    public BookDTO get(final UUID id) {
        return bookRepository.findById(id)
                .map(book -> mapToDTO(book, new BookDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final BookDTO bookDTO) {
        final Book book = new Book();
        mapToEntity(bookDTO, book);
        return bookRepository.save(book).getId();
    }

    public void update(final UUID id, final BookDTO bookDTO) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(bookDTO, book);
        bookRepository.save(book);
    }

    public void delete(final UUID id) {
        bookRepository.deleteById(id);
    }

    private BookDTO mapToDTO(final Book book, final BookDTO bookDTO) {
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setBookAuthor(book.getBookAuthor() == null ? null : book.getBookAuthor().getId());
        return bookDTO;
    }

    private Book mapToEntity(final BookDTO bookDTO, final Book book) {
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        final Author bookAuthor = bookDTO.getBookAuthor() == null ? null : authorRepository.findById(bookDTO.getBookAuthor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "bookAuthor not found"));
        book.setBookAuthor(bookAuthor);
        return book;
    }

}
