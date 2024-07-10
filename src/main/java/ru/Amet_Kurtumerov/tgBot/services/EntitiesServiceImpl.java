package ru.Amet_Kurtumerov.tgBot.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Amet_Kurtumerov.tgBot.entity.ClientOrder;
import ru.Amet_Kurtumerov.tgBot.entity.Product;
import ru.Amet_Kurtumerov.tgBot.repository.*;
import ru.Amet_Kurtumerov.tgBot.entity.OrderProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
       List<ClientOrder> clientOrders = clientOrderRepository.findByClientId(id);
        return clientOrders.stream()
                .flatMap(order -> orderProductRepository.findAllByClientOrderId(order.getId()).stream())
                .map(OrderProduct::getProduct)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<Product> getTopPopularProducts(Integer limit) {
        List<OrderProduct> allOrderProducts = orderProductRepository.findAll();
        Map<Product, Integer> productCounts = new HashMap<>();
        for (OrderProduct orderProduct : allOrderProducts) {
            Product product = orderProduct.getProduct();
            if (productCounts.containsKey(product)) {
                productCounts.put(product, productCounts.get(product) + 1);
            } else {
                productCounts.put(product, 1);
            }
        }
        List<Product> topProducts = new ArrayList<>();
        List<Map.Entry<Product, Integer>> entryList = new ArrayList<>(productCounts.entrySet());
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
        int count = 0;
        for (Map.Entry<Product, Integer> entry : entryList) {
            topProducts.add(entry.getKey());
            count++;
            if (count == limit) {
                break;
            }
        }
        return topProducts;
    }

    public EntitiesServiceImpl(ClientOrderRepository clientOrderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository){
        this.productRepository = productRepository;
        this.clientOrderRepository = clientOrderRepository;
        this.orderProductRepository = orderProductRepository;
    }
}
