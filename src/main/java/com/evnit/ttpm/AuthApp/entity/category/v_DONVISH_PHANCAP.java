package com.evnit.ttpm.AuthApp.entity.category;

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

@Table(name = "v_DONVISH_PHANCAP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@Entity
public class v_DONVISH_PHANCAP implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @JsonProperty("ID")
    @Column(name = "ID")
    private Integer id;

    @JsonProperty("ParentId")
    @Column(name = "PARENTID")
    private Integer ParentId;

    @JsonProperty("level")
    @Column(name = "level")
    private Integer level;

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonProperty("rn")
    @Column(name = "rn")
    private String rn;

    @JsonProperty("NAMESHORT")
    @Column(name = "NAMESHORT")
    private String nameShort;

    @JsonProperty("NOTE")
    @Column(name = "NOTE")
    private String note;

    @JsonProperty("IDHIS")
    @Column(name = "IDHIS")
    private String idHis;

    @JsonProperty("PARENT")
    @Column(name = "PARENT")
    private String parent;
}
