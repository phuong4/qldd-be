package com.evnit.ttpm.AuthApp.model.category.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SCategoryHistoryListDto {
    private Integer id;
    private String action;
    private Integer userId;
    private String oldContent;
    private String newContent;
    private String categoryType;
    private String username;
}
