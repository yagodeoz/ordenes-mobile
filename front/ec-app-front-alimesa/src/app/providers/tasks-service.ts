
import { Injectable } from '@angular/core';
import { SQLiteObject } from '@ionic-native/sqlite';


@Injectable()
export class TasksServiceProvider {

  db: SQLiteObject = null;

  //Nombres Tablas
  public TABLA_SINCRONIZACION = "SINCRONIZACION";
  public TABLA_CONFIG = "CONFIG";
  public TABLA_USUARIO = "USUARIOS";
  public TABLA_SUCURSALES = "SUCURSALES";
  public TABLA_CLIENTES = "CLIENTES";
  public TABLA_PRODUCTOS = "PRODUCTOS";
  public TABLA_DETALLES_PRODUCTOS = "DETALLESPRODUCTOS";
  public TABLA_LISTAPRECIO = "LISTAPRECIO";
  public TABLA_CATEGORIAPRODUCTO = "CATEGORIAPRODUCTO";

  public TABLA_CABORDEN = "CABORDEN";
  public TABLA_DETALLESORDEN = "DETALLESORDEN";

  public TABLA_LISTAPRECIOPRODUCTO = "LISTAPRECIOPRODUCTO";

  public TABLA_CABECERACOBRO = "CABECERACOBRO";

  public TABLA_DETALLECOBRO = "DETALLECOBRO";

  public TABLA_COMPROBANTECOBRO = "COMPROBANTECOBRO";

  public TABLA_BANCO = "BANCO";

  public TABLA_CABDESPACHO = "CABDESPACHO";
  public TABLA_DETALLESDESPACHO = "DETALLESDESPACHO";
  public TABLA_STOCK = "STOCK";

/* 
  
   double precision,
   double precision,
   double precision,
   double precision,
   double precision,
   character varying(255),
   character varying(255),
   character varying(255),
   bigint,
   character varying(255),
  usuarioasignado character varying(255),
  usuarioasignante character varying(255),

*/

  public SQL_CONFIG = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_CONFIG + ' ( id INTEGER PRIMARY KEY, ipapigateway TEXT ) ';

  public SQL_CABDESPACHO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_CABDESPACHO + '('
    + 'id INTEGER PRIMARY KEY, '
    + 'estado TEXT, '
    + 'idreferencia TEXT, '
    + 'campo_auditoria TEXT, '
    + 'fecha_actualizacion TEXT, '
    + 'fecha_registro TEXT, '
    + 'observacion TEXT, '
    + 'direccioncomprador TEXT, '
    + 'estadoproceso TEXT, '
    + 'fechaemision TEXT, '
    + 'idcliente TEXT,'
    + 'idsucursal INTEGER,'
    + 'idusuario INTEGER,'
    + 'identificacioncomprador TEXT,'
    + 'importetotal REAL,'
    + 'moneda TEXT,'
    + 'nombrevendedor TEXT,'
    + 'numerofactura TEXT,'
    + 'numerooden TEXT,'
    + 'razonsocialcomprador REAL,'
    + 'subtotal0 REAL,'
    + 'subtotal12 REAL,'
    + 'totaldescuento REAL,'
    + 'totalimpuesto REAL,'
    + 'totalsinimpuestos REAL,'
    + 'canalcreacion TEXT,'
    + 'codigocliente TEXT,'
    + 'codigosucursal TEXT,'
    + 'diaspago INTEGER,'
    + 'placavehiculo TEXT,'
    + 'usuarioasignado TEXT,'
    + 'usuarioasignante TEXT,'
    + 'descripcionlistaprecio TEXT,'
    + 'tipopago TEXT)';

    public SQL_DETALLESDESPACHO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_DETALLESDESPACHO + '('
    + 'id INTEGER PRIMARY KEY, '
    + 'estado TEXT, '
    + 'idreferencia TEXT, '
    + 'campo_auditoria TEXT, '
    + 'fecha_actualizacion TEXT, '
    + 'fecha_registro TEXT, '
    + 'observacion TEXT, '
    + 'cantidad REAL, '
    + 'codigoauxiliar TEXT,'
    + 'codigoprincipal TEXT,'
    + 'descripcion TEXT,'
    + 'descuento REAL,'
    + 'detallesadicionales TEXT,'
    + 'preciototalconimpuesto REAL,'
    + 'preciototalsinimpuesto REAL,'
    + 'preciounitario REAL,'
    + 'id_cab_despacho INTEGER,'
    + 'id_producto INTEGER,'
    + 'codigoproducto TEXT,'
    + 'numerofactura TEXT,'
    + 'numerooden TEXT,'    
    + 'preciopedido REAL)';

