package cz.tul.service;

import cz.tul.domain.Tag;
import cz.tul.domain.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    public String create(Tag tag) {
        repository.save(tag);
        return tag.getId();
    }

    public Tag findOne(String id) {
        Tag tag = (Tag) repository.findOne(id);
        if (tag == null) {
            throw new TagNotFoundException();
        } else {
            return tag;
        }
    }

    public Tag update(Tag tag) {
        return repository.save(tag);
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
