/*
    INDEX
    - SQL 명령문의 처리 속도를 향상시키기 위해서 행들의 위치 정보를 가지고 있다.
    
    * 데이터 검색 방식
    1. TABLE FULL SCAN
        : 테이블 데이터를 처음부터 끝까지 검색하여 원하는 데이터를 찾는 방법
    2. INDEX SCAN : 인텍스를 통해 데이터를 찾는 방식
*/
-- ROWID : 데이터베이스에 저장되어 있는 데이터의 주소
SELECT ROWID, E.* FROM EMPLOYEE E;

-- 현재 계정 소유의 인덱스 정보를 열람하고 싶다면...
SELECT * FROM USER_INDEXES;
SELECT * FROM USER_IND_COLUMNS;

-- 인덱스가 걸린 컬럼으로 데이터 조회
-- 데이터가 얼마 없으면 오라클에서 굳이 인덱스를 사용해서 데이터를 조회 X
SELECT *
FROM EMPLOYEE
WHERE EMP_ID = 210;

/*
    INDEX 생성
    
    [표현식]
    CREATE INDEX 인덱스명
    ON 테이블명(컬럼, 컬럼, ...);
*/

-- STUDY 계정으로 접속
CREATE INDEX IDX_STUDENT_NAME
ON TB_STUDENT(STUDENT_NAME);

SELECT *
FROM TB_STUDENT
WHERE STUDENT_NAME = '한효종';

-- 결합 인덱스 생성

CREATE INDEX IDX_STUDENT_CLASS_NO
ON TB_GRADE(STUDENT_NO, CLASS_NO);

SELECT *
FROM TB_GRADE
WHERE STUDENT_NO = 'A357025' AND CLASS_NO = 'C4507100';

/*
    INDEX 삭제
    [표현식]
    DROP INDEX 인덱스명;
    
    - 인덱스가 항상 좋은 결과로 이어지지는 않음
    - 정확한 데이터 분석에 기반을 두지 않은 무분별한 인덱스는 오히려 더 안좋은 성능을 보임
*/
DROP INDEX IDX_STUDENT_NAME;
DROP INDEX IDX_STUDENT_CLASS_NO;

/*
    동의어(SYNONYM)
    - 데이터베이스 객체에 별칭을 생성
    
    [표현식]
    CREATE [PUBLIC] SYNONYM 동의어명
    FOR [사용자.][객체명];
*/

-- 1. 비공개 SYNONYM 생성
-- 관리자 계정으로 kh 계정에 권한 부여
GRANT CREATE SYNONYM TO KH;

-- EMPLOYEE 테이블에 비공개 SYNONYM 생성 (KH 계정에서) 
CREATE SYNONYM EMP01
FOR EMPLOYEE;

SELECT * FROM EMPLOYEE;
SELECT * FROM EMP01;

-- 2. 공개 SYNONYM 생성
-- 관리자 계정으로 접속하여 공개 SYNONYM 생성
CREATE PUBLIC SYNONYM DEPT FOR KH.DEPARTMENT;

-- 관리자 계정으로 다른 사용자 계정에서 테이블에 접근할 수 있는 권한 부여
GRANT SELECT ON KH.DEPARTMENT TO STUDY;

-- STUDY 계정으로 실행
SELECT * FROM KH.DEPARTMENT;
SELECT * FROM DEPT;

/*
    SYNONYM 삭제
    
    [표현식]
    DROP [PUBLIC] SYNONYM 동의어;
*/
-- KH에서 삭제
DROP SYNONYM EMP01;

-- 관리자 계정에서 삭제
DROP PUBLIC SYNONYM DEPT;