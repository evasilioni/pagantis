BEGIN;


insert into TRANSFERS (transfer_type_id, transfer_date, source_account_number, target_account_number, amount, account_id)
SELECT  2, now() , 'GR12345467898900', 'GRTH9878998766',  500,
(select id from ACCOUNT where account_number = 'GR12345467898900');

UPDATE ACCOUNT
SET BALANCE = 9500
WHERE account_number = 'GR12345467898900';


--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('NGB0987654321', 3490, 2);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('HSB12345467898900', 23000, 2);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('WX09878998766', 11000, 1);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('GRTH9878998766', 12000, 1);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('ASTH9878998766', 4000, 1);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('ASTH9878998764', 4000, 3);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('ASTH9878998712', 3000, 3);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('ASTH9878998734', 5000, 3);
--
--
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('ABC45467898900', 23000, 4);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('GFE0987654321', 1864, 5);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('LOI345467898900', 2400, 6);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('MART878998766', 4520, 7);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('GRTE9878998766', 1230, 8);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('POI878998766', 5200, 9);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('QWE9878998764', 2000, 10);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('HJK9878998712', 2140, 11);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('PLMNB9878998734', 8120, 12);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('SDF878998734', 14000, 13);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('EDT878998734', 15000, 14);
--insert into ACCOUNT (account_number, balance, bank_customer_id) values ('EDRF9878998734', 16000, 15);

COMMIT;