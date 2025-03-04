package employee.service.pay;

public class PayRateManager implements PayRaiseRate{
    @Override
    public Integer apply(Integer salary) {
        return (int)(salary * 1.1);
    }
}
