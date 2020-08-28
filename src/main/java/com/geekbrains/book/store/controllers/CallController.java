package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.ServiceCall;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
@AllArgsConstructor
public class CallController {

    private final ServiceCall serviceCall;

    @GetMapping
    public String showAllHistory(Model model){
        model.addAttribute("callMethod", serviceCall.getAllHistory());
        return "history";
    }
}
