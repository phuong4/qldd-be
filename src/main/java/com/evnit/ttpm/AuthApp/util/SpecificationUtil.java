package com.evnit.ttpm.AuthApp.util;

import com.evnit.ttpm.AuthApp.model.filter.JoinColumnProps;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class SpecificationUtil {
    public static <T> Specification<T> bySearchQuery(SearchQuery searchQuery, Class<T> classes) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> orPredicates = new ArrayList<>();
            //Add predicates for table to be joined
            List<JoinColumnProps> joinColumnProps = searchQuery.getJoinColumnProps();
            if (joinColumnProps != null && !joinColumnProps.isEmpty()) {
                for (JoinColumnProps joinColumnProp : joinColumnProps) {
                    addJoinColumnProps(predicates, joinColumnProp, criteriaBuilder, root);

                }
            }
            List<SearchFilter> searchFilters = searchQuery.getSearchFilters();
            if (searchFilters != null && !searchFilters.isEmpty()) {
                for (final SearchFilter searchFilter : searchFilters) {
                    if ("or".equalsIgnoreCase(searchFilter.getGroupOperator())) {
                        addPredicates(orPredicates, searchFilter, criteriaBuilder, root);
                    } else {
                        addPredicates(predicates, searchFilter, criteriaBuilder, root);
                    }
                }
            }
            Predicate andPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            Predicate orPredicate = criteriaBuilder.or(orPredicates.toArray(new Predicate[0]));
            if (!predicates.isEmpty() && !orPredicates.isEmpty()) {
                return criteriaBuilder.and(andPredicate, orPredicate);
            } else if (!predicates.isEmpty()) {
                return andPredicate;
            } else if (!orPredicates.isEmpty()) {
                return orPredicate;
            } else {
                return criteriaBuilder.conjunction();
            }
            //criteriaBuilder.or(orPredicates.toArray(new Predicate[0]));
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static <T> void addJoinColumnProps(List<Predicate> predicates, JoinColumnProps joinColumnProp,
                                               CriteriaBuilder criteriaBuilder, Root<T> root) {
        SearchFilter searchFilter = joinColumnProp.getSearchFilter();
        Join<Object, Object> joinParent = root.join(joinColumnProp.getJoinColumnName());
        String property = searchFilter.getProperty();
        Path expression = joinParent.get(property);
        addPredicate(predicates, searchFilter, criteriaBuilder, expression);
    }

    private static <T> void addPredicates(List<Predicate> predicates, SearchFilter searchFilter,
                                          CriteriaBuilder criteriaBuilder, Root<T> root) {
        String property = searchFilter.getProperty();
        Path expression = root.get(property);
        addPredicate(predicates, searchFilter, criteriaBuilder, expression);
    }

    private static <T> void addPredicate(List<Predicate> predicates, SearchFilter searchFilter,
                                         CriteriaBuilder criteriaBuilder, Path expression) {
        switch (searchFilter.getOperator()) {
            case "=":
                predicates.add(criteriaBuilder.equal(expression, searchFilter.getValue()));
                break;
            case "LIKE":
                predicates.add(criteriaBuilder.like(expression, "%" + searchFilter.getValue() + "%"));
                break;
            case "IN":
                predicates.add(criteriaBuilder.in(expression).value(searchFilter.getValue()));
                break;
            case "NotIn":  // Handle NOT IN operator
                predicates.add(criteriaBuilder.not(expression.in(searchFilter.getValue())));
                break;
            case ">":
                predicates.add(criteriaBuilder.greaterThan(expression, (Comparable) searchFilter.getValue()));
                break;
            case "<":
                predicates.add(criteriaBuilder.lessThan(expression, (Comparable) searchFilter.getValue()));
                break;
            case ">=":
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(expression, (Comparable) searchFilter.getValue()));
                break;
            case "<=":
                predicates.add(criteriaBuilder.lessThanOrEqualTo(expression, (Comparable) searchFilter.getValue()));
                break;
            case "!":
                predicates.add(criteriaBuilder.notEqual(expression, searchFilter.getValue()));
                break;
            case "IsNull":
                predicates.add(criteriaBuilder.isNull(expression));
                break;
            case "NotNull":
                predicates.add(criteriaBuilder.isNotNull(expression));
                break;
            default:
                System.out.println("Predicate is not matched");
                throw new IllegalArgumentException(searchFilter.getOperator() + " is not a valid predicate");
        }

    }
}

