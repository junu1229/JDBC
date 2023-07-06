/*
    트리거(TRIGGER)
    - 테이블이나 뷰가 DML(INSERT, UPDATE, DELETE)문에 의해 변경될 경우 자동으로 실행될 내용을 정의하여 저장

    ex) 
    - 회원탈퇴시 기존의 회원테이블에 데이터 DELETE 후 곧바로 탈퇴된 회원들만 따로 보관하는 테이블에 자동으로 INSERT 처리
    - 신고횟수가 일정 수를 넘었을 대 해당 회원을 자동으로 블랙리스트로 처리
    - 입출고에 대한 데이터가 기록(INSERT)될 때마다 해당 상품에 대한 재고수량을 매번 수정(UPDATE)해야 될 때
    
    * 트리거 종류
    - SQL문의 실행시기에 따른 분류
        > BEFORE TRIGGER: 내가 지정한 테이블에 이벤트가 발생되기 전에 트리거 실행
        > AFTER TRIGGER: 내가 지정한 테이블에 이벤트가 발생된 후에 트리거 실행
    - SQL문에 의해 영향을 받는 각 행에 따른 분류
        > STATEMENT TRIGGER(문장 트리거): 이벤트가 발생한 SQL문에 대해 딱 한번만 트리거 실행
        > ROW TRIGGER(행 트리거): 해당 SQL문 실행할 때마다 매번 트리거 실행(FOR EACH ROW 옵션 기술해야됨)
            > :OLD - BEFORE UPDATE(수정전 자료), BEFORE DELETE(삭제전 자료)
            > :NEW - AFTER INSERT(추가된 자료), AFTER UPDATE(수정 후 자료)
        
    * 트리거 생성
    
    [표현식]
    CREATE [OR REPLACE] TRIGGER 트리거명
    BEFORE|AFTER INSERT|UPDATE|DELETE ON 테이블명
    [FOR EACH ROW]
    DECLARE
        변수선언;
    BEGIN
        실행내용(해당 위에 지정된 이벤트가 발생시 자동으로 실행할 구문)
    EXCEPTION
        예외처리구문;
    END;
    /
    
*/
--1. 문장 트리거
-- EMPLOYEE 테이블에서 새로운 행이 INSERT될 때
-- '신입 사원이 입사했습니다!' 메시지를 자동으로 출력하는 트리거 생성
CREATE OR REPLACE TRIGGER TRG_01
AFTER INSERT ON EMPLOYEE
BEGIN
    DBMS_OUTPUT.PUT_LINE('신입사원이 입사했습니다!');
END;
/
 
INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO)
VALUES(500, '박진실','111111-2222222'); 
 
INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO)
VALUES(501, '이창희','222222-1111111'); 
 
--2. 행 트리거
-- :OLD : 수정, 삭제 전 데이터에 접근 가능
-- :NEW : 추가, 수정 후 데이터에 접근 가능
-- EMPLOYEE 테이블에 UPDATE 수행 후 메세지를 자동으로 출력
CREATE OR REPLACE TRIGGER TRG_02
AFTER UPDATE ON EMPLOYEE
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('변경 전: ' || :OLD.DEPT_CODE ||', 변경 후: ' || :NEW.DEPT_CODE);
END;
/
 
UPDATE EMPLOYEE SET DEPT_CODE = 'D3'
WHERE DEPT_CODE = 'D6';

ROLLBACK;

-- 상품 입/출고 관련 예시
--1. 상품에 대한 데이터를 보관할 테이블 생성(TB_PRODUCT)
CREATE TABLE TB_PRODUCT(
    PCODE NUMBER PRIMARY KEY,       -- 상품번호
    PNAME VARCHAR2(30) NOT NULL,    -- 상품명
    BRAND VARCHAR2(30) NOT NULL,    -- 브랜드명
    PRICE NUMBER,                   -- 가격
    STOCK NUMBER DEFAULT 0          -- 재고수량
);
CREATE SEQUENCE SEQ_PCODE;
 
INSERT INTO TB_PRODUCT VALUES(SEQ_PCODE.NEXTVAL, '갤럭기23', '삼성', 1500000, DEFAULT);
INSERT INTO TB_PRODUCT VALUES(SEQ_PCODE.NEXTVAL, '갤럭시 Z플립4', '삼성', 1000000, 10);
INSERT INTO TB_PRODUCT VALUES(SEQ_PCODE.NEXTVAL, '아이폰14', '애플', 1180000, 20);
 
COMMIT;
SELECT * FROM TB_PRODUCT;
 
--2. 상품 입/출고 상세 이력 테이블 생성(TB_PRODETAIL)
CREATE TABLE TB_PRODETAIL(
    DCODE NUMBER PRIMARY KEY,               --이력번호
    PCODE NUMBER REFERENCES TB_PRODUCT,     --상품번호
    PDATE DATE NOT NULL,                    --상품입출고일
    AMOUNT NUMBER NOT NULL,                 --입출고수량
    STATUS CHAR(6) CHECK(STATUS IN ('입고','출고')) --상태
);
CREATE SEQUENCE SEQ_DCODE;
--1번 상품이 오늘 날짜로 10개 입고
INSERT INTO TB_PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, 1, SYSDATE, 10, '입고');
--> 1번 상품의 재고 수량이 10개 증가
UPDATE TB_PRODUCT
SET STOCK = STOCK + 10
WHERE PCODE = 1;

COMMIT;
SELECT * FROM TB_PRODETAIL;
SELECT * FROM TB_PRODUCT;
--> 3번 상품이 오늘날짜로 5개 출고
INSERT INTO TB_PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, 3, SYSDATE, 5, '출고');

UPDATE TB_PRODUCT
SET STOCK = STOCK - 5
WHERE PCODE = 3;

-- TB_PRODETAIL 테이블에 INSERT 이벤트 발생시 TB_PRODUCT 테이블에 매번 자동으로 재고수량이 UPDATE 되게끔 트리거 정의
CREATE OR REPLACE TRIGGER TRG_03
AFTER INSERT ON TB_PRODETAIL
FOR EACH ROW
BEGIN
    IF (:NEW.STATUS = '입고')
    THEN 
    UPDATE TB_PRODUCT
    SET STOCK = STOCK + :NEW.AMOUNT
    WHERE PCODE = :NEW.PCODE;
    ELSE 
    UPDATE TB_PRODUCT
    SET STOCK = STOCK - :NEW.AMOUNT
    WHERE PCODE = :NEW.PCODE;
    END IF;
END;
/