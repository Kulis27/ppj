package cz.tul.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "author")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();

    private String name;

    private Date registration;

    protected Author() {
    }

    public Author(String name, Date registration) {
        this.name = name;
        this.registration = registration;
    }

    public Long getId() {
        return id;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

}
