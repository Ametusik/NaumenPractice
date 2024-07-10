package ru.Amet_Kurtumerov.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.Amet_Kurtumerov.tgBot.entity.ClientOrder;
import ru.Amet_Kurtumerov.tgBot.entity.Product;


import java.util.List;


@RepositoryRestResource(collectionResourceRel = "clientOrders", path = "clientOrders")
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

    List<ClientOrder> findByClientId(Long Id);

}