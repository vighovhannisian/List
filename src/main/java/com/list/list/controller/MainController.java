package com.list.list.controller;

import com.list.list.model.Advertisement;
import com.list.list.model.Category;
import com.list.list.repository.AdvertisementRepository;
import com.list.list.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;


    @GetMapping(value = "/main")
    public String mainPage(ModelMap map) {
        map.addAttribute("allAdvertisements", advertisementRepository.findAll());
        map.addAttribute("allCategories",categoryRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/find/{id}")
    public String findAdvertisementByCategory(@PathVariable("id") int id, ModelMap map) {
        List<Advertisement> advertisements = advertisementRepository.findAllByCategoryId(id);
        map.addAttribute("advert", advertisements);
        return "result";
    }
}
