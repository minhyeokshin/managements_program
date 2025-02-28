package employee.service.pay;

import java.util.function.Function;

@FunctionalInterface
public interface PayRaiseRate extends Function<Integer,Integer> {
    @Override
    Integer apply(Integer salary);
}
