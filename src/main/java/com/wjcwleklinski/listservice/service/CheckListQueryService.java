package com.wjcwleklinski.listservice.service;

import com.wjcwleklinski.listservice.model.view.CheckListDetailsView;
import com.wjcwleklinski.listservice.repository.CheckListRepository;
import com.wjcwleklinski.listservice.model.entity.CheckList;
import com.wjcwleklinski.listservice.model.projection.CheckListCollectionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckListQueryService {

    private final CheckListRepository checkListRepository;

    private final CommonService commonService;

    public List<CheckListCollectionProjection> getCheckLists() {
        return checkListRepository.findAllShoppingListsBy();
    }

    public CheckListDetailsView getCheckListDetails(String listCode) {
        CheckList checkList = (CheckList) commonService.getByCode(listCode, checkListRepository);
        return CheckListDetailsView.getInstance(checkList);
    }

    public void deleteCheckList(String listCode) {
        commonService.deleteByCode(listCode, checkListRepository);
    }
}
