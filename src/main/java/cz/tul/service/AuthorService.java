package cz.tul.service;

import cz.tul.domain.Author;
import cz.tul.domain.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public String create(Author author) {
        repository.save(author);
        return author.getId();
    }

    public Author findOne(String id) {
        Author author = (Author) repository.findOne(id);
        if (author == null) {
            throw new AuthorNotFoundException();
        } else {
            return author;
        }
    }

    public Author update(Author author) {
        return repository.save(author);
    }

    public void delete(String id) {
        repository.delete(id);
    }

}