  public SQL_STOCK = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_STOCK + '('
    + 'id INTEGER PRIMARY KEY, '
    + 'estado TEXT, '
    + 'idreferencia TEXT, '
    + 'campo_auditoria TEXT, '
    + 'fecha_actualizacion TEXT, '
    + 'fecha_registro TEXT, '
    + 'observacion TEXT, '
    + 'canalcreacion TEXT, '
    + 'cantidadentera INTEGER,'
    + 'codigoproducto TEXT,'
    + 'codigosucursal TEXT,'
    + 'contidaddecimal REAL,'
    + 'idsucursal INTEGER,'
    + 'idusuario INTEGER,'
    + 'placacamion TEXT,'
    + 'usuarioasignado TEXT,'
    + 'usuarioasignante TEXT,'
    + 'preciounitario REAL,'
    + 'precioconimpuesto REAL,'    
    + 'precionsinimpuesto REAL,'    
    + 'saldo REAL)';  


public SQL_BANCO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_BANCO + '('
    + 'id INTEGER PRIMARY KEY, '
    + 'estado TEXT, '
    + 'idreferencia TEXT, '
    + 'campo_auditoria TEXT, '
    + 'fecha_actualizacion TEXT, '
    + 'fecha_registro TEXT, '
    + 'observacion TEXT, '
    + 'codigobanco TEXT, '
    + 'nombrebanco TEXT)';


public SQL_COMPROBANTECOBRO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_COMPROBANTECOBRO + '('
    + 'id INTEGER PRIMARY KEY, '
    + 'estado TEXT, '
    + 'idreferencia TEXT, '
    + 'campo_auditoria TEXT, '
    + 'fecha_actualizacion TEXT, '
    + 'fecha_registro TEXT, '
    + 'observacion TEXT, '
    + 'fechaemision TEXT, '
    + 'fechavencimiento TEXT,'
    + 'idtipoformapago TEXT,'
    + 'monto REAL,'
    + 'numeropago TEXT,'
    + 'comprobante TEXT,'
    + 'banco TEXT,'
    + 'codigobanco TEXT,'
    + 'cuenta TEXT,'
    + 'cheque TEXT,'
    + 'valorcheque REAL,'
    + 'usuarioasignado TEXT,'
    + 'usuarioasignante TEXT,'
    + 'codigocliente TEXT,'
    + 'nombrecliente TEXT,'
    + 'identificacion TEXT)';

  public SQL_DETALLECOBRO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_DETALLECOBRO + '('
    + 'id INTEGER PRIMARY KEY, '
    + 'estado TEXT, '
    + 'idreferencia TEXT, '
    + 'campo_auditoria TEXT, '
    + 'fecha_actualizacion TEXT, '
    + 'fecha_registro TEXT, '
    + 'observacion TEXT, '
    + 'codigobanco TEXT, '
    + 'cuenta TEXT,'
    + 'fechavencimiento TEXT,'
    + 'idbanco TEXT,'
    + 'nombrebanco TEXT,'
    + 'numerocheque TEXT,'
    + 'numerocomprobante TEXT,'
    + 'numeropago REAL,'
    + 'tipopago TEXT,'
    + 'valorcheque REAL,'
    + 'valorpago REAL,'
    + 'id_cab_cobro TEXT,'
    + 'numerocomprobanteerp TEXT,'
    + 'numerodocumento TEXT,'
    + 'numerofactura TEXT,'
    + 'numeropagoerp TEXT)';

  public SQL_CABECERACOBRO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_CABECERACOBRO + '('
    + 'id INTEGER PRIMARY KEY, '
    + 'estado TEXT, '
    + 'idreferencia TEXT, '
    + 'campo_auditoria TEXT, '
    + 'fecha_actualizacion TEXT, '
    + 'fecha_registro TEXT, '
    + 'observacion TEXT, '
    + 'fechaemision TEXT, '
    + 'fechavencimiento TEXT,'
    + 'idtipoformapago TEXT,'
    + 'monto REAL,'
    + 'numerodocumento TEXT,'
    + 'numerofactura TEXT,'
    + 'redcheque REAL,'
    + 'saldo REAL,'
    + 'banco TEXT,'
    + 'canalcreacion TEXT,'
    + 'codigobanco TEXT,'
    + 'codigocliente TEXT,'
    + 'codigosucursal TEXT,'
    + 'estadoproceso TEXT,'
    + 'idcliente INTEGER,'
    + 'idsucursal INTEGER,'
    + 'idusuario INTEGER,'
    + 'usuarioasignado TEXT,'
    + 'usuarioasignante TEXT,'
    + 'diasvencido INTEGER)';
  
  

  //Creacion Tablas
  public SQL_SINCRONIZACION = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_SINCRONIZACION + '('
    + 'IDSINCV TEXT PRIMARY KEY, '
    + 'FECHAACTUALIZACION TEXT NOT NULL)';


