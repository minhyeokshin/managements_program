package employee.repository;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import employee.dto.SalaryHistoryDto;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Employee의 Salary 변경 이력을 DB에 저장하고 반환하는 클래스
 */
public class SalaryRepositoryImp implements SalaryRepository {
    Connection connection = ObjectIo.getConnection();
    ResultSet rs = null;
    CallableStatement cs = null;


    /**
     * Employee의 Salary 변경이력을 DB에 저장합니다.
     *
     * @param eno
     * @param oldSalary
     * @param newSalary
     * @throws EmployeeException
     */
    @Override
    public void updateSalaryHistory(int eno, int oldSalary, int newSalary) throws EmployeeException {
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call DB_HISTORY_INSERT(?,?,?)}");
            cs.setInt(1, eno);
            cs.setInt(2, oldSalary);
            cs.setInt(3, newSalary);
            cs.executeUpdate();
            connection.commit();
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeException(ErrorCode.DB_UPDATE_SALARY_ERROR);
        }
    }

    /**
     * Employee의 Salary 변경 이력 반환
     *
     * @param eno
     * @return List<SalaryHistoryDto>
     */
    @Override
    public Optional<List<SalaryHistoryDto>> salaryHistory(int eno) throws EmployeeException {
        List<SalaryHistoryDto> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            cs = connection.prepareCall("{call DB_HISTORY_SELECT(?)}");
            cs.setInt(1, eno);
            rs = cs.executeQuery();

            while (rs.next()) {
                SalaryHistoryDto dto = SalaryHistoryDto.builder()
                        .eno(rs.getInt("eno"))
                        .name(rs.getString("name"))
                        .oldSalary(rs.getInt("oldSalary"))
                        .newSalary(rs.getInt("newSalary")).build();
                list.add(dto);
            }
            rs.close();
            cs.close();
            return Optional.of(list);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeException(ErrorCode.DB_UPDATE_SALARY_HISTORY_ERROR);
        }
    }
}
