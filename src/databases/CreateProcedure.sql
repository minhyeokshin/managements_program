use employeedb;

DELIMITER $$
CREATE PROCEDURE DB_EMPLOYEE_INSERT(in eno int, in name varchar(20), in enteryear int, in entermonth int,
                                    in enterday int, in role varchar(20), in secno int, in salary int, in tbname varchar(20))
BEGIN
    SET @strsql = concat(
                  'INSERT INTO ', tbname, ' VALUES(?,?,?,?,?,?,?,?);'
                  );

    SET @eno = eno;
    SET @name = name;
    SET @enteryear = enteryear;
    SET @entermonth = entermonth;
    SET @enterday = enterday;
    SET @role = role;
    SET @secno = secno;
    SET @salary = salary;

PREPARE stmt FROM @strsql;
EXECUTE stmt using @eno, @name, @enteryear, @entermonth, @enterday, @role, @secno, @salary;
deallocate prepare stmt;

commit;
end $$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE DB_EMPLOYEE_READONE(in eno int)
BEGIN
    SET @strsql = concat(
				'SELECT * FROM EMPLOYEE WHERE ENO = ?'
                  );

    SET @eno = eno;

PREPARE stmt FROM @strsql;
EXECUTE stmt using @eno;
deallocate prepare stmt;

commit;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE DB_HISTORY_INSERT(in eno int, in oldSalary int, in newSalary int)
BEGIN
    SET @strsql = concat(
                  'INSERT INTO PAY_RAISE_HISTORY (eno, oldSalary, newSalary) VALUES (?, ?, ?);'
                  );

    SET @eno = eno;
    SET @oldSalary = oldSalary;
	SET @newSalary = newSalary;

PREPARE stmt FROM @strsql;
EXECUTE stmt using @eno, @oldSalary, @newSalary;
deallocate prepare stmt;

commit;
end $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE DB_HISTORY_SELECT(in eno int)
BEGIN
    SET @strsql = concat(
                  'SELECT p.eno, e.name, p.oldSalary, p.newSalary FROM EMPLOYEE e, PAY_RAISE_HISTORY p WHERE e.ENO = p.ENO and e.eno = ?'
                  );

    SET @eno = eno;


PREPARE stmt FROM @strsql;
EXECUTE stmt using @eno;
deallocate prepare stmt;

commit;
end $$
DELIMITER ;


DROP PROCEDURE IF EXISTS EmployeeUpdate;

DELIMITER $$
CREATE PROCEDURE EmployeeUpdate (
    IN u_TName VARCHAR(20),
    IN u_name VARCHAR(20),
    IN u_enteryear INT,
    IN u_entermonth INT,
    IN u_enterday INT,
    IN u_role VARCHAR(20),
    IN u_secno INT,
    IN u_salary INT,
    IN u_eno INT
)
BEGIN
    SET @strsql = CONCAT ('UPDATE ', u_TName, ' SET ',
                  'name = ?, enteryear = ?, entermonth = ?, enterday = ?, role = ?, secno = ?, salary = ? ',
                  'WHERE eno = ?');

    SET @name = u_name;
    SET @enteryear = u_enteryear;
    SET @entermonth = u_entermonth;
    SET @enterday = u_enterday;
    SET @role = u_role;
    SET @secno = u_secno;
    SET @salary = u_salary;
    SET @eno = u_eno;

PREPARE stmt FROM @strsql;
EXECUTE stmt USING @name, @enteryear, @entermonth, @enterday, @role, @secno, @salary, @eno;
DEALLOCATE PREPARE stmt;

COMMIT ;
end $$
DELIMITER ;


DROP PROCEDURE IF EXISTS EmployeeDelete;

DELIMITER $$
CREATE PROCEDURE EmployeeDelete (IN d_TName VARCHAR(20), IN d_eno INT)
BEGIN
    SET @strsql = CONCAT('DELETE FROM ', d_TName,
                  ' WHERE eno = ?');
    SET @eno = d_eno;

PREPARE stmt FROM @strsql;
EXECUTE stmt USING @eno;
DEALLOCATE PREPARE stmt;

COMMIT ;
end $$
DELIMITER ;