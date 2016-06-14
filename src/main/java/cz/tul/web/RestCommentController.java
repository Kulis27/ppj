package cz.tul.web;

import cz.tul.domain.Comment;
import cz.tul.service.CommentNotFoundException;
import cz.tul.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RepositoryRestController
@RequestMapping(path = "/comments")
public class RestCommentController implements ResourceProcessor<Resource<Comment>> {

    @Autowired
    private CommentService service;

    @RequestMapping(path = "/{id}/like", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void like(@PathVariable String id) {
        service.like(id);
    }

    @RequestMapping(path = "/{id}/dislike", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislike(@PathVariable String id) {
        service.dislike(id);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleCommentNotFoundException(CommentNotFoundException exception) {

    }

    @Override
    public Resource<Comment> process(Resource<Comment> resource) {
        Comment comment = resource.getContent();
        resource.add(linkTo(RestCommentController.class).slash(comment.getId()).slash("like").withRel("like"));
        resource.add(linkTo(RestCommentController.class).slash(comment.getId()).slash("dislike").withRel("dislike"));
        return resource;
    }

}
