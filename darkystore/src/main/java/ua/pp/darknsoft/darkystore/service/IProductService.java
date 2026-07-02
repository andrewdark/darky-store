package ua.pp.darknsoft.darkystore.service;

import ua.pp.darknsoft.darkystore.dto.ProductRecord;

import java.util.List;

public interface IProductService {
    List<ProductRecord> getProducts();
}
