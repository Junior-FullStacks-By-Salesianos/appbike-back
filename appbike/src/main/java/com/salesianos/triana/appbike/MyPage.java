package com.salesianos.triana.appbike;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record MyPage<T>(

        List<T> content,

        Integer size,

        Long totalElements,

        Integer pageNumber,

        boolean first,
        boolean last

)  {
    public static <T> MyPage<T> of(Page<T> page){
        return MyPage.<T>builder()
                .content(page.getContent())
                .first(page.isFirst())
                .last(page.isLast())
                .pageNumber(page.getPageable().getPageNumber())
                .size(page.getPageable().getPageSize())
                .totalElements(page.getTotalElements())
                .build();
    }

}
