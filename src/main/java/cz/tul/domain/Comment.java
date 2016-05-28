package cz.tul.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Author author;

    @Column
    private Date creation;

    @Column
    private Date modification;

    @Column
    private Long likes;

    @Column
    private Long dislikes;

    public Comment(Author author, Date creation, Date modification, Long likes, Long dislikes) {
        this.author = author;
        this.creation = creation;
        this.modification = modification;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getModification() {
        return modification;
    }

    public void setModification(Date modification) {
        this.modification = modification;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }
}
