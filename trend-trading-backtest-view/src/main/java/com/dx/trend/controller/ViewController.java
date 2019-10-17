package com.dx.trend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dx
 */
@Controller
public class ViewController {
    @GetMapping("/")
    public String view() throws Exception {
        return "view";
    }
}
