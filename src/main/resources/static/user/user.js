var userCtrl = (function () {
    $("#userSubmit").click(function () {
        alert("Handler for .click() called.");
        $('#nameLabel').text('Rentius');
    });

    return {
        sayHello: function () {
            $('#nameLabel').text('Rentius');
        }
    };
})();

