package uz.bprodevelopment.app.category;


import lombok.Getter;
import lombok.Setter;
import uz.bprodevelopment.base.spec.BaseFilter;


@Getter
@Setter
public class CategoryFilter extends BaseFilter {

    private String name;

    public CategorySpecification retrieveSpecification() {
        CategorySpecification spec = new CategorySpecification();

        spec.addCriteria("name", ":", name);

        return spec;
    }

}