  public SQL_USUARIOS = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_USUARIO + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ESTADO TEXT, '
    + 'CLAVE TEXT, '
    + 'USUARIO TEXT, '
    + 'DESCRIPCION TEXT, '
    + 'NOMBRES TEXT, '
    + 'APELLIDOS TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT,'
    + 'CORREO TEXT)';


  public SQL_SUCURSALES = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_SUCURSALES + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ESTADO TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT,'
    + 'CODIGO TEXT, '
    + 'DIRECCION TEXT, '
    + 'NOMBRE TEXT, '
    + 'IDEMPRESA INTEGER)';


  public SQL_CLIENTES = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_CLIENTES + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ESTADO TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT,'
    + 'CODCLIENTE TEXT, '
    + 'DIRECCION TEXT,  '
    + 'NOMBRECOMPLETO TEXT,  '
    + 'IDENTIFICACION TEXT, '
    + 'CUPO TEXT, '
    + 'FORMAPAGO TEXT, '
    + 'CODIGOLISTAPRECIO TEXT, '
    + 'CODIGOCIUDAD TEXT, '
    + 'CELULAR TEXT, '
    + 'EMAIL TEXT)';


  public SQL_PRODUCTOS = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_PRODUCTOS + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ID_CATEGORIA INTEGER, '
    + 'ESTADO TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT, '
    + 'URLIMAGEN TEXT,  '
    + 'PRECIOUNITARIO REAL,  '
    + 'PRECIOIVA REAL,  '
    + 'NOMBRE TEXT, '
    + 'DESCRIPCION TEXT, '
    + 'DETALLESADICIONALES TEXT, '
    + 'CODIGOAUXILIAR TEXT, '
    + 'CODIGOPRODUCTO TEXT, '
    + 'ID_EMPRESA INTEGER, '
    + 'ID_SUCURSAL INTEGER)';


    public SQL_LISTAPRECIO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_LISTAPRECIO + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ESTADO TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT,'
    + 'DESCRIPCION TEXT, '
    + 'CODIGOLISTAPRECIO TEXT )';


    public SQL_CATEGORIAPRODUCTO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_CATEGORIAPRODUCTO + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ESTADO TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT,'
    + 'CODIGOERP TEXT,'
    + 'LTIPOPRODUCTO TEXT )';


     public SQL_CABORDEN = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_CABORDEN + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ESTADO TEXT, '
    + 'IDREFERENCIA TEXT, '
    + 'CAMPO_AUDITORIA TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT,'
    + 'OBSERVACION TEXT, '
    + 'DIRECCIONCOMPRADOR TEXT, '
    + 'ESTADOPROCESO TEXT, '
    + 'FECHAEMISION TEXT, '
    + 'IDCLIENTE TEXT, '
    + 'IDSUCURSAL INTEGER, '
    + 'IDUSUARIO INTEGER, '
    + 'IDENTIFICACIONCOMPRADOR TEXT, '
    + 'IMPORTETOTAL REAL, '
    + 'MONEDA TEXT, '
    + 'NOMBREVENDEDOR TEXT, '
    + 'NUMEROODEN TEXT, '
    + 'RAZONSOCIALCOMPRADOR TEXT, '
    + 'SUBTOTAL0 REAL, '
    + 'SUBTOTAL12 REAL, '
    + 'TOTALDESCUENTO REAL, '
    + 'TOTALIMPUESTO REAL, '
    + 'TOTALSINIMPUESTOS REAL, '
    + 'CANALCREACION TEXT, '
    + 'CODIGOCLIENTE TEXT, '
    + 'CODIGOSUCURSAL TEXT, '
    + 'USUARIOASIGNADO TEXT, '
    + 'USUARIOASIGNANTE TEXT '
    + '  )';


    public SQL_DETALLESORDEN = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_DETALLESORDEN + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ESTADO TEXT, '
    + 'IDREFERENCIA TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT,'
    + 'OBSERVACION TEXT, '
    + 'CANTIDAD NUMERIC, '
    + 'CODIGOAUXILIAR TEXT, '
    + 'CODIGOPRINCIPAL TEXT, '
    + 'DESCRIPCION TEXT, '
    + 'DESCUENTO REAL, '
    + 'DETALLESADICIONALES TEXT, '
    + 'PRECIOTOTALCONIMPUESTO REAL, '
    + 'PRECIOTOTALSINIMPUESTO REAL, '
    + 'PRECIOUNITARIO REAL, '
    + 'ID_CABECERA INTEGER, '
    + 'ID_PRODUCTO INTEGER, '
    + 'NUMEROORDEN TEXT '
    +' )';


