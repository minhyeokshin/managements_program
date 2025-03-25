# 👥 학생·직원 통합 관리 CLI 프로그램

## 📌 프로젝트 소개
Java 기반 CLI 프로젝트로, 학사관리 시스템과 직원관리 시스템을 하나의 구조로 통합한 프로그램입니다.  
입력 검증, 계층 분리, 유틸 공통화 등을 통해 유지보수성과 확장성을 고려한 구조 설계를 목표로 했습니다.

---

## 📚 목차
1. [프로젝트 소개](#📌-프로젝트-소개)
2. [사용 기술 스택 및 라이브러리](#🛠-기술-스택)
3. [프로젝트 기능](#✅-주요-기능)
4. [프로젝트 구조](#📁-프로젝트-구조)
5. [사용 방법](#▶-실행-방법)
6. [실행 중 발생할 수 있는 문제 및 해결 방법](#⚠️-실행-중-문제-및-해결-방법)
7. [기타 링크](#🔗-기타-링크)

---

## 🛠 기술 스택

### **기본 기술**
- Language: Java (JDK 17)
- DB: MySQL 8
- 연결: JDBC
- 구조: MVC 계층 분리
- 설정: DI 구조 (`DIConfig.java`)

### **외부 라이브러리**
- **Lombok**: Getter/Setter 자동 생성
- **Jackson**: JSON 데이터 직렬화/역직렬화

---

## ✅ 주요 기능

### 👨‍🎓 학사 관리
- 학생 등록 / 수정 / 삭제 / 검색
- 과목, 점수 기반 통계 기능
- Stream, Lambda 활용 필터링 로직

### 🧑‍💼 직원 관리
- 직원 등록 / 수정 / 삭제 / 조회
- 부서별 구분 및 출력
- 유효성 검증 기반 흐름 제어

---

## 📁 프로젝트 구조

src/
├── Main.java                 👉 프로그램 진입점
├── DIConfig.java            👉 의존성 주입 설정 클래스
├── common/                  👉 공통 유틸 (검증, 출력 등)
├── employee/                👉 직원관리 도메인 (Controller, Service 등)
├── student/                 👉 학사관리 도메인
├── databases/, exception/, object/ 등 기타

---

## ▶ 실행 방법

1. **Javadoc 확인**  
   → `/javaDoc/index.html`에서 문서 확인

2. **데이터베이스 테이블 및 프로시저 생성**  
   - `database/CreateTable.sql` 실행  
   - `database/CreateProcedure.sql` 실행  

3. **DB 연결 설정**  
   - `/common/Ignore` 파일에서 DB 주소 및 비밀번호 등 환경 설정

4. **실행**  
   - IDE에서 `Main.java` 실행  
   - CLI 메뉴 선택 후 학사/직원 관리 기능 사용

---

## ⚠️ 실행 중 문제 및 해결 방법

| 문제 | 원인 | 해결 방법 |
|------|------|------------|
| DB 연결 실패 | 잘못된 커넥션 설정, 비밀번호 오류 | `/common/Ignore`에서 DB 정보 확인 |
| 테이블 또는 프로시저 미생성 | SQL 파일 실행 누락 | `CreateTable.sql`, `CreateProcedure.sql` 실행 |
| JDBC 오류 | 드라이버 미설치 | 라이브러리 설정에서 JDBC 활성화 확인 |

---


## 🔗 기타 링크
- 🔗 [학생관리 Notion 포트폴리오 보기](https://scientific-monkey-6c4.notion.site/Team-1842e3ff653581e999c2e73333a6cddf?pvs=74)
- 🔗 [직원관리 Notion 포트폴리오 보기](https://scientific-monkey-6c4.notion.site/Team-1842e3ff6535812d9971d4616b64945c?pvs=74)
- 🔗 [GitHub Repository](https://github.com/dlwjdtjq001/managements_program)
---

## 💡 설계 포인트 정리
- `ValidCheck.java`로 사전 검증 → try-catch 최소화
- `DIConfig.java`로 의존성 관리 → 확장성 & 유지보수성 확보
- 공통 로직 → `common/`에 통합하여 재사용성 극대화
