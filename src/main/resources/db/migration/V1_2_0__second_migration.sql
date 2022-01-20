

CREATE TABLE IF NOT EXISTS candidate(
  id BIGINT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   desired_salary BIGINT NULL,
   CONSTRAINT pk_candidate PRIMARY KEY (id)
);

INSERT INTO candidate (first_name,last_name,email,desired_salary)
VALUES ('M','P','matt.parker@gmail.com',4500);

INSERT INTO candidate (first_name,last_name,email,desired_salary)
VALUES ('M','J','mary.jane@gmail.com',7500);

INSERT INTO candidate (first_name,last_name,email,desired_salary)
VALUES ('T','S','t.s@gmail.com',3500);
