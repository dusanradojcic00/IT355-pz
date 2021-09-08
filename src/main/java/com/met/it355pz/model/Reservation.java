package com.met.it355pz.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endingDate;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "reservation")
    private Invoice invoice;

    public User getUser() {
        return user;
    }

    public Reservation(Long id, LocalDate startingDate, LocalDate endingDate, Car car) {
        this.id = id;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.car = car;
    }
}
