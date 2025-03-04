package employee.repository;

import common.ErrorCode;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  Employee 정보를 DB에서 수정하는 클래스
 */
public class EmployeeUpdateRepoImp implements EmployeeUpdateRepo{

    Connection connection = ObjectIo.getConnection();
    PreparedStatement pstmt = null;

    /**
     * 입력받은 eno에 해당하는 Employee 정보를 DB에서 수정하는 메서드
     * @param employeeVo
     * @return boolean check
     * @throws EmployeeException
     */
    @Override
    public Boolean update(EmployeeVo employeeVo) throws EmployeeException {

        String sql = new StringBuilder()
                .append("UPDATE employee")
                .append("SET name = ?, enteryear = ?, entermonth = ?, enterday = ?, role = ?, secno = ?, salary = ?")
                .append("WHERE eno = ?").toString();
        int check = 0;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employeeVo.getName());
            pstmt.setInt(2, employeeVo.getEnteryear());
            pstmt.setInt(3, employeeVo.getEntermonth());
            pstmt.setInt(4, employeeVo.getEnterday());
            pstmt.setString(5, employeeVo.getRole());
            pstmt.setInt(6, employeeVo.getSecno());
            pstmt.setInt(7, employeeVo.getSalary());
            pstmt.setInt(8, employeeVo.getEno());

            check = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new EmployeeException(ErrorCode.UPDATE_FAILED);
        }
        return check == 1;
    }
}
