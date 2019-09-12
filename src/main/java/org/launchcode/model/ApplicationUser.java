package org.launchcode.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @Column(name = "username")
    private String userName;

    @NotNull
    @Column(name = "password")
    private String password;

    @OneToMany
    @JoinColumn(name= "application_user_id")
    private List<Habit> habits;


}



