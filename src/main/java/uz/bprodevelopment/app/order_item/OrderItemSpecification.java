package uz.bprodevelopment.app.order_item;


import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import uz.bprodevelopment.app.category.Category;
import uz.bprodevelopment.base.spec.BaseSpec;
import uz.bprodevelopment.base.spec.SearchCriteria;


public class OrderItemSpecification extends BaseSpec<OrderItem> {

    private SearchCriteria criteria;
    private Specification<OrderItem> specification = Specification.where(null);

    public OrderItemSpecification() {
    }

    public OrderItemSpecification(SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<OrderItem> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria == null || criteria.getValue() == null) return null;

        if (criteria.getKey().equals("categoryId")) {
            Join<Category, OrderItem> teacher = root.join("category");
            return builder.equal(teacher.get("id"), criteria.getValue());
        }

        return super.toPredicate(root, query, builder);
    }

    public void addCriteria(String key, String operation, Object value) {
        if (value != null) {
            specification = specification.and(new OrderItemSpecification(new SearchCriteria(key, operation, value)));
        }
    }

    public Specification<OrderItem> getSpecification() {
        return specification;
    }
}