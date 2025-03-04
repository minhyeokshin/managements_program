package employee.repository;

import employee.dto.EmployeeDto;

public class EmployeeDeleteRepoImp implements EmployeeDeleteRepo{

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
