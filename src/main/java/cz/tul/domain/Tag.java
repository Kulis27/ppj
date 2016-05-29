package cz.tul.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(name = "IMAGE_TAG", joinColumns = @JoinColumn())
    private Set<Image> images = new HashSet<>();

    private String name;

    protected Tag() {

    }

    public Tag(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
