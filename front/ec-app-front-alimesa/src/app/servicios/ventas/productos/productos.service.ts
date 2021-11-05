import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {ContextoService} from '../../configuracion/contexto.service';

@Injectable({
    providedIn: 'root'
})
export class ProductosService {

    constructor(private contexto: ContextoService) { }

    getProductosCriterio(postData): Observable<any> {
        return this.contexto.invocacionPost(postData);
    }
}
