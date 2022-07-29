package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.Product;
import com.hugo.businesssystem.entities.dto.ProductDTO;
import com.hugo.businesssystem.repositories.ProductRepository;
import com.hugo.businesssystem.services.exceptions.DatabaseException;
import com.hugo.businesssystem.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){

        Optional<Product> product = repository.findById(id);

        return product.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Product insert(ProductDTO productDTO){
        Product product = toEntity(productDTO);

        return repository.save(product);
    }

    public void delete(Long id){

        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Product update(Long id, ProductDTO productDTO){
        Product product =
                repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        updateData(product, productDTO);
        return repository.save(product);
    }

    private void updateData(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
    }

    private Product toEntity(ProductDTO productDTO){
        return new Product(null, productDTO.getName(), productDTO.getPrice());
    }
}
