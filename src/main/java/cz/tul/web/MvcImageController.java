package cz.tul.web;

import cz.tul.domain.Image;
import cz.tul.service.ImageNotFoundException;
import cz.tul.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Controller
@RequestMapping("/mvc/images")
public class MvcImageController {

    @Autowired
    private ImageService service;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Image get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String create(@Valid Image image, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        return service.create(image);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Image update(@Valid Image image, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        return service.update(image);
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

    @RequestMapping(path = "/findByName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Image> findByName(@RequestParam("name") String name) {
        return service.findByName(name);
    }

    @RequestMapping(path = "/findByAuthorName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Image> findByAuthorName(@RequestParam("name") String name) {
        return service.findByAuthorName(name);
    }

    @RequestMapping(path = "/findByTagName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Image> findByTagName(@RequestParam("name") String name) {
        return service.findByTagName(name);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleImageNotFoundException(ImageNotFoundException exception) {
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleValidationException(ValidationException exception) {
    }

}
