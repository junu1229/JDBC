 /*
 
    - 데이터(data) : 필요에 의해 수집했지만 아직 특정 목적을 위해 정제하지 않은 값
    
    vs 정보(info) : 수집한 데이터를 어떠한 목적을 위해 분석하거나 가공하여 새로운 의미 부여
    
    - 데이터베이스 : 데이터(data) + 베이스(base)
    DBMS : Database Management System 의 약자, 데이터베이스 관리 시스템
    
    - 관계형(Relational 
    1. 가장 대표적인 데이터베이스 시스템
    2. 데이터를 테이블 형태로 저장하고 여러 테이블을 조합하여 비즈니스 관계를 도출하는 구조
    3. 데이터의 중복을 최소화할 수 있으며 사용하기 편리하고 데이터의 무결성, 트랜잭션 처리 등 데이터 베이스 관리 시스템으로 뛰어난 성능을 보여준다.
    
    -SQL(Structured Query Language)
    : 관계형 데이터베이스에서 데이터를 조회하거나 조작하기 위해 사용하는 표준 검색 언어
    
    - SQL 종류
        1. DDL (Data Definition Language) : 데이터 정의어 - DBMS의 구조를 정의하거나 변경, 삭제하기 위해 사용하는 언어
            (CREATE 생성, ALTER 수정, DROP 삭제) - 테이블
        2. DML (Data Manipulation Language) : 데이터 조작어 - 실제 데이터를 조작하기 위해 사용하는 언어
            (INSERT 삽입, UPDATE 수정, DELETE 삭제) - 데이터
        3. DQL (Data Query Language) : 데이터 질의어 - 데이터를 조회(검색)하기 위해 사용하는 언어 (SELECT)
        4. DCL (Data Control Language) : 데이터 제어어 - DBMS에 대한 보안, 무결성, 복구 등 DBMS를 제어하기 위한 언어
            (GRANT 권한 부여, REVOKE 권환 회수)
        5. TCL (Transaction Control Language) : 트랜잭션을 제어하는 언어
            (COMMIT 실행, ROLLBACK 취소)
 
 */
 
 /*
    SELECT
    
    [표현법]
    SELECT 컬럼, [, 컬럼, ...] FROM 테이블명;
    
    - 테이블에서 데이터를 조회할 때 사용하는 SQL문
    - SELECT를 통해서 조회된 결과를 RESULT SET이라고 한다. (즉, 조회된 행들의 집합)
    - 조회하고자 하는 컬럼들은 반드시 FROM 절에 기술한 테이블에 존재하는 컬럼이어야 한다.
    - 모든 컬럼을 조회할 경우 컬럼명 대신 * 기호 사용
 */
 
 -- EMPLOYEE 테이블에 전체 사원의 모든 컬럼 조회
 SELECT * FROM EMPLOYEE;
 
 -- EMPLOYEE 테이블의 전체 사원들의 사번, 이름, 급여만을 조회
 -- 급여 (SALARY)만을 조회
 SELECT EMP_ID, EMP_NAME, SALARY FROM EMPLOYEE;
 
 SELECT emp_id, emp_name, salary from employee;
 
 -- 실습문제 ----------
 
 -- 1. job 테이블의 모든 정보 조회
  SELECT * FROM JOB;

 -- 2. job 테이블의 직급 이름 조회
  SELECT JOB_NAME FROM JOB;

 -- 3. department 테이블의 모든 정보 조회
 SELECT * FROM DEPARTMENT;
 -- 4. employee 테이블의 직원명, 이메일, 전화번호, 입사일 정보만 조회
 SELECT EMP_NAME, EMAIL, PHONE, HIRE_DATE FROM EMPLOYEE;
 -- 5. employee 테이블의 입사일, 직원명, 급여 조회
 SELECT HIRE_DATE, EMP_NAME, SALARY FROM EMPLOYEE;
 
 /*
 
    컬럼의 산술 연산
    - SELECT 절에 컬럼명 입력 부분에서 산술 연산자를 사용하여 결과를 조회할 수 있다.
 
 */
 
 -- EMPLOYEE 테이블에서 직원명 (EMP_NAME), 직원의 연봉 (=급여 SALARY *12)조회
 SELECT EMP_NAME, SALARY, SALARY*12
 FROM EMPLOYEE;
 
 -- EMPLOYEE 테이블에서 직원며으 보너스가 포함된 연봉(급여+(보너스*급여))*12) 조회
 SELECT EMP_NAME, (SALARY+(SALARY*BONUS))*12
 FROM EMPLOYEE;
 
 -- EMPLOYEE에 사원명, 입사일, 근무일수(오늘날짜 - 입사일) 조회
 -- 오늘날짜 : SYSDATE
 -- CEIL(NUMBER) : 매개값으로 전달되는 수를 올림하는 함수
 SELECT EMP_NAME, HIRE_DATE, CEIL(SYSDATE-HIRE_DATE)
 FROM EMPLOYEE;
 
 /*
 
    컬럼 별칭
    
    [표현법]
    
    컬럼 AS 별침 / 컬럼 AS "별칭" / 컬럼 별칭 / 컬럼 "별칭"
    
    - 산술연산을 하게 되면 컬럼명이 지저분해진다. 이때 컬럼명에 별칭을 부여해서 깔끔하게 보여줄 수 있다.
    - 별칭을 부여할 때 띄어쓰기 혹은 특수문자가 별칭에 포함될 경우 반드시 큰따옴표("")로 감싸줘야 한다.
    
    
 */
 -- EMPLOYEE 테이블에서 직원명, 연봉, 보너스가 포함된 연봉
 
 SELECT EMP_NAME AS 직원명, SALARY*12 AS "연봉", (SALARY + (BONUS*SALARY))*12 AS "총 소득(원)"
 FROM EMPLOYEE;
 
