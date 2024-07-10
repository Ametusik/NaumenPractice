package ru.Amet_Kurtumerov.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.Amet_Kurtumerov.tgBot.entity.Product;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCategoryId(Long categoryId);

    @Query("SELECT op.product " +
            "FROM OrderProduct op " +
            "GROUP BY op.product " +
            "ORDER BY COUNT(op) DESC")
    List<Product> findTopProductsOrderByOrderCountDesc(@Param("limit") Integer limit);

}