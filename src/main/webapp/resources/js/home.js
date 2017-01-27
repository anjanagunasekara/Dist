DIST.namespace("module.dashboard");

DIST.module.home = function () {
    var regButtonClick = function () {
        $.ajax({
            url: 'http://localhost:8080/Dist/join',
            type: 'POST',
            success: function (result) {
                var x = result;
                // alert(x);
                $("#reg_response").html(x);
            }
        }); 
    };
    var leaveButtonClick = function () {
        $.ajax({
            url: 'http://localhost:8080/Dist/leave',
            type: 'POST',
            success: function (result) {
                var x = result;
                // alert(x);
                $("#leave_response").html(x);
            }
        });
    };
    return {
        init: function () {
            $("#regBtn").off('click').on('click',regButtonClick);
            $("#leaveBtn").off('click').on('click',leaveButtonClick);
        }
    }








}();

$(function () {
    DIST.module.home.init();
});
  