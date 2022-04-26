Proyecto almacenado en GitHub
url repositorio: https://github.com/AndresFRivero/concurso

Aplicación realizada con Java v1.8

Base de datos utilizada PostgreSQL

Entorno de desarrollo, Apache NetBeans IDE 12.0

Dentro de la aplicación esta la configuración de la ruta, para
la conexión a la DB

Debe crearse localmente un base de datos llamada "concurso"
para la correcta ejecución de la aplicacion.

url de conexión: jdbc:postgresql://localhost:5432/concurso?useSSL=false&serverTimezone=UTC
puerto utilizado: 5432
Base de datos: concurso

scripts de tablas necesarias en DB

CREATE TABLE public.pregunta (
	id int4 NOT NULL,
	enunciado varchar(255) NULL,
	nivel int4 NULL,
	opcion1 varchar(255) NULL,
	opcion2 varchar(255) NULL,
	opcion3 varchar(255) NULL,
	opcion4 varchar(255) NULL,
	respuesta varchar(255) NULL,
	CONSTRAINT pregunta_pkey PRIMARY KEY (id)
);

CREATE TABLE public.jugador (
	id int4 NOT NULL,
	nombre varchar(255) NULL,
	apellido varchar(255) NULL,
	nivel_alcanzado int4 NULL,
	premio float4 NULL,
	retiro_voluntario bool NULL,
	CONSTRAINT jugador_pkey PRIMARY KEY (id)
);