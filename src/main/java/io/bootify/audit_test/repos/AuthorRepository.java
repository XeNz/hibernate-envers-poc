package io.bootify.audit_test.repos;

import io.bootify.audit_test.domain.Author;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
