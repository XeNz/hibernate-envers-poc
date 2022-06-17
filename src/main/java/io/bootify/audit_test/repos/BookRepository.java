package io.bootify.audit_test.repos;

import io.bootify.audit_test.domain.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, UUID> {
}
