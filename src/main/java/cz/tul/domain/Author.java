package cz.tul.domain;

import java.util.Date;

/**
 * Created by filak on 28.05.2016.
 */
public class Author {

    private Long id;

    private String name;

    private Date registration;

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
