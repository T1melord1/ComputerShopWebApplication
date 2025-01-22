package com.example.website.entity.Videocard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "videocards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videocard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @CreatedDate
    @Column(updatable = false, name = "created_date")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(columnDefinition = "enum('NVIDIA', 'AMD', 'Intel','Apple')")
    @Enumerated(EnumType.STRING)
    private VideocardType manufacturer;
    @Column(name = "graphic_processor")
    private String graphicProcessor = "Geforce RTX";
    @Column(name = "video_memory")
    private BigDecimal videoMemory = BigDecimal.ONE;
    private String color = "Black";
    private double price;
    @Column(name = "order_id")
    private Integer orderId;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
