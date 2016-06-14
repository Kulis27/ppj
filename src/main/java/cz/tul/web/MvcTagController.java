package cz.tul.web;

import cz.tul.domain.Tag;
import cz.tul.service.TagNotFoundException;
import cz.tul.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@Controller
@RequestMapping("/mvc/tags")
public class MvcTagController {

    @Autowired
    private TagService service;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Tag get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String create(@Valid Tag tag, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        return service.create(tag);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Tag update(@Valid Tag tag, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        return service.update(tag);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("id") String id) {
        service.delete(id);
    }

    @ExceptionHandler(TagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTagNotFoundException(TagNotFoundException exception) {
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidationException(ValidationException exception) {
    }

}
