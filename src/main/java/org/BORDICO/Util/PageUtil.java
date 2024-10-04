package org.BORDICO.Util;

import lombok.RequiredArgsConstructor;
import org.BORDICO.Model.Enum.ESort;
import org.BORDICO.Model.Inputs.PageInput;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class PageUtil {
    public static Pageable buildPage(PageInput input) {
        Pageable paging;
        if (input.getSort().equals(ESort.ASC)) {
            paging = PageRequest.of(input.getPageNo(), input.getPageSize(), Sort.by(input.getSortBy()).ascending());
        } else if (input.getSort().equals(ESort.DES)) {
            paging = PageRequest.of(input.getPageNo(), input.getPageSize(), Sort.by(input.getSortBy()).descending());
        } else {
            throw new IllegalArgumentException("Invalid sort direction");
        }
        return paging;
    }
}