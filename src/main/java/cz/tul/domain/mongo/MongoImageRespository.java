package cz.tul.domain.mongo;

import cz.tul.ApplicationProfile;
import cz.tul.domain.Image;
import cz.tul.domain.ImageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@Profile(ApplicationProfile.PROD_MONGO)
@RepositoryRestResource(collectionResourceRel = "images", path = "images")
public interface MongoImageRespository extends ImageRepository<Image, Long> {

    List<Image> findByName(@Param("name") String name);

}
