package employee.repository;



import common.ErrorCode;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  Employee 정보를 DB에 저장하는 클래스
 */
public class EmployeeCreateRepoImp implements EmployeeCreateRepo {

    Connection conn = ObjectIo.getConnection();
    CallableStatement cs = null;

    /**
     * 입력받은 Employee 정보를 DB에 저장하는 메서드
     *
     * @param employeeVo
     */
    @Override
    public void create(EmployeeVo employeeVo) throws EmployeeException {
        try {
            conn.setAutoCommit(false);
            cs = conn.prepareCall("{call DB_EMPLOYEE_INSERT(?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, employeeVo.getEno());
            cs.setString(2, employeeVo.getName());
            cs.setInt(3, employeeVo.getEnteryear());
            cs.setInt(4, employeeVo.getEntermonth());
            cs.setInt(5, employeeVo.getEnterday());
            cs.setString(6, employeeVo.getRole());
            cs.setInt(7, employeeVo.getSecno());
            cs.setInt(8, employeeVo.getSalary());
            cs.setString(9, "EMPLOYEE");

            cs.execute();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeException(ErrorCode.DB_CREATE_ERROR);
        }
    }
}
