DIST.namespace("module.dashboard");

DIST.module.home = function () {
    var regButtonClick = function () {
        $.ajax({
            url: 'http://localhost:8082/Dist/join',
            type: 'POST',
            success: function (result) {
                var x = result;
                var status;
                if(x[0] == true) {
                    status = "Registration successful";
                }else{
                    status = "Registration failed";
                }
                $("#status").html(status+" with IP address "+x[1]+" on port "+x[2]);
            }
        }); 
    };
    var leaveButtonClick = function () {
        $.ajax({
            url: 'http://localhost:8082/Dist/leave',
            type: 'POST',
            success: function (result) {
                var x = result;
                if(x[0] == true) {
                    status = "Leaving successful";
                }else{
                    status = "Leaving failed";
                }
                $("#status").html(status+" with IP address "+x[1]+" on port "+x[2]);
            }

        });
    };

    var searchButtonClick = function () {
        $.ajax({
            url: 'http://localhost:8082/Dist/search',
            type: 'POST',
            success: function (result) {
                var x = result;
                if(x[0] == true) {
                    status = "Searching successful";
                }else{
                    status = "Searching failed";
                }
                $("#status").html(status+" with IP address "+x[1]+" on port "+x[2]);
            }

        });
    };
    
    return {
        init: function () {
            $("#regBtn").off('click').on('click',regButtonClick);
            $("#leaveBtn").off('click').on('click',leaveButtonClick);
            $("#searchBtn").off('click').on('click',searchButtonClick);   
        }
    }








}();

$(function () {
    DIST.module.home.init();
});
  