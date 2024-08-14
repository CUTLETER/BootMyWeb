package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersVO {
     private String id;
     private String pw;
     private String name;

     // 1대N
     // N 테이블의 데이터를 list로 담아둠
    private ArrayList<MemoVO> memoList;
}
