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
```
{
    "success": true,
    "data": {
    "tmax":{}
    "somethings":[{},{}]
    } 
} 

{
    "success": false,
    "error": {
    "code": "E1101",
    "message": "login fail"
    } 
}
```

## Error 처리

1. ErrorCode Enum 구현 필드는 status, code, message

2. RuntimeException 상속받는 Exception 클래스 구현

3. 필요하다면 ErrorResponse 내부 클래스로 추가 구현 -> code와 message를 바인딩 용도

4. ExceptionHandler에서 ResponseEntity로 묶어서 반환

## backend 서버에서 jwt 받는 방법

- 
    1. http://localhost:8080/ 에 접속한다.
    2. 카카오 간편 로그인을 클릭 후 로그인 한다.
    3. 콘솔에 찍히는 access token 을 복사한다.
    4. login api 를 사용한다.
  

- application.yml의 USER_PROFILE 을 dev로 설정한다.