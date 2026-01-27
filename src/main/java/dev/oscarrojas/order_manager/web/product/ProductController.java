package dev.oscarrojas.order_manager.web.product;

import dev.oscarrojas.order_manager.product.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String getProductList(Model model) {
        Collection<ProductView> products =
                service.getProductList().stream().map(this::mapToView).toList();

        model.addAttribute("products", products);

        return "/products/product-list";
    }

    @GetMapping("/create")
    public String createProductForm() {

        return "/products/create";
    }

    @PostMapping("/create")
    public String createProductFormSubmit(CreateProductForm form) {
        Collection<CreateVariantCommand> variants = form.variants().stream()
                .map(variant -> new CreateVariantCommand(variant.sku(), variant.attributes()))
                .toList();
        CreateProductCommand product =
                new CreateProductCommand(form.name(), form.brand(), form.description(), variants);

        ProductResponse response = service.createProduct(product);

        System.out.println(response);

        return "/products/product-list";
    }

    private ProductView mapToView(ProductResponse product) {
        return new ProductView(product.id(), product.brand(), product.name(), mapToView(product.variants()));
    }

    private Collection<ProductVariantView> mapToView(Collection<ProductVariantResponse> variants) {
        return variants.stream()
                .map(variant ->
                        new ProductVariantView(variant.id(), variant.sku(), variant.attributes(), variant.productId()))
                .toList();
    }
}
