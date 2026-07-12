package com.app.Ecommerce.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role=UserRole.CUSTOMER;
}
