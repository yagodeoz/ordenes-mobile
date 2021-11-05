import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'io.ionic.starter',
  appName: 'groccy',
  webDir: 'www',
  bundledWebRuntime: false,
  cordova: {
    preferences: {
      ScrollEnabled: 'false',
      BackupWebStorage: 'none',
      SplashMaintainAspectRatio: 'true',
      FadeSplashScreenDuration: '2000',
      SplashShowOnlyFirstTime: 'false',
      SplashScreen: 'screen',
      SplashScreenDelay: '30000',
      Orientation: 'portrait',
      ShowSplashScreenSpinner: 'false',
      AndroidPersistentFileLocation: 'Internal',
      WebViewBounce: 'false',
      UIWebViewBounce: 'false',
      DisallowOverscroll: 'true',
      KeyboardResize: 'true',
      KeyboardResizeMode: 'ionic',
      'android-windowSoftInputMode': 'stateVisible',
      Hostname: 'localhost:8080',
      iosScheme: 'http',
      LogLevel: 'ERROR',
      GOOGLE_MAPS_ANDROID_API_KEY: 'AIzaSyDTOco0xCetKtb997Ke8pDrPjDjHXeDeRk'
    }
  }
};

export default config;
