var exec = require('cordova/exec')

module.exports = {
    toast: function (message) {
        exec(null, null, 'ToastPlugin', 'toast', [message])
    },
    hello: function (message, success, error) {
        exec(success, error, 'ToastPlugin', 'hello', [message])
    },
    startSplash: function () {
        exec(null, null, 'ToastPlugin', 'startSplash', [])
    },
    interval: function (success) {
        exec(success, null, 'ToastPlugin', 'interval', [])
    },
    clearInterval: function (success) {
        exec(success, null, 'ToastPlugin', 'clearInterval', null)
    },

}
