package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //    private Collection<Product> products;
    private Map<Product, Integer> products;

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

        sum = products.keySet().stream()
                .map(product -> product.getPrice().multiply(new BigDecimal(products.get(product))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        for (Product product : products.keySet()) {
//            sum = sum.add(product.getPrice().multiply(BigDecimal.valueOf(products.get(product))));
//        }

        return sum;
    }

    public BigDecimal getTax() {
        BigDecimal sum = BigDecimal.ZERO;

        sum = products.keySet().stream()
                .map(product -> product.getTaxPrice().multiply(BigDecimal.valueOf(products.get(product))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        for (Product product : products.keySet()) {
//            sum = sum.add(product.getTaxPrice().multiply(BigDecimal.valueOf(products.get(product))));
//        }

        return sum;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;

        sum = products.keySet().stream()
                .map(product -> product.getPriceWithTax().multiply(BigDecimal.valueOf(products.get(product))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

//        for (Product product : products.keySet()) {
//            sum = sum.add(product.getPriceWithTax().multiply(BigDecimal.valueOf(products.get(product))));
//        }

        return sum;
    }
}
