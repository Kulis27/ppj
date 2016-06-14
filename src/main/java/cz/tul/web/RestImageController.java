package cz.tul.web;

import cz.tul.domain.Image;
import cz.tul.service.ImageNotFoundException;
import cz.tul.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RepositoryRestController
@RequestMapping(path = "/images")
public class RestImageController implements ResourceProcessor<Resource<Image>> {

    @Autowired
    private ImageService service;

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

    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleImageNotFoundException(ImageNotFoundException exception) {

    }

    @Override
    public Resource<Image> process(Resource<Image> resource) {
        Image image = resource.getContent();
        resource.add(linkTo(RestImageController.class).slash(image.getId()).slash("like").withRel("like"));
        resource.add(linkTo(RestImageController.class).slash(image.getId()).slash("dislike").withRel("dislike"));
        return resource;
    }

}