/*
    리터럴
    - SELECT 절에 리터럴을 사용하면 테이블에 존재하는 데이터처럼 조회가 가능
    - 즉, 리터럴은 RESULT SET의 모든 행에 반복적으로 출력된다.
*/

-- EMPLOYEE 테이블에서 사원, 직원명, 급여 조회, 단위(원) 조회
SELECT EMP_ID AS "사번", EMP_NAME AS "직원명", SALARY AS "급여", '원' AS "단위(원)"
FROM EMPLOYEE;

/*
    연결 연산자 : || 
    - 여러 컬럼값들을 마치 하나의 컬럼인것처럼 연결하거나 컬럼값과 티터럴을 연결할 수 있음
*/

-- 사번, 이름, 급여를 하나의 컬럼으로 조회
SELECT EMP_ID || EMP_NAME || SALARY
FROM EMPLOYEE;

-- XXX의 월급은 XXX원 입니다.
SELECT EMP_NAME || '의 월급은' || SALARY || '원 입니다.' AS "급여정보"
FROM EMPLOYEE;

/*
    DISTINCT - 컬럼에 중복된 값들은 한번씩만 표시하고자 할 때 사용
*/
--  EMPLOYEE에 직급코드 조회
SELECT DISTINCT JOB_CODE 
FROM EMPLOYEE;

-- ENPLOYEE에 부서코드 조회
SELECT DISTINCT DEPT_CODE 
FROM EMPLOYEE;

-- 유의사항! DISTINCT는 SELECT절에 딱 한번만 기술 가능
-- EMPLOYEE에 직급코드, 부서코드 조회
SELECT DISTINCT JOB_CODE, DEPT_CODE
FROM EMPLOYEE;


/*
    WHERE 절
    
    [표현법]
    SELECT 컬럼, 컬럼, ...
    FROM 테이블명
    WHERE 조건식;
    
    - 조회하고자 하는 테이블로부터 특정 조건에 만족하는 데이터만을 조회하고자 할 때 사용
    - 이때 WHERE 절에 조건식을 제시
    - 조건식에는 다양한 연산자를 사용 가능!
    
    비교연산자
    >, <, <=, >= : 대소비교
    =            : 같은지 비교
    !=, ^=, <>   : 같지 않은지 비교
*/

-- EMPLOYEE에서 부서코드가 'D9'인 사원들만 조회(이때, 모든 컬럼 조회)
SELECT * 
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- EMPLOYEE에서 부서코드가 'D1'인 사원들의 사원명, 급여, 부서코드만 조회
SELECT EMP_NAME, SALARY, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE = 'D1';

