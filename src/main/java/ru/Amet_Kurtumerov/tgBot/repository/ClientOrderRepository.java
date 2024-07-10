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

    public List<ClientOrder> findByClientId(Long Id);

    @Query("select Product from OrderProduct op " +
            "join ClientOrder co on op.clientOrder.id = co.id " +
            "where co.client.id=:id")
    public List<Product> findAllProductsByClientId(Long id);
}