package employee.repository;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;
import exception.EmployeeException;
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
public class SalaryRepositoryImp implements SalaryRepository {
    Connection connection = ObjectIo.getConnection();
    ResultSet rs = null;
    PreparedStatement pstmt;


    /**
     * Employee의 Salary 변경이력을 DB에 저장합니다.
     * @param eno
     * @param oldSalary
     * @param newSalary
     * @throws EmployeeException
     */
    @Override
    public void updateSalaryHistory(int eno, int oldSalary, int newSalary) throws EmployeeException {
        try {
            String sql = new StringBuilder()
                    .append("INSERT INTO PAY_RAISE_HISTORY (eno, oldSalary, newSalary) ")
                    .append("VALUES (?,?,?);").toString();

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, eno);
            pstmt.setInt(2, oldSalary);
            pstmt.setInt(3, newSalary);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            throw new EmployeeException(ErrorCode.DB_UPDATE_SALARY_ERROR);
        }
    }
        /**
         * Employee의 Salary 변경 이력 반환
         * @param eno
         * @return List<SalaryHistoryDto>
         */
        @Override
        public List<SalaryHistoryDto> salaryHistory ( int eno) throws EmployeeException {
            List<SalaryHistoryDto> list = new ArrayList<>();
            try {
                String sql = new StringBuilder()
                        .append("SELECT p.eno, e.name, p.oldSalary, p.newSalary ")
                        .append("FROM EMPLOYEE e, PAY_RAISE_HISTORY p ")
                        .append("WHERE e.ENO = p.ENO;").toString();

                pstmt = connection.prepareStatement(sql);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    SalaryHistoryDto dto = SalaryHistoryDto.builder()
                            .eno(rs.getInt("eno"))
                            .name(rs.getString("name"))
                            .oldSalary(rs.getInt("oldSalary"))
                            .newSalary(rs.getInt("newSalary")).build();
                    list.add(dto);
                }
                pstmt.close();
                return list;
            } catch (SQLException e) {
                throw new EmployeeException(ErrorCode.DB_UPDATE_SALARY_HISTORY_ERROR);
            }
        }
    }
