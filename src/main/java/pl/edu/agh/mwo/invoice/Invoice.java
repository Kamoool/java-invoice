package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //    private Collection<Product> products;
    private LinkedHashMap<Product, Integer> products;

    public Invoice() {
//        this.products = new ArrayList<>();
        this.products = new LinkedHashMap<Product, Integer>();
    }

    public void addProduct(Product product) {

        if (this.products.containsKey(product))
            this.products.put(product, (this.products.get(product) + 1));
        else
            this.products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (!(quantity > 0))
            throw new IllegalArgumentException("Quantity must be greater than 0!");

        if (this.products.containsKey(product))
            this.products.put(product, (this.products.get(product) + quantity));
        else
            this.products.put(product, quantity);

    }

    public BigDecimal getSubtotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            sum = sum.add(product.getPrice().multiply(new BigDecimal(products.get(product))));
        }
        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal sum = BigDecimal.ZERO;

        for (Product product : products.keySet()) {
            sum = sum.add(product.getTaxPercent().multiply(new BigDecimal(products.get(product))).multiply(new BigDecimal(100)));
        }

        return sum;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            sum = sum.add(product.getPriceWithTax().multiply(new BigDecimal(products.get(product))));
        }
        return sum;
    }
}
