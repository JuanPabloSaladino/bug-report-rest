package org.acme.responseutils;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public record PaginationResponse<E>(List<E> data, int page, int totalPages) {

    public PaginationResponse(PanacheQuery<E> query) {
        this(query.list(), query.page().index + 1, query.pageCount());
    }
}
