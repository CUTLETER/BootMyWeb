package com.coding404.myweb.controller;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @Value("${project.upload.path}")
    String uploadPath;// 파일 업로드 경로

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    // 1단 카테고리
    @GetMapping("/getCategory")
    public ResponseEntity<List<CategoryVO>> getCategory() {
        ArrayList<CategoryVO> list = productService.getCategory();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 2단, 3단 카테고리
    @GetMapping("/getCategoryChild/{groupId}/{categoryLv}/{categoryDetailLv}")
    public ResponseEntity<List<CategoryVO>> getCategoryChild(@PathVariable("groupId") String groupId,
                                                             @PathVariable("categoryLv") int categoryLv,
                                                             @PathVariable("categoryDetailLv") int categoryDetailLv) {

        CategoryVO vo = CategoryVO.builder().groupId(groupId).
                categoryLv(categoryLv).categoryDetailLv(categoryDetailLv).build();

        ArrayList<CategoryVO> list = productService.getCategoryChild(vo);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 이미지 데이터 응답에 대한 요청
//    @GetMapping("/display/{filePath}/{uuid}/{fileName}")
//    public byte[] display(@PathVariable("filePath") String filePath,
//                          @PathVariable("uuid") String uuid,
//                          @PathVariable("fileName") String fileName) {
//        System.out.println(filePath);
//        System.out.println(uuid);
//        System.out.println(fileName);
//
//        // 하드 디스크에 있는 파일 경로
//        byte[] result = null;
//        String path = uploadPath + "/" + filePath + "/" + uuid + "_" + fileName; // 파일의 실제 경로
//        File file = new File(path);
//
//        try {
//            result = FileCopyUtils.copyToByteArray(file); // 파일 데이터의 byte 배열 값을 구해서 반환시킴
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return result; // 요청이 들어온 곳으로 응답함
//    }

    //ResponEntity를 이용해서 이미지 응답 문서를 작성하기 (위랑 똑같지만 좀 더 간결하게)
    @GetMapping("/display/{filePath}/{uuid}/{fileName}")
    public ResponseEntity<byte[]> display(@PathVariable("filePath") String filePath,
                          @PathVariable("uuid") String uuid,
                          @PathVariable("fileName") String fileName) {
        System.out.println(filePath);
        System.out.println(uuid);
        System.out.println(fileName);

        // 하드 디스크에 있는 파일 경로
        ResponseEntity<byte[]> result = null;
        String path = uploadPath + "/" + filePath + "/" + uuid + "_" + fileName; // 파일의 실제 경로
        File file = new File(path);

        try {
            byte[] arr = FileCopyUtils.copyToByteArray(file); // 파일 데이터의 byte 배열 값을 구해서 반환시킴
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", Files.probeContentType(file.toPath())); // 해당 경로에 있는 파일의 MIME 타입을 구해옴

            result = new ResponseEntity<>(arr, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result; // 요청이 들어온 곳으로 응답함
    }

}
