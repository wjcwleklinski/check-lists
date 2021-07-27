package com.wjcwleklinski.listservice.repository;

import com.wjcwleklinski.listservice.model.Entry;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EntryRepository extends CommonRepository<Entry> {

    List<Entry> findProductsByCheckListCode(String listCode);
}
