window.addEventListener('DOMContentLoaded', (event) => {
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/job4j_sellcars/view.do',
        data: {id: getId()}
    }).done(function (data) {
        $('.container').empty().html(data);
    }).fail(function (err) {
        alert(err);
    });
});

function getId() {
    const search = window.location.search;
    return search.substring(search.indexOf("=") + 1);
}

