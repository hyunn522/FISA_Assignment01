# 🔍 N잡러 직업 추천 시스템
성향 기반 직업 추천 콘솔 애플리케이션 (Java, MVC 구조)
사용자의 응답 데이터를 바탕으로 점수를 산정하고, 가장 적합한 직업을 추천.

## 🧠 프로젝트 개요
프로젝트 명: N잡러 직업 추천 시스템

기술 스택: Java (JDK 11 이상), Maven, JDBC, MySQL

아키텍처 패턴: MVC (Model-View-Controller)

구동 환경: CLI 기반 콘솔 애플리케이션

## 👤 팀원 소개
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/dlacowns21"><img src="https://github.com/dlacowns21.png" width="100px;" alt=""/><br /><sub><b>@dlacowns21</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/songhajang"><img src="https://github.com/songhajang.png" width="100px;" alt=""/><br /><sub><b>@songhajang</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/Jsumin07"><img src="https://github.com/Jsumin07.png" width="100px;" alt=""/><br /><sub><b>@Jsumin07</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/hyunn522"><img src="https://github.com/hyunn522.png" width="100px;" alt=""/><br /><sub><b>@hyunn522</b></sub></a><br /></td>
  </tbody>
</table>
<br>

## 📌 개발 배경 및 목적
현대 사회에서 "N잡러"라는 개념 **자아 실현과 경제적 다각화를 동시에 추구하는 새로운 직업관**. 


본 프로젝트는 **개인의 성향을 기반으로 직업을 추천**함으로써 스스로에 대한 이해도를 높이고, 이직/부업 등을 고려하는 사용자에게 구체적인 제안을 제공하는 것이 목표.

---

## 🏗 아키텍처 및 구조 설계
### 1. MVC 패턴 설계 이유
| 계층         | 설명             | 책임               |
| ---------- | -------------- | ---------------- |
| Model      | DB 및 도메인 모델 처리 | DB 연결, 데이터 가공    |
| View       | 사용자 인터페이스 처리   | 질문 출력, 결과 표시     |
| Controller | 흐름 제어          | 사용자 응답 수집, 로직 분기 |


➡ 구조 분리를 통해 기능 확장 및 유지보수성 향상.

## 클래스 구성 및 역할
### 🔹 Model 계층
Database.java: JDBC를 이용해 MySQL 연결 수행 (DB 연결 관리)

Model.java: 점수 산정 로직 및 추천 알고리즘 핵심 처리

domain/: 도메인 클래스 (Job, Person, Question, Answer)
→ Java Beans 형태로 구성, getter/setter 및 생성자 포함

### 🔹 View 계층
StartView.java: 인트로 메시지 출력

QuizView.java: 질문과 응답 수집

FinishView.java: 최종 직업 추천 결과 출력

### 🔹 Controller 계층
Controller.java: 메인 실행 클래스
→ 각 View와 Model을 조립하여 실행 흐름을 제어



## 🧮 핵심 로직: 직업 추천 방식
### 1. 질문 수집

사용자에게 질문 8개를 순차적으로 제시

### 2.응답 처리

각 질문마다 **네/아니오** 에 따라 점수 가중치를 다르게 적용

선택한 응답은 Answer 객체로 저장됨

### 3.직업 매칭

Model.java에서 각 직업별로 사전 정의된 성향 점수와 비교

가장 유사한 점수를 가지는 직업 리스트 반환



## 💡 설계적 고려사항
✅ 도메인 주도 설계(Domain-Driven Design)
: Question, Job, Person, Answer 등을 독립된 클래스로 분리하여 각 역할을 명확히 함

✅ 데이터베이스 의존성 최소화
: JDBC 설정은 Database.java에 집중시켜 실제 추천 로직과 DB 분리

✅ 유지보수를 고려한 구조화
: 기능 확장이 쉬운 구조 (ex: 질문 추가, 직업 카테고리 세분화)

## 🧪 테스트 및 검증
샘플 사용자 3인에 대해 테스트 수행

질문 응답 패턴에 따라 적절히 직업이 달라지는 것을 확인

---

## ⚙ 실행 방법
1. 실행
``` bash
.
```

2. DB 연결 정보 수정. 
src/Model/Database.java 파일에서 사용자 환경에 맞는 DB 정보 입력:

``` java
.
```

## 📂 파일 구조 요약
```css
src/
├── Model/
│   ├── domain/
│   │   ├── Answer.java
│   │   ├── Job.java
│   │   └── ...
│   ├── Database.java
│   └── Model.java
├── View/
│   ├── StartView.java
│   └── ...
├── controller/
│   └── Controller.java
```

## ✍️ 향후 개선 방향
GUI 버전으로 확장 (JavaFX 등)

성향 분석 방식에 AI 요소 도입 (간단한 룰베이스 → ML 모델)

사용자 결과 DB 저장 및 통계 시각화
