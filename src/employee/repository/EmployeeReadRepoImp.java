package employee.repository;

import common.ErrorCode;
import employee.dto.EmployeeDto;
import exception.EmployeeException;
import object.ObjectIo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * DB에 저장된 Employee 정보를 가져오는 클래스
 */
public class EmployeeReadRepoImp implements EmployeeReadRepo {

    Connection connection = ObjectIo.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * Employee 1명의 정보를 가져오는 메서드
     *
     * @param eno
     * @return Employee
     */
    @Override
    public Optional<EmployeeDto> ReadOne(Integer eno) throws EmployeeException {
        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM EMPLOYEE WHERE eno = ?").toString();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, eno);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                EmployeeDto dto = EmployeeDto.builder()
                        .eno(rs.getInt("eno"))
                        .name(rs.getString("name"))
                        .enteryear(rs.getInt("enteryear"))
                        .entermonth(rs.getInt("entermonth"))
                        .enterday(rs.getInt("enterday"))
                        .role(rs.getString("role"))
                        .secno(rs.getInt("secno"))
                        .salary(rs.getInt("salary"))
                        .build();

                pstmt.close();
                return Optional.of(dto);
            } else return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeException(ErrorCode.DB_READ_ONE_ERROR);


        }
    }

    /**
     * Employee 전체 정보를 가져오는 메서드
     *
     * @return List<EmployeeDto>
     */
    @Override
    public Optional<List<EmployeeDto>> ReadAll() throws EmployeeException {
        List<EmployeeDto> list = new ArrayList<>();

        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM EMPLOYEE").toString();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                EmployeeDto dto = EmployeeDto.builder()
                        .eno(rs.getInt("eno"))
                        .name(rs.getString("name"))
                        .enteryear(rs.getInt("enteryear"))
                        .entermonth(rs.getInt("entermonth"))
                        .enterday(rs.getInt("enterday"))
                        .role(rs.getString("role"))
                        .secno(rs.getInt("secno"))
                        .salary(rs.getInt("salary"))
                        .build();
                list.add(dto);
            }
            pstmt.close();

            return Optional.of(list);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeException(ErrorCode.DB_READ_ALL_ERROR);
        }


    }
}