    public SQL_LISTAPRECIOPRODUCTO = 'CREATE TABLE IF NOT EXISTS ' + this.TABLA_LISTAPRECIOPRODUCTO + '('
    + 'ID INTEGER TEXT PRIMARY KEY, '
    + 'ESTADO TEXT, '
    + 'IDREFERENCIA TEXT, '
    + 'CAMPO_AUDITORIA TEXT, '
    + 'FECHAACTUALIZACION TEXT, '
    + 'FECHAREGISTRO TEXT,'
    + 'OBSERVACION TEXT, '
    + 'NOMBRELISTAPRECIO TEXT, '
    + 'PRECIO1 REAL, '
    + 'PRECIO2 REAL, '
    + 'PRECIO3 REAL, '
    + 'ID_PRODUCTO INTEGER, '
    + 'CODIGOLISTAPRECIO TEXT, '
    + 'CODIGOPRODUCTO TEXT, '
    + 'CODIGOUNIDAD TEXT, '
    + 'DESCRIPCIONUNIDAD TEXT, '
    + 'PORCENTAJEDESCUENTO REAL, '
    + 'PORCENTAJEIVA REAL, '
    + 'PRECIOCAJA REAL, '
    + 'PRECIOLIBRA REAL, '
    + 'PRECIOUNITARIO REAL, '
    + 'ID_LISTA_PRODUCTO INTEGER '
    +' )';

  constructor() { }

  setDatabase(db: SQLiteObject) {
    if (this.db === null) {
      this.db = db;
    }
  }

  //CREACION DE TABLAS BASE DE DATOS
  inicializarBaseDatos() {

    //Creacion de tablas
    this.crearTabla(this.TABLA_SINCRONIZACION);
    this.crearTabla(this.TABLA_USUARIO);
    this.crearTabla(this.TABLA_SUCURSALES);
    this.crearTabla(this.TABLA_CLIENTES);
    this.crearTabla(this.TABLA_PRODUCTOS);
    this.crearTabla(this.TABLA_CATEGORIAPRODUCTO);
    this.crearTabla(this.TABLA_LISTAPRECIO);
    this.crearTabla(this.TABLA_CABORDEN);
    this.crearTabla(this.TABLA_DETALLESORDEN);
    this.crearTabla(this.TABLA_LISTAPRECIOPRODUCTO);
    this.crearTabla(this.TABLA_CABECERACOBRO);
    this.crearTabla(this.TABLA_DETALLECOBRO);
    this.crearTabla(this.TABLA_COMPROBANTECOBRO);
    this.crearTabla(this.TABLA_BANCO);
    this.crearTabla(this.TABLA_STOCK);
    this.crearTabla(this.TABLA_CABDESPACHO);
    this.crearTabla(this.TABLA_DETALLESDESPACHO);
    this.crearTabla(this.TABLA_CONFIG);
    return true;
  }

  crearTabla(tabla: string) {
    let sql = "";

    if (this.TABLA_SINCRONIZACION == tabla) {
      sql = this.SQL_SINCRONIZACION;
    }


    if (this.TABLA_USUARIO == tabla) {
      sql = this.SQL_USUARIOS;
    }

    if (this.TABLA_SUCURSALES == tabla) {
      sql = this.SQL_SUCURSALES;
    }

    if (this.TABLA_PRODUCTOS == tabla) {
      sql = this.SQL_PRODUCTOS;
    }

    if (this.TABLA_CLIENTES == tabla) {
      sql = this.SQL_CLIENTES;
    }

    if (this.TABLA_CATEGORIAPRODUCTO == tabla) {
      sql = this.SQL_CATEGORIAPRODUCTO;
    }

    if (this.TABLA_LISTAPRECIO == tabla) {
      sql = this.SQL_LISTAPRECIO;
    }
    if (this.TABLA_CABORDEN == tabla) {
      sql = this.SQL_CABORDEN;
    }
    if (this.TABLA_DETALLESORDEN == tabla) {
      sql = this.SQL_DETALLESORDEN;
    }
    if (this.TABLA_LISTAPRECIOPRODUCTO == tabla) {
      sql = this.SQL_LISTAPRECIOPRODUCTO;
    }

    if (this.TABLA_CABECERACOBRO == tabla) {
      sql = this.SQL_CABECERACOBRO;
    }

    if (this.TABLA_DETALLECOBRO == tabla) {
      sql = this.SQL_DETALLECOBRO;
    }

    if (this.TABLA_COMPROBANTECOBRO == tabla) {
      sql = this.SQL_COMPROBANTECOBRO;
    }

    if (this.TABLA_BANCO == tabla) {
      sql = this.SQL_BANCO;
    }

     if (this.TABLA_STOCK == tabla) {
      sql = this.SQL_STOCK;
    }
    
    if (this.TABLA_CABDESPACHO == tabla) {
      sql = this.SQL_CABDESPACHO;
    }

    if (this.TABLA_DETALLESDESPACHO == tabla) {
      sql = this.SQL_DETALLESDESPACHO;
    }

    if (this.TABLA_CONFIG == tabla) {
      sql = this.SQL_CONFIG;
    }

    console.log("sql ==> " + sql);
    return this.db.executeSql(sql, []);
  }

