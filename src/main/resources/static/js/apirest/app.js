var app = (function () {

	var map;
	var markersList;
	var markers;
	var bounds;

	var getTable = function (airports) {

		$("#airportsTableBody").empty();

		airports.map(function (airport) {
			$("#airportsTable > tbody").append(
				"<tr> <td>" + airport.code + "</td>" +
				"<td>" + airport.name + "</td>" +
				"<td>" + airport.city + "</td>" +
				"<td>" + airport.countryCode + "</td>" +
				"</tr>"
			);
		});
	};


	var plotMarkers = function (ms) {
		
	};

	var init = function () {

		document.addEventListener('DOMContentLoaded', function () {
			if (document.querySelectorAll('#map').length > 0) {
				if (document.querySelector('html').lang)
					lang = document.querySelector('html').lang;
				else
					lang = 'en';

			}
		});

		map = new google.maps.Map(document.getElementById('map'), {
			center: { lat: -34.397, lng: 150.644 },
			zoom: 8
		});

	};


	var clearMarkers = function () {
		if (markersList) {
			markersList.forEach(function (marker) {
				marker.setMap(null);
			});
		}

	};

	var findAirportsByName = function (name) {
		clearMarkers();
		apiclient.getAirportsByName(name, plotMarkers);
	};

	var getCovids = function () {
		//clearMarkers();
		apiclient.getAllData();
	};


	return {
		init: init,
		getCovids: getCovids
	}


})();