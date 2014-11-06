archetype project
==================

* 구현기능

1. spring MVC를 기반
2. spring AOP를 이용한 Parameter Validation 구현
3. logback을 이용한 log 구현
4. logback의 기능을 이용해서 특정 정보의 사용자 log level을 조정(default: info -> debug)
5. DB연결은 MyBatis, CUBRID를 이용할 수 있도록 설정
6. Redis를 이용한 조회 메서드 Cache 기능
7. 기본적인 CRUD 화면
8. REST API 구현(반환형은 JSON)
9. Mockito, JUnit을 이용한 TDD구현
10. jquery를 이용한 화면 구성
11. Spring MVC의 다국어 지원 기능 추가


* Sample을 돌려보기 위한 준비사항

1. JDK 7.0 이상
2. Tomcat 7 이상
3. Oracle(10g이상), Cubrid(8.4.4) 중 한 가지 DBMS는 local에 설치되어 있어야 함
4. Redis