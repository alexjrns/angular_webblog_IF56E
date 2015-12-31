angular.module("commentsAPP", ["ngRoute"])
        .controller("CommentsController", ["$http", function ($http) {
                var self = this;
                self.message = "";
                self.comment = "";

                self.doComment = function () {
                    if(self.comment !== ""){
                        self.message = 1;
                        $http.post("../AngularBlog/comments", "comment=" + self.comment + "&message=" + self.message, {
                            headers: {
                                "Content-Type": "application/x-www-form-urlencoded"
                            }
                        }).then(function successCallback(response) {
                            console.log('Sucesso');
                            window.location = "view/protected/messages.html";
                          }, function errorCallback(response) {
                              console.log('Erro');
                          });
                  }
                };
            }]);