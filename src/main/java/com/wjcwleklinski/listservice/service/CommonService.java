package com.wjcwleklinski.listservice.service;

import com.wjcwleklinski.listservice.error.exception.ConflictException;
import com.wjcwleklinski.listservice.error.exception.NotFoundException;
import com.wjcwleklinski.listservice.model.entity.CommonEntity;
import com.wjcwleklinski.listservice.repository.CommonRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonService {

    public <T extends CommonEntity> void save(T entity, JpaRepository<T, Long> repository) {
        if (entity.getCode() == null) {
            entity.setCode(RandomStringUtils.randomAlphanumeric(15));
        }
        try {
            repository.save(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException(entity.getCode());
        }
    }

    public <T extends CommonEntity> CommonEntity getByCode(String entityCode , CommonRepository<T> repository) {
        Optional<T> entity = repository.getByCode(entityCode);
        if (entity.isEmpty()) {
            throw new NotFoundException(entityCode);
        }
        return entity.get();
    }

    public <T extends CommonEntity> void deleteByCode(String entityCode , CommonRepository<T> repository) {
        if (repository.getByCode(entityCode).isEmpty()) {
            throw new NotFoundException(entityCode);
        }
        repository.deleteByCode(entityCode);
    }
}
