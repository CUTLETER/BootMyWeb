package com.coding404.myweb.controller;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService") //이름으로연결
    private ProductService productService;


/*  //목록 1
    @GetMapping("/productList")
    public String productList(Model model, Criteria cri) {

        //현재 로그인되어 있는 사람 아이디가 admin이라고 가정하고
        String userId = "admin";

        ArrayList<ProductVO> list = productService.getList(userId, cri);
        model.addAttribute("list", list);

        //페이지VO
        int total = productService.getTotal(userId); //전체게시글 수
        PageVO pageVO = new PageVO(cri, total ); //페이지네이션
        model.addAttribute("pageVO", pageVO);

        return "product/productList";
    }*/

    // 목록 2
    // criteria 같은 객체에 검색 키워드를 추가하고
    // 목록 sql, 전체 게시글 sql 둘 다 동적 쿼리로 변경하고
    // 화면에서 사용자가 검색 버튼을 누를 때 다시 page 번호를 1번으로 돌리고 amount는 유지시키기
    // 사용자가 입력한 검색 값의 유지 (Criteria 안에 들어 있음)
    // 페이지네이션을 누를 때 검색 키워드를 같이 넘겨주기
    // 100개씩 보기 버튼 추가
    @GetMapping("/productList")
    public String productList(Model model, Criteria cri) {

        //현재 로그인되어 있는 사람 아이디가 admin이라고 가정하고
        String userId = "admin";

        ArrayList<ProductVO> list = productService.getList(userId, cri);
        model.addAttribute("list", list);

        //페이지VO
        int total = productService.getTotal(userId, cri); //전체게시글 수
        PageVO pageVO = new PageVO(cri, total ); //페이지네이션
        model.addAttribute("pageVO", pageVO);

        return "product/productList";
    }

    //등록
    @GetMapping("/productReg")
    public String productReg() {
        return "product/productReg";
    }

    //상세
    @GetMapping("/productDetail")
    public String productDetail(@RequestParam("prodId") int prodId,
                                Model model) {

        ProductVO vo = productService.getDetail(prodId);
        model.addAttribute("vo", vo);

        return "product/productDetail";
    }

    //등록요청
    @PostMapping("/registForm")
    public String registForm(ProductVO vo,
                             RedirectAttributes ra ) {

        //서버측에서 유효성 검사 진행가능

        int result = productService.productInsert(vo);

        if(result == 1) {
            ra.addFlashAttribute("msg", "정상 등록되었습니다");
        } else {
            ra.addFlashAttribute("msg", "등록에 실패했습니다. 1577-1577 문의해 주세요.");
        }


        return "redirect:/product/productList"; //다시 목록을 태워서 나감(데이터를 들고)
    }

    //상품 수정기능 - 반드시 필요한값은 PK
    @PostMapping("/productUpdate")
    public String productUpdate(ProductVO vo,
                                RedirectAttributes ra) {

        //업데이트
        int result = productService.productUpdate(vo);
        if(result == 1) {
            ra.addFlashAttribute("msg", "수정 되었습니다");
        } else {
            ra.addFlashAttribute("msg", "수정에 실패했습니다");
        }


        return "redirect:/product/productDetail?prodId=" + vo.getProdId(); //상세화면은 id값을 필요로 함
    }

    //상품 삭제기능 -
    @PostMapping("/productDelete")
    public String productDelete(@RequestParam("prodId") int prodId) {

        productService.productDelete(prodId);

        return "redirect:/product/productList";
    }


}
