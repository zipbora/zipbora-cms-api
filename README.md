# zipbom-cms-api
집봄 프로젝트 cms spring boot repository
## Environments
- language : java
- framework : Spring boot
- DB : MariaDB
- data access : jpa
## Versions
- java : 1.8
- Spring : 2.6.1
- DB : 10.6.5

## swagger
- url:port/swagger-ui.html

## Response
- CMResponse 를 활용한다. C는 code M은 message 이고 data 까지 담을 수 있다.

## backend 서버에서 jwt 받는 방법
- 1. http://localhost:8080/ 에 접속한다.
    2. 카카오 간편 로그인을 클릭 후 로그인 한다.
    3. 콘솔에 찍히는 access token 을 복사한다.
    4. login api 를 사용한다.