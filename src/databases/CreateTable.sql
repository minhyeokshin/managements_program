use employeedb;

CREATE TABLE EMPLOYEE (
                          eno INTEGER NOT NULL primary key,
                          name varchar(20) NOT NULL,
                          enteryear INTEGER NOT NULL,
                          entermonth INTEGER NOT NULL,
                          enterday INTEGER NOT NULL,
                          role varchar(20) NOT NULL,
                          secno Integer NOT NULL,
                          salary Integer NOT NULL
);

CREATE TABLE PAY_RAISE_HISTORY (
                                   eno INTEGER NOT NULL,
                                   oldSalary INTEGER NOT NULL,
                                   newSalary INTEGER NOT NULL
);

alter table PAY_RAISE_HISTORY add constraint fk_eno foreign key(eno) references EMPLOYEE(eno) on delete cascade;

commit;