  eliminarTabla(tabla: string) {
    let sql = 'DROP TABLE IF EXISTS ' + tabla;
    console.log("sql ==> " + sql);
    return this.db.executeSql(sql, []);
  }

  encerarTabla(tabla: string) {
    let sql = 'DELETE FROM ' + tabla;
    console.log("sql ==> " + sql);
    return this.db.executeSql(sql, []);
  }


  insertarRegistros(tabla: string, registro: any) {

    let sql = 'INSERT OR REPLACE INTO [TABLA]([CAMPOS]) VALUES([VALORES])';
    let valores = null;

    if (this.TABLA_SINCRONIZACION == tabla) {
      sql = sql.replace('[TABLA]', this.TABLA_SINCRONIZACION);
      sql = sql.replace('[CAMPOS]', "IDSINCV, FECHAACTUALIZACION");
      sql = sql.replace('[VALORES]', "?,?");

      console.log("sql ==> " + sql);
      valores = [registro.IDSINCV, registro.FECHAACTUALIZACION];
    }


    if (this.TABLA_USUARIO == tabla) {
      sql = sql.replace('[TABLA]', this.TABLA_USUARIO);
      sql = sql.replace('[CAMPOS]', "ID, ESTADO, CLAVE, USUARIO, DESCRIPCION, NOMBRES, APELLIDOS, FECHAACTUALIZACION, FECHAREGISTRO, CORREO");
      sql = sql.replace('[VALORES]', "?,?,?,?,?,?,?,?,?,?");

      console.log("sql ==> " + sql);
      valores = [registro.ID,
      registro.ESTADO,
      registro.CLAVE,
      registro.USUARIO,
      registro.DESCRIPCION,
      registro.NOMBRES,
      registro.APELLIDOS,
      registro.FECHAACTUALIZACION,
      registro.FECHAREGISTRO,
      registro.CORREO
      ];
    }

    if (this.TABLA_SUCURSALES == tabla) {
      sql = sql.replace('[TABLA]', this.TABLA_SUCURSALES);
      sql = sql.replace('[CAMPOS]', "ID, ESTADO, FECHAACTUALIZACION, FECHAREGISTRO, CODIGO, DIRECCION, NOMBRE, IDEMPRESA");
      sql = sql.replace('[VALORES]', "?,?,?,?,?,?,?,?");

      valores = [registro.ID,
      registro.ESTADO,
      registro.FECHAACTUALIZACION,
      registro.FECHAREGISTRO,
      registro.CODIGO,
      registro.DIRECCION,
      registro.NOMBRE,
      registro.IDEMPRESA];
    }


    if (this.TABLA_CLIENTES == tabla) {
      sql = sql.replace('[TABLA]', this.TABLA_CLIENTES);
      sql = sql.replace('[CAMPOS]', "ID, ESTADO, FECHAACTUALIZACION, FECHAREGISTRO, CODCLIENTE, DIRECCION, NOMBRECOMPLETO, IDENTIFICACION, " +
        "CUPO, FORMAPAGO,  CODIGOLISTAPRECIO, CODIGOCIUDAD, CELULAR, EMAIL");
      sql = sql.replace('[VALORES]', "?,?,?,?,?,?,?,?,?,?,?,?,?,?");

      valores = [registro.ID,
      registro.ESTADO,
      registro.FECHAACTUALIZACION,
      registro.FECHAREGISTRO,
      registro.CODCLIENTE,
      registro.DIRECCION,
      registro.NOMBRECOMPLETO,
      registro.IDENTIFICACION,
      registro.CUPO,
      registro.FORMAPAGO,
      registro.CODIGOLISTAPRECIO,
      registro.CODIGOCIUDAD,
      registro.CELULAR,
      registro.EMAIL
      ];
    }

    if (this.TABLA_PRODUCTOS == tabla) {
      sql = sql.replace('[TABLA]', this.TABLA_PRODUCTOS);
      sql = sql.replace('[CAMPOS]', "ID, ESTADO, FECHAACTUALIZACION, FECHAREGISTRO, URLIMAGEN, NOMBRE, DESCRIPCION, DETALLESADICIONALES, " +
        "CODIGOAUXILIAR, CODIGOPRODUCTO,  ID_EMPRESA, ID_SUCURSAL");
      sql = sql.replace('[VALORES]', "?,?,?,?,?,?,?,?,?,?,?,?");
      valores = [registro.ID,
      registro.ESTADO,
      registro.FECHAACTUALIZACION,
      registro.FECHAREGISTRO,
      registro.URLIMAGEN,
      registro.NOMBRE,
      registro.DESCRIPCION,
      registro.DETALLESADICIONALES,
      registro.CODIGOAUXILIAR,
      registro.CODIGOPRODUCTO,
      registro.ID_EMPRESA,
      registro.ID_SUCURSAL
      ];
    }
    console.log("sql ==> " + sql);
    return this.db.executeSql(sql, valores);
  }


