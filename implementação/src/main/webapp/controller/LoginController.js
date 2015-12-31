angular.module("loginApp", ["ngAnimate"])
        .controller("LoginController", ["$http", function ($http) {
                var self = this;
                self.login = "";
                self.password = "";
                self.message = "";
                
                self.doLoging = function () {
                    $http.post("home", "login=" + self.login + "&pass=" + self.password, {
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        }
                    }).then(function successCallback(response) {
                        window.location = "view/protected/home.html";
                      }, function errorCallback(response) {
                        self.message = ("<p><button class=\"close\" data-dismiss=\"alert\" type=\"button\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Fechar</span></button>"
                                            + "<h4>Usu&aacute;rio e/ou senha incorretos!</h4>"
                                            + "<p>Verifique o usu&aacute;rio, a senha e digite os dados novamente.</p>"
                                            + "</div></p>");
                      });
                };
            }]);