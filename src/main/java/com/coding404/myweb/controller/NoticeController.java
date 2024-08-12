package com.coding404.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/notice")
public class NoticeController {

    @GetMapping("/noticeDetail")
    public String noticeDetail() {
        return "/notice/noticeDetail";
    }

    @GetMapping("/noticeList")
    public String noticeList() {
        return "/notice/noticeList";
    }

    @GetMapping("/noticeReg")
    public String noticeReg() {
        return "/notice/noticeReg";
    }
}
