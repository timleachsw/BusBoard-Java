function post_request() {
    $.ajax({
        url : "/busData",
        type : "POST",
        async : !1,
        data : { postcode: document.querySelector("#postcode").innerHTML},
        success : function(data) {
            // strip first 12 lines and last 3
            var splitLines = data.split('\n');
            var bitsWeWant = splitLines.slice(12, -3);
            console.log(bitsWeWant.join('\n'));
            document.querySelector("#bus-info-container").innerHTML = bitsWeWant.join('\n');
            formatBus();
        },
        error : function() {
            console.log("error")
        }
    })
}

setInterval(post_request, 5000);

function formatBus() {
    console.log("Redrawing buses");
    var gaps = document.querySelectorAll(".bus-gap");
    for (var i = 0; i < gaps.length; i++) {
        var gap = gaps[i];
        var time = gap.children[0].innerHTML;
        gap.style.marginLeft = (Number(time) / 2.5).toString() + "px";
    }
}