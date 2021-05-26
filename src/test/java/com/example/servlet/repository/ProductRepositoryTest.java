package com.example.servlet.repository;

import com.example.servlet.util.XMLParser;
import com.example.servlet.util.XMLParserDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class ProductRepositoryTest {

    private XMLParser xmlParser;
    private XMLParserDecorator proxy;

    @BeforeEach
    void init() {
        xmlParser = new XMLParser();
        proxy = new XMLParserDecorator(xmlParser);
    }

    @Test
    public void testClone() throws Exception {
        final ProductRepository instance1 = ProductRepository.getInstance();
        final ProductRepository instance2 = ProductRepository.getInstance();
        final ProductRepository instance3 = ProductRepository.getInstance();
        final ProductRepository instance4 = ProductRepository.getInstance();
        final ProductRepository instance5 = ProductRepository.getInstance();
        assertSame(instance1, instance2);
        assertSame(instance1, instance3);
        assertSame(instance1, instance4);
        assertSame(instance1, instance5);
    }

    @Test
    void testProxy() {
        proxy.parse("asd");
    }
}