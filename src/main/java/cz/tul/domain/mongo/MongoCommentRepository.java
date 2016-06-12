package cz.tul.domain.mongo;

import cz.tul.ApplicationProfile;
import cz.tul.domain.Comment;
import cz.tul.domain.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Profile(ApplicationProfile.PROD_MONGO)
@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface MongoCommentRepository extends CommentRepository<Comment, Long> {
}
