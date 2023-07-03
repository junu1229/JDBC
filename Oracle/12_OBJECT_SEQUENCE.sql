/*
    시퀀스(SEQUENCE)
    - 자동으로 번호 발생시켜주는 역활을 하는 객체
    - 정수값을 순차적으로 일정값씩 증가시키면서 생성해줌
    
    EX) 회원번호, 사원번호, 게시글번호, ...
    
*/

/*
    1. 시퀀스 객체 생성
    
    [표현법]
    CREATE SEQUENCE 시퀀스명
    [START WITH 시작숫자]     --> 처음 발생시킬 시작값 지정(기본값 1)
    [INCREMENT BY 숫자]     --> 몇씩 증가시킬 건지 (기본값 1)
    [MAXVALUE 숫자]          --> 최대값 지정 (기본값이 무진장 큼)
    [MINVALUE 숫자]          --> 최소값 지정 (기본값 1)
    [CYCLE|NOCYCLE]         --> 값 순환 여부 지정 (기본값 NOCYCLE)
    [NOCACHE|CACHE 바이트크기] --> 캐시메모리 할당 (기본값 CACHE 20)
    
    * 캐시메모리
     - 미리 발생된 값들을 생성해서 저장해두는 공간
     - 매번 호출될 때마다 새로이 번호를 생성하는게 아니라 캐시메모리 공간에 미리 생성된 값들을 가져다 쓸 수 있음 (속도가 빨라짐)
     - 접속이 해제되면 => 캐시메모리에 미리 만들어 둔 번호들은 다 날라감
    
    테이블명: TB_
    뷰명 : VW_
    시퀀스명 : SEQ_
    트리거명 : TRG_
    
*/

-- EMPLOYEE 테이블의 PK 값을 생성할 시퀀스 생성
CREATE SEQUENCE SEQ_EMPID
START WITH 300
INCREMENT BY 5
MAXVALUE 310
NOCYCLE
NOCACHE;

-- 현재 계정이 소유하고 있는 시퀀스들의 구조를 보고자 할 때
SELECT * FROM USER_SEQUENCES;

/*
    2. 시퀀스 사용
    
    시퀀스명.CURRVAL : 현재 시퀀스의 값
    시퀀스명.NEXTVAL : 시퀀스값에 일정값을 증가시켜서 발생된 값
                    - 현재 시퀀스 값에서 INCREMENT BY 값 만큼 증가된 값
                    - 시퀀스명.CURRVAL + INCREAMENT BY 값
*/
-- NEXTVAL를 한 번이라도 수행하지 않은 이상 CURRVAL를 가져올 수 없음!
-- WHY? CURRVAL은 마지막으로 성공적으로 수행된 NEXTVAL 의 값을 저장해서 보여주는 임시값

SELECT SEQ_EMPID.CURRVAL FROM DUAL;

SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 300
SELECT SEQ_EMPID.CURRVAL FROM DUAL; -- 300

SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 305
SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 310

SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- ERROR 발생
SELECT SEQ_EMPID.CURRVAL FROM DUAL; -- 310

/*
    3. 시퀀스 구조 변경
    
    ALTER SEQUENCE 시퀀스명
    [INCREMENT BY 숫자]     --> 몇씩 증가시킬 건지 (기본값 1)
    [MAXVALUE 숫자]          --> 최대값 지정 (기본값이 무진장 큼)
    [MINVALUE 숫자]          --> 최소값 지정 (기본값 1)
    [CYCLE|NOCYCLE]         --> 값 순환 여부 지정 (기본값 NOCYCLE)
    [NOCACHE|CACHE 바이트크기] --> 캐시메모리 할당 (기본값 CACHE 20)
    
    * START WITH은 변경불가!
*/
ALTER SEQUENCE SEQ_EMPID
INCREMENT BY 10
MAXVALUE 400;
SELECT SEQ_EMPID.NEXTVAL FROM DUAL; -- 320

/*
    4. 시퀀스 삭제
*/
DROP SEQUENCE SEQ_EMPID;

SELECT * FROM USER_SEQUENCES;

/*
    5. SEQUENCE 예시
*/

-- 1) 매번 새로운 사번이 발생되는 SEQUENCE 생성
CREATE SEQUENCE SEQ_EMPID
START WITH 300;

-- 2) 매번 새로운 사번이 발생되는 SEQUENCE 사용해서 INSERT 구문 작성
INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO)
VALUES(SEQ_EMPID.NEXTVAL, '김강우', '221227-3123456');

INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO)
VALUES(SEQ_EMPID.NEXTVAL, '고아라', '230629-4987654');

SELECT * FROM EMPLOYEE ORDER BY EMP_ID DESC;

CREATE TABLE EMP(
    EMP_ID NUMBER PRIMARY KEY,
    EMP_NAME VARCHAR(30) NOT NULL,
    DEPT_TITLE VARCHAR(30) DEFAULT '개발팀',
    HIRE_DATE DATE DEFAULT SYSDATE
);
SELECT * FROM PERSON;
DROP TABLE PERSON;
CREATE TABLE PERSON(
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(20),
    ADDRESS VARCHAR2(50)
);
DROP SEQUENCE SEQ_PERSON;

CREATE SEQUENCE SEQ_PERSON;
UPDATE person SET address = '제주도' WHERE id = 1;
SELECT * FROM person WHERE id = 1;