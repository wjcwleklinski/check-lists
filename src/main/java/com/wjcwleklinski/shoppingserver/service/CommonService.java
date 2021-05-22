package com.wjcwleklinski.shoppingserver.service;

import com.wjcwleklinski.shoppingserver.error.exception.DuplicatedCodeException;
import com.wjcwleklinski.shoppingserver.model.entity.CommonEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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


}
