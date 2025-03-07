package employee.repository;

import common.ErrorCode;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeUpdateRepoImp implements EmployeeUpdateRepo{
    Connection connection = ObjectIo.getConnection();
    CallableStatement cs = null;

    @Override

    public void update(EmployeeVo employeeVo) throws EmployeeException {

        String sql = "{ CALL EmployeeUpdate(?, ?, ?, ?, ?, ?, ?, ?) }";
        int check = 0;

        try {
            cs = connection.prepareCall(sql);
            cs.setString(1, employeeVo.getName());
            cs.setInt(2, employeeVo.getEnteryear());
            cs.setInt(3, employeeVo.getEntermonth());
            cs.setInt(4, employeeVo.getEnterday());
            cs.setString(5, employeeVo.getRole());
            cs.setInt(6, employeeVo.getSecno());
            cs.setInt(7, employeeVo.getSalary());
            cs.setInt(8, employeeVo.getEno());

            check = cs.executeUpdate();
            if (check == 0) {
                throw new EmployeeException(ErrorCode.DB_NO_UPDATE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeException(ErrorCode.DB_UPDATE_ERROR);
        } finally {
            try {
                if (cs != null) cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
