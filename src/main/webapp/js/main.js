(function() {

    // Init Material UI
    $(document).ready(function() { $('body').bootstrapMaterialDesign(); });

    $("#bar-btn").click(function(e) {
        e.stopPropagation()
        $(".left-pannel").css({
            left: "0"
        })
        $('.chat-container').click(function(event) {
            $(".left-pannel").css({
                left: "-300px"
            })
            $('.chat-container').off("click")
            $(".left-pannel").off("click")
        })
        $(".left-pannel").click(function(event) {
            event.stopPropagation()
        })
    })

    //Init Username
    if (localStorage.getItem("username")) {
        var username = localStorage.getItem("username")
        $('#username').html(username)
        $('#username-input').prop("value", username)
        $('#username-hidden-input').prop('value', username)
    } else {
        $('#anonymous-switch').prop("checked", "checked")
        $('#username-input').prop("disabled", true)
    }

    // Add Username listenner
    $('#user-area').click(function() {
        $('#username_modal').modal()
    })
    $('#anonymous-switch').click(function() {
        if (this.checked) {
            $('#username-input').prop("disabled", true)
        } else {
            console.log(true)
            $('#username-input').prop("disabled", false)
        }
    })
    $('#username-save-btn').click(function() {
        if ($('#anonymous-switch').prop("checked")) {
            localStorage.removeItem("username")
            $('#username').html("Anonymous")
            $('#username-hidden-input').prop('value', '')
        } else {
            var username = $('#username-input').prop("value")
            localStorage.setItem("username", username)
            $('#username').html(username)
            $('#username-hidden-input').prop('value', username)
        }
        $('#username_modal').modal('hide')
    })

    // Add theme button listenner
    $('#theme-btn').click(function() {
        $('#theme_modal').modal()
    })
    $('#theme-save-btn').click(function() {
        var color = $('#theme_modal input[name="color"]:checked').val();
        document.cookie = "color=" + color;
        location.reload()
    })

    // Add refresh button listenner
    $('#refresh-btn').click(function() {
        location.reload()
    })

    // Add Download button listenner
    $('#download-btn').click(function() {
        $('#download_modal').modal()
    })
    $('#download-save-btn').click(function() {
        var from_raw = $('#download-from').val()
        var to_raw = $('#download-to').val()
        var from = to = ''
        if (from_raw && to_raw) {
            from = new Date(from_raw + 'Z').getTime();
            to = new Date(to_raw + 'Z').getTime();
        }
        var format = $('#download-format input[name="format"]:checked').val()
        window.open("./?from=" + from + "&to=" + to + "&format=" + format, "_blank")
        $('#download_modal').modal('hide')
    })

    // Add Clear button listenner
    $('#clear-btn').click(function() {
        $('#clear_modal').modal()
    })

    $('#clear-form').submit(function() {
        var from_raw = $('#clear-from').val()
        var to_raw = $('#clear-to').val()
        var from = to = ''
        if (from_raw && to_raw) {
            from = new Date(from_raw + 'Z').getTime();
            to = new Date(to_raw + 'Z').getTime();
        }
        console.log(from, to)
        $('#from-hidden-input').val(from)
        $('#to-hidden-input').val(to)
    })

})()