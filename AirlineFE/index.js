$(function () {
    var availableTags = [];

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            const cities = (this.response || []);
            cities.forEach((element) => {
                availableTags.push(element.name);
            });
        }
    };
    xhttp.open("GET", "http://localhost:8080/Airports", true);
    xhttp.responseType = 'json';
    xhttp.send();

    $(".autocomplete").autocomplete({
        source: availableTags
    });
});

function validateMandatoryFields() {
    if (document.getElementById("input-airline").value == "") {
        alert("Departure Airport must be filled out");
        return false;
    }

    if (document.getElementById("input-arribalAirport").value == "") {
        alert("Arrival Airport must be filled out");
        return false;
    }

    if (document.getElementById("input-flightdate").value == "") {
        alert("Date must be filled out");
        return false;
    }
    return true;
}


function loadFlightListTable() {
    if (validateMandatoryFields()) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                const flights = (this.response || []);
                var scheduledFlights = flights.scheduledFlights;
                const flightTable = document.getElementById("flightTable-tbody");
                while (flightTable.firstChild) {
                    flightTable.removeChild(flightTable.firstChild);
                }

                if (scheduledFlights === undefined || scheduledFlights.length == 0) {
                    alert("no flights were found");
                }
                scheduledFlights.forEach((element) => {

                    const tRow = document.createElement('tr');

                    const fNumberCol = document.createElement('td');
                    fNumberCol.innerHTML = element.flightNumber;
                    tRow.appendChild(fNumberCol);

                    const depTerminal = document.createElement('td');
                    depTerminal.innerHTML = element.departureTerminal;
                    tRow.appendChild(depTerminal);

                    const arrTerminal = document.createElement('td');
                    arrTerminal.innerHTML = element.arrivalTerminal;
                    tRow.appendChild(arrTerminal);

                    const depTime = document.createElement('td');
                    depTime.innerHTML = element.departureTime;
                    tRow.appendChild(depTime);

                    const arrTime = document.createElement('td');
                    arrTime.innerHTML = element.arrivalTime;
                    tRow.appendChild(arrTime);
                    flightTable.appendChild(tRow);
                });


            }
        };
        var date = document.getElementById("input-flightdate").value;
        var datearray = date.split("-");
        var url = "http://localhost:8080/getFlightByRouteAndGivenDateUrl?departureAirportCode=" +
            document.getElementById("input-airline").value
            + "&arrivalAirportCode=" + document.getElementById("input-arribalAirport").value
            + "&year=" + datearray[0] + "&month=" + datearray[1] + "&day= " + datearray[2];

        xhttp.open("GET", url, true);
        xhttp.responseType = 'json';
        xhttp.send();
    }
}





