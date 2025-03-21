package employee.service.pay;


/**
 * 안전요완 급여 인상 정책
 */
public class PayRateSecretary implements PayRaiseRate<Integer,Integer>{
    /**
     * 안전요원 급여 인상 메소드
     * @param salary the function argument
     * @return 업데이트된 직원 급여
     */
    @Override
    public Integer apply(Integer salary) {
        return (int)(salary * 1.2);
    }
}
