package cz.tul.web;

import cz.tul.domain.Author;
import cz.tul.service.AuthorNotFoundException;
import cz.tul.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@RequestMapping("/mvc/authors")
public class MvcAuthorController {

    @Autowired
    private AuthorService service;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Author get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String create(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        return service.create(author);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Author update(@Valid Author author, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        return service.update(author);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("id") String id) {
        service.delete(id);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleAuthorNotFoundException(AuthorNotFoundException exception) {
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidationException(ValidationException exception) {
    }

}
