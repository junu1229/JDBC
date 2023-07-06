/*
    PL/SQL (PROCEDURE LANGUAGE EXTENSION TO SQL)
    - 오라클에서 제공하는 절차적인 프로그래밍 언어
    - SQL 문자 내에서 변수의 정의, 조건처리(IF), 반복처리(LOOP, FOR, WHILE) 등을 지원하여 SQL의 단점을 보완
    - 다수의 SQL문을 한번에 실행 가능 (BLOCK 구조)
    
    * 블록(BLOCK) : 명령어를 모아 둔 PL/SQL 프로그램의 기본 단위
    
    * PL/SQL 구조
    - [선언부(DECLARE SECTION)] : DECLARE로 시작, 변수나 상수를 선언 및 초기화하는 부분
    - 실행부 (EXECUTABLE SECTION) : BEGIN으로 시작, SQL문 또는 제어문(조건문, 반복문) 등의 로직을 기술하는 부분
    - [예외처리부(EXCEPTION SECTION)] : EXCEPTION으로 시작, 예외 발생시 해결하기 위한 구문을 미리 기술해둘 수 있는 부분
*/
-- 출력 기능 활성화
SET SERVEROUTPUT ON;

-- HELLO ORACLE 출력
BEGIN 
    DBMS_OUTPUT.PUT_LINE('HELLO ORACLE');
END;
/

/*
    1. DECLARE 선언부
    - 변수 및 상수를 선언하는 공간 (선언과 동시에 초기화도 가능)
    - 일반 타입 변수, 레퍼런스 타입 변수, ROW 타입 변수
*/
/*
    1_1) 일반 타입 변수 선언 및 초기화
        [표현식]
        변수명 [CONSTANT] 자료형 [:=값];
*/
DECLARE 
    EID NUMBER;
    ENAME VARCHAR2(20);
    PI CONSTANT NUMBER :=3.14;
BEGIN
    EID := &번호;
    ENAME := '&이름';
    
    DBMS_OUTPUT.PUT_LINE('EID : ' || EID);
    DBMS_OUTPUT.PUT_LINE('ENAME : ' || ENAME);
    DBMS_OUTPUT.PUT_LINE('PI : ' || PI);
END;
/

/*
    1_2) 래퍼런스 타입 변수 선언 및 초기화
        (어떤 테이블의 어떤 컬럼의 데이터 타입을 참조해서 그 타입으로 지정)
        
        [표현식]
        변수명 테이블명.컬럼명%TYPE;
*/

-- 박나라 사원의 사번, 직원명, 급여 정보를 조회해서 출력 
DECLARE
    EID EMPLOYEE.EMP_ID%TYPE;
    ENAME EMPLOYEE.EMP_NAME%TYPE;
    SAL EMPLOYEE.SALARY%TYPE;
BEGIN
    SELECT EMP_ID, EMP_NAME, SALARY
    INTO EID, ENAME, SAL
    FROM EMPLOYEE
    WHERE EMP_NAME = '&이름';
    
    DBMS_OUTPUT.PUT_LINE('EID : ' || EID);
    DBMS_OUTPUT.PUT_LINE('ENAME : ' || ENAME);
    DBMS_OUTPUT.PUT_LINE('SAL : ' || SAL);

END;
/

-- 문제로 풀어보기!
-- 레퍼런스 타입의 변수로 EID, ENAME, JCODE, DTITLE, SAL 을 선언하고 각 변수의 자료형은 EMPLOYEE 테이블의 EMP_ID, EMP_NAME, JOB_CODE, SALARY컬럼과
-- DEPARTMENT 테이블의 DEPT_TITLE 컬럼의 자료형을 참조 사용자가 입력한 사번과 일치하는 사원을 조회한 후 조회 결과를 출력
DECLARE
    EID EMPLOYEE.EMP_ID%TYPE;
    ENAME EMPLOYEE.EMP_NAME%TYPE;
    JCODE EMPLOYEE.JOB_CODE%TYPE;
    DTITLE DEPARTMENT.DEPT_TITLE%TYPE;
    SAL EMPLOYEE.SALARY%TYPE;
