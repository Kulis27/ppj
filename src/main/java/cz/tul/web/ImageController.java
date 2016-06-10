package cz.tul.web;

import cz.tul.domain.Image;
import cz.tul.domain.ImageRepository;
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
@RequestMapping(path = "/images")
public class ImageController implements ResourceProcessor<Resource<Image>> {

    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(path = "/{id}/like", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void like(@PathVariable Long id) {
        Image image = imageRepository.findOne(id);
        if (image == null) {
            throw new ImageNotFound();
        }
        image.setLikes(image.getLikes() + 1);
        imageRepository.save(image);
    }

    @RequestMapping(path = "/{id}/dislike", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislike(@PathVariable Long id) {
        Image image = imageRepository.findOne(id);
        if (image == null) {
            throw new ImageNotFound();
        }
        image.setDislikes(image.getDislikes() + 1);
        imageRepository.save(image);
    }

    @Override
    public Resource<Image> process(Resource<Image> resource) {
        Image image = resource.getContent();
        resource.add(linkTo(ImageController.class).slash(image.getId()).slash("like").withRel("like"));
        resource.add(linkTo(ImageController.class).slash(image.getId()).slash("dislike").withRel("dislike"));
        return resource;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ImageNotFound extends RuntimeException {
    }

}
