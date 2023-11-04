package uz.bprodevelopment.app.product;


import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import uz.bprodevelopment.app.category.Category;
import uz.bprodevelopment.base.spec.BaseSpec;
import uz.bprodevelopment.base.spec.SearchCriteria;


public class ProductSpecification extends BaseSpec<Product> {

    private SearchCriteria criteria;
    private Specification<Product> specification = Specification.where(null);

    public ProductSpecification() {
    }

    public ProductSpecification(SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria == null || criteria.getValue() == null) return null;

        if (criteria.getKey().equals("categoryId")) {
            Join<Category, Product> category = root.join("category");
            return builder.equal(category.get("id"), criteria.getValue());
        }

        return super.toPredicate(root, query, builder);
    }

    public void addCriteria(String key, String operation, Object value) {
        if (value != null) {
            specification = specification.and(new ProductSpecification(new SearchCriteria(key, operation, value)));
        }
    }

    public Specification<Product> getSpecification() {
        return specification;
    }
}