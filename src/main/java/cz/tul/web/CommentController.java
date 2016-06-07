package cz.tul.web;

import cz.tul.domain.Comment;
import cz.tul.domain.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RepositoryRestController
@RequestMapping(path = "/comments")
public class CommentController implements ResourceProcessor<Resource<Comment>> {

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(path = "/{id}/like", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void like(@PathVariable Long id) {
        Comment comment = commentRepository.findOne(id);
        if (comment != null) {
            comment.setLikes(comment.getLikes() + 1);
            commentRepository.save(comment);
        }
    }

    @RequestMapping(path = "/{id}/dislike", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislike(@PathVariable Long id) {
        Comment comment = commentRepository.findOne(id);
        if (comment != null) {
            comment.setDislikes(comment.getDislikes() + 1);
            commentRepository.save(comment);
        }
    }

    @Override
    public Resource<Comment> process(Resource<Comment> resource) {
        Comment comment = resource.getContent();
        resource.add(linkTo(CommentController.class).slash(comment.getId()).slash("like").withRel("like"));
        resource.add(linkTo(CommentController.class).slash(comment.getId()).slash("dislike").withRel("dislike"));
        return resource;
    }

}
