package com.evnit.ttpm.AuthApp.model.payload;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PagingParam {
    public static int defaultPageSize = 20;
    private Sort sortExpression;
    private List<String> sortBy;
    private List<String> sortOrders;
    private int limit;
    private int page;
    private int StartingIndex;

    public PagingParam() {
        limit = defaultPageSize;
        page = 0;
        sortBy = new ArrayList<>();
        sortOrders = new ArrayList<>();
    }

    public Sort getSortExpression() {
        List<Sort.Order> orders = new ArrayList<>();
        int sortBySize = sortBy.size();
        int sortOrderSize = sortOrders.size();
        if (sortBySize > sortOrderSize) {
            int diff = sortBySize - sortOrderSize;
            for (int i = 0; i < diff; i++) {
                sortOrders.add("desc");
            }
        }
        for (int i = 0; i < sortBySize; i++) {
            String field = sortBy.get(i);
            String order = sortOrders.get(i);
            orders.add(new Sort.Order(Sort.Direction.fromString(order), field));
        }
        return this.sortExpression = Sort.by(orders);
//        if(!sortBy.isEmpty()){
//
//            return this.sortExpression =
//        }
//        if(sortBy.isEmpty() && sortDesc){
//            return this.sortExpression = Sort.by("").descending();
//        }
//        else if(sortBy.isEmpty() &&  !sortDesc){
//            return this.sortExpression = Sort.by("").ascending();
//        }
//        return this.sortExpression = sortDesc ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
    }

    public int getStartingIndex() {
        return limit * (page - 1);
    }

    public void setSortExpression(Sort sortExpression) {
        this.sortExpression = sortExpression;
    }

    public List<String> getSortBy() {
        return sortBy;
    }

    public void setSortBy(List<String> sortBy) {
        this.sortBy = sortBy;
    }

    public List<String> getSortOrders() {
        return sortOrders;
    }

    public void setSortOrders(List<String> sortOrders) {
        this.sortOrders = sortOrders;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
