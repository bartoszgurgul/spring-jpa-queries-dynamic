package pl.javastart.springjpaqueriesdynamic.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.findAllOrderByPrice", query = "SELECT p FROM Product p ORDER BY p.price asc"),
        @NamedQuery(name = "Product.deleteAll", query = "DELETE FROM Product p"),
        @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name=:name"),
})
public class Product implements Serializable {
    private static final long serialVersionUID = -8903956080357392266L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String producer;
    private Double price;

    @Override
    public String toString() {
        return "Product [id=" + id
                + ", name=" + name
                + ", producer=" + producer
                + ", price=" + price + "]";
    }

    public Product() {
    }

    public Product(String name, String product, Double price) {
        this.name = name;
        this.producer = product;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String product) {
        this.producer = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
