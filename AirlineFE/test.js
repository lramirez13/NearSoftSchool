function loadXMLDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const cities = (this.response || []);
            const list = document.getElementById('autocomplete-results');
            cities.forEach((element) => {
                console.log(`name: ${element.name}`);
                const city = document.createElement('li')
                city.innerText=element.name;
                list.appendChild(city);
            });
            document.getElementById('autocomplete-results').innerHtml = list;
        }
    };
    xhttp.open("GET", "http://localhost:8080/Airports", true);
    xhttp.responseType = 'json';
    xhttp.send();
}