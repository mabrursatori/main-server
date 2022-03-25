package com.mabrur.server.doa.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.google.gson.Gson;
import com.mabrur.server.doa.dto.CategoryFormData;
import com.mabrur.server.doa.dto.CategoryFormEditData;
import com.mabrur.server.doa.entities.Category;
import com.mabrur.server.doa.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doa/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "doa/category/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("categoryFormData", new CategoryFormData());
        return "doa/category/add";
    }

    @PostMapping("/add")
    public String create(@Valid CategoryFormData categoryFormData, Errors result,
            RedirectAttributes redirAttrs, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categoryFormData", (CategoryFormData) result.getAllErrors());
            return "doa/category/add";
        }

        Category category = categoryService.save(categoryFormData.getImage(), categoryFormData.getName());
        if (category != null) {
            redirAttrs.addFlashAttribute("message", categoryFormData.getName() + " Saved!");
            redirAttrs.addFlashAttribute("status", "success");
            return "redirect:/doa/category";
        }

        redirAttrs.addFlashAttribute("message", categoryFormData.getName() + "not Saved!");
        redirAttrs.addFlashAttribute("status", "danger");
        return "redirect:/doa/category";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {

        Optional<Category> category = categoryService.findOne(id);
        if (category.isPresent()) {
            CategoryFormEditData categoryFormEditData = new CategoryFormEditData();
            categoryFormEditData.setName(category.get().getName());
            categoryFormEditData.setId(category.get().getId());
            model.addAttribute("categoryFormEditData", categoryFormEditData);
            return "doa/category/edit";
        }
        return "doa/category/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @Valid CategoryFormEditData categoryFormEditData,
            BindingResult result,
            RedirectAttributes redirAttrs,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categoryFormEditData", (CategoryFormEditData) result.getTarget());
            return "doa/category/edit";
        }

        Category category = categoryService.save(categoryFormEditData.getImage(), categoryFormEditData.getName(), id);
        if (category != null) {
            redirAttrs.addFlashAttribute("message", categoryFormEditData.getName() + " Updated!");
            redirAttrs.addFlashAttribute("status", "success");
            return "redirect:/doa/category";
        }

        redirAttrs.addFlashAttribute("message", categoryFormEditData.getName() + "not Updated!");
        redirAttrs.addFlashAttribute("status", "danger");
        return "redirect:/doa/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirAttrs) {
        categoryService.deleteById(id);

        redirAttrs.addFlashAttribute("message", "Succesfull delete the record!");
        return "redirect:/doa/category";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleValidationName(
            DataIntegrityViolationException e, RedirectAttributes redirAttrs) {

        redirAttrs.addFlashAttribute("message", e.getMessage());
        redirAttrs.addFlashAttribute("status", "danger");
        return "redirect:/doa/category";

    }

}
