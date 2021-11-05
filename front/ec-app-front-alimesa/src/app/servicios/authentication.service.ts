import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Router} from '@angular/router';
import {Platform} from '@ionic/angular';
import { Storage } from '@ionic/storage';

@Injectable()
export class AuthenticationService {
  presentarMenu: boolean;

  authState = new BehaviorSubject(false);

  constructor(
              private router: Router,
              private storage: Storage,
              private platform: Platform
  ) {
    this.platform.ready().then(() => {
      this.ifLoggedIn();
    });
  }

  ifLoggedIn() {
    this.storage.get('USER_INFO').then((response) => {
      if (response) {
        this.presentarMenu = true;
        this.authState.next(true);
      }
    });
  }

  login(respuestaLogin) {
    this.storage.set('USER_INFO', respuestaLogin).then((response) => {
      this.presentarMenu = true;
      this.authState.next(true);
    });
  }

  logout() {
    this.storage.remove('USER_INFO').then(() => {
      this.presentarMenu = false;
      this.router.navigate(['login']);
      this.authState.next(false);
    });
  }

  isAuthenticated() {
    return this.authState.value;
  }

}
