package com.wjcwleklinski.shoppingserver.repository;

import com.wjcwleklinski.shoppingserver.model.entity.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CommonRepository<T extends CommonEntity> extends JpaRepository<T, Long> {

    Optional<T> getByCode(String code);

    void deleteByCode(String code);

}
