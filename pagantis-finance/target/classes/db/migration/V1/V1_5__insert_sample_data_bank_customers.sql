BEGIN;

insert into BANK (name) values ('ALPHA BANK');
insert into BANK (name) values ('HSBC');
insert into BANK (name) values ('BELFIUS');
insert into BANK (name) values ('EUROBANK');
insert into BANK (name) values ('NATIONAL BANK OF GREECE');


insert into CUSTOMERS (last_name, first_name, social_security_number, email, phone_number)
                values ('SILIONI', 'EVANGELIA', 'AK234567', 'evesino@gmail.com', '6948889031');
insert into CUSTOMERS (last_name, first_name, social_security_number, email, phone_number)
                values ('SMITH', 'ANTONI', 'WXO23456', 'smith@gmail.com', '6912309876');
insert into CUSTOMERS (last_name, first_name, social_security_number, email, phone_number)
                values ('PHOINIX', 'JOSEF', 'ZO76523', 'phoinix@gmail.com', '6948889031');
insert into CUSTOMERS (last_name, first_name, social_security_number, email, phone_number)
                values ('TARANTINO', 'QUENTIN', 'AK234567', 'tarantino@gmail.com', '6948889031');
insert into CUSTOMERS (last_name, first_name, social_security_number, email, phone_number)
                values ('FRAGKOULI', 'THEODORA', 'AK234567', 'tfrackouli@gmail.com', '6948889031');
insert into CUSTOMERS (last_name, first_name, social_security_number, email, phone_number)
                values ('FRANK', 'PAUL', 'AK234567', 'p.frank@gmail.com', '6948889031');


insert into BANK_CUSTOMERS (bank_id, customer_id) values (1, 1);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (1, 3);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (1, 5);

insert into BANK_CUSTOMERS (bank_id, customer_id) values (2, 2);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (2, 4);

insert into BANK_CUSTOMERS (bank_id, customer_id) values (3, 1);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (3, 2);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (3, 3);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (3, 4);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (3, 5);

insert into BANK_CUSTOMERS (bank_id, customer_id) values (4, 4);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (4, 5);

insert into BANK_CUSTOMERS (bank_id, customer_id) values (5, 1);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (5, 2);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (5, 3);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (5, 4);
insert into BANK_CUSTOMERS (bank_id, customer_id) values (5, 5);


COMMIT;