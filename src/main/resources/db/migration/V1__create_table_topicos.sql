create table topicos(
    id bigint not null auto_increment primary key,
    titulo varchar(100) unique not null,
    mensaje text not null,
    fecha_creacion timestamp not null,
    status enum('ACTIVO', 'ELIMINADO', 'ACTUALIZADO') not null,
    nombre_autor varchar(100) not null,
    nombre_curso varchar(100) not null,
    unique (mensaje(255))
)