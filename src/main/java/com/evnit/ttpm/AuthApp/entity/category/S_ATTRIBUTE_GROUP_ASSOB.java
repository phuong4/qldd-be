package com.evnit.ttpm.AuthApp.entity.category;

import com.evnit.ttpm.AuthApp.entity.common.SListAll;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Date;

@Table(name = "S_ATTRIBUTE_GROUP_ASSOBJ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
@IdClass(SAttrGroupAssObjId.class)
public class S_ATTRIBUTE_GROUP_ASSOB implements Serializable {
    private static final long serialVersionUID = 1L;
 	@Id
    @Size(max = 1000000)
    @JsonProperty("OBJID")
    private String OBJID;
    @Size(max = 1000000)
    @JsonProperty("OBJTYPEID")
    @Column(name = "OBJTYPEID")
    @Id
    private String OBJTYPEID;
    @Size(max = 1000000)
    @JsonProperty("ATTRGROUPID")
    @Column(name = "ATTRGROUPID")
    @Id
    private String ATTRGROUPID;
    @Size(min = 1)
    @JsonProperty("ATTRGROUPDESC")
    @Column(name = "ATTRGROUPDESC")
    private String ATTRGROUPDESC;
    @Size(max = 1000000)
    @JsonProperty("ATTRGROUPORD")
    @Column(name = "ATTRGROUPORD")
    private int ATTRGROUPORD;
    @Size(max = 1000000)
    @JsonProperty("ATTRDATAID")
    @Column(name = "ATTRDATAID")
    private String ATTRDATAID;
   
}
