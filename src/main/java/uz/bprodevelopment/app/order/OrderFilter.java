package uz.bprodevelopment.app.order;


import lombok.Getter;
import lombok.Setter;
import uz.bprodevelopment.base.spec.BaseFilter;
import uz.bprodevelopment.base.util.AppUtils;


@Getter
@Setter
public class OrderFilter extends BaseFilter {

    private String status;
    private Long userId;

    public OrderSpecification retrieveSpecification() {
        OrderSpecification spec = new OrderSpecification();

        spec.addCriteria("status", ":", status);

        if (!AppUtils.isAdmin() && !AppUtils.isWaiter()) {
            spec.addCriteria("userId", ":", AppUtils.getCurrentUserId());
        } else {
            spec.addCriteria("userId", ":", userId);
        }

        return spec;
    }

}
