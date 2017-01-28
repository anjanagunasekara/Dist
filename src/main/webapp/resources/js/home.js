DIST.namespace("module.dashboard");

DIST.module.home = function () {
    var regButtonClick = function () {
        var port=document.getElementById("port").innerHTML;
        var ip=document.getElementById("ip").innerHTML;
        $.ajax({
            url: 'http://'+ip+':'+port+'/Dist/join',
            type: 'POST',
            success: function (result) {
                var x = result;
                var status;
                var popupColor;
                var popupBorder;
                if(x[0] == true) {
                    status = "Registration successful";
                    popupBorder="#00ff00";
                    popupColor="#20b26f";
                }else{
                    status = "Registration failed";
                    popupBorder="#b12222";
                    popupColor="#ec5555";
                }
                var notification = '<div class="notificationPopup" style="border-color:'+popupBorder+';background-color: '+popupColor+' ">'+status+' with IP address '+x[1]+' on port '+x[2]+'</div>';
                $(notification).appendTo("#notificationPopupContainer").fadeIn(1500).fadeOut(1000);
            }
        }); 
    };
    var leaveButtonClick = function () {
        var port=document.getElementById("port").innerHTML;
        var ip=document.getElementById("ip").innerHTML;
        $.ajax({
            url: 'http://'+ip+':'+port+'/Dist/leave',
            type: 'POST',
            success: function (result) {
                var x = result;
                var status;
                var popupColor;
                var popupBorder;
                if(x[0] == true) {
                    status = "Leaving successful";
                    popupBorder="#00ff00";
                    popupColor="#20b26f";
                }else{
                    status = "Leaving failed";
                    popupBorder="#b12222";
                    popupColor="#ec5555";
                }
                var notification = '<div class="notificationPopup" style="border-color:'+popupBorder+';background-color: '+popupColor+' ">'+status+' with IP address '+x[1]+' on port '+x[2]+'</div>';
                $(notification).appendTo("#notificationPopupContainer").fadeIn(1500).fadeOut(1000);

            }

        });
    };

    var searchButtonClick = function () {
        var port=document.getElementById("port").innerHTML;
        var ip=document.getElementById("ip").innerHTML;
        $.ajax({
            url: 'http://'+ip+':'+port+'/Dist/search',
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
            var repeatHandle = window.setInterval(function() {
                $("#notificationPopupContainer").empty();
            }, 10000);
        }
    }








}();

$(function () {
    DIST.module.home.init();
});
  