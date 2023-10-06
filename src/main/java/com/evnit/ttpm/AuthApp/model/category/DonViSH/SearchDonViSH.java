package com.evnit.ttpm.AuthApp.model.category.DonViSH;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;

public class SearchDonViSH extends PagingParam  {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
