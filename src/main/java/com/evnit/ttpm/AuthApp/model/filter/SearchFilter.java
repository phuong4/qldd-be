package com.evnit.ttpm.AuthApp.model.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchFilter {
    private String property;
    private String operator;
    private Object value;
    private String groupOperator;
    public SearchFilter(String property,String operator,Object value){
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.groupOperator = "AND";
    }
    public SearchFilter(String property,String operator,Object value,String groupOperator){
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.groupOperator = groupOperator;
    }
    public String getGroupOperator() {
        return groupOperator;
    }

    public void setGroupOperator(String groupOperator) {
        this.groupOperator = groupOperator;
    }
}
