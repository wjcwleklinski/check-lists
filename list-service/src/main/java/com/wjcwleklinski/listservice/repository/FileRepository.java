package com.wjcwleklinski.listservice.repository;

import com.wjcwleklinski.listservice.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
