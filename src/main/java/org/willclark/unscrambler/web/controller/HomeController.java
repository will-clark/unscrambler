package org.willclark.unscrambler.web.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage(ModelMap model, @RequestParam("name") Optional<String> name){
        model.put("name", name.orElse("World"));
        return "home";
    }

}