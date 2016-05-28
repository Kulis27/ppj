package cz.tul.domain;

import org.springframework.boot.orm.jpa.EntityScan;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Date registration;

    public Author(String name, Date registration) {
        this.name = name;
        this.registration = registration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
