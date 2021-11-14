import { Component, OnInit } from '@angular/core';
import {ControlParametrosService} from '../servicios/transversales/control-parametros.service';
import {Router} from '@angular/router';
import { TasksServiceProvider } from '../providers/tasks-service';
import { LoadingService } from '../servicios/transversales/loading.service';
import { AlertController } from '@ionic/angular';
import { ControlaccesologinService } from '../servicios/login/controlaccesologin.service';
import { ContextoService } from '../servicios/configuracion/contexto.service';
import { UtilmensajeService } from "../servicios/transversales/utilmensaje.service";

@Component({
  selector: 'app-sucursal',
  templateUrl: './sucursal.page.html',
  styleUrls: ['./sucursal.page.scss'],
})
export class SucursalPage implements OnInit {

  private datosReplicacionOrden = {
  "cabeceraOrdenes": [],
  "detalleOrdenes": []
  };

  private datosReplicacionCobros = {
  "listaComprobante": [],
  "listaDetallePagos": []
  };

  private datosReplicacionDespacho = {
  "listaCabeceraDespacho": [],
  "listaDetalleDespacho": []
  };

  private sucursales:any;

  private ordenesSinReplicar:any;
  private comprobanteSinReplicar:any;
  private despachosSinReplicar:any;


  constructor(private loadingService: LoadingService, 
  private controlParametros: ControlParametrosService, private router: Router, 
  private tasksService: TasksServiceProvider,
  private controlAcceso: ControlaccesologinService, 
  public alertController: AlertController,
  private contextoService: ContextoService,
  private utilMensaje: UtilmensajeService, 
  ) { }


  ngOnInit() {

  }

  ionViewDidEnter() {
      this.loadingService.loadingPresent('Analizando datos...');
      this.tasksService.listaDatosReplicar().then(res => {
        console.log (res);
        this.ordenesSinReplicar = res[0].ordenesSinReplicar;
        this.comprobanteSinReplicar = res[0].comprobanteSinReplicar;
        this.despachosSinReplicar =  res[0].despachosSinReplicar;
        this.loadingService.loadingDismiss();
      });         
  }  


  descargarCatalogos (){

      if (this.ordenesSinReplicar > 0) {
        this.utilMensaje.presentarMensaje('Existen órdenes que no se han replicado al servidor');
        return;
      }

      if (this.comprobanteSinReplicar > 0) {
        this.utilMensaje.presentarMensaje('Existen pagos que no se han replicado al servidor');
        return;
      }

      if (this.despachosSinReplicar > 0) {
        this.utilMensaje.presentarMensaje('Existen despachos que no se han replicado al servidor');
        return;
      }



      let alert = this.alertController.create({
            header: 'Sincronización',
            message: 'El Sistema realiza la descarga de catálogos en éste momento...',
            buttons: [{
              text: 'Aceptar',
              handler: () => {
                this.descarga();
              }
            }
            ]
          }).then(res => {

            res.present();
          });
  }

