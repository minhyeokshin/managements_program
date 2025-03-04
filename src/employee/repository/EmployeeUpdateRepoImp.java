package employee.repository;

import employee.dto.EmployeeDto;

public class EmployeeUpdateRepoImp implements EmployeeUpdateRepo{


    @Override

    public void update(EmployeeVo employeeVo) throws EmployeeException {

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
            if (check == 0) {
                throw new EmployeeException(ErrorCode.DB_NO_UPDATE);
            }
            pstmt.close();
        } catch (SQLException e) {
            throw new EmployeeException(ErrorCode.DB_UPDATE_ERROR);
        }

    }
}
