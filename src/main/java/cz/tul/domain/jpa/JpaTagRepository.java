package cz.tul.domain.jpa;

import cz.tul.ApplicationProfile;
import cz.tul.domain.TagRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile({ApplicationProfile.PROD_MYSQL, ApplicationProfile.TEST})
@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface JpaTagRepository extends TagRepository {
}
