export const REST_ENDPOINT = {
  auth: {
    validate: "/v1/api/cookie",
    verifyToken: "/v1/api/token/verify",
    fetchToken: "/v1/api/token",
    deleteTokn: "/v1/api/cookie/delete",
  },
  user: {
    fetch: "/v1/api/user/info",
  },
  tenants: {
    get: "/v1/api/tenants",
    getTenant: "/v1/tenant",
  },
};