-- 부서코드가 'D1'이 아닌 사원들의 사번, 사원명, 부서코드 조회
SELECT EMP_NAME, SALARY, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE != 'D1';

-- 급여가 400만원 이상인 사원들의 사원명, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 4000000;
-- 재직중(ENT_YN 컬럼값이 'N')인 사원들의 사번, 사원명, 입사일 조회
SELECT EMP_ID, EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE ENT_YN = 'N';

-- OR, AND
-- 부서코드가 'D6' 이거나 급여가 300만원 이상인 사원들의 사원명, 부서코드, 급여조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6' OR SALARY>=3000000;
-- 급여가 350만원 이상 600만원 이하를 받는 사원들의 사원명, 사번, 급여 조회
SELECT EMP_NAME, EMP_ID, SALARY
FROM EMPLOYEE
WHERE SALARY<=6000000 AND SALARY>=3500000;

/*
    BETWEEN AND
    - 조건식에서 사용되는 구문
    - 몇 이상 몇 이하인 범위에 대한 조건을 제시할 때 사용되는 연산자
    
    [표현법]
    비교대상컬럼 BETWEEN 하한값 AND 상한값
    
*/
SELECT EMP_NAME, EMP_ID, SALARY
FROM EMPLOYEE
WHERE SALARY BETWEEN 3500000 AND 6000000;

-- 입사일이 '90/01/01' ~ '01/01/01'

SELECT *
FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '90/01/01' AND '01/01/01';

/*
    LIKE - 비교하고자 하는 컬럼값이 내가 제시한 특정 패턴에 만족될 경우 조회
    
    [표현법]
    비교대상컬럼 LIKE '특정패턴'
    
    - 특정패턴에는 '%', '_'를 와일드 카드로 사용
    
    '%' : 0글자 이상
    비교대상컬럼 LIKE '문자%' => 비교대상컬럼값이 문자로 "시작"되는걸 조회
    비교대상컬럼 LIKE '%문자' => 비교대상컬럼값이 문자로 "끝" 나는걸 조회
    비교대상컬럼 LIKE '%문자%' => 비교대상컬럼값이 문자로 "포함" 되는걸 조회 (키워드 검색)
    
    '_' : 1글자
    비교대상컬럼 LIKE '_문자' => 비교대상컬럼값에 문자앞에 무조건 한 글자가 올 경우 조회
    비교대상컬럼 LIKE '__문자' => 비교대상컬럼값에 문자앞에 무조건 두 글자가 올 경우 조회
    비교대상컬럼 LIKE '_문자_' => 비교대상컬럼값에 문자앞과 뒤에 무조건 한 글자씩 올 경우 조회
    
    
    
*/

-- 사원들 중 성이 전씨인 사원명, 급여, 입사일 조회
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '전%';

-- 이름 중에 하 가 포함된 사원들의 사원명, 주민번호, 전화번호 조회
SELECT EMP_NAME, EMP_NO, PHONE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%하%';
-- 이름의 가운데 글자가 하인 사원들의 사원명, 전화번호 조회
SELECT EMP_NAME, PHONE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '_하_';
-- 전화번호의 3번째 자리가 1인 사원들의 사번, 사원명, 전화번호, 이메일 조회
SELECT EMP_ID, EMP_NAME, PHONE, EMAIL
FROM EMPLOYEE
WHERE PHONE LIKE '__1%';
-- 이메일 중 _앞글자가 3글자인 사원들의 사번, 이름, 이메일 조회
-- ESCAPE OPTION 

SELECT EMP_ID, EMP_NAME, EMAIL
FROM EMPLOYEE
WHERE EMAIL LIKE '___$_%' ESCAPE '$';

-- 위의 사원들 이외의 사원들 조회
-- 논리부정연산자 : NOT
SELECT EMP_ID, EMP_NAME, EMAIL
FROM EMPLOYEE
WHERE EMAIL NOT LIKE '___$_%' ESCAPE '$';

/*
    IS NULL / IS NOT NULL
    - 컬럼값에 NULL이 있을 경우 NULL 값 비교에 사용되는 사용자
    
*/

-- 보너스를 받지 않는 사원(BONUS값이 NULL)들의 사번, 이름, 급여, 보너스조회
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE BONUS IS NULL;

-- 부서 배치를 아직 받지 않고 보너스는 받는 사원들의 이름, 보너스, 부서코드 조회

