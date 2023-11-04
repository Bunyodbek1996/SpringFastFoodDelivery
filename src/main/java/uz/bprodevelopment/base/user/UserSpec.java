package uz.bprodevelopment.base.user;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import uz.bprodevelopment.base.spec.BaseSpec;
import uz.bprodevelopment.base.spec.SearchCriteria;


public class UserSpec extends BaseSpec<User> {

    private SearchCriteria criteria;
    private Specification<User> specification = Specification.where(null);

    public UserSpec() {}

    public UserSpec(SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    public UserSpec(String key, String operation, Object value) {
        super(new SearchCriteria(key, operation, value));
    }


    @Override
    public Predicate toPredicate
            (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria == null || criteria.getValue() == null) return null;
        return super.toPredicate(root, query, builder);
    }


    public void addCriteria(String key, String operation, Object value) {
        if (value != null){
            specification = specification.and(new UserSpec(new SearchCriteria(key, operation, value)));
        }
    }

    public Specification<User> getSpecification() {
        return specification;
    }

}