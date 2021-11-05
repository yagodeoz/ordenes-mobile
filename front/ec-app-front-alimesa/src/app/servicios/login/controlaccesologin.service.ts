import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})

export  class ControlaccesologinService {
    private presentarMenu: boolean;


    private dataLogin = {
        username: '',
        nombre: '',
        email: ''
    };

    constructor() {
        this.presentarMenu = false;
    }

    setPresentarMenu(presentarMenu: boolean){
        this.presentarMenu = presentarMenu;
    }

    getPresentarMenu() {
        return this.presentarMenu;
    }
    getDataLogin() {
        return this.dataLogin;
    }
}