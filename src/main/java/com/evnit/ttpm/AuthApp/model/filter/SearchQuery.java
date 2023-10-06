package com.evnit.ttpm.AuthApp.model.filter;

import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import lombok.Data;

import java.util.List;

@Data
public class SearchQuery {
    private List<SearchFilter> searchFilters;
    private int pageNumber;
    private int pageSize;
    private SortOrder sortOrder;
    private List<JoinColumnProps> joinColumnProps;
    public void addSearchFilter(SearchFilter searchFilter) {
        this.searchFilters.add(searchFilter);
    }
}
