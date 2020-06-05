package com.meal.list.backend;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Greeting {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String message;

    public Greeting() {
    }

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

interface GreetingRepository extends CrudRepository<Greeting, Long>{

    @Override
    List<Greeting> findAll();
}
