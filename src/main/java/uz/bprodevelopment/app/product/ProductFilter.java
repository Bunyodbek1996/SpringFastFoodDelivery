package uz.bprodevelopment.app.product;


import lombok.Getter;
import lombok.Setter;
import uz.bprodevelopment.base.spec.BaseFilter;


@Getter
@Setter
public class ProductFilter extends BaseFilter {

    private String name;
    private Long categoryId;

    public ProductSpecification retrieveSpecification() {
        ProductSpecification spec = new ProductSpecification();

        spec.addCriteria("name", ":", name);
        spec.addCriteria("categoryId", ":", categoryId);
        return spec;
    }

}
