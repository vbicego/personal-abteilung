
CREATE TABLE candidate (
  id BIGINT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255) NULL,
   last_name VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   desired_salary BIGINT NULL,
   CONSTRAINT pk_candidate PRIMARY KEY (id)
);
