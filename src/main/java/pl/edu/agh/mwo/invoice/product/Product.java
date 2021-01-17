package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price; //lepiej BigDecimal na pieniądzach, bo są dokładne

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");

        if (name.equals(""))
            throw new IllegalArgumentException("Name cannot be empty");

        if (price == null)
            throw new IllegalArgumentException("Price cannot be null");

        if (price.compareTo(BigDecimal.ZERO) == -1)//price.signum - sprawdza czy mniejsze czy większe od 0
            throw new IllegalArgumentException("Price must be greater than 0");

        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getTaxPrice() {
        return this.price.multiply(this.taxPercent);
    }

    public BigDecimal getPriceWithTax() {
        return this.price.multiply(this.taxPercent).add(this.price);
    }
}
