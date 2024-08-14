package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryVO {
//    CATEGORY_ID INT PRIMARY KEY AUTO_INCREMENT, ##카테고리 PK
//    GROUP_ID VARCHAR(10),			##카테고리 대분류 그룹
//    CATEGORY_LV INT, 				  ##1,2,3단
//    CATEGORY_NM VARCHAR(100), 		##대분류 중분류 소분류
//    CATEGORY_DETAIL_LV INT, 			##분류별 순서
//    CATEGORY_DETAIL_NM VARCHAR(100), 		##이름
//    CATEGORY_PARENT_LV INT , 			  ##1,2,3에 대한 부모컬럼
//    CATEGORY_DETAIL_PARENT_LV INT		##분류별 순서에 대한 부모컬럼
    private int categoryId;
    private String groupId;
    private int categoryLv;
    private String categoryNm;
    private int categoryDetailLv;
    private String categoryDetailNm;
    private int categoryParentLv;
    private int categoryDetailParentLv;
}
