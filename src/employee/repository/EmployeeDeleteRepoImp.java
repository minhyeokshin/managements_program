package employee.repository;

import common.ErrorCode;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeDeleteRepoImp implements EmployeeDeleteRepo{

    Connection connection = ObjectIo.getConnection();
    CallableStatement cs = null;
    @Override
    public void Delete(EmployeeVo employeeVo) throws EmployeeException {

        String sql = "{ CALL EmployeeDelete(?, ?) }";
        int check = 0;

        try {
            cs = connection.prepareCall(sql);
            cs.setString(1, "employee");
            cs.setInt(2, employeeVo.getEno());

            check = cs.executeUpdate();
            if (check == 0) {
                throw new EmployeeException(ErrorCode.DB_NO_DELETE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeException(ErrorCode.DB_DELETE_ERROR);
        } finally {
            try {
                if (cs != null) cs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
