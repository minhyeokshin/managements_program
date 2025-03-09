package employee.service.pay;

import employee.service.EmployeeSalaryService;

import java.util.function.Function;

/**
 * 급여 인상 전략 인터페이스
 */
@FunctionalInterface
public interface PayRaiseRate<T,U> {
    /**
     * 급여 인상 메소드
     * @param salary the function argument
     * @return 업데이트된 직원 급여
     */
    T apply(U salary);
}