BEGIN
    SELECT EMP_ID, EMP_NAME, JOB_CODE, DEPT_TITLE, SALARY
    INTO EID, ENAME, JCODE, DTITLE, SAL
    FROM EMPLOYEE
    JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)
    WHERE EMP_ID = '&사번';
    
    DBMS_OUTPUT.PUT_LINE('EID : ' || EID);
    DBMS_OUTPUT.PUT_LINE('ENAME : ' || ENAME);
    DBMS_OUTPUT.PUT_LINE('JCODE : ' || JCODE);
    DBMS_OUTPUT.PUT_LINE('DTITLE : ' || DTITLE);
    DBMS_OUTPUT.PUT_LINE('SAL : ' || SAL);

END;
/

-- 3) ROW 타입 변수 선언 및 초기화
DECLARE
    EMP EMPLOYEE%ROWTYPE;
BEGIN
    SELECT*
    INTO EMP
    FROM EMPLOYEE
    WHERE EMP_NAME = '&직원명';
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || EMP.EMP_ID);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || EMP.EMP_NAME);
    DBMS_OUTPUT.PUT_LINE('입사일 : ' || TO_CHAR(EMP.HIRE_DATE, 'YYYY"년" MM"월" DD"일"'));
END;
/

/*
    2. 실행부
        제어문, 반복문, 쿼리문 등의 로직을 기술하는 영역
        
    1) 선택문
    1-1) IF 구문
    
    [표현식]
    IF 조건식 THEN 실행내용 END IF;
*/

-- 사번을 입력 받은 후 해당 사원의 사번, 이름, 급여, 보너스를 출력
-- 단, 보너스를 받지 않는 사원은 보너스 출력전에 '보너스를 지급받지 않는 사원입니다.'라는 문구를 출력

DECLARE
    EID EMPLOYEE.EMP_ID%TYPE;
    ENAME EMPLOYEE.EMP_NAME%TYPE;
    SAL EMPLOYEE.SALARY%TYPE;
    BONUS EMPLOYEE.BONUS%TYPE;
BEGIN
    SELECT EMP_ID, EMP_NAME, SALARY, NVL(BONUS, 0)
    INTO EID, ENAME, SAL, BONUS
    FROM EMPLOYEE
    WHERE EMP_ID = '&사번';
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || EID);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || ENAME);
    DBMS_OUTPUT.PUT_LINE('급여 : ' || SAL);
    
    IF(BONUS = 0) THEN
        DBMS_OUTPUT.PUT_LINE('보너스를 지급받지 않는 사원입니다.');
    END IF;
    DBMS_OUTPUT.PUT_LINE('보너스 : ' || BONUS);
    

END;
/

/*
    1-2) IF ~ ELSE 구문
    
    [표현식]
    IF 조건식 THEN 실행내용
    ELSE 실행내용
    END IF;
*/

DECLARE
    EID EMPLOYEE.EMP_ID%TYPE;
    ENAME EMPLOYEE.EMP_NAME%TYPE;
    SAL EMPLOYEE.SALARY%TYPE;
    BONUS EMPLOYEE.BONUS%TYPE;
BEGIN
    SELECT EMP_ID, EMP_NAME, SALARY, NVL(BONUS, 0)
    INTO EID, ENAME, SAL, BONUS
    FROM EMPLOYEE
    WHERE EMP_ID = '&사번';
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || EID);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || ENAME);
    DBMS_OUTPUT.PUT_LINE('급여 : ' || SAL);
    
    IF(BONUS = 0) THEN
        DBMS_OUTPUT.PUT_LINE('보너스를 지급받지 않는 사원입니다.');
    ELSE DBMS_OUTPUT.PUT_LINE('보너스 : ' || BONUS);
    END IF;
    
    

END;
/

SET SERVEROUTPUT ON;

-- 사용자가 입력한 사원의 사번(EID), 이름(ENAME), 부서명(DTITLE), 근무국가코드(NCODE) 조회 후 각 변수에 대입
-- 일반타입 변수 TEAM을 데이터 타입 VARCHAR2(10)으로 선언하고 NCODE 값이 KO일경우 => TEAM에 '국내팀' 대입,
-- 그게 아닐경우 => TEAM에 '해외팀' 대입 사번, 이름, 부서, 소속에 대해 출력
DECLARE
    EID EMPLOYEE.EMP_ID%TYPE;
    ENAME EMPLOYEE.EMP_NAME%TYPE;
    DTITLE DEPARTMENT.DEPT_TITLE%TYPE;
    NCODE LOCATION.NATIONAL_CODE%TYPE;
    TEAM VARCHAR2(10);
