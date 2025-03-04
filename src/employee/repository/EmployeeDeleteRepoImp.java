package employee.repository;

import common.ErrorCode;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import object.ObjectIo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDeleteRepoImp implements EmployeeDeleteRepo{

    Connection connection = ObjectIo.getConnection();
    PreparedStatement pstmt = null;
    @Override
    public void Delete(EmployeeVo employeeVo) throws EmployeeException {

        String sql = new StringBuilder()
                .append("DELETE FROM employee")
                .append("WHERE eno = ?").toString();
        int check = 0;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, employeeVo.getEno());

            check = pstmt.executeUpdate();
            if (check == 0) {
                throw new EmployeeException(ErrorCode.DB_NO_DELETE);
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new EmployeeException(ErrorCode.DB_DELETE_ERROR);
        }
    }
}
