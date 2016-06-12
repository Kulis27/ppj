package cz.tul.domain.jpa;

import cz.tul.ApplicationProfile;
import cz.tul.domain.Author;
import cz.tul.domain.AuthorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile({ApplicationProfile.PROD_MYSQL, ApplicationProfile.TEST})
@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")
public interface JpaAuthorRepository extends AuthorRepository<Author, Long> {
}
