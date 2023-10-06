package com.evnit.ttpm.AuthApp.model.request.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreeSelect {
    private Integer id;
    private String name;
    public String value;
    private Integer parentId;
    private Integer type;
    public TreeSelect parent;
    public List<TreeSelect> children;
    public List<TreeSelect> _children;
}
