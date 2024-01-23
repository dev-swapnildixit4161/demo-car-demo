package com.nashtech.inventory.restapi;

import com.nashtech.inventory.query.FindProductsQuery;
import com.nashtech.inventory.query.ProductsSummary;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final QueryGateway queryGateway;

    public ProductsController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("product/{productId}")
    public ProductsSummary getProducts(@PathVariable String productId) {
        return queryGateway.query(new FindProductsQuery(productId), ProductsSummary.class).join();
    }

}
