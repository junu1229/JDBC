DROP TABLE TB_RENT;
DROP TABLE TB_BOOK;
DROP TABLE TB_MEMBER;
DROP SEQUENCE SEQ_TB_BOOK;
DROP SEQUENCE SEQ_TB_MEMBER;
DROP SEQUENCE SEQ_TB_RENT;

CREATE TABLE TB_BOOK(
    BK_NO NUMBER,
    BK_TITLE VARCHAR2(100) NOT NULL,
    BK_AUTHOR VARCHAR2(20) NOT NULL,
    CONSTRAINT TB_BOOK_BK_NO_PK PRIMARY KEY(BK_NO)
);

CREATE TABLE TB_MEMBER(
    MEMBER_NO NUMBER,
    MEMBER_ID VARCHAR2(20) UNIQUE,
    MEMBER_PWD VARCHAR2(30) NOT NULL,
    MEMBER_NAME VARCHAR2(20) NOT NULL,
    STATUS VARCHAR2(1) DEFAULT 'N' CHECK(STATUS IN ('Y', 'N')),
    ENROLL_DATE DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT TB_MEMBER_MEMBER_NO_PK PRIMARY KEY(MEMBER_NO)
);

CREATE TABLE TB_RENT(
    RENT_NO NUMBER CONSTRAINT TB_RENT_RENT_NO_PK PRIMARY KEY,
    RENT_MEM_NO NUMBER CONSTRAINT TB_RENT_RENT_MEM_NO_FK REFERENCES TB_MEMBER ON DELETE SET NULL,
    RENT_BOOK_NO NUMBER CONSTRAINT TB_RENT_RENT_BOOK_NO_FK REFERENCES TB_BOOK ON DELETE SET NULL,
    RENT_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE SEQ_TB_BOOK;
CREATE SEQUENCE SEQ_TB_MEMBER;
CREATE SEQUENCE SEQ_TB_RENT;