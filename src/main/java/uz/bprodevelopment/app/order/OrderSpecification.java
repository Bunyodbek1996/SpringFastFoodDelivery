package uz.bprodevelopment.app.order;


import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import uz.bprodevelopment.base.spec.BaseSpec;
import uz.bprodevelopment.base.spec.SearchCriteria;
import uz.bprodevelopment.base.user.User;


public class OrderSpecification extends BaseSpec<Order> {

    private SearchCriteria criteria;
    private Specification<Order> specification = Specification.where(null);

    public OrderSpecification() {
    }

    public OrderSpecification(SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria == null || criteria.getValue() == null) return null;

        if (criteria.getKey().equals("userId")) {
            Join<User, Order> user = root.join("user");
            return builder.equal(user.get("id"), criteria.getValue());
        }

        if (criteria.getKey().equals("status")) {
            return builder.equal(builder.toString(root.get("status")),
                    criteria.getValue());
        }

        return super.toPredicate(root, query, builder);
    }

    public void addCriteria(String key, String operation, Object value) {
        if (value != null) {
            specification = specification.and(new OrderSpecification(new SearchCriteria(key, operation, value)));
        }
    }

    public Specification<Order> getSpecification() {
        return specification;
    }
}