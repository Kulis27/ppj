package cz.tul.service;

import cz.tul.domain.Comment;
import cz.tul.domain.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public String create(Comment comment) {
        repository.save(comment);
        return comment.getId();
    }

    public Comment findOne(String id) {
        Comment comment = (Comment) repository.findOne(id);
        if (comment == null) {
            throw new CommentNotFoundException();
        } else {
            return comment;
        }
    }

    public Comment update(Comment comment) {
        return repository.save(comment);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public void like(String id) {
        Comment comment = (Comment) repository.findOne(id);
        if (comment == null) {
            throw new CommentNotFoundException();
        }
        comment.setLikes(comment.getLikes() + 1);
        repository.save(comment);
    }

    public void dislike(String id) {
        Comment comment = (Comment) repository.findOne(id);
        if (comment == null) {
            throw new CommentNotFoundException();
        }
        comment.setDislikes(comment.getDislikes() + 1);
        repository.save(comment);
    }

}
