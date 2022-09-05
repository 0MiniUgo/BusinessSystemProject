package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.Product;
import com.hugo.businesssystem.entities.dto.ProductDTO;
import com.hugo.businesssystem.repositories.ProductRepository;
import com.hugo.businesssystem.services.exceptions.ResourceNotFoundException;
import com.hugo.businesssystem.util.product.ProductCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepositoryMock;

    @BeforeEach
    void setUp(){
        List<Product> products = List.of(ProductCreator.createValidProduct());
        BDDMockito.when(productRepositoryMock.findAll())
                .thenReturn(products);

        BDDMockito.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProductCreator.createValidProduct()));

        BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any(Product.class)))
                .thenReturn(ProductCreator.createValidProduct());

    }

    @Test
    void findAll_ReturnsListOfProducts_WhenSuccessful(){

        String expectedName = ProductCreator.createValidProduct().getName();
        List<Product> products = productService.findAll();

        Assertions.assertThat(products)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(products.get(0).getName()).isEqualTo(expectedName);
    }
    @Test
    void findById_ReturnsProduct_WhenSuccessful(){
        Long expectedId = ProductCreator.createValidProduct().getId();
        Product product = productService.findById(1L);

        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getId()).isNotNull().isEqualTo(expectedId);
    }
    @Test
    void findById_ThrowsResourceNotFoundException_WhenProductNotFound(){

        BDDMockito.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> productService.findById(1L));
    }
    @Test
    void insert_ReturnsProduct_WhenSuccessful(){
        Product product = productService.insert(ProductCreator.createValidProductDTO());

        Assertions.assertThat(product)
                .isNotNull()
                .isEqualTo(ProductCreator.createValidProduct());
    }
    @Test
    void update_UpdatesProduct_WhenSuccessful(){

        Long productId = ProductCreator.createValidProduct().getId();
        ProductDTO productDTO = ProductCreator.createValidProductDTO();

        Assertions.assertThat( productService.update(productId, productDTO))
                .isNotNull()
                .isEqualTo(ProductCreator.createValidProduct());
    }
    @Test
    void delete_RemovesProduct_WhenSuccessful(){

        final Long id = 1L;
        productService.delete(id);
        BDDMockito.verify(productRepositoryMock).deleteById(id);
    }
}