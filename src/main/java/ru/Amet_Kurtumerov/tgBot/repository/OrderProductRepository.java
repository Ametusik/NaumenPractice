package ru.Amet_Kurtumerov.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Amet_Kurtumerov.tgBot.entity.Client;
import ru.Amet_Kurtumerov.tgBot.entity.OrderProduct;
import ru.Amet_Kurtumerov.tgBot.entity.Product;

import java.util.Collection;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "orderProducts", path = "orderProducts")
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {


    List<OrderProduct> findAllByClientOrderId(Long id);
}
