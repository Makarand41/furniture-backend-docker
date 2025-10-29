package com.nanotech.furniture.controller;

import com.nanotech.furniture.entity.Category;
import com.nanotech.furniture.entity.Furniture;
import com.nanotech.furniture.service.FurnitureService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/furniture")
//@CrossOrigin(origins = "http://localhost:5173")
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public Furniture addFurniture(
            @RequestParam("name") String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "images", required = false) MultipartFile[] images
    ) throws Exception {
        Furniture furniture = new Furniture();
        furniture.setName(name);
        furniture.setDescription(description);
        furniture.setQuantity(quantity != null ? quantity : 0);
        furniture.setPrice(price != null ? price : 0.0);
        furniture.setStatus(status);

        Category category = new Category();
        category.setId(categoryId);
        furniture.setCategory(category);

        return furnitureService.addFurniture(furniture, image, images);
    }
    
    @GetMapping("/all")
    public List<Furniture> getAllFurnitures() {
        return furnitureService.getAllFurnitures();
    }
    
    @GetMapping("/{id}")
    public Furniture getFurnitureById(@PathVariable("id") Long id) {
        return furnitureService.getFurnitureById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteFurniture(@PathVariable("id") Long id) {
        furnitureService.deleteFurnitureById(id);
        return "Furniture deleted successfully with ID: " + id;
    }
    
    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public Furniture updateFurniture(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "images", required = false) MultipartFile[] images
    ) throws Exception {
        return furnitureService.updateFurniture(id, name, description, quantity, price, status, categoryId, image, images);
    }





}
