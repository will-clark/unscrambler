package org.willclark.unscrambler.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.willclark.unscrambler.jpa.repository.WordRepository;

@Controller
public class HomeController {

    @Autowired
    private WordRepository wordRepository;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showHomePage(ModelMap model, @RequestParam("name") Optional<String> name){
        model.put("name", name.orElse("World"));
        model.put("words", wordRepository.findAll());
        return "home";
    }

}