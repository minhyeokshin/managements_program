package employee.vo;

import employee.dto.EmployeeDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class EmployeeVo {
    private Integer eno;
    private String name;
    private Integer enteryear;
    private Integer entermonth;
    private Integer enterday;
    private String role;
    private Integer secno;
    private Integer salary;

    private EmployeeVo(){};

    public EmployeeVo(Integer eno, String name, Integer enteryear, Integer entermonth, Integer enterday, String role, Integer secno, Integer salary) {
            this.eno = eno;
            this.name = name;
            this.enteryear = enteryear;
            this.entermonth = entermonth;
            this.enterday = enterday;
            this.role = role;
            this.secno = secno;
            this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeVo that = (EmployeeVo) o;
        return Objects.equals(eno, that.eno) && Objects.equals(name, that.name) && Objects.equals(enteryear, that.enteryear) && Objects.equals(entermonth, that.entermonth) && Objects.equals(enterday, that.enterday) && Objects.equals(role, that.role) && Objects.equals(secno, that.secno) && Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eno, name, enteryear, entermonth, enterday, role, secno, salary);
    }

    @Override
    public String toString() {
        return "EmployeeVo{" +
                "eno=" + eno +
                ", name='" + name + '\'' +
                ", enteryear=" + enteryear +
                ", entermonth=" + entermonth +
                ", enterday=" + enterday +
                ", role='" + role + '\'' +
                ", secno=" + secno +
                ", salary=" + salary +
                '}';
    }
}

