CREATE TABLE IF NOT EXISTS usuarios(
    id INTEGER,
    estado TEXT,
    fecha_actualizacion TEXT,
    fecha_registro TEXT,
    clave TEXT,
    correo TEXT,
    descripcion TEXT,
    usuario TEXT,
    lapellidos TEXT,
    lesadmin TEXT,
    lnombres TEXT  
);


CREATE TABLE IF NOT EXISTS sucursales(
    id INTEGER,
    estado TEXT,
    fecha_actualizacion TEXT,
    fecha_registro TEXT,
    codigo TEXT,
    direccion TEXT,
    nombre TEXT,
    id_empresa INTEGER
);

CREATE TABLE IF NOT EXISTS clientes(
    id INTEGER,
    estado TEXT,
    fecha_actualizacion TEXT,
    fecha_registro TEXT,
    codigo TEXT,
    direccion TEXT,
    nombreCompleto TEXT,
    identificacion TEXT,
    id_empresa INTEGER.
    id_sucursal INTEGER
);


CREATE TABLE IF NOT EXISTS productos(
    id INTEGER,
    estado TEXT,
    fecha_actualizacion TEXT,
    fecha_registro TEXT,
    urlImagen TEXT,
    direccion TEXT,
    nombre TEXT,
    descripcion TEXT,
    detallesadicionales TEXT,
    codigoauxiliar TEXT,
    codigoproducto TEXT,
    id_empresa INTEGER
    id_sucursal INTEGER
);

CREATE TABLE IF NOT EXISTS listaPrecios(
    id INTEGER,
    estado TEXT,
    fecha_actualizacion TEXT,
    fecha_registro TEXT,
    codigo TEXT,
    direccion TEXT,
    nombre TEXT,
    id_producto INTEGER
);

CREATE TABLE IF NOT EXISTS ordenes(
    id INTEGER,
    estado TEXT,
    fecha_actualizacion TEXT,
    fecha_registro TEXT,
    numeroOrden TEXT,
    datosCliente TEXT,
    detalles TEXT,
    id_empresa INTEGER,
    idSucursal INTEGER,
    idUsuario INTEGER
);

CREATE TABLE IF NOT EXISTS bancos(
    id INTEGER,
    estado TEXT,
    fecha_actualizacion TEXT,
    fecha_registro TEXT,
    id_empresa INTEGER,
    idSucursal INTEGER,
    codigoBanco TEXT,
    nombreBanco TEXT
);


