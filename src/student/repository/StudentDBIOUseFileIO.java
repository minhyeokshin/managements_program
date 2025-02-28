package student.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import student.dto.StudentDto;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 파일을 사용하는 DBIO 클래스
 */
public class StudentDBIOUseFileIO implements StudentDBIO {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(StudentDBIOUseFileIO.class.getName());
    File file = new File("src/student.data/student.data.json");

    /**
     * 파일 입력 구현체.  json 형태로 통신
     * @param studentTable 기본키와 학생 DTO 를 밸류값으로 가지는 map 자료구조
     */
    @Override
    public void fileInput(Map<String, StudentDto> studentTable) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); //writer 들여쓰기 설정
            objectMapper.writeValue(file, studentTable);  //map json 파일에 저장
            logger.info("파일 저장 성공");
        } catch (IOException e) {
            logger.warning("파일에 저장 실패");
            e.printStackTrace();
        }
    }

    /**
     * 파일 출력 구현체 json 형태로 통신
     * @return 키값으로 스튜던트 넘버, 밸류값으로 학생dTo로 구성된 해쉬맵을 반환하는 메소드
     */
    @Override
    public HashMap<String, StudentDto> fileOutput() {
        // 파일 존재 확인
        if (!file.exists()) {
            logger.warning("파일 접근 불가");
            return new HashMap<>(); // 빈 HashMap 반환
        }

        try {
            // JSON 데이터를 정확히 HashMap<String, StudentDto>로 읽어오기
            HashMap<String, StudentDto> fileMap = objectMapper.readValue(
                    file,
                    new TypeReference<HashMap<String, StudentDto>>() {}
            );
            logger.info("파일 데이터 읽어옴");
            return fileMap;
        } catch (MismatchedInputException em) {
            logger.warning("파일이 비어있거나 JSON 형식이 아닙니다. " + em.getMessage());
            return new HashMap<>(); // 빈 HashMap 반환
        } catch (Exception e) {
            logger.warning("파일 출력 중 오류 발생: " + e.getMessage());
            throw new RuntimeException(e); // 기타 오류
        }
    }
}
