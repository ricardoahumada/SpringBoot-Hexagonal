CREATE SCHEMA `tareas_proyectos_hex` ;

USE mysql;

CREATE USER 'tareas_user_hex'@'%' IDENTIFIED BY 'tar123';
GRANT ALL PRIVILEGES ON tareas_proyectos_hex.* TO 'tareas_user_hex'@'%';
FLUSH PRIVILEGES;