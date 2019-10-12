
CREATE TABLE BANK (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(500) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE CUSTOMERS (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  social_security_number varchar(255) NOT NULL,
  email varchar(500) NOT NULL,
  phone_number varchar(500) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE BANK_CUSTOMERS (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  bank_id bigint(20) NOT NULL,
  customer_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT bank_ibfk_1 FOREIGN KEY (bank_id) REFERENCES BANK (id),
  CONSTRAINT customer_ibfk_1 FOREIGN KEY (customer_id) REFERENCES CUSTOMERS (id)
);