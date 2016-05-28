package cz.tul.domain;

import javax.persistence.*;
import java.net.URI;
import java.util.Date;
import java.util.List;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private URI uri;

    @Column
    private String name;

    @Column
    private Author author;

    @Column
    private Date creation;

    @Column
    private Date modification;

    @Column
    private Long likes;

    @Column
    private Long dislikes;

    @OneToMany
    private List<String> tags;

    @OneToMany
    private List<Comment> comments;

    public Image(URI uri, String name, Author author, Date creation, Date modification, Long likes, Long dislikes, List<String> tags, List<Comment> comments) {
        this.uri = uri;
        this.name = name;
        this.author = author;
        this.creation = creation;
        this.modification = modification;
        this.likes = likes;
        this.dislikes = dislikes;
        this.tags = tags;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
