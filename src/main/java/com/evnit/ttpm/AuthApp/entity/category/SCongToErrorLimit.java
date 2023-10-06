package com.evnit.ttpm.AuthApp.entity.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "S_CONG_TO_ERROR_LIMIT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SCongToErrorLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PHASE")
    private String phase;
    @Column(name = "LOADLN")
    private String loadLN;
    @Column(name = "PF")
    private String pf;
    @Column(name = "QUANTITYLIMIT02")
    private Double quantityLimit02;
    @Column(name = "QUANTITYLIMIT05")
    private Double quantityLimit05;
    @Column(name = "QUANTITYLIMIT1")
    private Double quantityLimit1;
    @Column(name = "QUANTITYLIMIT2")
    private Double quantityLimit2;
    @Column(name = "QUANTITYLIMIT3")
    private Double quantityLimit3;
    @Column(name = "IDX")
    private Integer idx;

}
