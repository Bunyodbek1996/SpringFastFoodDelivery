package uz.bprodevelopment.app.order_item;


import lombok.Getter;
import lombok.Setter;
import uz.bprodevelopment.base.spec.BaseFilter;


@Getter
@Setter
public class OrderItemFilter extends BaseFilter {

    private String name;
    private Boolean closed;
    private Long teacherId;
    private String teacherName;
    private Long subjectId;
    private String subjectName;

    public OrderItemSpecification retrieveSpecification() {
        OrderItemSpecification spec = new OrderItemSpecification();

        spec.addCriteria("name", ":", name);
        spec.addCriteria("closed", ":", closed);
        spec.addCriteria("teacherId", ":", teacherId);
        spec.addCriteria("teacherName", ":", teacherName);
        spec.addCriteria("subjectId", ":", subjectId);
        spec.addCriteria("subjectName", ":", subjectName);

        return spec;
    }

}
