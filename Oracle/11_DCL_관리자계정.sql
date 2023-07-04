/*
    DCL(Data Control Language) : 데이터 제어 언어
    - 계정에게 시스템 권한 또는 객체 접근 권한을 부여(GRANT)하거나 회수(REVOKE)하는 구문
*/

/*
    * 시스템 권한
    - DB에 접근하는 권한, 객체들을 생성/삭제할 수 있는 권한
    
    종류
    - CREATE SESSION : 데이터베이스에 접속할 수 있는 권한
    - CREATE TABLE : 테이블을 생성할 수 있는 권한
    - CREATE VIEW : 뷰를 생성할 수 있는 권한
    - CREATE SEQUENCE : 시퀀스를 생성할 수 있는 권한
    - DROP USER : 계정을 삭제할 수 있는 권한
    ...
    
    [표현법]
    GRANT 권한[, 권한, 권한, ...] TO 계정;
    REVOKE 권한[, 권한, 권한, ...] FROM 계정;
*/
-- 1. SAMPLE 계정 생성
CREATE USER SAMPLE IDENTIFIED BY SAMPLE;

-- 2. 계정에 접속을 위해 CREATE SESSION 권한 부여
GRANT CREATE SESSION TO SAMPLE;

-- 3. SAMPLE 계정에서 테이블을 생성할 수 있는 CREATE TABLE 권한 부여
GRANT CREATE TABLE TO SAMPLE;

-- 4. TABLESPACE(테이블, 뷰, 인덱스 등 객체들이 저장되는 공간)에 대한 권한 부여
GRANT UNLIMITED TABLESPACE TO SAMPLE;

/*
    * 객체 접근 권한
    - 특정 객체에 접근해서 조작할 수 있는 권한
    
    권한종류  특정객체
    SELECT  TABLE, VIEW, SEQUENCE
    INSERT  TABLE, VIEW
    UPDATE  TABLE, VIEW
    DELETE  TABLE, VIEW
    ALTER   TABLE, SEQUENCE
    ...
    [표현법]
    GRANT 권한[, 권한, 권한, ...] ON 객체 TO 계정;
    REVOKE 권한[, 권한, 권한, ...] ON 객체 FROM 계정;
*/

-- 5. KH.EMPLOYEE 테이블을 조회할 수 있는 권한 부여
GRANT SELECT ON KH.EMPLOYEE TO SAMPLE;

-- 6. KH.DEPARTMANT 테이블에 데이터를 삽입할 수 있는 권한 부여
GRANT INSERT ON KH.DEPARTMENT TO SAMPLE;

-- 7. KH.DEPARTMENT 테이블을 조회할 수 있는 권한 부여
GRANT SELECT ON KH.DEPARTMENT TO SAMPLE;

-- 8. KH.EMPLOYEE 테이블을 조회할 수 있는 권한 회수
REVOKE SELECT ON KH.EMPLOYEE FROM SAMPLE;

GRANT CONNECT, RESOURCE TO SAMPLE;

/*
    * 롤(ROLE)
    - 특정 권한들을 하나의 집합으로 모아놓은 것
    
    CONNECT : CREATE SESSION
    RESOURCE : CREATE TABLE, CREATE SEQUENCE, ...
*/
SELECT *
FROM ROLE_SYS_PRIVS
WHERE ROLE IN ('CONNECT', 'RESOURCE')
ORDER BY 1;

-- JDBC 관련 계정
CREATE USER jdbc IDENTIFIED BY 1234;
