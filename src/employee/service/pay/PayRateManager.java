package employee.service.pay;

/**
 * 매니저 급여 인상 정책
 */
public class PayRateManager implements PayRaiseRate<Integer,Integer>{
    /**
     * 매니저 급여 인상 메소드
     * @param salary the function argument
     * @return 업데이트된 직원 급여
     */
    @Override
    public Integer apply(Integer salary) {
        return (int)(salary * 1.1);
    }
}
