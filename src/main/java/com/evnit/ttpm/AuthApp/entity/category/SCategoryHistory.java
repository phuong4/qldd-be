package com.evnit.ttpm.AuthApp.entity.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "S_Category_History")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SCategoryHistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String action;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "OLDCONTENT")
    private String oldContent;
    @Column(name = "NEWCONTENT")
    private String newContent;
    @Column(name = "CATEGORYTYPE")
    private String categoryType;
    @Column(name = "CATEGORYID")
    private String categoryId;
    @Column(name = "DATEMODIFIER")
    private Date dateModifier;
}