  //Verifica Usuario Base Datos
  verificaAccesoUsuario(usuario: string, clave: string) {
    let sql = "SELECT * FROM " + this.TABLA_USUARIO + " WHERE CODUSUARIO=? AND CLAVE=?";
    console.log("sql ==> " + sql);
    return this.db.executeSql(sql, [usuario.toLowerCase(), clave])
      .then(response => {
        let tasks = [];
        for (let index = 0; index < response.rows.length; index++) {
          tasks.push(response.rows.item(index));
        }
        return Promise.resolve(tasks);
      })
      .catch(error => Promise.reject(error));
  }

  verificaSincronizacion() {
    let sql = "SELECT * FROM " + this.TABLA_SINCRONIZACION;
    console.log("sql ==> " + sql);
    return this.db.executeSql(sql, [])
      .then(response => {
        let tasks = [];
        for (let index = 0; index < response.rows.length; index++) {
          tasks.push(response.rows.item(index));
        }
        return Promise.resolve(tasks);
      })
      .catch(error => Promise.reject(error));
  }

  listaProductoLikeNombre(patronNombre:string){

    let sql = "SELECT * FROM "+this.TABLA_PRODUCTOS+" WHERE  NOMBRE LIKE  '%"+ patronNombre.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaProductoLikeCodigo(patronCodigo:string){

    let sql = "SELECT * FROM "+this.TABLA_PRODUCTOS+" WHERE  CODIGOPRODUCTO LIKE  '%"+ patronCodigo.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaClienteLikeNombre(patronNombre:string){

    let sql = "SELECT *, (select DESCRIPCION from " + this.TABLA_LISTAPRECIO +" a where a.CODIGOLISTAPRECIO = CODIGOLISTAPRECIO  ) listaPrecio FROM "+this.TABLA_CLIENTES+" WHERE  NOMBRECOMPLETO LIKE  '%"+ patronNombre.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaClienteLikeRegistroFiscal(patronCodigo:string){

    let sql = "SELECT *, (select DESCRIPCION from " + this.TABLA_LISTAPRECIO +" a where a.CODIGOLISTAPRECIO = CODIGOLISTAPRECIO  ) listaPrecio FROM "+this.TABLA_CLIENTES+" WHERE  IDENTIFICACION LIKE  '%"+ patronCodigo.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }


  listaSucursales(){

    let sql = "SELECT * FROM "+this.TABLA_SUCURSALES;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaCategoriaProducto() {

    let sql = "SELECT * FROM "+this.TABLA_CATEGORIAPRODUCTO;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }  


  listaProductoCategoria(idCategoria){

    let sql = "SELECT * FROM "+this.TABLA_PRODUCTOS+" WHERE  ID_CATEGORIA = "+ idCategoria;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaProductoLikeNombreCat(patronNombre:string, idCategoria){

    let sql = "SELECT * FROM "+this.TABLA_PRODUCTOS+" WHERE ID_CATEGORIA = "+ idCategoria + " and NOMBRE LIKE  '%"+ patronNombre.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaProductoLikeCodigoCat(patronCodigo:string, idCategoria){

    let sql = "SELECT * FROM "+this.TABLA_PRODUCTOS+" WHERE  ID_CATEGORIA = "+ idCategoria + " and CODIGOPRODUCTO LIKE  '%"+ patronCodigo.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaOrdenesDia(){

    let sql = "SELECT * FROM "+this.TABLA_CABORDEN+" WHERE  CANALCREACION = 'MOB' ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaOrdenesDiaNombre(patronNombre:string) {   

    let sql = "SELECT * FROM "+this.TABLA_CABORDEN+" WHERE   CANALCREACION = 'MOB' and  RAZONSOCIALCOMPRADOR LIKE  '%"+ patronNombre.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }


clienteRegistroFiscal(identificacion:string){

    let sql = "SELECT *, (select DESCRIPCION from " + this.TABLA_LISTAPRECIO +" a where a.CODIGOLISTAPRECIO = CODIGOLISTAPRECIO  ) listaPrecio FROM " + 
    this.TABLA_CLIENTES + 
    " WHERE  IDENTIFICACION LIKE  '%"+ identificacion +"%'" ;
    
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

   listaOrdenesDiaReferencia(referencia: String){

    let sql = "SELECT * FROM "+this.TABLA_CABORDEN+" WHERE   CANALCREACION = 'MOB' and IDREFERENCIA =" + referencia;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaDetalleOrdenesDiaReferencia(referencia: String){

    let sql = "SELECT * FROM "+this.TABLA_DETALLESORDEN+" WHERE   IDREFERENCIA =" + referencia;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  eliminarOrdenDia(referencia: String){

    let sql = "DELETE FROM "+this.TABLA_CABORDEN+" WHERE  ESTADOPROCESO = 'C' and CANALCREACION = 'MOB' and IDREFERENCIA =" + referencia;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {      
      return Promise.resolve( true );
    })
    .catch(error => Promise.reject(error));

  }

  eliminarDetalleOrdenesDiaReferencia(referencia: String){

    let sql = "DELETE FROM "+this.TABLA_DETALLESORDEN+" WHERE   IDREFERENCIA =" + referencia;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      return Promise.resolve( true );
    })
    .catch(error => Promise.reject(error));

  }


  listaCobrosCliente(cliente){

    let sql = "SELECT b.*, "
     + " IFNULL( ( select sum ( a.valorpago ) from " 
     +   this.TABLA_DETALLECOBRO 
     + " a where a.numerodocumento = b.numerodocumento and a.numerofactura = b.numerofactura) ,  0  )  pagosrealizados " 
     + " FROM " 
     + this.TABLA_CABECERACOBRO  
     + " b WHERE  b.codigocliente =  '"+ cliente.CODCLIENTE +"'" ;
    
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => { 
      Promise.reject(error);
      console.log (error);
      });
  }

 listaCobrosDia(){

    let sql = "SELECT * FROM "+this.TABLA_COMPROBANTECOBRO;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaCobrosDiaNombre(patronNombre:string) {

    let sql = "SELECT * FROM "+this.TABLA_COMPROBANTECOBRO+" WHERE  nombrecliente LIKE  '%"+ patronNombre.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaBancos() {

    let sql = "SELECT * FROM "+this.TABLA_BANCO;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  pagosRealizadosNumeroPago(numeroPago) {

    let sql = "SELECT * FROM "+this.TABLA_DETALLECOBRO + " WHERE numeropago =" + numeroPago ;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  //------
   obtenerListaDespachos(){
    let sql = "SELECT * FROM "+this.TABLA_CABDESPACHO + " WHERE estadoproceso = 'XDESPACHAR' " ;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

   obtenerListaDespachosRealizados(){
    let sql = "SELECT * FROM "+this.TABLA_CABDESPACHO + " WHERE estadoproceso in ('DESPACHADOLOCAL', 'DESPACHADOREPLICADO') " ;
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  ///---

  listaDespachoNombre(patronNombre:string) {

    let sql = "SELECT * FROM "+this.TABLA_CABDESPACHO+" WHERE  estadoproceso = 'XDESPACHAR' and  razonsocialcomprador LIKE  '%"+ patronNombre.toUpperCase() +"%'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaDetalleDespacho(item) {
    let sql = "SELECT *, ( SELECT a.saldo from " +  this.TABLA_STOCK + " a where a.codigoproducto = codigoprincipal ) saldostock FROM "+ this.TABLA_DETALLESDESPACHO +" WHERE  numerooden = '" + item.numerooden +"'";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }


  listaProductoCategoriaStock(idCategoria){

    let sql = "SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE  a.ID_CATEGORIA = "+ idCategoria 
    + " and b.codigoproducto = a.CODIGOPRODUCTO and b.saldo > 0  ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaProductoLikeCodigoStock(patronCodigo:string){

    let sql ="SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE  a.CODIGOPRODUCTO LIKE  '%"+ patronCodigo.toUpperCase() +"%'" 
    + " and b.codigoproducto = a.CODIGOPRODUCTO and b.saldo > 0  ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

   listaProductoLikeCodigoCatStock(patronCodigo:string, idCategoria){

    let sql = "SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE  a.ID_CATEGORIA = "+ idCategoria + " and a.CODIGOPRODUCTO LIKE  '%"+ patronCodigo.toUpperCase() +"%'"
    + " and b.codigoproducto = a.CODIGOPRODUCTO and b.saldo > 0  ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaProductoLikeNombreStock(patronNombre:string){

    let sql = "SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE  a.NOMBRE LIKE  '%"+ patronNombre.toUpperCase() +"%'"
    + " and b.codigoproducto = a.CODIGOPRODUCTO and b.saldo > 0  ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }


  listaProductoLikeNombreCatStock(patronNombre:string, idCategoria){

    let sql = "SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE ID_CATEGORIA = "+ idCategoria + " and NOMBRE LIKE  '%"+ patronNombre.toUpperCase() +"%'"
    + " and b.codigoproducto = a.CODIGOPRODUCTO and b.saldo > 0  ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }


  //--------------

    listaProductoCategoriaStock_(idCategoria){

    let sql = "SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE  a.ID_CATEGORIA = "+ idCategoria 
    + " and b.codigoproducto = a.CODIGOPRODUCTO  ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaProductoLikeCodigoStock_(patronCodigo:string){

    let sql ="SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE  a.CODIGOPRODUCTO LIKE  '%"+ patronCodigo.toUpperCase() +"%'" 
    + " and b.codigoproducto = a.CODIGOPRODUCTO   ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

   listaProductoLikeCodigoCatStock_(patronCodigo:string, idCategoria){

    let sql = "SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE  a.ID_CATEGORIA = "+ idCategoria + " and a.CODIGOPRODUCTO LIKE  '%"+ patronCodigo.toUpperCase() +"%'"
    + " and b.codigoproducto = a.CODIGOPRODUCTO   ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaProductoLikeNombreStock_(patronNombre:string){

    let sql = "SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE  a.NOMBRE LIKE  '%"+ patronNombre.toUpperCase() +"%'"
    + " and b.codigoproducto = a.CODIGOPRODUCTO   ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }


  listaProductoLikeNombreCatStock_(patronNombre:string, idCategoria){

    let sql = "SELECT a.*, b.saldo, b.preciounitario, b.precioconimpuesto, b.precionsinimpuesto FROM "+this.TABLA_PRODUCTOS + " a, "+ this.TABLA_STOCK + " b WHERE ID_CATEGORIA = "+ idCategoria + " and NOMBRE LIKE  '%"+ patronNombre.toUpperCase() +"%'"
    + " and b.codigoproducto = a.CODIGOPRODUCTO   ";
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

   listaDatosReplicar(){

    let comprobanteSinReplicar = "( SELECT count (*) FROM "+this.TABLA_COMPROBANTECOBRO + " where estado = 'C' ) comprobanteSinReplicar ";
    let ordenesSinReplicar = "( SELECT count (*) FROM "+this.TABLA_CABORDEN + " where ESTADOPROCESO = 'C' ) ordenesSinReplicar ";
    let despachosSinReplicar = " ( SELECT count (*) FROM "+this.TABLA_CABDESPACHO + " where estadoproceso = 'DESPACHADOLOCAL' ) despachosSinReplicar";
    
    let sql = "SELECT " + comprobanteSinReplicar + " , " 
                        + ordenesSinReplicar + " , " 
                        + despachosSinReplicar ;    
    console.log("sql ==> "+sql);
    return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));
  }

  listaCabeceraOrdenesReplicar() {

     let sql = "SELECT * FROM "+this.TABLA_CABORDEN + " where ESTADOPROCESO = 'C'";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaDetallesOrdenesReplicar() {  

     let sql = "SELECT * FROM " + this.TABLA_DETALLESORDEN + " WHERE IDREFERENCIA IN ( SELECT IDREFERENCIA FROM "+this.TABLA_CABORDEN + " where ESTADOPROCESO = 'C' ) ";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }


  



  listaCabeceraOrdenesReplicada() {

     let sql = "UPDATE "+this.TABLA_CABORDEN + " SET ESTADOPROCESO = 'R' where ESTADOPROCESO = 'C'";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  //----

  listaCromprobantesReplicar() {

     let sql = "SELECT * FROM "+this.TABLA_COMPROBANTECOBRO + " where estado = 'C'";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaDetallesPagoReplicar() {  

     let sql = "SELECT * FROM " + this.TABLA_DETALLECOBRO + " WHERE idreferencia IN ( SELECT idreferencia FROM "+this.TABLA_COMPROBANTECOBRO + " where estado = 'C' ) ";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaCabeceraCobroReplicada() {

     let sql = "UPDATE "+this.TABLA_COMPROBANTECOBRO + " SET estado = 'R' where estado = 'C'";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  //*-****

  listaDespachoReplicar() {

     let sql = "SELECT * FROM "+this.TABLA_CABDESPACHO + " where estadoproceso = 'DESPACHADOLOCAL'";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }

  listaDetallesDespacho() {

     let sql = "SELECT * FROM " + this.TABLA_DETALLESDESPACHO + " WHERE idreferencia IN ( SELECT idreferencia FROM "+this.TABLA_CABDESPACHO + " where estadoproceso = 'DESPACHADOLOCAL' ) ";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));

  }


  listaCabeceraDespachoReplicada() {  

     let sql = "UPDATE "+this.TABLA_CABDESPACHO + " SET estadoproceso  = 'DESPACHADOREPLICADO' where estadoproceso = 'DESPACHADOLOCAL'";
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));  

  }

  ipconfiguracionapi() {  

     let sql = "SELECT * FROM " + this.TABLA_CONFIG;
     
     return this.db.executeSql(sql, [])
    .then(response => {
      let tasks = [];
      for (let index = 0; index < response.rows.length; index++) {
        tasks.push( response.rows.item(index) );
      }
      return Promise.resolve( tasks );
    })
    .catch(error => Promise.reject(error));  

  }

}