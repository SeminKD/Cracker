package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WelcomeController {

    @GetMapping("")
    public String home(){
        return "start";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "user") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
