import { Component, OnInit } from '@angular/core';
import { MessagesProvider } from '../messages/messages';
import { AuthenticationService } from '../servicios/authentication.service';
import { Router } from '@angular/router';
import { ControlaccesologinService } from '../servicios/login/controlaccesologin.service';
import { UtilmensajeService } from '../servicios/transversales/utilmensaje.service';
import { TasksServiceProvider } from '../providers/tasks-service';
import { AlertController } from '@ionic/angular';
import { ContextoService } from '../servicios/configuracion/contexto.service';
import { LoadingService } from '../servicios/transversales/loading.service';
import { ConfigipPage } from '../configip/configip.page';
import { ModalController } from '@ionic/angular';
import {ControlParametrosService} from '../servicios/transversales/control-parametros.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  titulo: string;
  formUsuario: string;
  formClave: string;
  formUsuarioPH: string;
  formClavePH: string;
  formBtn: string;

  postData = {
    username: '',
    password: ''
  };

  constructor(private msg: MessagesProvider, private router: Router, private auth: AuthenticationService,
    private controlAcceso: ControlaccesologinService, private utilMensaje: UtilmensajeService,
    private tasksService: TasksServiceProvider, public alertController: AlertController, public viewCtrl: ModalController, 
    private contextoService: ContextoService, private loadingService: LoadingService, private controlParametros: ControlParametrosService) {
    this.titulo = this.msg.get('titulo_login');
    this.formUsuario = this.msg.get('form_login_usuario');
    this.formClave = this.msg.get('form_login_clave');
    this.formUsuarioPH = this.msg.get('form_login_usuario_ph');
    this.formClavePH = this.msg.get('form_login_clave_ph');
    this.formBtn = this.msg.get('form_login_btn');

  }

  ngOnInit() {



  }

  validarInputs() {
    console.log(this.postData);
    return (
      this.postData.username &&
      this.postData.password &&
      this.postData.username.trim().length > 0 &&
      this.postData.password.trim().length > 0
    );
  }

  login() {

     this.tasksService.ipconfiguracionapi().then( res => {

              if (res.length > 0) {

                this.controlParametros.setParametro("ip_api", res[0].ipapigateway);

              if (this.validarInputs()) {

                this.tasksService.verificaSincronizacion().then(dataSincronizacion => {

                  if (dataSincronizacion.length > 0) {

                    this.tasksService.verificaAccesoUsuario(this.postData.username, this.postData.password).then(dataLogin => {

                      if (dataLogin.length > 0) {

                        //validar la fecha de sincronizacion fecha de ultima sincronizacion vs fecha actual
                        console.log("Datos de login " + JSON.stringify(dataLogin));
                        console.log("Datos de sincronizacion " + JSON.stringify(dataSincronizacion));

                        let fechaUltimaSincronizacion = new Date(dataSincronizacion[0].FECHAACTUALIZACION);
                        let fechaActual = new Date();
                        fechaActual.setHours(0, 0, 0, 0);

                        if (fechaUltimaSincronizacion < fechaActual) {

                          let alert = this.alertController.create({
                            header: 'Atención',
                            message: 'El Sistema necesita ser Actualizado antes de empezar su operación.',
                            buttons: ["Aceptar"]
                          }).then(res => {

                            res.present().then(data => {
                              
                            });

                          });


                        } else {
                          this.controlAcceso.getDataLogin().username = dataLogin[0].USUARIO;
                          this.controlAcceso.getDataLogin().nombre = dataLogin[0].NOMBRES + dataLogin[0].APELLIDOS;
                          this.controlAcceso.getDataLogin().email = dataLogin[0].CORREO;
                          this.controlAcceso.setPresentarMenu(true);
                          this.auth.login('{usuario: ' + this.controlAcceso.getDataLogin().username 
                                        + ', nombre: ' + this.controlAcceso.getDataLogin().nombre 
                                         +', email: '  + this.controlAcceso.getDataLogin().email + '}');
                          this.router.navigateByUrl('/folder/PrincipalLogin');
                        }



                      } else {
                        this.utilMensaje.presentarMensaje(
                          'usuario/password incorrecto.');
                      }

                    });

                  } else {

                    let alert = this.alertController.create({
                      header: 'Atención',
                      message: 'El Sistema necesita ser Actualizado antes de empezar su operación.',
                      buttons: [{
                        text: 'Aceptar',
                        handler: () => {
                          this.validarUsuarioRemoto();
                        }
                      }
                      ]
                    }).then(res => {

                      res.present();
                    });
                  }
                });

                /*
                if (this.postData.username === 'ADMIN' && this.postData.password === 'ADMIN') {
                  this.controlAcceso.getDataLogin().username = this.postData.username;
                  this.controlAcceso.getDataLogin().nombre = 'Administrador';
                  this.controlAcceso.getDataLogin().email = 'admin@gmail.com';
                  this.controlAcceso.setPresentarMenu(true);
                  this.auth.login('{usuario: "BYRON"}');
                  this.router.navigateByUrl('/folder/PrincipalLogin');
                } else {
                  this.utilMensaje.presentarMensaje(
                      'usuario/password incorrecto.'
                  );
                }*/

              } else {
                this.utilMensaje.presentarMensaje(
                  'Por favor ingrese su usuario/password.'
                );
              }
        } else {

            let alert = this.alertController.create({
                header: 'Atención',
                message: 'No se encuentra registrada la url del sistema de órdenes de pedido',
                buttons: [{
                  text: 'Aceptar'
                }
                ]
              }).then(res => {

                res.present();
              });

        }

     }).catch(e => {
              console.error(e);
              let alert = this.alertController.create({
                header: 'Atención',
                message: 'Error al realizar la verificacion de la url del api',
                buttons: [{
                  text: 'Aceptar'
                }
                ]
              }).then(res => {

                res.present();
              });
            }
            );

  }

  validarUsuarioRemoto() {

    //this.loadingService.loadingPresent('Autenticando con el servidor');
    let postData = {
      "usuario": this.postData.username,
      "password": this.postData.password
    };


    this.loadingService.loadingPresent('Autenticandose con el servidor, esta operación puede tardar unos minutos...');

    this.contextoService.invocacionServicioPost(postData, this.contextoService.getServicioLogin()).subscribe(dataLoginRemoto => {
      if (dataLoginRemoto.usuario) {
        console.log("Data " + JSON.stringify(dataLoginRemoto));
        //this.loadingService.loadingDismiss();
        //llamar la descarga de datos 

        let postData = {
          "usuario": this.postData.username
        };

        this.contextoService.invocacionServicioPost(postData, this.contextoService.getServicioBaseDatos()).subscribe(
          dataBase => {
            console.log("Data " + JSON.stringify(dataBase));


            //--

            //recrear tablas, las que el servidor se descarga
            this.recrearTablas().then(res => {

            this.insertarDatos(dataLoginRemoto, dataBase).then(data => {

              this.irPaginaPrincipal(dataLoginRemoto);

            }).catch(e => {
              console.error(e);
              this.loadingService.loadingDismiss();
              let alert = this.alertController.create({
                header: 'Atención',
                message: 'Error al realizar la actualización de datos',
                buttons: [{
                  text: 'Aceptar'
                }
                ]
              }).then(res => {

                res.present();
              });
            }
            );
            //---
            }).catch(e => {
                  console.error(e);
                  this.loadingService.loadingDismiss();
                  let alert = this.alertController.create({
                    header: 'Atención',
                    message: 'Error al realizar preparación de las tablas locales',
                    buttons: [{
                      text: 'Aceptar'
                    }
                    ]
                  }).then(res => {

                    res.present();
                  });
                }
                );
          });

      } else {
        this.loadingService.loadingDismiss();
        this.utilMensaje.presentarMensaje(
          'usuario/password incorrecto.');
      }
    }, error => {

        this.loadingService.loadingDismiss();  
        let alert = this.alertController.create({
            header: 'Error',
            message: 'No tiene conexón con el servidor, por favor asegurese que está conectado a internet',
            buttons: [{
              text: 'Aceptar',
              handler: () => {
                //this.validarUsuarioRemoto();
              }
            }
            ]
          }).then(res => {
            res.present();
          });

    });
  }


  insertarDatos(dataUsuario, dataBase) {

    //Variables
    let parametroWS: any;
    let respuestaWS: any;
    let sqlGeneral = 'INSERT OR REPLACE INTO [TABLA]([CAMPOS]) VALUES([VALORES]);';
    let sqlInsert: Array<string> = [];

    return new Promise((resolve, reject) => {

      this.tasksService.insertarRegistros(this.tasksService.TABLA_USUARIO, dataUsuario).then(data => {

        let sqlSucursales = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_SUCURSALES);
        sqlSucursales = sqlSucursales.replace('[CAMPOS]', "ID, ESTADO, FECHAACTUALIZACION, FECHAREGISTRO, CODIGO, " +
          "DIRECCION, NOMBRE, IDEMPRESA");

        let arregloSucursal = dataBase.listaSucursales;

        console.log("Data  Suc" + JSON.stringify(arregloSucursal));

        arregloSucursal.forEach(element => {
          console.log(element);

          sqlInsert.push(sqlSucursales.replace('[VALORES]', "\"" + element.id + "\"," +
            "\"" + element.estado + "\"," +
            "\"" + element.fechaActualizacion + "\"," +
            "\"" + element.fechaRegistro + "\"," +
            "\"" + element.codigo + "\"," +
            "\"" + element.direccion + "\"," +
            "\"" + element.nombre + "\"," +
            "\"0\""));

        });

        sqlInsert.forEach(element => {
          console.log(element);
        });

        this.tasksService.db.sqlBatch(sqlInsert)
          .then(() => {
            let sqlInsert: Array<string> = [];
            let sqlClientes = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_CLIENTES);

            sqlClientes = sqlClientes.replace('[CAMPOS]', "ID, ESTADO, FECHAACTUALIZACION, FECHAREGISTRO, CODCLIENTE, " +
              "DIRECCION, NOMBRECOMPLETO, IDENTIFICACION, CUPO, FORMAPAGO, CODIGOLISTAPRECIO,  CODIGOCIUDAD, CELULAR, EMAIL");

            let arregloCliente = dataBase.listaClientes;
            arregloCliente.forEach(element => {
              console.log(element);

              sqlInsert.push(sqlClientes.replace('[VALORES]', "\"" + element.id + "\"," +
                "\"" + element.estado + "\"," +
                "\"" + element.fechaActualizacion + "\"," +
                "\"" + element.fechaRegistro + "\"," +
                "\"" + element.codigoCliente + "\"," +
                "\"" + element.direccionComprador + "\"," +
                "\"" + element.razonSocialComprador + "\"," +
                "\"" + element.identificacionComprador + "\"," +
                "\"" + element.cupo + "\"," +
                "\"" + element.formaPago + "\"," +
                "\"" + element.codigoListaPrecio + "\"," +
                "\"" + element.codigoCiudad + "\"," +
                "\"" + element.celularComprador + "\"," +
                "\"" + element.emailsComprador + "\""));
            });

            sqlInsert.forEach(element => {
              console.log(element);
            });


            this.tasksService.db.sqlBatch(sqlInsert)
              .then(() => {

                let sqlInsert: Array<string> = [];
                let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_PRODUCTOS);

                sqlProductos = sqlProductos.replace('[CAMPOS]', "ID, ID_CATEGORIA, ESTADO, FECHAACTUALIZACION, FECHAREGISTRO, URLIMAGEN, " +
                  "NOMBRE, DESCRIPCION, DETALLESADICIONALES, CODIGOAUXILIAR, CODIGOPRODUCTO, PRECIOUNITARIO, PRECIOIVA,  ID_EMPRESA,  ID_SUCURSAL");

                let arregloProducto = dataBase.listaProductos;

                arregloProducto.forEach(element => {
                  console.log(element);

                  //tipoProducto

                  sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                  "\"" + element.tipoProducto.id + "\"," +
                    "\"" + element.estado + "\"," +
                    "\"" + element.fechaActualizacion + "\"," +
                    "\"" + element.fechaRegistro + "\"," +
                    "\"" + element.imagenReferencia + "\"," +
                    "\"" + element.lNombre + "\"," +
                    "\"" + element.lDescripcion + "\"," +
                    "\"" + element.lDetallesAdicionales + "\"," +
                    "\"" + element.lCodigoAuxiliar + "\"," +
                    "\"" + element.lCodigoProducto + "\"," +
                    "\"" + element.lPrecioUnitario + "\"," +
                    "\"" + element.lPrecioIVA + "\"," +
                    "\"0\"," +
                    "\"0\""));
                });

                sqlInsert.forEach(element => {
                  console.log(element);
                });

                this.tasksService.db.sqlBatch(sqlInsert)
                  .then(() => {


                    let sqlInsert: Array<string> = [];
                    let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_CATEGORIAPRODUCTO);

                    sqlProductos = sqlProductos.replace('[CAMPOS]', "ID, ESTADO, FECHAACTUALIZACION, FECHAREGISTRO, CODIGOERP,LTIPOPRODUCTO");


                    let arregloProducto = dataBase.listaCategoria;

                    arregloProducto.forEach(element => {
                      console.log(element);

                      sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                        "\"" + element.estado + "\"," +
                        "\"" + element.fechaActualizacion + "\"," +
                        "\"" + element.fechaRegistro + "\"," +
                        "\"" + element.idReferencia + "\"," +
                        "\"" + element.lTipoProducto + "\""));
                    });

                    sqlInsert.forEach(element => {
                      console.log(element);
                    });

                    this.tasksService.db.sqlBatch(sqlInsert)
                      .then(() => {
                        
                        let sqlInsert: Array<string> = [];
                        let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_LISTAPRECIO);

                        sqlProductos = sqlProductos.replace('[CAMPOS]', "ID, ESTADO, FECHAACTUALIZACION, FECHAREGISTRO, " +
                          "DESCRIPCION, CODIGOLISTAPRECIO");


                        let arregloProducto = dataBase.listaPrecio;

                        arregloProducto.forEach(element => {
                          console.log(element);

                          sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                            "\"" + element.estado + "\"," +
                            "\"" + element.fechaActualizacion + "\"," +
                            "\"" + element.fechaRegistro + "\"," +
                            "\"" + element.descripcion + "\"," +
                            "\"" + element.codigoListaPrecio + "\""));
                        });

                        sqlInsert.forEach(element => {
                          console.log(element);
                        });

                        this.tasksService.db.sqlBatch(sqlInsert)
                          .then(() => {

                        let sqlInsert: Array<string> = [];
                        let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_LISTAPRECIOPRODUCTO);

                        sqlProductos = sqlProductos.replace('[CAMPOS]', "ID, ESTADO, IDREFERENCIA, CAMPO_AUDITORIA, FECHAACTUALIZACION, FECHAREGISTRO, " +
                          "OBSERVACION,  CODIGOLISTAPRECIO, CODIGOPRODUCTO, CODIGOUNIDAD, " +
                          "DESCRIPCIONUNIDAD, PORCENTAJEDESCUENTO, PORCENTAJEIVA, PRECIOCAJA, PRECIOLIBRA, PRECIOUNITARIO"
                          );



                        let arregloProducto = dataBase.listaPrecio;

                        arregloProducto.forEach(element => {
                          console.log(element);

                          sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                            "\"" + element.estado + "\"," +
                            "\"" + element.idReferencia + "\"," +
                            "\"" + element.auditoria + "\"," +
                            "\"" + element.fechaActualizacion + "\"," +
                            "\"" + element.fechaRegistro + "\"," +
                            "\"" + element.observacion + "\"," +
                            
                            "\"" + element.codigoListaPrecio + "\"," +
                            "\"" + element.codigoProducto + "\"," +
                            "\"" + element.codigoUnidad + "\"," +
                            "\"" + element.descripcionUnidad + "\"," +
                            "\"" + element.porcentajeDescuento + "\"," +
                            "\"" + element.porcentajeIVA + "\"," +
                            "\"" + element.precioCaja + "\"," +
                            "\"" + element.precioLibra + "\"," +
                            "\"" + element.precioUnitario + "\""));
                        });

                        sqlInsert.forEach(element => {
                          console.log(element);
                        });

                            this.tasksService.db.sqlBatch(sqlInsert)
                          .then(() => {

                            //--------cobros
                            let sqlInsert: Array<string> = [];
                            let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_CABECERACOBRO);

                            sqlProductos = sqlProductos.replace('[CAMPOS]', "id, estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, " +
                              "observacion,  fechaemision, fechavencimiento, idtipoformapago, monto, " +
                              "numerodocumento, numerofactura, redcheque, saldo, banco, canalcreacion, codigobanco, codigocliente, codigosucursal, "
                              +"estadoproceso, idcliente, idsucursal, idusuario, usuarioasignado, usuarioasignante, diasvencido"
                            );

                            let arregloProducto = dataBase.listaCobros;
                          arregloProducto.forEach(element => {
                          console.log(element);
                          sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                            "\"" + element.estado + "\"," +
                            "\"" + element.idReferencia + "\"," +
                            "\"" + element.auditoria + "\"," +
                            "\"" + element.fechaActualizacion + "\"," +
                            "\"" + element.fechaRegistro + "\"," +
                            "\"" + element.observacion + "\"," +
                            
                            "\"" + element.fechaEmision + "\"," +
                            "\"" + element.fechaVencimiento + "\"," +
                            "\"" + element.idTipoFormaPago + "\"," +
                            "\"" + element.monto + "\"," +
                            "\"" + element.numeroDocumento + "\"," +
                            "\"" + element.numeroFactura + "\"," +
                            "\"" + element.redCheque + "\"," +
                            "\"" + element.saldo + "\"," +
                            "\"" + element.banco + "\"," +
                            "\"" + element.canalCreacion + "\"," +
                            "\"" + element.codigoBanco + "\"," +
                            "\"" + element.codigoCliente + "\"," +
                            "\"" + element.codigoSucursal + "\"," +
                            "\"" + element.estadoProceso + "\"," +
                            "\"" + element.idCliente + "\"," +
                            "\"" + element.idCucursal + "\"," +
                            "\"" + element.idUsuario + "\"," +
                            "\"" + element.usuarioAsignado + "\"," +
                            "\"" + element.usuarioAsignante + "\"," +
                            "\"" + element.diasVencido + "\""));
                        });

                        sqlInsert.forEach(element => {
                          console.log(element);
                        });

                           this.tasksService.db.sqlBatch(sqlInsert)
                          .then(() => {


                            let sqlInsert: Array<string> = [];
                            let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_BANCO);

                            sqlProductos = sqlProductos.replace('[CAMPOS]', "id, estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, " +
                              "observacion, codigobanco, nombrebanco"
                            );

                            let arregloProducto = dataBase.listaBanco;

                            arregloProducto.forEach(element => {
                                    console.log(element);
                                    sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                                    "\"" + element.estado + "\"," +
                                    "\"" + element.idReferencia + "\"," +
                                    "\"" + element.auditoria + "\"," +
                                    "\"" + element.fechaActualizacion + "\"," +
                                    "\"" + element.fechaRegistro + "\"," +
                                    "\"" + element.observacion + "\"," +
                                    
                                    "\"" + element.codigoBanco + "\"," +
                                    "\"" + element.nombreBanco + "\""));
                             });

                               this.tasksService.db.sqlBatch(sqlInsert)
                              .then(() => {
                                  //Stock

                                  let sqlInsert: Array<string> = [];
                                  let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_STOCK);

                                  sqlProductos = sqlProductos.replace('[CAMPOS]', "id, estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, observacion, " +
                                      "canalcreacion, cantidadentera, codigoproducto, codigosucursal, contidaddecimal, idsucursal, idusuario, placacamion, usuarioasignado, " +
                                      "usuarioasignante, preciounitario, precioconimpuesto, precionsinimpuesto, saldo"
                                  );

                                  let arregloProducto = dataBase.listaStockDespacho;

                                   arregloProducto.forEach(element => {
                                    console.log(element);
                                    sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                                    "\"" + element.estado + "\"," +
                                    "\"" + element.idReferencia + "\"," +
                                    "\"" + element.auditoria + "\"," +
                                    "\"" + element.fechaActualizacion + "\"," +
                                    "\"" + element.fechaRegistro + "\"," +
                                    "\"" + element.observacion + "\"," +
                                    
                                    "\"" + element.canalCreacion + "\"," +
                                    "\"" + element.cantidadEntera + "\"," +
                                    "\"" + element.codigoProducto + "\"," +
                                    "\"" + element.codigoSucursal + "\"," +
                                    "\"" + element.contidadDecimal + "\"," +
                                    "\"" + element.idSucursal + "\"," +
                                    "\"" + element.idUsuario + "\"," +
                                    "\"" + element.placaCamion + "\"," +
                                    "\"" + element.usuarioAsignado + "\"," +
                                    "\"" + element.usuarioAsignante + "\"," +
                                    "\"" + element.precioUnitario + "\"," +
                                    "\"" + element.precioConImpuesto + "\"," +
                                    "\"" + element.precionSinImpuesto + "\"," +
                                    "\"" + element.saldo + "\""));
                                    });

                                   this.tasksService.db.sqlBatch(sqlInsert)
                                    .then(() => {  
                                      //CABDESPACHO            
                                      
                                      let sqlInsert: Array<string> = [];
                                      let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_CABDESPACHO);

                                      sqlProductos = sqlProductos.replace('[CAMPOS]', "id, estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, observacion, " +
                                        "direccioncomprador, estadoproceso, fechaemision, idcliente, idsucursal, idusuario, identificacioncomprador, importetotal, moneda, " +
                                        "nombrevendedor, numerofactura, numerooden, razonsocialcomprador, subtotal0, subtotal12, totaldescuento, totalimpuesto, totalsinimpuestos, canalcreacion, " +
                                        "codigocliente, codigosucursal, diaspago, placavehiculo, usuarioasignado, usuarioasignante, descripcionlistaprecio, tipopago, "+
                                        "codigoticket, cichofer, nombrechofer, camion, fechaaut, autorizacion, claveacceso, telefono "
                                      );
                                      
                                      

                                      let arregloProducto = dataBase.listaDespacho;
                                      
                                       arregloProducto.forEach(element => {
                                        console.log(element);
                                        sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                                        "\"" + element.estado + "\"," +
                                        "\"" + element.idReferencia + "\"," +
                                        "\"" + element.auditoria + "\"," +
                                        "\"" + element.fechaActualizacion + "\"," +
                                        "\"" + element.fechaRegistro + "\"," +
                                        "\"" + element.observacion + "\"," +
                                        
                                        "\"" + element.direccionComprador + "\"," +
                                        "\"" + element.estadoProceso + "\"," +
                                        "\"" + element.fechaEmision + "\"," +
                                        "\"" + element.idCliente + "\"," +
                                        "\"" + element.idSucursal + "\"," +
                                        "\"" + element.idUsuario + "\"," +
                                        "\"" + element.identificacionComprador + "\"," +
                                        "\"" + element.importeTotal + "\"," +
                                        "\"" + element.moneda + "\"," +

                                        "\"" + element.nombreVendedor + "\"," +
                                        "\"" + element.numeroFactura + "\"," +
                                        "\"" + element.numeroOden + "\"," +
                                        "\"" + element.razonSocialComprador + "\"," +
                                        "\"" + element.subTotal0 + "\"," +
                                        "\"" + element.subTotal12 + "\"," +
                                        "\"" + element.totalDescuento + "\"," +
                                        "\"" + element.totalImpuesto + "\"," +
                                        "\"" + element.totalSinImpuestos + "\"," +
                                        "\"" + element.canalCreacion + "\"," +

                                        "\"" + element.codigoCliente + "\"," +
                                        "\"" + element.codigoSucursal + "\"," +
                                        "\"" + element.diasPago + "\"," +
                                        "\"" + element.placaVehiculo + "\"," +
                                        "\"" + element.usuarioAsignado + "\"," +
                                        "\"" + element.usuarioAsignante + "\"," +
                                        "\"" + element.descripcionListaPrecio + "\"," +
                                        "\"" + element.tipoPago + "\"," +
                                        "\"" + element.codigoTicket + "\"," +
                                        "\"" + element.ciChofer + "\"," +
                                        "\"" + element.nombreChofer + "\"," +
                                        "\"" + element.camion + "\"," +
                                        "\"" + element.fechaAut + "\"," +
                                        "\"" + element.autorizacion + "\"," +
                                        "\"" + element.claveAcceso + "\"," +                                        
                                        "\"" + element.telefono + "\""));
                                      });
                                      
                                      this.tasksService.db.sqlBatch(sqlInsert)
                                        .then(() => {  

                                          //Detalles despacho
                                          let sqlInsert: Array<string> = [];
                                          let sqlProductos = sqlGeneral.replace('[TABLA]', this.tasksService.TABLA_DETALLESDESPACHO);

                                          sqlProductos = sqlProductos.replace('[CAMPOS]', "id, estado, idreferencia, campo_auditoria, fecha_actualizacion, fecha_registro, observacion, " +
                                            "cantidad, codigoauxiliar, codigoprincipal, descripcion, descuento, detallesadicionales, preciototalconimpuesto, preciototalsinimpuesto, preciounitario, " +
                                            "id_cab_despacho, id_producto, codigoproducto, numerofactura, numerooden, preciopedido"
                                          );
                                      
                                          let arregloProducto = dataBase.listaDetallesDespacho;

                                        arregloProducto.forEach(element => {
                                        console.log(element);
                                        sqlInsert.push(sqlProductos.replace('[VALORES]', "\"" + element.id + "\"," +
                                        "\"" + element.estado + "\"," +
                                        "\"" + element.idReferencia + "\"," +
                                        "\"" + element.auditoria + "\"," +
                                        "\"" + element.fechaActualizacion + "\"," +
                                        "\"" + element.fechaRegistro + "\"," +
                                        "\"" + element.observacion + "\"," +
                                        
                                        "\"" + element.cantidad + "\"," +
                                        "\"" + element.codigoAuxiliar + "\"," +
                                        "\"" + element.codigoPrincipal + "\"," +
                                        "\"" + element.descripcion + "\"," +
                                        "\"" + element.descuento + "\"," +
                                        "\"" + element.detallesAdicionales + "\"," +
                                        "\"" + element.precioTotalConImpuesto + "\"," +
                                        "\"" + element.precioTotalSinImpuesto + "\"," +
                                        "\"" + element.precioUnitario + "\"," +

                                        "\"" + element.idCabDespacho + "\"," +
                                        "\"" + element.idProducto + "\"," +
                                        "\"" + element.codigoProducto + "\"," +
                                        "\"" + element.numeroFactura + "\"," +
                                        "\"" + element.numeroOden + "\"," +
                                        "\"" + element.precioPedido + "\""));
                                      });

                                        this.tasksService.db.sqlBatch(sqlInsert)
                                        .then(() => {  
                                          resolve(true);      
                                        });  
                                    });                                                                           
                                  });
                              });
                            }); 
                          });
                        });
                      });

                  });
              });

          });

      });

    });
  }

  irPaginaPrincipal(dataLoginRemoto) {

    this.controlAcceso.getDataLogin().username = dataLoginRemoto.usuario;
    this.controlAcceso.getDataLogin().nombre = dataLoginRemoto.nombres + " " + dataLoginRemoto.apellidos;
    this.controlAcceso.getDataLogin().email = dataLoginRemoto.correo;
    this.controlAcceso.setPresentarMenu(true);
    this.auth.login('{usuario: ' + dataLoginRemoto.usuario + '}');
    this.router.navigateByUrl('/folder/PrincipalLogin');
  }


  recrearTablas( ) {
    return new Promise((resolve, reject) => {
        let sqlInsert: Array<string> = [];

         sqlInsert.push('DELETE FROM '  + this.tasksService.TABLA_CABECERACOBRO) ;
         sqlInsert.push('DELETE FROM '  + this.tasksService.TABLA_STOCK) ;
         sqlInsert.push('DELETE FROM '  + this.tasksService.TABLA_CABDESPACHO) ;
         sqlInsert.push('DELETE FROM '  + this.tasksService.TABLA_DETALLESDESPACHO) ;

        this.tasksService.db.sqlBatch(sqlInsert)
              .then(() => {
                  resolve(true); 
              });
         
    });

  }

  registrarIP() {

       this.tasksService.ipconfiguracionapi().then( res => {
            if (res.length > 0) {
            this.controlParametros.setParametro("ip_api", res[0].ipapigateway);
            }else {
              this.controlParametros.setParametro("ip_api", null);
            }
            this.openModal();
        });      
  }


  async openModal() {
    const modal = await this.viewCtrl.create({
      component: ConfigipPage,
      cssClass: 'my-custom-modal-css'

    });

     modal.onDidDismiss().then((dataReturned) => {
        
    });
    return await modal.present();
  }
}
