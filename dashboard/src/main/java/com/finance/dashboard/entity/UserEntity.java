package com.finance.dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;

    @Column(name = "created_at")
    private LocalDate createdAt;

    private String status;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
