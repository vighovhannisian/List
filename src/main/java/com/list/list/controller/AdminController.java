package com.list.list.controller;

import com.list.list.model.Advertisement;
import com.list.list.model.Category;
import com.list.list.repository.AdvertisementRepository;
import com.list.list.repository.CategoryRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AdminController {
    @Value("D\\mvc\\")
    private String imagePath;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;

@RequestMapping(value = "/adminHome")
public String adminController(ModelMap map){
    map.addAttribute("addCategory",new Category());
    map.addAttribute("addAdvertisement",new Advertisement());
    map.addAttribute("allCategories",categoryRepository.findAll());

    return "admin";
}
    @GetMapping(value = "/addCategory")
    public String addCategory(@ModelAttribute("Category")Category category){
                categoryRepository.save(category);
        return "redirect:/adminHome";
    }
    @PostMapping(value = "/addAdvertisement")
    public String addAdvertisement(@ModelAttribute("Advertisement") Advertisement advertisement, @RequestParam("pic")MultipartFile file) throws IOException {
        File file1=new File(imagePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        String picName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
        File picture = new File("D:\\listpictures\\" + picName);
        file.transferTo(picture);
        advertisement.setPicture(picName);
     advertisementRepository.save(advertisement);
return "redirect:/adminHome";
}
    @GetMapping(value = "/advertisement/image")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
        InputStream in = new FileInputStream("D:\\listpictures\\" + fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
