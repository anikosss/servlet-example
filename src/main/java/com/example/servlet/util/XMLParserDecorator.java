package com.example.servlet.util;

public class XMLParserDecorator {

    private XMLParser xmlParser;

    public XMLParserDecorator(XMLParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    public String parse(String xml) {
        System.out.println("Before parsing");
        final String result = xmlParser.parse(xml);
        System.out.println("After parsing");
        return result;
    }
}
