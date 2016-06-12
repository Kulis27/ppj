package cz.tul.domain.mongo;

import cz.tul.ApplicationProfile;
import cz.tul.domain.Tag;
import cz.tul.domain.TagRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile(ApplicationProfile.PROD_MONGO)
@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface MongoTagRepository extends TagRepository<Tag, Long> {
}
