package ru.Amet_Kurtumerov.tgBot.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.Amet_Kurtumerov.tgBot.entity.Category;
import ru.Amet_Kurtumerov.tgBot.entity.Client;
import ru.Amet_Kurtumerov.tgBot.entity.ClientOrder;
import ru.Amet_Kurtumerov.tgBot.entity.Product;
import ru.Amet_Kurtumerov.tgBot.repository.*;

import java.util.List;

@Service
@Transactional
public class EntitiesServiceImpl implements EntitiesService {

    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findProductByCategoryId(id);
    }

    @Transactional
    @Override
    public List<ClientOrder> getClientOrders(Long id) {
        return clientOrderRepository.findByClientId(id);
    }

    @Transactional
    @Override
    public List<Product> getClientProducts(Long id) {
        return clientOrderRepository.findAllProductsByClientId(id);
    }

    @Transactional
    @Override
    public List<Product> getTopPopularProducts(Integer limit) {
        return productRepository.findTopProductsOrderByOrderCountDesc(limit);
    }
}
