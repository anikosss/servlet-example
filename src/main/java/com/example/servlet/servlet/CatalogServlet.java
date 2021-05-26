package com.example.servlet.servlet;

import com.example.servlet.entity.ProductEntity;
import com.example.servlet.repository.ProductRepository;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "catalog", urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    private static final Logger ROOT_LOGGER = LogManager.getLogger();

    private ProductRepository productRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productRepository = ProductRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final List<ProductEntity> products = productRepository.getProducts();
        if (!products.isEmpty()) {
            request.setAttribute("products", products);
        }
        ROOT_LOGGER.debug("Products count: {}, {}", products.size(), "bla");
        ROOT_LOGGER.info("Products retrieved successfully");
        request.getRequestDispatcher("WEB-INF/catalog.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
