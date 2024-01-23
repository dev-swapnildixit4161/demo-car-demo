(function (window) {
  window['envconfig'] = window['envconfig'] || {};

  // Environment variables
  window['envconfig']['apiurl'] = 'http://localhost:8080/api';
  window['envconfig']['ssoApiUrl'] = 'https://auth-test.sso.sensormatic.com';
  window['envconfig']['redirectApiurl'] = '/auth-process';

})(this);
