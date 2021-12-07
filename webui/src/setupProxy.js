const {createProxyMiddleware} = require("http-proxy-middleware");
module.exports = function(app) {
    app.use(createProxyMiddleware("/tasker/**", {
        target: "http://192.168.86.221:8080/",
        secure: false
    }));
};