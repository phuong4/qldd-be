package com.evnit.ttpm.AuthApp.model.category.DeliveryUnit;
import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
public class SearchDeliveryUnit extends PagingParam {
    private  String name;
    public String getName(){return  name;}
    public void setName(String NAME){this.name=NAME;}

    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String DESCRIPTION) {
        this.description = DESCRIPTION;
    }

}
