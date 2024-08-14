package com.coding404.myweb;

import com.coding404.myweb.command.MemoVO;
import com.coding404.myweb.command.UsersVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TestMapper {
    // EX_MEMO 테이블 N
    // EX_USERS 테이블 1

    // N대1 관계 조인 - N 테이블에 1 테이블을 조인시키면 됨
    public ArrayList<MemoVO> joinOne();

    // 1대N 관계 조인 - user 데이터는 하나만 필요함
    public UsersVO joinTwo();

}
