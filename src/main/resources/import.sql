insert into libro (ID, AUTOR, DISPONIBLE, FEC_PUBLICACION, ISBN, TITULO) values(0,'Stephen king', true, '2017-01-01 01:00:00.0', 0, 'Prueba');
insert into libro (ID, AUTOR, DISPONIBLE, FEC_PUBLICACION, ISBN, TITULO) values(1,'Stephen king', true, '2017-01-01 01:00:00.0', 1, 'La Niebla');
insert into persona (ID, DIRECCION, FECHA_NACIMIENTO, DNI, NOMBRE, TELEFONOS, APELLIDOS) values(0, 'Calle 1', '1981-01-01 01:00:00.0' , '11111111H', 'Juan', '666666666', 'Español Español');
insert into persona (ID, DIRECCION, FECHA_NACIMIENTO, DNI, NOMBRE, TELEFONOS, APELLIDOS) values(1, 'Calle 2', '1981-01-01 01:00:00.0' , '11111112H', 'Pepe', '666666667', 'Martin Martin');
insert into prestamo (ID, PERSONA, FEC_DEVOLUCION, FEC_PRESTAMO, LIBRO) values('0', '11111111H', '2017-09-25 02:00:00.0', '2017-09-22 02:00:00.0', 1);
commit;