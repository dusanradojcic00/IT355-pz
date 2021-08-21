package com.met.it355pz.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NaturalId
    @Email
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    @OneToOne
    private Address adress;

    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany
    private List<Reservation> reservations;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
