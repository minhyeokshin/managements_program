package employee.repository;

import common.ErrorCode;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Employee 정보를 DB에서 삭제하는 클래스
 */
public class EmployeeDeleteRepoImp implements EmployeeDeleteRepo{

    Connection connection = ObjectIo.getConnection();
    PreparedStatement pstmt = null;

    /**
     * 입력받은 eno에 해당하는 Employee 정보를 DB에서 삭제하는 메서드
     * @param employeeVo
     * @return boolean check
     * @throws EmployeeException
     */
    @Override
    public Boolean Delete(EmployeeVo employeeVo) throws EmployeeException {

        String sql = new StringBuilder()
                .append("DELETE FROM employee")
                .append("WHERE eno = ?").toString();
        int check = 0;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, employeeVo.getEno());

            check = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new EmployeeException(ErrorCode.DELETE_FAILED);
        }
        return check == 1;
    }
}
