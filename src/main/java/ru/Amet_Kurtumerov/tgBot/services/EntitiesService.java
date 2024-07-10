package ru.Amet_Kurtumerov.tgBot.services;
import org.springframework.stereotype.Service;
import ru.Amet_Kurtumerov.tgBot.entity.ClientOrder;
import ru.Amet_Kurtumerov.tgBot.entity.Product;

import java.util.List;
import java.util.Set;

@Service
public interface EntitiesService {
    List<Product> getProductsByCategoryId(Long id);

    List<ClientOrder> getClientOrders(Long id);

    Set<Product> getClientProducts(Long id);

    List<Product> getTopPopularProducts(Integer limit);
}
