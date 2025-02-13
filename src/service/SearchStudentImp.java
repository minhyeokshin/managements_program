package service;

import dto.StudentDto;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchStudentImp implements SearchStudent {
    StudentIO studentIO;

    public SearchStudentImp(StudentIO studentIO) {
        this.studentIO = studentIO;
    }

    @Override
    public StudentDto search(String sno) {
        return studentIO.getStudentTable().get(sno);
    }


    //studentNumber 기준검색
    @Override
    public StudentDto searchBySno(String studentNumber) {
        return studentIO.getStudentTable().values().stream()
                .filter(student ->
                        student.getStudentNumber() != null &&
                                student.getStudentNumber().equalsIgnoreCase(studentNumber))
                .findFirst()
                .orElse(null);
    }

    //전체 학생 검색
    @Override
    public Map<String, StudentDto> searchAll() {
        if (studentIO.getStudentTable() != null)
            return studentIO.getStudentTable(); // 🔹 studentTable이 존재하면 그대로 반환
        else
            return Collections.emptyMap();
    }

    //특정 등급 학생 검색
    @Override
    public List<StudentDto> searchByGrade(String grade) {
        if (studentIO.getStudentTable() == null)
            return Collections.emptyList();

        return studentIO.getStudentTable().values().stream()
                .filter(student -> student.getGrade() != null
                        && student.getGrade().equalsIgnoreCase(grade))
                .collect(Collectors.toList());
    }





    //최고 점수 검색 로직
    @Override
    public List<StudentDto> searchMaxLogic(Function<StudentDto,Integer> function) {
        OptionalInt maxscore = studentIO.getStudentTable().values().stream()
                .mapToInt(function::apply)
                .max();

        return maxscore.isPresent() ?

                studentIO.getStudentTable().values().stream()
                        .filter(student -> function.apply(student) ==maxscore.getAsInt())
                        .collect(Collectors.toList())

                : Collections.emptyList();
    }

    //최고 점수 검색 기능
    @Override
    public Map<String, List<StudentDto>> MaxTotalMap(String subject) {
        Map<String, Function<StudentDto, Integer>> map = new HashMap<>();
        map.put("총점", StudentDto::getTotal);
        map.put("영어", StudentDto::getEnglish);
        map.put("수학", StudentDto::getMath);
        map.put("국어", StudentDto::getKorean);
        map.put("과학", StudentDto::getScience);

        if (!map.containsKey(subject)) {
            System.out.println("올바른 과목명을 입력하세요.");
            return Collections.emptyMap();}

        List<StudentDto> students = searchMaxLogic(map.get(subject));

        return students.isEmpty()
                ? Collections.emptyMap()
                : Collections.singletonMap(subject,students);
    }


    //최저 점수 검색 로직
    @Override
    public List<StudentDto> searchMinLogic(Function<StudentDto,Integer> function) {
        OptionalInt minscore = studentIO.getStudentTable().values().stream()
                .mapToInt(function::apply)
                .min();

        //List<StudentDto> list = studentIO.getStudentTable().

        return minscore.isPresent() ?

                studentIO.getStudentTable().values().stream()
                        .filter(student -> function.apply(student) ==minscore.getAsInt())
                        .collect(Collectors.toList())

                : Collections.emptyList();
    }

    //최저 점수 검색 기능
    @Override
    public Map<String, List<StudentDto>> MinTotalMap(String subject) {
        Map<String, Function<StudentDto, Integer>> map = new HashMap<>();
        map.put("총점", StudentDto::getTotal);
        map.put("영어", StudentDto::getEnglish);
        map.put("수학", StudentDto::getMath);
        map.put("국어", StudentDto::getKorean);
        map.put("과학", StudentDto::getScience);

        if (!map.containsKey(subject)) {
            System.out.println("올바른 과목명을 입력하세요.");
            return Collections.emptyMap();}

        List<StudentDto> students = searchMinLogic(map.get(subject));

        return students.isEmpty()
                ? Collections.emptyMap()
                : Collections.singletonMap(subject,students);
    }


    //범위 검색 로직
    @Override
    public List<StudentDto> searchRangeLogic(Function<StudentDto,Integer> function,double min , double max) {
        return studentIO.getStudentTable().values().stream()
                .filter(student -> {
                    int score= function.apply(student);
                    return score >= min && score <= max;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<StudentDto>> SearchRange(String subject,double min,double max){
        Map<String,Function<StudentDto,Integer>> map = new HashMap<>();
        map.put("총점",StudentDto::getTotal);
        map.put("영어",StudentDto::getEnglish);
        map.put("수학",StudentDto::getMath);
        map.put("국어",StudentDto::getKorean);
        map.put("과학",StudentDto::getScience);

        if(map.containsKey(subject)){
            List<StudentDto> students = searchRangeLogic(map.get(subject),min,max);
            return Collections.singletonMap(subject,students);
        }
        else {
            System.out.println("올바른 과목명을 입력하세요.");
            return Collections.emptyMap();
        }
    }

    //재시험 대상 학생 필터링
    //60점이하(F등급)
    @Override
    public List<StudentDto> searchByReTest (){
        return studentIO.getStudentTable().values().stream()
                .filter(student -> student.getGrade() != null &&
                        student.getGrade().equalsIgnoreCase("F"))
                .collect(Collectors.toList());
    }
}
