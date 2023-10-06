package com.evnit.ttpm.AuthApp.entity.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "S_CONG_TO_ERROR_LIMIT_MT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SCongToErrorLimitMT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "EXACTLEVEL")
    private Integer exactLevel;
    @Column(name = "LOADIN")
    private String loadIn;
    @Column(name = "PHASE")
    private String phase;
    @Column(name = "COSSINPHI")
    private String cosSinPhi;
    @Column(name = "WH")
    private Double wh;
    @Column(name = "VARH")
    private Double varh;
    @Column(name = "IDX")
    private Integer idx;
}
