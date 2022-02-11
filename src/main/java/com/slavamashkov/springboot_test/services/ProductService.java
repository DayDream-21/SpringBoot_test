package com.slavamashkov.springboot_test.services;

import com.slavamashkov.springboot_test.entities.Product;
import com.slavamashkov.springboot_test.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsWithFilter(String word) {
        List<Product> fullList = productRepository.findAll();


        if (word == null) {
            return fullList;
        } else {
            return fullList.stream()
                    .filter(product -> product.getTitle().contains(word))
                    .collect(Collectors.toList());
        }
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }
}
