package cz.tul.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URI;
import java.util.*;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "image")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    private URI url;

    private String name;

    private Date creation;

    private Date modification;

    private Long likes;

    private Long dislikes;

    protected Image() {

    }

    public Image(Author author, URI url, String name, Date creation) {
        this.author = author;
        this.url = url;
        this.name = name;
        this.creation = creation;
        this.modification = creation;
        this.likes = (long) 0;
        this.dislikes = (long) 0;
    }

    public Long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
