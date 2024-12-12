package com.example.website.entity.Videocard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import jakarta.persistence.*;

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
    @Column(columnDefinition = "enum('NVIDIA', 'AMD', 'Intel','Apple')")
    @Enumerated(EnumType.STRING)
    private VideocardType manufacturer;
    @Column(name = "graphic_processor")
    private String graphicProcessor = "Geforce RTX";
    @Column(name = "video_memory")
    private BigDecimal videoMemory = BigDecimal.ONE;
    private String color = "Black";
    private BigDecimal price = BigDecimal.ZERO;
}
