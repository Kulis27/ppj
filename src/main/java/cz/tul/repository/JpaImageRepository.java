package cz.tul.repository;

import cz.tul.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaImageRepository extends JpaRepository<Image, Long> {
}
