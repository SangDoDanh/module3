package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.impl.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private static IProductService productService = new ProductService();
    private static final int AMOUNT_ELEMENT_IN_PAGE = 20;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        List<Product> products = productService.findAll();
        String indexPageString = request.getParameter("indexPage");
        int indexPage = 1;
        if (indexPageString != null) {
            indexPage = Integer.parseInt(indexPageString);
        }
            listProduct(indexPage, AMOUNT_ELEMENT_IN_PAGE, request, response, products);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response, List<Product> products) throws ServletException, IOException {
        request.setAttribute("products", products);
        request.getRequestDispatcher("/product/list.jsp").forward(request, response);
    }

    private void listProduct(int indexPage, int amount, HttpServletRequest request, HttpServletResponse response, List<Product> products) throws ServletException, IOException {
        int start;
        int end;
        double amountPage = 1;
        if (products.size() < amount) {
            amount = products.size();
        } else {
            amountPage = Math.ceil((double) products.size() / amount);
        }

        start = (indexPage - 1) * amount;
        end = indexPage * amount;

        if (end > products.size() || start < 0) {
            start = 0;
            end = amount;
            indexPage = 1;
        }
        List<Product> productPage = products.subList(start, end);
        request.setAttribute("amountPage", (int) amountPage);
        request.setAttribute("indexPage", indexPage);
        request.setAttribute("products", productPage);
        request.getRequestDispatcher("/product/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String regexNumber = "^^[\\d]+\\.{0,1}[\\d]*$";
        String action = request.getParameter("action");
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String producer = request.getParameter("productProducer");
        String priceString = request.getParameter("productPrice");
        String indexPageString = request.getParameter("indexPage");
        int indexPage = 1;
        if (indexPageString != null) {
            indexPage = Integer.parseInt(indexPageString);
        }
        int id;
        double price = 0;
        if (priceString != null && priceString.matches(regexNumber)) {
            price = Double.parseDouble(priceString);
        }

        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                List<Product> products = productService.findAll();
                id = 1 + products.get(products.size() - 1).getId();
                createProduct(request, response, name, description, producer, price, id);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("productId"));
                deleteProduct(request, response, id);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("productId"));
                updateProduct(request, response, name, id, description, price, producer);
                break;
            case "search":
                String search = request.getParameter("search");

                searchProduct(indexPage,request, response, search);
                break;
            default:
                response.sendRedirect("/product");
        }
    }

    private void searchProduct(int indexPage, HttpServletRequest request, HttpServletResponse response, String search) throws ServletException, IOException {
        search = search.toLowerCase();
        List<Product> products = productService.findAll();
        List<Product> productsBySearch = new ArrayList<>();
        if (search.equals("")) {
            listProduct(indexPage, AMOUNT_ELEMENT_IN_PAGE, request, response, products);
        } else {
            for (Product product : products) {
                if (product.getName().toLowerCase().contains(search)) {
                    productsBySearch.add(product);
                }
            }
        }
        if (productsBySearch.size() > 0) {
            listProduct(request, response, productsBySearch);
        } else {
            listProduct(request, response, null);
        }
    }


    private void updateProduct(HttpServletRequest request, HttpServletResponse response, String name, int idEdit, String description, double price, String producer) throws ServletException, IOException {
        productService.update(idEdit, new Product(idEdit, name, description, producer, price));
        List<Product> products = productService.findAll();
        listProduct(request, response, products);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response, int idDelete) throws ServletException, IOException {
        productService.remove(idDelete);
        List<Product> products = productService.findAll();
        listProduct(request, response, products);

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response, String name, String description, String producer, double price, int id) throws ServletException, IOException {
        Product product = new Product(id, name, description, producer, price);
        if(((ProductService)(productService)).checkProduct(product)) {
            productService.add(product);
        } else {
            request.setAttribute("productValid", false);
            request.setAttribute("nameValid",((ProductService)(productService)).checkProductName(name));
            request.setAttribute("descriptionValid",((ProductService)(productService)).checkProductDescription(description));
            request.setAttribute("producerValid",((ProductService)(productService)).checkProductProducer(producer));
            request.setAttribute("priceValid",((ProductService)(productService)).checkProductPrice(price));
        }
        List<Product> products = productService.findAll();
        listProduct(request, response, products);
    }
}