SELECT EMP_NAME, BONUS, DEPT_CODE
FROM EMPLOYEE
WHERE BONUS IS NOT NULL AND DEPT_CODE IS NULL;

/*
    IN
    - 비교대상 컬럼 값이 내가 제시한 목록중에 일치하는 값이 있는지
    
    [표현법]
    비교대상컬럼 IN ('값1', '값2', ...)
*/
-- 부서코드가 D6 이거나 D8이거나 D5가 아닌 부서원들의 이름, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE NOT IN ('D6','D8','D5');
/*
    연산자 우선순위
    0. ()
    1. 산술연산자
    2. 연결연산자 ||
    3. 비교연산자
    4. IS NULL / LIKE / IN
    5. BETWEEN AND
    6. NOT(논리연산자)
    7. AND(논리연산자)
    8. OR(논리연산자)
*/
-- 직급 코드가 J7이거나 J2인 사원들 중 급여가 200만원 이상인 사원들의 모든 컬럼 조회
SELECT *
FROM EMPLOYEE
WHERE JOB_CODE IN ('J7','J2') AND SALARY >= 2000000;

-- 연습문제----------------------------------------------
-- 1. 사수가 없고 부서배치도 받지 않은 사원들의 사원명, 사수사번, 부서코드 조회
SELECT EMP_NAME, MANAGER_ID, DEPT_CODE
FROM EMPLOYEE
WHERE MANAGER_ID IS NULL AND DEPT_CODE IS NULL;

-- 2. 연봉(보너스포함X)이 3000만원 이상이고 보너스를 받지 않은 사원들의 사번, 사원명, 급여, 보너스 조회
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE SALARY*12 >=30000000 AND BONUS IS NULL;

-- 3. 입사일이 '95/01/01' 이상이고 부서배치를 받은 사원들의 사번, 사원명, 입사일, 부서코드 조회
SELECT EMP_ID, EMP_NAME, HIRE_DATE, DEPT_CODE
FROM EMPLOYEE
WHERE HIRE_DATE >= '95/01/01' AND DEPT_CODE IS NOT NULL;
-- 4. 급여가 200만원 이상 500만원 이하고 입사일이 '01/01/01' 이상이고 보너스를 받지 않는 사원들의 사번, 사원명, 급여, 입사일, 보너스 조회
SELECT EMP_ID, EMP_NAME, SALARY, HIRE_DATE, BONUS
FROM EMPLOYEE
WHERE SALARY BETWEEN 2000000 AND 5000000 AND BONUS IS NULL AND HIRE_DATE >= '01/01/01';
-- 5. 보너스 포함 연봉이 NULL이 아니고 이름에 '하'가 포함되어있는 사원들의 사번, 사원명, 급여, 보너스포함연봉 조회 (별칭)
SELECT EMP_ID, EMP_NAME, SALARY, (SALARY+(BONUS*SALARY))*12 AS "보너스포함연봉"
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%하%' AND BONUS IS NOT NULL;
/*
    ORDER BY 절
    - SELECT 문 가장 마지막 줄에 작성 뿐만 아니라 실행순서 또한 마지막에 실행
    
    - 실행순서 : FROM -> WHERE -> SELECT -> ORDER BY
    
    [표현법]
    SELECT 컬럼, 컬럼, ...
    FROM 테이블명
    WHERE 조건식
    ORDER BY 정렬하고자 하는 컬럼값 [ASC|DESC] [NULLS FIRST|NULLS LAST];
    
    - ASC : 오름차순 정렬 (생략시 기본값)
    - DESC : 내림차순 정렬
    - NULLS FIRST : 정렬하고자하는 컬럼값에 NULL이 있을경우 해당 데이터를 맨 앞 배치 (생략시 DESC일때 기본값)
    - NULLS LAST : 정렬하고자하는 컬럼값에 NULL이 있을경우 해당 데이터를 맨 뒤 배치 (생략시 ASC일때 기본값)
    
*/

-- 전 사원의 사원명, 보너스 조회 (오름차순 정렬)
SELECT EMP_NAME, BONUS 
FROM EMPLOYEE
--ORDER BY BONUS ASC;
ORDER BY BONUS DESC NULLS LAST;

