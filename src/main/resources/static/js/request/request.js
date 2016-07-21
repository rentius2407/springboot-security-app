var httpRequest = (function () {
    var request = {
        url: "",
        type: "",
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    };

    return  {
        post: function (url, data, successPage) {
            request.url = url;
            request.type = "post";
            request.data = JSON.stringify(data);

            $.ajax(request).done(function (data) {
                console.log('done');
                console.log(data);
                window.location.href = "user/user.html";
            }).fail(function () {
                console.log('failed method');
            });
        }
    };
})();