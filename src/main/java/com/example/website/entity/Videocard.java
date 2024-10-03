package com.example.website.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "videocards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videocard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "enum('NVIDIA', 'AMD', 'Intel')")
    @Enumerated(EnumType.STRING)
    private VideocardType manufacturer;
    @Column(name = "graphic_processor")
    private String graphicProcessor;
    @Column(name = "video_memory")
    private Integer videoMemory;
    private String color;
    private BigDecimal price;
}
