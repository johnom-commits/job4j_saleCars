window.addEventListener('DOMContentLoaded', getHTML);

let hasPhoto = false;
let brand = -1;
let lastDay = false;

function getHTML() {
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/job4j_sellcars/ads.do',
        data: {
            brand_id: brand,
            photo: hasPhoto,
            last_day: lastDay
        }
    }).done(function (data) {
        const d = JSON.parse(data);
        $('#ads').empty().html(d.ads);
        $('#auth').empty().html(d.login);
        if (brand === -1) {
            $('#brands').empty().html(d.brands);
        }
    }).fail(function (err) {
        alert(err);
    });
}

const sel_brand = document.querySelector('#brands');

sel_brand.addEventListener('change', (event) => {
    brand = event.target.value;
    getHTML();
});

const photo = document.querySelector('#photo');

photo.addEventListener('change', (event) => {
    hasPhoto = photo.checked;
    getHTML();
});

const onlyLastDay = document.querySelector('#last_day');

onlyLastDay.addEventListener('change', (event) => {
    lastDay = onlyLastDay.checked;
    getHTML();
});
