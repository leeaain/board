package com.leeaain.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String index() {
        System.out.println();
        System.out.println();
        System.out.println("********************************************** ");
        System.out.println("**************** !!! 홈 페이지 !!!**************** ");
        System.out.println("********************************************** ");
        System.out.println();
        return "index";
    }
}
