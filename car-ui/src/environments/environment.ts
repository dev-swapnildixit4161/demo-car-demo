export const environment = {
  production: false,
  webapiurl: (window as any)["envconfig"]["apiurl"] || "default",
  ssoApiUrl: (window as any)["envconfig"]["ssoApiUrl"] || "default",
  redirectApiurl: (window as any)["envconfig"]["redirectApiurl"] || "default",
  env: "LOCAL"
};
