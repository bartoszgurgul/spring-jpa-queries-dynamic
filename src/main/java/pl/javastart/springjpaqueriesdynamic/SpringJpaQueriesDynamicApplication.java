package pl.javastart.springjpaqueriesdynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javastart.springjpaqueriesdynamic.dao.ProductDao;
import pl.javastart.springjpaqueriesdynamic.model.Product;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringJpaQueriesDynamicApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaQueriesDynamicApplication.class, args);

        List<Product> products = new ArrayList<>();
        products.add(new Product("Telewizor", "Samsung", 4500.0));
        products.add(new Product("Opiekacz", "Opiex", 120.0));
        products.add(new Product("Laptop", "Samsung", 3599.0));
        products.add(new Product("Kino domowe", "Yamaha", 2600.0));
        products.add(new Product("Smartfon", "Sony", 2100.0));

        ProductDao productDao = ctx.getBean(ProductDao.class);
        products.forEach(productDao::save);

        System.out.println("All products");
        List<Product> all = productDao.getAll();
        all.forEach(System.out::println);

        System.out.println("Product more expensive than 3000");
        List<Product> productList = productDao.customGet("SELECT p FROM Product p WHERE p.price > 3000");
        productList.forEach(System.out::println);

        System.out.println("All product order by price");
        List<Product> productByPrice = productDao.customGet("SELECT p FROM Product p ORDER BY p.price ASC");
        productByPrice.forEach(System.out::println);

        System.out.println("Expensive Samsung product");
        productDao.customGet("SELECT p FROM Product p WHERE p.producer='Samsung' AND p.price > 4000")
                .forEach(System.out::println);

        productDao.deleteAll();

        ctx.close();

    }

}
