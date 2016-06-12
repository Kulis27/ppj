package cz.tul.domain.mongo;

import cz.tul.ApplicationProfile;
import cz.tul.domain.Author;
import cz.tul.domain.AuthorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile(ApplicationProfile.PROD_MONGO)
@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")
public interface MongoAuthorRespository extends AuthorRepository<Author, Long> {
}
