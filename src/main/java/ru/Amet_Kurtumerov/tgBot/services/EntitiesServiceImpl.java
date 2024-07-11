package ru.Amet_Kurtumerov.tgBot.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.Amet_Kurtumerov.tgBot.entity.ClientOrder;
import ru.Amet_Kurtumerov.tgBot.entity.Product;
import ru.Amet_Kurtumerov.tgBot.repository.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntitiesServiceImpl implements EntitiesService {

    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;

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
        return orderProductRepository.findProductsByClientId(id);
    }

    @Transactional
    @Override
    public List<Product> getTopPopularProducts(Integer limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Product> popularProducts = orderProductRepository.findMostPopularProducts(pageable);
        return popularProducts;
    }

    public EntitiesServiceImpl(ClientOrderRepository clientOrderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.productRepository = productRepository;
        this.clientOrderRepository = clientOrderRepository;
        this.orderProductRepository = orderProductRepository;
    }
}
