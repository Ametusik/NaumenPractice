package ru.Amet_Kurtumerov.tgBot.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Amet_Kurtumerov.tgBot.entity.ClientOrder;
import ru.Amet_Kurtumerov.tgBot.entity.Product;
import ru.Amet_Kurtumerov.tgBot.repository.*;
import ru.Amet_Kurtumerov.tgBot.entity.OrderProduct;

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
    public Set<Product> getClientProducts(Long id) {
       List<ClientOrder> clientOrders = clientOrderRepository.findByClientId(id);
        return clientOrders.stream()
                .flatMap(order -> orderProductRepository.findAllByClientOrderId(order.getId()).stream())
                .map(OrderProduct::getProduct)
                .collect(Collectors.toSet());
    }

    @Transactional
    @Override
    public List<Product> getTopPopularProducts(Integer limit) {
        Map<Long, Integer> productCounts = orderProductRepository.findAll().stream()
                .collect(Collectors.groupingBy(op -> op.getProduct().getId(),
                        Collectors.summingInt(OrderProduct::getCountProduct)));

        List<Product> topProducts = productCounts.entrySet().stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue(Comparator.reverseOrder()))
                .limit(limit)
                .map(entry -> productRepository.findById(entry.getKey())
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return topProducts;
    }

    public EntitiesServiceImpl(ClientOrderRepository clientOrderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository){
        this.productRepository = productRepository;
        this.clientOrderRepository = clientOrderRepository;
        this.orderProductRepository = orderProductRepository;
    }
}
