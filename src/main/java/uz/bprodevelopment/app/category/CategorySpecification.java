package uz.bprodevelopment.app.category;


import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import uz.bprodevelopment.base.spec.BaseSpec;
import uz.bprodevelopment.base.spec.SearchCriteria;


public class CategorySpecification extends BaseSpec<Category> {

    private SearchCriteria criteria;
    private Specification<Category> specification = Specification.where(null);

    public CategorySpecification() {
    }

    public CategorySpecification(SearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria == null || criteria.getValue() == null) return null;

        return super.toPredicate(root, query, builder);
    }

    public void addCriteria(String key, String operation, Object value) {
        if (value != null) {
            specification = specification.and(new CategorySpecification(new SearchCriteria(key, operation, value)));
        }
    }

    public Specification<Category> getSpecification() {
        return specification;
    }
}