BEGIN;


insert into TRANSFERS (transfer_type_id, transfer_date, source_account_number, target_account_number, amount, account_id)
SELECT  2, now() , 'GR12345467898900', 'GRTH9878998766',  500,
(select id from ACCOUNT where account_number = 'GR12345467898900');

UPDATE ACCOUNT
SET BALANCE = 9500
WHERE account_number = 'GR12345467898900';


COMMIT;