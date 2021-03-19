package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();
    private static int totalInvoicesNumber = 0;
    private int invoiceNumber = 0;

    public Invoice() {
        this.invoiceNumber = totalInvoicesNumber++;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException();
        }
        if (products.containsKey(product)) {
            products.put(product, (products.get(product) + 1));
        } else {
            products.put(product, 1);
        }
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        if (products.containsKey(product)) {
            products.put(product, (products.get(product) + quantity));
        } else {
            products.put(product, quantity);
        }
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.invoiceNumber + "\n");
        for (Product product : products.keySet()) {
            sb.append(product.getName() + "\t" + products.get(product) + "\t"
                    + product.getPrice() + "\n");
        }
        sb.append("Liczba produktów: " + products.size());

        return sb.toString();
    }
}
