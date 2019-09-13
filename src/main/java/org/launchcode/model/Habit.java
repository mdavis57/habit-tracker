package org.launchcode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "habit")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name= "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private ApplicationUser applicationUser;

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
