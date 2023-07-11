package sg.edu.nus.iss.paf27_boardgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class LandingPageController {
    
    @GetMapping
    public String getLandingPage() {
        return "redirect:/api/form";
    }
}
