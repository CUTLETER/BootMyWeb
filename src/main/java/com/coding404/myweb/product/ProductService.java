package com.coding404.myweb.product;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

import java.util.ArrayList;

public interface ProductService {
    public int productInsert(ProductVO vo); //등록
    public ArrayList<ProductVO> getList(String userId, Criteria cri); //목록
    public int getTotal(String userId, Criteria cri); //전체게시글 수
    public ProductVO getDetail(int prodId); //상세내역
    public int productUpdate(ProductVO vo); //수정
    public void productDelete(int prodId); //삭제
}
