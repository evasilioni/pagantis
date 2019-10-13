
CREATE TABLE ACCOUNT (
  id bigint(20) NOT NULL  AUTO_INCREMENT,
  account_number varchar(500) NOT NULL,
  balance decimal(20,2) NOT NULL DEFAULT '0',
  bank_customer_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY account_number (account_number),
  CONSTRAINT account_ibfk_1 FOREIGN KEY (bank_customer_id) REFERENCES BANK_CUSTOMERS (id)
);
