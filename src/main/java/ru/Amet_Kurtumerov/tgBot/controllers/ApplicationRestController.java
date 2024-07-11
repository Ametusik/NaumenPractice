package ru.Amet_Kurtumerov.tgBot.controllers;

import org.springframework.web.bind.annotation.*;
import ru.Amet_Kurtumerov.tgBot.entity.ClientOrder;
import ru.Amet_Kurtumerov.tgBot.entity.Product;
import ru.Amet_Kurtumerov.tgBot.services.EntitiesServiceImpl;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest")
public class ApplicationRestController {
    private final EntitiesServiceImpl entitiesService;

    public ApplicationRestController(EntitiesServiceImpl entitiesService) {
        this.entitiesService = entitiesService;
    }


    @GetMapping("/products/search")
    public List<Product> getProductsByCategoryId(@RequestParam Long categoryId) {
        return entitiesService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/clients/{id}/orders")
    public List<ClientOrder> getClientOrders(@PathVariable Long id) {
        return entitiesService.getClientOrders(id);
    }

    @GetMapping("/clients/{id}/products")
    public List<Product> getClientProducts(@PathVariable Long id){
        return entitiesService.getClientProducts(id);
    }

    @GetMapping("/products/popular")
    public List<Product> getTopPopularProducts(@RequestParam Integer limit) {
        return entitiesService.getTopPopularProducts(limit);
    }

}
