window.addEventListener('DOMContentLoaded', (event) => {
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/job4j_sellcars/ads.do',
        data: ""
    }).done(function (data) {
        const d = JSON.parse(data);
        $('#ads').empty().html(d.ads);
        $('#auth').empty().html(d.login);
    }).fail(function (err) {
        alert(err);
    });
});