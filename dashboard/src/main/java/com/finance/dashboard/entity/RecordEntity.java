package com.finance.dashboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "record")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float amount;
    private String recordType;
    private String category;
    private LocalDate createdAt;
    private Integer createdBy;
    private String description;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
