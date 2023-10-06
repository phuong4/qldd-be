package com.evnit.ttpm.AuthApp.entity.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "S_TU_ERROR_LIMIT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class STUErrorLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CCX")
    private Double ccx;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Column(name = "F")
    private BigDecimal f;
    @Column(name = "DELTA")
    private BigDecimal delta;
    @Column(name = "IDX")
    private Integer idx;
}
