package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import dto.StudentDto;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StudentDBIOUseFileIO implements StudentDBIO {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(StudentDBIOUseFileIO.class.getName());
    File file = new File("src/data/data.json");




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
