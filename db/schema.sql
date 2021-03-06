CREATE TABLE "PRODUCT" (
  "ID" NUMBER(10,0) NOT NULL ENABLE,
  "NAME" VARCHAR2(255 BYTE) NOT NULL ENABLE,
  "CREATION_DATE" DATE,
  "PRICE" NUMBER(10,4),
  CONSTRAINT "PRODUCT_PK" PRIMARY KEY ("ID")
);

CREATE TABLE "USERS" (
  "ID" NUMBER(10,0),
  "LOGIN" VARCHAR2(30 BYTE),
  "PASSWORD" VARCHAR2(255 BYTE),
  "USER_ROLE" VARCHAR2(10 BYTE),
  "SOLE" VARCHAR2(36 BYTE),
  CONSTRAINT "USER_PK" PRIMARY KEY ("ID")
);