package cz.tul.domain.jpa;

import cz.tul.ApplicationProfile;
import cz.tul.domain.Image;
import cz.tul.domain.ImageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Profile({ApplicationProfile.PROD_MYSQL, ApplicationProfile.TEST})
@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface JpaImageRepository extends ImageRepository<Image, Long> {

    List<Image> findByName(@Param("name") String name);

    @Query("select i from Image i where i.author.name = :name")
    List<Image> findByAuthorName(@Param("name") String name);

    @Query("select i from Image i, in (i.tags) t where t.name = :name")
    List <Image> findByTagName(@Param("name") String name);

}
