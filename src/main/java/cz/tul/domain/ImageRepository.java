package cz.tul.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByName(@Param("name") String name);

}
