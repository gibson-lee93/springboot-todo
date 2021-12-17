# Spring Boot Todo

스프링부트 프레임워크를 사용하여 유저 회원가입, 로그인과, 간단한 Todo CRUD REST API 프로젝트 입니다.

---

## 개발 환경

- 언어: JAVA
- 프레임워크: Spring Boot
- 데이터베이스: MySql
- 라이브러리: maven, jwt, model mapper

---

## ERD

![프로젝트 ERD](https://user-images.githubusercontent.com/57168321/146490853-91a1f581-3aae-4add-8e85-10de5501ba35.png)

---

## 구현 기능

### 회원가입

- 입력받은 비밀번호를 bcrypt로 암호화하여 저장했습니다.
- spring validation으로 입력 값의 유효성을 검사해 회원가입에서 발생가능한 오류를 줄였습니다.
- 유저 아이디 중복 체크를 통해 동일한 아이디로 가입을 하지 않도록 했습니다.

### 로그인, 로그인 인증

- 로그인 성공 시 유저 인증을 위한 JWT(Json Web Token)이 발급됩니다.

### Todo

- 모든 Todo 관련 API는 반드시 로그인 후 토큰을 발급 받아야 호출이 가능하도록 구현했습니다.
- 유저는 다수의 todo를 소유할 수 있어 1대다 관계로 설정했습니다.
- 생성, 이벤트 전체 조회, 이벤트 상세 조회, 업데이트 삭제 기능을 수행할 수 있습니다. (CRUD)
- Todo 생성시 유저의 정보가 같이 저장됩니다.
- Todo 상세 조회 또는 전체 조회시 유저가 소유한 todo만 조회 가능합니다.
- Todo 수정 또는 삭제시 유저가 소유한 todo만 수정 및 삭제 할 수 있습니다.

---

## API 문서

<!-- TODO -->

API를 로컬에서 테스트를 위한 방법을 [POSTMAN document](https://documenter.getpostman.com/view/14991464/UVR8q8fq)에서 확인하실 수 있습니다.

---

## 로컬 실행 방법

1. ./src/main/resources/application.properties 파일에 `app.jwt-secret`에 임의의 문자열을 작성해 저장합니다.
2. ./src/main/resources/application.properties 파일에 `spring.datasource.username`에 로컬 MySql 데이터베이스의 usernamed을 작성해 저장합니다.
3. ./src/main/resources/application.properties 파일에 `spring.datasource.password`에 로컬 MySql 데이터베이스의 password을 작성해 저장합니다.
4. 로컬 MySql 데이터베이스에 'todos'라는 새로운 database를 생성합니다.
5. 로컬에서 서버를 실행시킵니다.
6. POST `localhost:8080/auth/signup`에서 `username`, `password`를 입력해 유저를 생성합니다.
7. POST `localhost:8080/auth/signin`에 `username`, `password`을 입력하신 후 결과값으로 accessToken을 발급받습니다.
8. Todo API는 권한이 필요하기때문에 API의 주소를 입력한 후, Headers 의 Authorization에 accessToken을 붙여넣어 권한을 얻은 후 API를 호출합니다.