BEGIN
    SELECT EMP_ID, EMP_NAME, DEPT_TITLE, NATIONAL_CODE
    INTO EID, ENAME, DTITLE, NCODE
    FROM EMPLOYEE
    JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    JOIN LOCATION ON (LOCAL_CODE = LOCATION_ID)
    WHERE EMP_ID = '&사번';
    
    IF(NCODE ='KO') THEN 
        TEAM := '국내팀'; 
    ELSE
        TEAM := '해외팀';
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || EID);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || ENAME);
    DBMS_OUTPUT.PUT_LINE('부서 : ' || DTITLE);
    DBMS_OUTPUT.PUT_LINE('소속 : ' || TEAM);

END;
/

/*
    1-3) IF ~ ELSEIF ~ ELSE 구문
    
    [표현식]
    IF 조건식1 THEN 실행내용1
    ELSIF 조건식2 THEN 실행내용2
    ...
    [ELSE 실행내용N]
    END IF;
*/

-- 사용자에게 점수를 입력받아 SCORE 변수에 저장한 후 학점은 입력된 점수에 따라 GRADE 변수에 저장
-- 90점 이상은 'A', 80점 이상은 'B', 70점 이상은 'C', 60점 이상은 'D', 60점 미만은 'F'
-- 출력 '당신의 점수는 **이고, 학점은 *입니다.'


DECLARE
    SCORE NUMBER;
    GRADE CHAR(1);
    
BEGIN
   
    SCORE := '&점수';
    IF (SCORE >= 90) THEN GRADE := 'A';
    ELSIF (SCORE >= 80) THEN GRADE := 'B';
    ELSIF (SCORE >= 70) THEN GRADE := 'C';
    ELSIF (SCORE >= 60) THEN GRADE := 'D';
    ELSE GRADE := 'F';
    END IF;

    DBMS_OUTPUT.PUT_LINE('당신의 점수는 ' || SCORE || '이고, 학점은 '|| GRADE || '입니다.');
    
END;
/

-- 사용자에게 입력받은 사원과 일치하는 사원의 급여 조회 후 출력
-- (조회한 급여는 SAL변수에 대입)
-- 500만원 이상이면 '고급', 300만원 이상이면 '중급', 300만원 미만이면 '초급'
-- 출력은 '해당 사원의 급여 등급은 고급입니다.' 와 같이 출력

DECLARE
    SAL EMPLOYEE.SALARY%TYPE;
    GRADE VARCHAR(10);
BEGIN
    SELECT SALARY
    INTO SAL
    FROM EMPLOYEE
    WHERE EMP_ID = '&사번';
    
    IF(SAL >= 5000000) THEN GRADE := '고급';
    ELSIF(SAL >= 3000000) THEN GRADE := '중급';
    ELSE GRADE := '초급';
    END IF;
    
    DBMS_OUTPUT.PUT_LINE('해당 사원의 급여 등급은 ' || GRADE || '입니다.');
END;
/

DECLARE
    SAL EMPLOYEE.SALARY%TYPE;
    GRADE VARCHAR2(10);
BEGIN
    SELECT SALARY
    INTO SAL
    FROM EMPLOYEE
    WHERE EMP_ID = '&사번';
    
    SELECT SAL_LEVEL
    INTO GRADE
    FROM SAL_GRADE
    WHERE SAL BETWEEN MIN_SAL AND MAX_SAL;
    DBMS_OUTPUT.PUT_LINE('해당 사원의 급여 등급은 ' || GRADE || '입니다.');
END;
/
/*
    1-4) CASE 구문
    
    [표현식]
    CASE 비교대상자
        WHEN 비교값1 THEN 결과값1
        WHEN 비교값2 THEN 결과값2
        ELSE 결과값N
    END;
*/

