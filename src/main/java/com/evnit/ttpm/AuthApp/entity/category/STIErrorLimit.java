package com.evnit.ttpm.AuthApp.entity.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "S_TI_ERROR_LIMIT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class STIErrorLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CCX")
    private Double ccx;
    @Column(name = "CAPACITY")
    private Integer capacity;
    @Column(name = "F1")
    private BigDecimal f1;
    @Column(name = "DELTA1")
    private BigDecimal delta1;
    @Column(name = "F5")
    private BigDecimal f5;
    @Column(name = "DELTA5")
    private BigDecimal delta5;
    @Column(name = "F20")
    private BigDecimal f20;
    @Column(name = "DELTA20")
    private BigDecimal delta20;
    @Column(name = "F100")
    private BigDecimal f100;
    @Column(name = "DELTA100")
    private BigDecimal delta100;
    @Column(name = "F120")
    private BigDecimal f120;
    @Column(name = "DELTA120")
    private BigDecimal delta120;
    @Column(name = "IDX")
    private Integer idx;

}
