import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import {AuthGuardService} from './servicios/authGuard.service';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'folder/:id',
    loadChildren: () => import('./folder/folder.module').then( m => m.FolderPageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'listaproductos',
    loadChildren: () => import('./listaproductos/listaproductos.module').then(m => m.ListaProductosPageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'listaclientes',
    loadChildren: () => import('./listaclientes/listaclientes.module').then(m => m.ListaClientesPageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'producto',
    loadChildren: () => import('./producto/producto.module').then(m => m.ProductoPageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'cliente',
    loadChildren: () => import('./cliente/cliente.module').then(m => m.ClientePageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'sucursal',
    loadChildren: () => import('./sucursal/sucursal.module').then(m => m.SucursalPageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'crearcliente',
    loadChildren: () => import('./cliente/crearcliente/crearcliente.module').then(m => m.CrearclientePageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'flujodespacho',
    loadChildren: () => import('./flujodespacho/flujodespacho.module').then(m => m.FlujodespachoPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'cabeceradespacho',
    loadChildren: () => import('./flujodespacho/cabeceradespacho/cabeceradespachos.module').then(m => m.CabeceradespachoPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'stock',
    loadChildren: () => import('./flujodespacho/stock/stock.module').then(m => m.StockPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'despachodia',
    loadChildren: () => import('./flujodespacho/despachodia/despachodia.module').then(m => m.DespachodiaPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'detalledespacho',
    loadChildren: () => import('./flujodespacho/despachodetalle/despachodetalle.module').then(m => m.DespachodetallePageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'ordendia',
    loadChildren: () => import('./flujopedidos/ordenesdia/ordenesdia.module').then(m => m.OrdenesdiaPageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'cobrosdia',
    loadChildren: () => import('./cobros/cobrosdia/cobrosdia.module').then(m => m.CobrosdiaPageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'cobros',
    loadChildren: () => import('./cobros/cobros.module').then(m => m.CobrosPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'detallescobro',
    loadChildren: () => import('./cobros/detallescobro/detallecobro.module').then(m => m.DetalleCobroPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'cabecerapedido',
    loadChildren: () => import('./flujopedidos/cabecerapedido/cabecerapedidos.module').then(m => m.CabecerapedidosPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'cabeceracobro',
    loadChildren: () => import('./cobros/cabeceracobro/cabeceracobros.module').then(m => m.CabeceracobrosPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'geolocalizacion',
    loadChildren: () => import('./cliente/geolocalizacion/geolocalizacion.module').then(m => m.GeolocalizacionPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'cabeceracobrobanco',
    loadChildren: () => import('./cobros/cabeceracobrobanco/cabeceracobrobanco.module').then(m => m.CabeceracobrobancoPageModule),
    canActivate: [AuthGuardService]
  },

  {
    path: 'pedidos',
    loadChildren: () => import('./flujopedidos/flujopedidos.module').then(m => m.FlujopedidosPageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'editcliente',
    loadChildren: () => import('./cliente/editarcliente/editcliente.module').then(m => m.EditclientePageModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then(m => m.LoginPageModule)
  },  
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
