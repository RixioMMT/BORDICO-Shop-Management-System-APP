package org.BORDICO.Model.Pagination;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageOutput<T> {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private Set<T> objectList;
}