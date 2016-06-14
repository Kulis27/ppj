package cz.tul.service;

import cz.tul.domain.Image;
import cz.tul.domain.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    public String create(Image image) {
        repository.save(image);
        return image.getId();
    }

    public Image findOne(String id) {
        Image image = (Image) repository.findOne(id);
        if (image == null) {
            throw new ImageNotFoundException();
        } else {
            return image;
        }
    }

    public Image update(Image image) {
        return repository.save(image);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public void like(String id) {
        Image image = (Image) repository.findOne(id);
        if (image == null) {
            throw new ImageNotFoundException();
        }
        image.setLikes(image.getLikes() + 1);
        repository.save(image);
    }

    public void dislike(String id) {
        Image image = (Image) repository.findOne(id);
        if (image == null) {
            throw new ImageNotFoundException();
        }
        image.setDislikes(image.getDislikes() + 1);
        repository.save(image);
    }

    public List<Image> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Image> findByAuthorName(String name) {
        return repository.findByAuthorName(name);
    }

    public List<Image> findByTagName(String name) {
        return repository.findByTagName(name);
    }

}
