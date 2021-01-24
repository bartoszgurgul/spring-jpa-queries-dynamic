package pl.javastart.springjpaqueriesdynamic.dao;

import org.springframework.stereotype.Repository;
import pl.javastart.springjpaqueriesdynamic.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product get(Long id){
        return entityManager.find(Product.class, id);
    }

    public void save(Product product){
        entityManager.persist(product);
    }

    public List<Product> getAll(){
        final String getAllQuery = "SELECT p FROM Product p";
        TypedQuery<Product> typedQuery = entityManager.createQuery(getAllQuery, Product.class);
        List<Product> resultList = typedQuery.getResultList();
        return  resultList;
    }

    public void deleteAll(){
        final String deleteAllQuery = "DELETE FROM Product p";
        Query query = entityManager.createQuery(deleteAllQuery);
        query.executeUpdate();
    }

    public List<Product> customGet(String jpqlQuery){
        TypedQuery<Product> query = entityManager.createQuery(jpqlQuery, Product.class);
        return query.getResultList();
    }
}
