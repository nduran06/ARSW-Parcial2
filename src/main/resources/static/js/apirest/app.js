var app = (function () {

	var map;
	var markersList;
	var markers;
	var bounds;

	var getTable = function (covids) {
		alert("table")

		$("#covidsTableBody").empty();

		covids.map(function (covid) {
			alert(covid)
			$("#covidsTableBody").append(
				"<tr> <td>" + covid.country + "</td>"+
				"<td>" +  + "</td>" +
				"<td>" +  + "</td>" +
				"<td>" +  + "</td>" +
				"</tr>"
			);
		});
	};


	var plotMarkers = function (ms) {
		getTable(ms)
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
		apiclient.getAllData(plotMarkers);
	};


	return {
		init: init,
		getCovids: getCovids
	}


})();