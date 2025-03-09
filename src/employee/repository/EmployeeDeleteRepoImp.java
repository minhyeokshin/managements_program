package employee.repository;

import common.ErrorCode;
import employee.vo.EmployeeVo;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 사원 정보를 삭제하는 클래스
 */
public class EmployeeDeleteRepoImp implements EmployeeDeleteRepo{

    Connection connection = ObjectIo.getConnection();
    CallableStatement cs = null;

    /**
     * 사원 정보를 삭제하는 메서드
     * @param employeeVo 삭제할 사원 정보 객체
     * @throws EmployeeException 사원 정보 삭제 중 오류 발생시 예외처리
     */
    @Override
    public void Delete(EmployeeVo employeeVo) throws EmployeeException {

        String sql = "{ CALL EmployeeDelete(?, ?) }";

        try {
            cs = connection.prepareCall(sql);
            cs.setString(1, "employee");
            cs.setInt(2, employeeVo.getEno());

            cs.execute();
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
