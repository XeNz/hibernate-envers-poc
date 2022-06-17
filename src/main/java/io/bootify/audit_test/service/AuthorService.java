package io.bootify.audit_test.service;

import io.bootify.audit_test.domain.Author;
import io.bootify.audit_test.model.AuthorDTO;
import io.bootify.audit_test.repos.AuthorRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> findAll() {
        return authorRepository.findAll(Sort.by("id"))
                .stream()
                .map(author -> mapToDTO(author, new AuthorDTO()))
                .collect(Collectors.toList());
    }

    public AuthorDTO get(final UUID id) {
        return authorRepository.findById(id)
                .map(author -> mapToDTO(author, new AuthorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UUID create(final AuthorDTO authorDTO) {
        final Author author = new Author();
        mapToEntity(authorDTO, author);
        return authorRepository.save(author).getId();
    }

    public void update(final UUID id, final AuthorDTO authorDTO) {
        final Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(authorDTO, author);
        authorRepository.save(author);
    }

    public void delete(final UUID id) {
        authorRepository.deleteById(id);
    }

    private AuthorDTO mapToDTO(final Author author, final AuthorDTO authorDTO) {
        authorDTO.setId(author.getId());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        return authorDTO;
    }

    private Author mapToEntity(final AuthorDTO authorDTO, final Author author) {
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        return author;
    }

}
