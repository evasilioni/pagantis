
CREATE TABLE TRANSFER_TYPES (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(250) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE TRANSFERS (
    id bigint(20) not null AUTO_INCREMENT primary key,
    transfer_type_id int,
    transfer_date datetime not null default now(),
    source_account_number varchar(500) not null,
    target_account_number varchar(500) not null,
    amount decimal(20,2) not null,
    account_id bigint(20) not null,
    constraint transfer_ibfk_1 foreign key (transfer_type_id) references TRANSFER_TYPES (id),
    constraint transfer_ibfk_2 foreign key (account_id) references ACCOUNT (id)
);
