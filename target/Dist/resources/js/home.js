DIST.namespace("module.dashboard");

DIST.module.home = function () {
    var buttonClick = function () {
        $.ajax({
            url: 'http://localhost:8082/Dist/join',
            type: 'POST',
            success: function (result) {
                var x = result;
            }
        }); 
    };
    return {
        init: function () {
            $("#regBtn").off('click').on('click',buttonClick);
        }
    }
}();

$(function () {
    DIST.module.home.init();
});
  