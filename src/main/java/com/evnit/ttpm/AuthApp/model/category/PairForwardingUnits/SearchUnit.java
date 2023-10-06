package com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
public class SearchUnit extends PagingParam{
    private  Integer unit1;
    public Integer getUnit1(){return  unit1;}
    public void setUnit1(Integer UNIT1){this.unit1=UNIT1;}

    private  Integer unit2;
    public Integer getUnit2(){return  unit2;}
    public void setUnit2(Integer UNIT2){this.unit2=UNIT2;}

    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String DESCRIPTION) {
        this.description = DESCRIPTION;
    }
}
