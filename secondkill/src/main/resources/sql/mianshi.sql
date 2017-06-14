
CREATE DATABASE IF NOT EXISTS secondkill;

DROP TABLE IF EXISTS t_date;

CREATE TABLE t_date(
  year INT,
  month INT,
  amount DOUBLE PRECISION,
  PRIMARY KEY (year, month, amount)
);

INSERT INTO t_date(year, month, amount)
VALUES
  (1991, 1, 1.1),
  (1991, 2, 1.2),
  (1991, 3, 1.3),
  (1991, 4, 1.4),
  (1991, 1, 2.1),
  (1991, 2, 2.2),
  (1991, 3, 2.3),
  (1991, 4, 2.4);

SELECT
  year
FROM student
GROUP BY year
