-- 1번
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, HIRE_DATE, 연봉등수
FROM (
SELECT EMP_ID,
    EMP_NAME, 
    DEPT_TITLE, 
    JOB_NAME, 
    HIRE_DATE, 
    RANK() OVER(ORDER BY (SALARY+(SALARY*NVL(BONUS,0)))*12 DESC) AS "연봉등수"
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN JOB USING (JOB_CODE))
WHERE 연봉등수 <= 5;

-- 2번
SELECT DEPT_TITLE, 연봉합
FROM (
    SELECT DEPT_TITLE, 
    SUM(SALARY) AS "연봉합"
    FROM EMPLOYEE
    JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
    GROUP BY DEPT_TITLE
)
WHERE 연봉합 >= (SELECT 0.2 * SUM(SALARY) FROM EMPLOYEE);

-- 3번
WITH T_SAL AS (SELECT SUM(SALARY) AS "총급여", AVG(SALARY) AS "평균급여"
FROM EMPLOYEE)

SELECT 총급여, 평균급여
FROM T_SAL;


