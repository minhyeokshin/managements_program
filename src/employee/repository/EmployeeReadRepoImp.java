package employee.repository;

import employee.dto.EmployeeDto;
import object.ObjectIo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *  DB에 저장된 Employee 정보를 가져오는 클래스
 */
public class EmployeeReadRepoImp implements EmployeeReadRepo {

    Connection connection = ObjectIo.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * Employee 1명의 정보를 가져오는 메서드
     * @param eno
     * @return Employee
     */
    @Override
    public EmployeeDto ReadOne(Integer eno) {

        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM EMPLOYEE WHERE eno = ?").toString();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, eno);
            rs = pstmt.executeQuery();

            EmployeeDto dto = new EmployeeDto();
            dto.setEno(rs.getInt("eno"));
            dto.setName(rs.getString("name"));
            dto.setEnteryear(rs.getInt("enteryear"));
            dto.setEntermonth(rs.getInt("entermonth"));
            dto.setEnterday(rs.getInt("enterday"));
            dto.setRole(rs.getString("role"));
            dto.setSecno(rs.getInt("secno"));
            dto.setSalary(rs.getInt("salary"));
            pstmt.close();
            return dto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Employee 전체 정보를 가져오는 메서드
     * @return List<EmployeeDto>
     */
    @Override
    public List<EmployeeDto> ReadAll() {
        List<EmployeeDto> list = new ArrayList<>();

        try {
            String sql = new StringBuilder()
                    .append("SELECT * FROM EMPLOYEE").toString();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                EmployeeDto dto = new EmployeeDto();
                dto.setEno(rs.getInt("eno"));
                dto.setName(rs.getString("name"));
                dto.setEnteryear(rs.getInt("enteryear"));
                dto.setEntermonth(rs.getInt("entermonth"));
                dto.setEnterday(rs.getInt("enterday"));
                dto.setRole(rs.getString("role"));
                dto.setSecno(rs.getInt("secno"));
                dto.setSalary(rs.getInt("salary"));
                list.add(dto);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
