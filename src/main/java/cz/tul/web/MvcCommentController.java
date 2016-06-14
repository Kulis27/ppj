package cz.tul.web;

import cz.tul.domain.Comment;
import cz.tul.service.CommentNotFoundException;
import cz.tul.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@RequestMapping("/mvc/comments")
public class MvcCommentController {

    @Autowired
    private CommentService service;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Comment get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String create(@Valid Comment comment, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        return service.create(comment);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Comment update(@Valid Comment comment, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        return service.update(comment);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("id") String id) {
        service.delete(id);
    }

    @RequestMapping(path = "/like", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void like(@RequestParam("id") String id) {
        service.like(id);
    }

    @RequestMapping(path = "/dislike", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void dislike(@RequestParam("id") String id) {
        service.dislike(id);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleCommentNotFoundException(CommentNotFoundException exception) {
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidationException(ValidationException exception) {
    }

}
