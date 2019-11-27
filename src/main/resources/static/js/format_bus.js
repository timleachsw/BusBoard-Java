var gaps = document.querySelectorAll(".bus-gap");
for (var i = 0; i < gaps.length; i++) {
    var gap = gaps[i];
    var time = gap.children[0].innerHTML;
    gap.style.marginLeft = (Number(time)/2.5).toString() + "px";
}