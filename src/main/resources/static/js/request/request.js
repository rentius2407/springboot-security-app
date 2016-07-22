var httpRequest = (function () {
    var request = {
        url: "",
        type: "",
        contentType: "application/json; charset=utf-8",
        dataType: 'json'
    };

    return  {
        post: function (url, data) {
            request.url = url;
            request.type = "post";
            request.data = JSON.stringify(data);
            return $.ajax(request);
        }
    };
})();