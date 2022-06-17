package io.bootify.audit_test.rest;

import io.bootify.audit_test.model.AuthorDTO;
import io.bootify.audit_test.service.AuthorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorResource {

    private final AuthorService authorService;

    public AuthorResource(final AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable final UUID id) {
        return ResponseEntity.ok(authorService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createAuthor(@RequestBody @Valid final AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.create(authorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable final UUID id,
            @RequestBody @Valid final AuthorDTO authorDTO) {
        authorService.update(id, authorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAuthor(@PathVariable final UUID id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
