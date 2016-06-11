package cz.tul.service;

import cz.tul.domain.Image;
import cz.tul.domain.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    public void like(Long id) {
        Image image = repository.findOne(id);
        if (image == null) {
            throw new ImageNotFoundException();
        }
        image.setLikes(image.getLikes() + 1);
        repository.save(image);
    }

    public void dislike(Long id) {
        Image image = repository.findOne(id);
        if (image == null) {
            throw new ImageNotFoundException();
        }
        image.setDislikes(image.getDislikes() + 1);
        repository.save(image);
    }

}
