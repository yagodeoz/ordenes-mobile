import { Injectable } from '@angular/core';
import { ToastController } from '@ionic/angular';

@Injectable({
    providedIn: 'root'
})
export class UtilmensajeService {
    constructor(public toastController: ToastController) {}

    async presentarMensaje(infoMessage: string) {
        const toast = await this.toastController.create({
            message: infoMessage,
            duration: 2000
        });
        await toast.present();
    }
}