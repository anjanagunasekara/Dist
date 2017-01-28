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
    var loadFiles = function(){
        $.ajax({
            url: 'http://localhost:8082/Dist/loadFiles',
            type: 'POST',
            success: function (result) {
                $("#fileContainer").empty();
                _.each(result,function (item) {
                    var row=' <div class="row dist-file-row" >'
                        +' <div class="col-lg-1">*</div>'
                        +' <div class="col-lg-3">'+item+'</div>'
                        +'<div class="col-lg-2"> <button class="btn btn-danger dist-btn" id="test">X</button></div>'
                        +'</div>';
                    $("#fileContainer").append(row);
                })
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
                $("#resultsContainer").empty();
                _.each(result,function (item) {
                    var row=' <div class="row dist-file-row" >'
                        +' <div class="col-lg-1">*</div>'
                        +' <div class="col-lg-3">'+item.name+'</div>'
                        +' <div class="col-lg-2">'+item.ip+'</div>'
                        +' <div class="col-lg-2">'+item.port+'</div>'
                        +'<div class="col-lg-2"> <button class="btn btn-success dist-btn" id="test">V</button></div>'
                        +'</div>';
                    $("#fileContainer").append(row);
                })
            }

        });
    };
    
    return {
        init: function () {
            $("#regBtn").off('click').on('click',regButtonClick);
            $("#leaveBtn").off('click').on('click',leaveButtonClick);
            $("#searchBtn").off('click').on('click',searchButtonClick);
            $("#showFiles").off('click').on('click',function () {
                $("#fileWell").slideToggle(1000);
            });
            $("#refreshBtn").off('click').on('click',loadFiles);
            loadFiles();
            var repeatHandle = window.setInterval(function() {
                $("#notificationPopupContainer").empty();
            }, 10000);
        }
    }
}();

$(function () {
    DIST.module.home.init();
});
  