-- 사번을 입력받은 후 사원의 모든 컬럼 데이터를 EMP에 대입하고 DEPT_CODE에 따라서 알맞은 부서를 출력
-- D1인 경우 인사관리부, D2인 경우 회계관리부, D3인 경우 마케팅부, D4인 경우 국내영업부, D5인 경우 해외영업 1부, D6인 경우 해외영업 2부, D7인 경우 해외영업3부
-- D8인 경우 기술지원부, D9인 경우 총무부 나머지는 부서없음
-- 출력은 사번, 이름, 부서코드, 부서가 출력 되도록!

DECLARE
    EMP EMPLOYEE%ROWTYPE;
    DT DEPARTMENT%ROWTYPE;
    
BEGIN
    SELECT *
    INTO EMP
    FROM EMPLOYEE
    WHERE EMP_ID = '&사번';
    
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || EMP.EMP_ID);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || EMP.EMP_NAME);
    DBMS_OUTPUT.PUT_LINE('부서코드 : ' || EMP.DEPT_CODE);
END;
/



DECLARE
    EMP EMPLOYEE%ROWTYPE;
    DNAME VARCHAR2(30);
    
BEGIN
    SELECT *
    INTO EMP
    FROM EMPLOYEE
    WHERE EMP_ID = '&사번';
    
    DNAME := CASE EMP.DEPT_CODE
                WHEN 'D1' THEN '인사관리부'
                WHEN 'D2' THEN '회계관리부'
                WHEN 'D3' THEN '마케팅부'
                WHEN 'D4' THEN '국내영업부'
                WHEN 'D5' THEN '해외영업1부'
                WHEN 'D6' THEN '해외영업2부'
                WHEN 'D7' THEN '해외영업3부'
                WHEN 'D8' THEN '기술지원부'
                WHEN 'D9' THEN '총무부'
                ELSE '부서없음'
            END;
    DBMS_OUTPUT.PUT_LINE('사번 : ' || EMP.EMP_ID);
    DBMS_OUTPUT.PUT_LINE('이름 : ' || EMP.EMP_NAME);
    DBMS_OUTPUT.PUT_LINE('부서코드 : ' || EMP.DEPT_CODE);
    DBMS_OUTPUT.PUT_LINE('부서코드 : ' || DNAME);
    
END;
/
    
/*
    2. 반복문
    
    2-1) BASIC LOOP
    
    [표현식]
    LOOP
        반복적으로 실행할 구문;
        
        * 반복문을 빠져나갈 수 있는 구문
    END LOOP;
    
    * 반복문을 빠져나갈 수 있는 구문 (2가지)
    1) IF 조건식 THEN EXIT; END IF;
    2) EXIT WHEN 조건식;
*/

-- 1~5까지 순차적으로 1씩 증가하는 값을 출력
DECLARE
    NUM NUMBER :=1;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE(NUM);
        NUM := NUM+1;
        IF NUM > 5 THEN EXIT;END IF;
    END LOOP;
END;
/

DECLARE
    NUM NUMBER :=1;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE(NUM);
        NUM := NUM+1;
        EXIT WHEN NUM > 5;
    END LOOP;
END;
/

/*
    2-2) FOR LOOP
    
    [표현식]
    FOR 변수 IN 초기값..최종값
    LOOP
        반복적으로 실행할 구문;
    END LOOP;
*/

-- 1 ~ 5까지 순차적으로 1씩 증가하는 값 출력
DECLARE
    NUM NUMBER:=1;
BEGIN
    FOR NUM IN 1..5
    LOOP 
        DBMS_OUTPUT.PUT_LINE(NUM);
    END LOOP;
END;
/

-- 역순으로 출력
DECLARE
    NUM NUMBER:=1;
BEGIN
    FOR NUM IN REVERSE 1..5
    LOOP 
        DBMS_OUTPUT.PUT_LINE(NUM);
    END LOOP;
END;
/

-- 구구단 (2~9단) 출력 (단, 짝수단만 출력)

BEGIN
    FOR NUM1 IN 2..9
    LOOP
        IF (MOD(NUM1,2)=0) THEN
            FOR NUM2 IN 1..9
                LOOP 
                    DBMS_OUTPUT.PUT_LINE(NUM1 || ' * ' || NUM2 || ' = ' || NUM1*NUM2);
                END LOOP;
                DBMS_OUTPUT.PUT_LINE('');

        END IF;
    END LOOP;
