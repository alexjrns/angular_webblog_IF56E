angular.module("loginApp2", ["ngAnimate"])
        .controller("MessagesController", ["$http", function ($http) {
                var self = this;
                self.message = "";
                self.messages = [];
                self.comments = [];

                $http.get("../../messages", { params: { busca: "alex"} })
                        .then(function (response) {
                            self.messages = response.data;
                        });
                        
                self.doInsertMessage = function () {
                    if(self.message !== ""){
                        $http.post("../../messages", "message=" + self.message, {
                            headers: {
                                "Content-Type": "application/x-www-form-urlencoded"
                            }
                        }).then(function successCallback(response) {
                            self.message = "";
                            $http.get("../../messages", { params: { busca: "alex"} })
                                .then(function (response) {
                                    self.messages = response.data;
                                });
                          }, function errorCallback(response) {
                              console.log("Não Inseriu");
                          });
                  }
                };
        }]);