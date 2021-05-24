package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.error.exception.DuplicatedCodeException;
import com.wjcwleklinski.shoppingserver.error.exception.NotFoundException;
import com.wjcwleklinski.shoppingserver.model.entity.CommonEntity;
import com.wjcwleklinski.shoppingserver.repository.CommonRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonService {

    protected <T extends CommonEntity> void save(T entity, JpaRepository<T, Long> repository) {
        if (entity.getCode() == null) {
            entity.setCode(RandomStringUtils.randomAlphanumeric(15));
        }
        try {
            repository.save(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicatedCodeException(entity.getCode());
        }
    }

    protected <T extends CommonEntity> CommonEntity getByCode(String entityCode , CommonRepository<T> repository) {
        Optional<T> entity = repository.getByCode(entityCode);
        if (entity.isEmpty()) {
            throw new NotFoundException(entityCode);
        }
        return entity.get();
    }
}
