package ru.Amet_Kurtumerov.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Amet_Kurtumerov.tgBot.entity.OrderProduct;

@RepositoryRestResource(collectionResourceRel =
        "orderProducts", path = "orderProducts")
public interface OrderProductRepository extends JpaRepository<Long, OrderProduct> {
}
