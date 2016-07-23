var userCtrl = (function () {
    $("#userSubmit").click(function () {
        $('#nameLabel').text('Rentius');
    });

    return {
        sayHello: function () {
            $('#nameLabel').text('Rentius');
        }
    };
})();

