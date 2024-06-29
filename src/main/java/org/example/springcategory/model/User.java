package org.example.springcategory.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String login;
    String password;
    String name;
//    @Column(name = "surname")
    String lastname;
    String email;
    Role role;
//    @Column(name = "registration_date")
    LocalDateTime created;

    @OneToMany(mappedBy = "user")
    List<CartItem> cartItems;

    @OneToMany(mappedBy = "user")
    List<Order> orders;
}