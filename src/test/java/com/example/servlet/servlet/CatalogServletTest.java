package com.example.servlet.servlet;

import com.example.servlet.ServletTest;
import com.example.servlet.data.ProductsArgumentsProvider;
import com.example.servlet.entity.ProductEntity;
import com.example.servlet.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class CatalogServletTest extends ServletTest {

    @InjectMocks
    private CatalogServlet catalogServlet;

    @Mock
    private ProductRepository productRepository;

    @ParameterizedTest
    @ArgumentsSource(ProductsArgumentsProvider.class)
    void testDoGet(List<ProductEntity> products) throws Exception {
        when(productRepository.getProducts()).thenReturn(products);
        catalogServlet.doGet(request, response);

        verify(productRepository).getProducts();
        verify(request).setAttribute("products", products);
        verify(request).getRequestDispatcher("WEB-INF/catalog.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGetNoProductsReturned() throws Exception {
        when(productRepository.getProducts()).thenReturn(Collections.emptyList());
        catalogServlet.doGet(request, response);

        verify(productRepository).getProducts();
        verify(request, times(0)).setAttribute(anyString(), anyList());
        verify(request).getRequestDispatcher("WEB-INF/catalog.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGetExceptionThrown() throws Exception {
        when(productRepository.getProducts()).thenThrow(new IllegalArgumentException("some message"));

        final IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> catalogServlet.doGet(request, response));
        assertNotNull(exception);
        assertEquals("some message", exception.getMessage());

        verify(productRepository).getProducts();
        verifyNoInteractions(request);
        verifyNoInteractions(requestDispatcher);
    }
}