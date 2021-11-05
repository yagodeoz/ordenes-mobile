import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {ContextoService} from '../../configuracion/contexto.service';

@Injectable({
    providedIn: 'root'
})
export class ClientesService {

    constructor(private contexto: ContextoService) { }

    getClientesCriterio(postData): Observable<any> {
        return this.contexto.invocacionPost(postData);
    }
}
