package ua.pp.darknsoft.darkystore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.darkystore.dto.ProductRecord;
import ua.pp.darknsoft.darkystore.model.Product;
import ua.pp.darknsoft.darkystore.repository.ProductRepository;
import ua.pp.darknsoft.darkystore.service.IProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductRecord> getProducts() {
        productRepository.findById(1L).ifPresent(System.out::println);
        return productRepository.findAll(Sort.by("productId")).stream().map(Product::toRecord).toList();
    }
}
