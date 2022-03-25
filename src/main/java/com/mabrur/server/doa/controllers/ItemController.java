package com.mabrur.server.doa.controllers;

import java.util.Optional;
import java.util.Locale.Category;

import javax.validation.Valid;

import com.google.gson.Gson;
import com.mabrur.server.doa.dto.ItemFormData;
import com.mabrur.server.doa.entities.Item;
import com.mabrur.server.doa.repos.CategoryRepo;
import com.mabrur.server.doa.repos.ItemRepo;
import com.mabrur.server.doa.services.CategoryService;
import com.mabrur.server.doa.services.ItemService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doa/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("items", itemService.findAll());
        return "doa/item/index";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("itemFormData", new ItemFormData());
        model.addAttribute("categories", categoryService.findAll());
        return "doa/item/add";
    }

    @PostMapping("/add")
    public String create(@Valid ItemFormData itemFormData, BindingResult result, RedirectAttributes redirAttrs,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("itemFormData", (ItemFormData) result.getTarget());
            model.addAttribute("categories", categoryService.findAll());
            return "doa/item/add";
        }

        Item item = modelMapper.map(itemFormData, Item.class);
        Item newItem = itemService.save(item);
        if (newItem != null) {

            redirAttrs.addFlashAttribute("message", item.getTitle() + " Saved!");
            return "redirect:/doa/item";
        }

        redirAttrs.addFlashAttribute("message", item.getTitle() + " failed to Saved!");
        return "redirect:/doa/item";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {

        Optional<Item> item = itemService.findOne(id);
        if (item.isPresent()) {
            ItemFormData itemFormData = modelMapper.map(item.get(), ItemFormData.class);
            model.addAttribute("itemFormData", itemFormData);
            model.addAttribute("categories", categoryService.findAll());
            return "doa/item/edit";
        }
        return "redirect:/doa/item";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @Valid ItemFormData itemFormData, BindingResult result,
            RedirectAttributes redirAttrs, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("itemFormData", (ItemFormData) result.getTarget());
            model.addAttribute("categories", categoryService.findAll());
            return "doa/item/edit";
        }
        Item item = modelMapper.map(itemFormData, Item.class);
        Item newItem = itemService.save(item);
        if (newItem != null) {

            redirAttrs.addFlashAttribute("message", item.getTitle() + " Updated!");
            return "redirect:/doa/item";
        }

        redirAttrs.addFlashAttribute("message", item.getTitle() + " failed to Updated!");
        return "redirect:/doa/item";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirAttrs) {
        itemService.deleteById(id);

        redirAttrs.addFlashAttribute("message", "Succesfull delete the record!");
        return "redirect:/doa/item";
    }

}
