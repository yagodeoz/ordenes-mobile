import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ControlParametrosService {

    mapaParametro = new Map<String, Object>();

    setParametro(clave, valor) {
        this.mapaParametro.set(clave, valor);
    }

    getParametro(clave) {
        return this.mapaParametro.get(clave);
    }

    removerParametro(clave) {
        return this.mapaParametro.delete(clave);
    }

}