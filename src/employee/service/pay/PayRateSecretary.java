package employee.service.pay;

public class PayRateSecretary implements PayRaiseRate{
    @Override
    public Integer apply(Integer salary) {
        return (int)(salary * 1.2);
    }
}
