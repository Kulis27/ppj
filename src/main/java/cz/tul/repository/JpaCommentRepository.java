package cz.tul.repository;

import cz.tul.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {
}
