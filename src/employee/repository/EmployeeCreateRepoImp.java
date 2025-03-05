package employee.repository;



import common.ErrorCode;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  Employee 정보를 DB에 저장하는 클래스
 */
public class EmployeeCreateRepoImp implements EmployeeCreateRepo {

    Connection connection = ObjectIo.getConnection();
    PreparedStatement pstmt = null;

    /**
     * 입력받은 Employee 정보를 DB에 저장하는 메서드
     * @param employeeVo
     * @return boolean값(true)
     */
    @Override
    public void create(EmployeeVo employeeVo) throws EmployeeException {
        String sql = new StringBuilder()
                .append("INSERT INTO EMPLOYEE (eno, name, enteryear, entermonth, enterday, role, secno, salary) ")
                .append("VALUES(?, ?, ?, ?, ?, ?, ?, ?)").toString();
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, employeeVo.getEno());
            pstmt.setString(2, employeeVo.getName());
            pstmt.setInt(3, employeeVo.getEnteryear());
            pstmt.setInt(4, employeeVo.getEntermonth());
            pstmt.setInt(5, employeeVo.getEnterday());
            pstmt.setString(6, employeeVo.getRole());
            pstmt.setInt(7, employeeVo.getSecno());
            pstmt.setInt(8, employeeVo.getSalary());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new EmployeeException(ErrorCode.DB_CREATE_ERROR);
        }

    }
}
