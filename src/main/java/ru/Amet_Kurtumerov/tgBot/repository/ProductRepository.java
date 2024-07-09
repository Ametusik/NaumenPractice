package ru.Amet_Kurtumerov.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Amet_Kurtumerov.tgBot.entity.Product;

import java.util.List;


@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCategoryId(Long categoryId);

    @Query("select OrderProduct.product, sum(OrderProduct .countProduct) as sum from OrderProduct where sum>= :limit" +
            " GROUP BY product ")
    List<Product> findTopProducts(Integer limit);
}