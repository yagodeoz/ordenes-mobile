import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import {ControlParametrosService} from '../transversales/control-parametros.service';


import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
@Injectable({
    providedIn: 'root'
})
export class ContextoService {
    //private baseUrlAPI = 'http://127.0.0.1:8080/api-gateway';

    private servicioProducto = '/acceso/login';


    private servicioLogin = '/acceso/login';
    private servicioBaseDatos = "/basedatos/obtenerbasedatos";


    private servicioCargaOrdenesBaseDatos = "/basedatosCarga/cargaordenes";

    private servicioCargaCobrosBaseDatos = "/basedatosCarga/cargacobros";

    private servicioCargaDespachoBaseDatos = "/basedatosCarga/cargadespacho";

    constructor(private http: HttpClient, private controlParametros: ControlParametrosService) {
    }

    getServicioCargaOrdenesBaseDatos(){
        return this.servicioCargaOrdenesBaseDatos;
    }

    getServicioCargaCobrosBaseDatos(){
        return this.servicioCargaCobrosBaseDatos;
    }

    getServicioCargaDespachoBaseDatos(){
        return this.servicioCargaDespachoBaseDatos;
    }

    getContexto() {
        console.log(this.controlParametros.getParametro('ip_api'));
        return this.controlParametros.getParametro('ip_api'); 
    }

    getServicio() {
        return this.servicioProducto;
    }

    getServicioLogin() {
        return this.servicioLogin;
    }

    getServicioBaseDatos() {
        return this.servicioBaseDatos;
    }

    invocacionPost(postData): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json' })
        };
        return this.http.post(`${this.getContexto() + this.getServicio()}`, postData, httpOptions);
    }

    invocacionServicioPost(postData, servicio): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json' })
        };
        return this.http.post(`${this.getContexto() + servicio}`, postData, httpOptions).pipe(
            catchError(error => {
                let errorMsg: string;
                if (error.error instanceof ErrorEvent) {
                    console.log(`Error: ${error.error.message}`);
                    errorMsg = "Error al realizar la invocación al servicio";

                } else {
                    errorMsg = this.getServerErrorMessage(error);
                }
                console.log(errorMsg);
                return throwError(errorMsg);
            })
        );
    }

    private getServerErrorMessage(error: HttpErrorResponse): string {
        switch (error.status) {
            case 404: {
                console.log(`Not Found: ${error.message}`);
                return "El servicio no se encuentra disponible";
            }
            case 403: {
                console.log(`Access Denied: ${error.message}`);
                return "No tiene acceso al servicio ";
            }
            case 500: {
                console.log(`Internal Server: ${error.message}`);
                return "El servidor no se encuentra disponible ";
            }
            default: {
                console.log(`Unknown Server Error: ${error.message}`);
                return "El servidor no existe ";
            }

        }
    }
}
