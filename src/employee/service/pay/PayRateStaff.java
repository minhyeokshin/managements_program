package employee.service.pay;

public class PayRateStaff implements PayRaiseRate{
    @Override
    public Integer apply(Integer salary) {
        return (int)(salary * 1.3);
    }
}
