# 학생·직원 통합 관리 프로그램

## 목차
1. [프로젝트 목적](#1-프로젝트-목적)
2. [사용 기술 스택 및 라이브러리](#2-사용-기술-스택-및-라이브러리)
3. [프로젝트 기능](#3-프로젝트-기능)
4. [프로젝트 구조](#4-프로젝트-구조)
5. [사용 방법](#5-사용-방법)
6. [실행 중 발생할 수 있는 문제 및 해결 방법](#6-실행-중-발생할-수-있는-문제-및-해결-방법)
7. [팀원별 역할 분담](#7-팀원별-역할-분담)



## 1. 프로젝트 목적




## 2. 사용 기술 스택 및 라이브러리

### **기본 기술 스택**
- **애플리케이션**: Java (JDK 17)
- **데이터베이스**: MySQL (MySQL 8)
- **연결 기술**: JDBC
- **데이터 처리**: JSON

### **외부 라이브러리**
- **Lombok**: 코드 간결화를 위한 자동 생성 기능 활용
- **Jackson**: JSON 데이터 변환 및 처리



## 3. 프로젝트 기능




## 4. 프로젝트 구조

### **아키텍처**


### **ERD (Entity-Relationship Diagram)**


### **클래스 다이어그램**




## 5. 사용 방법

1. **Javadoc 확인**: `javadoc/index.html`에서 문서 확인
2. **데이터베이스 테이블 및 프로시저 생성**:  
   - `database/CreateTable.sql` 실행  
   - `database/CreateProcedure.sql` 실행  
3. **DB 연결 설정**:  
   - `/common/Ignore` 파일에서 본인 환경에 맞는 DB 연결 주소 및 패스워드 입력



## 6. 실행 중 발생할 수 있는 문제 및 해결 방법

| 문제 | 원인 | 해결 방법 |
|------|------|---------|
| **DB 연결 실패 (Connection Fail)** | 잘못된 DB 커넥션 설정, 유저 또는 비밀번호 오류 | DB 연결 정보 및 계정 정보 확인 |
| **테이블 또는 프로시저 미생성** | `CreateTable.sql` 또는 `CreateProcedure.sql` 실행되지 않음 | 해당 SQL 파일을 실행하여 테이블과 프로시저 생성 |
| **JDBC 드라이버 오류** | JDBC 드라이버가 설치되지 않음 | 프로젝트의 라이브러리 설정에서 JDBC 드라이버 활성화 확인 |



## 7. 팀원별 역할 분담


# Page 1

# [Team] 학사 관리 프로그램 

![img-0.jpeg](img-0.jpeg)

## 요약

- 학생 관리 프로그램 개발(입력,검색,정렬)


## 역할

- 프로젝트 전체 인원 : 5 명
- 나의 역할
- 기존 반복문(for,if) 기반 검색 대비 Stream API를 활용하여 학번 검색 속도 최적화
- Null 체크 및 예외처리를 추가하여 안정성 강화


## 기술스택

- 언어 : Java(Stream API, Lambda)
- 협업 및 버전관리 : GitHub

---

# Page 2

- 데이터 : Json

시기

- 프로젝트 진행 기간 (2025.02.10. 2025.02.17.)


# 검색 기능 및 로직 

- 전체 학생 검색

```
@\#기_자라조
```


## 주요 기능(전체 학생 검색)

```
//전체 학생 검색
@Override l usage zCmkl031
public Map<String, StudentOto> searchAll() \{
    if (studentID.getStudentTable() != null)
        return studentID.getStudentTable(); // * studentTable이 존재하면 그대로 반환
    else
        return Collections.emptyMap();
}
```

1. 기능 설명
- 전체 학생을 검색하는 기능
- 학생 정보가 없을 경우 emptyMap()을 반환하여 불필요한 예외 처리를 방지.

2. 구현 방법

- emptyMap 반환 이유
- Map이 아닌 데이터를 반환하게 된다면 OutPut 처리 로직 증가
- 전체 학생 Table이 null이 아니면 전체 학생 Table 반환

3. 장점

- OutPut 처리를 고려한 로직 설계

---

# Page 3

- 학번 검색

```
*6기_자라조
```


# 주요 기능(학번 검색) 

```
/studentNumber 기준검색 (학번검색)
Wverride l usage &Cmkl01)
public StudentStc searchByBne(String studentNumber) {
    return studentIG.getStudentTable().values().stream() Stream<StudentDto>
        .filter( StudentDto student ->
            student.getStudentNumber() != null &&
                student.getStudentNumber().equalsIgnoreCase(studentNumber))
        .findFirst() Optional<StudentDto>
        .orElse( other null);
```

1. 기능 설명
- 특정 학번 학생을 검색하는 기능
- 학생 정보가 없을 경우 orElse를 이용하여 null 반환

2. 구현 방법

- findFirst() 사용 이유
- 첫 번째 일치하는 값만 가져와 불필요한 연산 감소
- 학번은 고유값이므로 중복값이 존재하지않아 불필요한 리소스 낭비 방지
- orElse(null) 사용 이유
- 값이 없을 경우 예외 발생하지않도록 명확한 처리

3. 장점

- findFirst()를 사용하여 성능 최적화
- 고유 값 검색 최적화
- 안정적인 null 처리 : 학생 정보가 없을 경우 null 반환하여 예외 방지
- 최고점수/최저점수 검색 로직 및 기능

---

# Page 4

# 주요 기능(최고 점수 검색) 

![img-1.jpeg](img-1.jpeg)

## ㅊ 6기_차라조

## 주요 기능(최저 점수 검색)

![img-2.jpeg](img-2.jpeg)

## 1. 기능 설명

- 특정 과목에서 최고/최저 점수를 받은 학생을 검색하는 기능
- 동일한 최고/최저 점수를 가진 학생이 여러명일수 있어서 List<Student>로 반환

2. 구현 방법

- 함수형 인터페이스 사용

- 신규 과목 추가 시 유연하게 확장가능 하도록 함수형 인터페이스 사용

---

# Page 5

- 로직
- Stream API를 활용하여 최고/최저 점수 검색 $\rightarrow$ 해당 점수 가진 학생 List 반 환

3. 장점

- 유연한 확장성
- 재사용성
- 가독성 및 유지보수성
- 범위 검색

# 주요 기능(범위 검색) 

![img-3.jpeg](img-3.jpeg)

1. 기능 설명

- 특정 범위의 점수를 가진 학생을 검색하는 기능

2. 구현 방법

- 함수형 인터페이스 사용
- 신규 과목 추가 시 유연하게 확장가능 하도록 함수형 인터페이스 사용
- 로직
- Stream API를 활용하여 해당 점수 범위 학생 검색 $\rightarrow$ 해당 점수 가진 학생 List 반환

---

# Page 6

# 3. 장점 

- 유연한 확장성
- 재사용성
- 가독성 및 유지보수성
- 등급 검색


## 주요 기능(등급 검색)

```
//특정 등급 학생 검색
@Override 1 usage ㅊCmk1031
public List<Student0to> searchByGrade(String grade) {
    if (studentIO.getStudentTable() == null)
        return Collections.emptyList();
    return studentIO.getStudentTable().values().stream()
        .filter( Student0to student -> student.getGrade() != null
        && student.getGrade().equalsIgnoreCase(grade))
        .collect(Collectors.toList());
}
```

1. 기능 설명
- 특정 등급의 학생을 검색 하는 기능
- 동일한 등급을 가진 학생이 여러명일수 있어서 List<Student>로 반환

2. 구현 방법

- 찾는 등급을 입력하여 해당 등급과 일치하는 학생정보를 List<Student>로 반환
- Table이 비어있으면 emptyMap 반환
- OutPut Class에서 필요없는 로직 감소
- 로직
- 등급 입력 $\rightarrow$ 해당등급 학생 검색 $\rightarrow$ List<Student> 반환

3. 장점

---

# Page 7

- OutPut 처리를 고려한 로직 설계
- 예외처리
- 재시험 대상 검색

```
* 6기_자라조
```


# 주요 기능(재시험 대상 검색) 

```
//재시험 대상 학생 필터링
//6D점미하(F등급)
@Override 1 usage ㅊCmk1031
public List<StudentDto> searchByReTest() {
    return studentID.getStudentTable().values().stream()
        .filter( StudentDto student -> student.getGrade() != null &&
            student.getGrade().equalsIgnoreCase( anotherString: "F"))
                .collect(Collectors.toList());
}
```

1. 기능 설명
- 재시험 대상 학생을 검색 하는 기능
- 재시험 대상 학생이 여러명일수 있어서 List<Student>로 반환

2. 구현 방법

- F등급 학생을 검색하여 List<Student>로 반환
- 로직
- F등급 학생 검색 $\rightarrow$ List<Student> 반환

3. 장점

- OutPut 처리를 고려한 로직 설계
- 예외처리


## 어려운점 및 고민

| OutPut Class를 고려한 설계

---

# Page 8

# 혼자 설계하는 프로그램이 아니기 때문에 

내가 처리한 데이터를 다른 팀원이 어떻게 하면 받는게 편할지 고민하여 설계

## GitHub 충돌 및 미숙 문제

각각 브랜치를 나누어 작업 할 때 충돌 발생
$\rightarrow$ 해결 방안 : merge 시 충돌 병합하여 해결, merge 규칙 준수(리뷰어 3인이 상 동의시 merge)

## 코드 상세 보기

GitHub
https://github.com/dlwjdtjq001/managements_program

## Class Diagram 상세 보기

Draw.io
https://drive.google.com/file/d/1YF3HH-c4Hgz8ygrzWxhdYSHzkOfAwuP u/view?usp=sharing

---

# Page 9

# 발표 자료 보기 

## PowerPoint

https://1drv.ms/p/c/c78e7497a2994a77/EU2v9f7hStZAm6ymIKjdWqoBB 74PHOuQOFcZHzheCjRxAw

이전 페이지 돌아가기
