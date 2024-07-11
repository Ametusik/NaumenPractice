package ru.Amet_Kurtumerov.tgBot.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Amet_Kurtumerov.tgBot.entity.OrderProduct;
import ru.Amet_Kurtumerov.tgBot.entity.Product;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orderProducts", path = "orderProducts")
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    @Query("SELECT op.product " +
            "FROM OrderProduct op " +
            "JOIN op.clientOrder co " +
            "WHERE co.client.id = :clientId")
    List<Product> findProductsByClientId(@Param("clientId") Long clientId);

    @Query("SELECT op.product " +
            "FROM OrderProduct op " +
            "GROUP BY op.product " +
            "ORDER BY SUM(op.countProduct) DESC")
    List<Product> findMostPopularProducts(Pageable pageable);
}
