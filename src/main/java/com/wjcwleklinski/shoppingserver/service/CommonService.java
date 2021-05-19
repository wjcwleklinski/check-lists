package com.wjcwleklinski.shoppingserver.service;

import org.springframework.data.jpa.repository.JpaRepository;

public class CommonService {

    protected <T> void save(T entity, JpaRepository<T, Long> repository) {
        validateCodeUniqueness(repository);
    }

    private <T> void validateCodeUniqueness(JpaRepository<T, Long> repository) {
    }


}