  descarga(){

        this.loadingService.loadingPresent('Por favor espere');
          let postData = {
          "usuario": this.controlAcceso.getDataLogin().username
        };

        this.contextoService.invocacionServicioPost(postData, this.contextoService.getServicioBaseDatos()).subscribe(
          dataBase => {
            console.log("Data " + JSON.stringify(dataBase));

            //recrear tablas, las que el servidor se descarga
            this.recrearTablas().then(res => {

                this.insertarDatos(dataBase).then(data => {

               this.loadingService.loadingDismiss();

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

          },
          
          error => {
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



  insertarDatos( dataBase) {

    //Variables
    let parametroWS: any;
    let respuestaWS: any;
    let sqlGeneral = 'INSERT OR REPLACE INTO [TABLA]([CAMPOS]) VALUES([VALORES]);';
    let sqlInsert: Array<string> = [];

    return new Promise((resolve, reject) => {
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
                            //----
                               

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
                                        "codigocliente, codigosucursal, diaspago, placavehiculo, usuarioasignado, usuarioasignante, descripcionlistaprecio, tipopago, " +
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


                            //----
                          });
                          });
                      });

                  });
              });

          });

      });
  }

  subirOrdenes () {

    if (this.ordenesSinReplicar <= 0) {
        this.utilMensaje.presentarMensaje('No existen órdenes pendientes de replicar');
        return;
    }

      this.datosReplicacionOrden["cabeceraOrdenes"] = [];
      this.datosReplicacionOrden["detalleOrdenes"] = [];
      this.loadingService.loadingPresent('Realizando la replicación de Ordenes...');
      this.tasksService.listaCabeceraOrdenesReplicar().then(cabOrden => {
        
        this.datosReplicacionOrden["cabeceraOrdenes"].push(...cabOrden);
            this.tasksService.listaDetallesOrdenesReplicar().then( detOrden => {
              this.datosReplicacionOrden["detalleOrdenes"].push(...detOrden);
                
              
              this.contextoService.invocacionServicioPost(JSON.stringify(this.datosReplicacionOrden), 
                                                          this.contextoService.getServicioCargaOrdenesBaseDatos()).subscribe(
                              dataBase => {
                                                  console.log("Data " + JSON.stringify(dataBase));
                                                  this.actualizarReplicacionOrden(dataBase).then(data => {                                                                                                     
                                                  this.tasksService.listaDatosReplicar().then(res => {
                                                      console.log (res);
                                                      this.ordenesSinReplicar = res[0].ordenesSinReplicar;
                                                      this.comprobanteSinReplicar = res[0].comprobanteSinReplicar;
                                                      this.despachosSinReplicar =  res[0].despachosSinReplicar;
                                                      this.loadingService.loadingDismiss();
                                                       let alert = this.alertController.create({
                                                       header: 'Atención',
                                                        message: 'Ordenes replicadas correctamente',
                                                        buttons: [{
                                                                text: 'Aceptar'
                                                                }]
                                                        }).then(res => {
                                                                  res.present();
                                                                  });
                                                        });
                                              }).catch(e => {
                                                    console.error(e);
                                                    this.loadingService.loadingDismiss();
                                                    let alert = this.alertController.create({
                                                    header: 'Atención',
                                                    message: 'Error al realizar la actualización de datos',
                                                    buttons: [{
                                                                text: 'Aceptar'
                                                              }]
                                                    }).then(res => {
                                                                  res.present();
                                                                  });
                                                  });

                                          },error => {
                                                  this.loadingService.loadingDismiss();
                                                  let alert = this.alertController.create({
                                                              header: 'Error',
                                                              message: 'No tiene conexón con el servidor, por favor asegurese que está conectado a internet',
                                                              buttons: [{
                                                                  text: 'Aceptar',
                                                                  handler: () => {
                                                                          //this.validarUsuarioRemoto();
                                                                          }
                                                                        }]
                                                                }).then(res => {
                                                                        res.present();
                                                                        });

                                          });              
        });
      
      });         
  }

  subirCobros () {
    
    if (this.comprobanteSinReplicar <= 0) {
        this.utilMensaje.presentarMensaje('No existen cobros pendientes por replicar al servidor');
        return;
      }

      this.datosReplicacionCobros["listaComprobante"] = [];
      this.datosReplicacionCobros["listaDetallePagos"] = [];
      this.loadingService.loadingPresent('Realizando la replicación de Cobros...');
      this.tasksService.listaCromprobantesReplicar().then(comprobantes => {
        
        this.datosReplicacionCobros["listaComprobante"].push(...comprobantes);
            this.tasksService.listaDetallesPagoReplicar().then( detPago => {
              this.datosReplicacionCobros["listaDetallePagos"].push(...detPago);                
              
              this.contextoService.invocacionServicioPost(JSON.stringify(this.datosReplicacionCobros), 
                                                          this.contextoService.getServicioCargaCobrosBaseDatos()).subscribe(
                              dataBase => {
                                                  console.log("Data " + JSON.stringify(dataBase));
                                                  this.actualizarReplicacionCobros(dataBase).then(data => {                                                                                                     
                                                  this.tasksService.listaDatosReplicar().then(res => {
                                                      console.log (res);
                                                      this.ordenesSinReplicar = res[0].ordenesSinReplicar;
                                                      this.comprobanteSinReplicar = res[0].comprobanteSinReplicar;
                                                      this.despachosSinReplicar =  res[0].despachosSinReplicar;
                                                      this.loadingService.loadingDismiss();
                                                       let alert = this.alertController.create({
                                                       header: 'Atención',
                                                        message: 'Cobros replicados correctamente',
                                                        buttons: [{
                                                                text: 'Aceptar'
                                                                }]
                                                        }).then(res => {
                                                                  res.present();
                                                                  });
                                                        });
                                              }).catch(e => {
                                                    console.error(e);
                                                    this.loadingService.loadingDismiss();
                                                    let alert = this.alertController.create({
                                                    header: 'Atención',
                                                    message: 'Error al realizar la actualización de datos',
                                                    buttons: [{
                                                                text: 'Aceptar'
                                                              }]
                                                    }).then(res => {
                                                                  res.present();
                                                                  });
                                                  });

                                          },error => {
                                                  this.loadingService.loadingDismiss();
                                                  let alert = this.alertController.create({
                                                              header: 'Error',
                                                              message: 'No tiene conexón con el servidor, por favor asegurese que está conectado a internet',
                                                              buttons: [{
                                                                  text: 'Aceptar',
                                                                  handler: () => {
                                                                          //this.validarUsuarioRemoto();
                                                                          }
                                                                        }]
                                                                }).then(res => {
                                                                        res.present();
                                                                        });

                                          });              
        });
      
      });         

  }

  subirDespacho () {
    
    if (this.despachosSinReplicar <= 0) {
        this.utilMensaje.presentarMensaje('No existen despachos pendientes por replicar al servidor');
        return;
      }

      this.datosReplicacionDespacho["listaCabeceraDespacho"] = [];
      this.datosReplicacionDespacho["listaDetalleDespacho"] = [];
      this.loadingService.loadingPresent('Realizando la replicación de Despachos...');
      this.tasksService.listaDespachoReplicar().then(cabDespachos => {
        
        this.datosReplicacionDespacho["listaCabeceraDespacho"].push(...cabDespachos);
            this.tasksService.listaDetallesDespacho().then( detDespacho => {
              this.datosReplicacionDespacho["listaDetalleDespacho"].push(...detDespacho);                
              
              this.contextoService.invocacionServicioPost(JSON.stringify(this.datosReplicacionDespacho), 
                                                          this.contextoService.getServicioCargaDespachoBaseDatos()).subscribe(
                              dataBase => {
                                                  console.log("Data " + JSON.stringify(dataBase));
                                                  this.actualizarReplicacionDespacho(dataBase).then(data => {                                                                                                     
                                                  this.tasksService.listaDatosReplicar().then(res => {
                                                      console.log (res);
                                                      this.ordenesSinReplicar = res[0].ordenesSinReplicar;
                                                      this.comprobanteSinReplicar = res[0].comprobanteSinReplicar;
                                                      this.despachosSinReplicar =  res[0].despachosSinReplicar;
                                                      this.loadingService.loadingDismiss();
                                                       let alert = this.alertController.create({
                                                       header: 'Atención',
                                                        message: 'Despachos replicados correctamente',
                                                        buttons: [{
                                                                text: 'Aceptar'
                                                                }]
                                                        }).then(res => {
                                                                  res.present();
                                                                  });
                                                        });
                                              }).catch(e => {
                                                    console.error(e);
                                                    this.loadingService.loadingDismiss();
                                                    let alert = this.alertController.create({
                                                    header: 'Atención',
                                                    message: 'Error al realizar la actualización de datos',
                                                    buttons: [{
                                                                text: 'Aceptar'
                                                              }]
                                                    }).then(res => {
                                                                  res.present();
                                                                  });
                                                  });

                                          },error => {
                                                  this.loadingService.loadingDismiss();
                                                  let alert = this.alertController.create({
                                                              header: 'Error',
                                                              message: 'No tiene conexón con el servidor, por favor asegurese que está conectado a internet',
                                                              buttons: [{
                                                                  text: 'Aceptar',
                                                                  handler: () => {
                                                                          //this.validarUsuarioRemoto();
                                                                          }
                                                                        }]
                                                                }).then(res => {
                                                                        res.present();
                                                                        });

                                          });              
        });
      
      });


  }





  actualizarReplicacionOrden (datosReplicacion){
    console.log(datosReplicacion);
    return new Promise((resolve, reject) => {
       
       this.tasksService.listaCabeceraOrdenesReplicada().then(res => {
          resolve(true);
       });       
    });
  }

  //---

  actualizarReplicacionCobros(datosReplicacion){
    
    console.log(datosReplicacion);
    return new Promise((resolve, reject) => {
       
       this.tasksService.listaCabeceraCobroReplicada().then(res => {
         this.tasksService.listaDetCobroReplicada().then(res => {
          resolve(true);
          });
       });       
    });

  }

  //-----

   actualizarReplicacionDespacho(datosReplicacion){
    
    console.log(datosReplicacion);
    return new Promise((resolve, reject) => {
       
       this.tasksService.listaCabeceraDespachoReplicada().then(res => {
          resolve(true);
       });       
    });

  }

}
