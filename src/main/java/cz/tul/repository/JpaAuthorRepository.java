package cz.tul.repository;

import cz.tul.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAuthorRepository extends JpaRepository<Author, Long> {
}
