window.addEventListener('DOMContentLoaded', (event) => {
    event.preventDefault();
    getPhoto();

    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/job4j_sellcars/brands.do',
        data: ""
    }).done(function (data) {
        $('#brand').empty().html(data);
    }).fail(function (err) {
        alert(err);
    });
});

function getPhoto() {
    const photo = parse();
    document.querySelector('form').insertAdjacentHTML('afterbegin', `<input type="hidden" name="photo" value=${photo}>`);
}

function parse() {
    const search = window.location.search;
    const number = search.indexOf("=");
    return search.substring(number + 1);
}

const sel_brand = document.querySelector('#brand');

sel_brand.addEventListener('change', (event) => {
    const brand = event.target.value;
    getModel(brand);
});

function getModel(brand) {
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/job4j_sellcars/models.do',
        data: {brand_id : brand}
    }).done(function (data) {
        $('#model').empty().html(data);
    }).fail(function (err) {
        alert(err);
    });
}

