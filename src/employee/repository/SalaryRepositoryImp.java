package employee.repository;

import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;
import object.ObjectIo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Salary 변경 이력을 DB에 저장하는 클래스 
 */
public class SalaryRepositoryImp implements SalaryRepository{
    Connection connection = ObjectIo.getConnection();
    ResultSet rs = null;

    /**
     * Salary 변경 반영
     * @param employeeDto
     * @param function
     * @return true or false
     * @throws SQLException
     */
    @Override
    public Boolean updateSalary(EmployeeDto employeeDto, Function<Integer, Integer> function) throws SQLException {
        Integer currentSalary = employeeDto.getSalary();
        Integer newSalary = function.apply(currentSalary);

        try {
            connection.setAutoCommit(false);

            String sql1 = new StringBuilder()
                    .append("UPDATE EMPLOYEE SET ")
                    .append("salary = ? ")
                    .append("WHERE eno = ?;").toString();

            PreparedStatement pstmt1 = connection.prepareStatement(sql1);
            pstmt1.setInt(1, newSalary);
            pstmt1.setInt(2, employeeDto.getEno());
            int check1 = pstmt1.executeUpdate();

            String sql2 = new StringBuilder()
                    .append("INSERT INTO PAY_RAISE_HISTORY (eno, oldSalary, newSalary) ")
                    .append("VALUES (?,?,?);").toString();

            PreparedStatement pstmt2 = connection.prepareStatement(sql2);
            pstmt2.setInt(1, employeeDto.getEno());
            pstmt2.setInt(2, currentSalary);
            pstmt2.setInt(3, newSalary);
            int check2 = pstmt2.executeUpdate();

            if (check1 > 0 && check2 > 0) {
                connection.commit();
                return true;
            }
            else {
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Employee의 Salary 변경 이력 반환
     * @param eno
     * @return List<SalaryHistoryDto>
     */
    @Override
    public List<SalaryHistoryDto> salaryHistory(int eno) {
        List<SalaryHistoryDto> list = new ArrayList<>();
        try {
            String sql = new StringBuilder()
                    .append("SELECT p.eno, e.name, p.oldSalary, p.newSalary ")
                    .append("FROM EMPLOYEE e, PAY_RAISE_HISTORY p ")
                    .append("WHERE e.ENO = p.ENO;").toString();

            PreparedStatement pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                SalaryHistoryDto dto = SalaryHistoryDto.builder()
                        .eno(rs.getInt("eno"))
                        .name(rs.getString("name"))
                        .oldSalary(rs.getInt("oldSalary"))
                        .newSalary(rs.getInt("newSalary")).build();
                list.add(dto);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
