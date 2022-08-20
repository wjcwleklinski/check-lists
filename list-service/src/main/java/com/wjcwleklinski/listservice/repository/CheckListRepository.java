package com.wjcwleklinski.listservice.repository;

import com.wjcwleklinski.listservice.model.entity.CheckList;
import com.wjcwleklinski.listservice.model.projection.CheckListCollectionProjection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckListRepository extends CommonRepository<CheckList> {

    List<CheckListCollectionProjection> findAllShoppingListsBy();
}
