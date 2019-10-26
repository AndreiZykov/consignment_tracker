function move(w, max) {
    var elem = document.getElementById("myBar");
    var width = w;
    var id = setInterval(frame, 10);

    function frame() {
        if (width >= max) {
            clearInterval(id);
            submitForm();

        } else {
            width++;
            elem.style.width = width + '%';
            elem.innerHTML = width * 1 + '%';
        }
    }


}
function submitForm() {
    document.getElementById("reg").submit();
}