END;
/

DROP TABLE TEST;

CREATE TABLE TEST(
    NUM NUMBER,
    CREATE_DATE DATE
);

-- TEST 테이블에 10개의 행을 INSERT 하는 PL/SQL 작성

BEGIN
    FOR NUM IN 1..10
    LOOP
        INSERT INTO TEST VALUES(NUM, SYSDATE);
    END LOOP;
END;
/

SELECT * FROM TEST;

/*
    2-3) WHILE LOOP
    
    [표현식]
    WHILE 반복문이수행될 조건
    LOOP
        반복적으로 실행할 구문;
    END LOOP;
*/

-- 1~5까지 순차적으로 1씩 증가하는 값을 출력
DECLARE
    NUM NUMBER :=1;
BEGIN
    WHILE NUM <=5
    LOOP
        DBMS_OUTPUT.PUT_LINE(NUM);
        NUM := NUM + 1;
    END LOOP;
END;
/

DECLARE
    DAN NUMBER := 2;
    SU NUMBER;
BEGIN
    WHILE DAN <=9
    LOOP
        SU :=1;
        WHILE SU <=9
        LOOP
            
            DBMS_OUTPUT.PUT_LINE(DAN || ' * ' || SU || ' = ' || DAN*SU);
            SU := SU + 1;
        END LOOP;
        DBMS_OUTPUT.PUT_LINE('');

        DAN := DAN +1;
    END LOOP;
END;
/


/*
    3. 예외처리부
    예외(EXCEPTION) : 실행 중 발생하는 오류
    
    [표현식]
    EXCEPTION
        WHEN 예외명1 THEN 예외처리구문1;
        WHEN 예외명2 THEN 예외처리구문2;
        ...
        WHEN OTHERS THEN 예외처리구문N;
        
    * 오라클에서 미리 정의되어 있는 시스템 예외
    - NO_DATA_FOUND : SELECT한 결과가 한 행도 없을 경우
    - TOO_MANY_ROWS : SELECT한 결과가 한 행이 리턴되어야 하는데 여러행일 경우
    - ZERO_DIVIDE : 숫자를 0으로 나눌 때
    - DUP_VAL_ON_INDEX : UNIQUE 제약조건에 위배되었을 경우
*/
-- 사용자가 입력한 수로 나눗셈 연산한 결과 출력
BEGIN
    DBMS_OUTPUT.PUT_LINE(10/&숫자);
EXCEPTION 
    WHEN ZERO_DIVIDE THEN DBMS_OUTPUT.PUT_LINE('나누기 연산 시 0으로 나눌 수 없습니다.');
END;
/

-- UNIQUE 제약조건 위배 시
BEGIN
    UPDATE EMPLOYEE
    SET EMP_ID = '203'
    WHERE EMP_NAME = '&이름';
EXCEPTION
    WHEN DUP_VAL_ON_INDEX
    THEN DBMS_OUTPUT.PUT_LINE('이미 존재하는 사원입니다.');
END;
/

-- NO_DATA_FOUND : SELECT한 결과가 한 행도 없는 경우
-- TOO_MANY_ROWS : SELECT한 결과가 여러 행인 경우 (한 행이 리턴이 되어야 하는데)

DECLARE
    EID EMPLOYEE.EMP_ID%TYPE;
    DCODE EMPLOYEE.DEPT_CODE%TYPE;
BEGIN
    SELECT EMP_ID, DEPT_CODE
    INTO EID, DCODE
    FROM EMPLOYEE
    WHERE DEPT_CODE = '&부서코드';
    
    DBMS_OUTPUT.PUT_LINE('사번 : ' || EID);
    DBMS_OUTPUT.PUT_LINE('부서코드 : ' || DCODE);
EXCEPTION
    WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.PUT_LINE('조회 결과가 없습니다.');
    WHEN TOO_MANY_ROWS THEN DBMS_OUTPUT.PUT_LINE('너무 많은 행이 조회되었습니다.');
    WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('에러가 발생했습니다. 관리자에 문의해 주세요.');
END;
/