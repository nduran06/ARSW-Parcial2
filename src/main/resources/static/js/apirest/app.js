var app = (function () {

	var map;
	var markersList;
	var markers;
	var bounds;
	var current_country;

	var getTable = function (covids) {

		$("#covidsTableBody").empty();

		covids.map(function (covid) {
			$("#covidsTableBody").append(
				"<tr> <td onclick=app.setCurrentCountry('"+covid.name+"')>" + covid.name + "</td>"+ 
				"<td>" + covid.deaths + "</td>" +
				"<td>" + covid.confirmed + "</td>" +
				"<td>" + covid.recovered + "</td>" +
				"</tr>"
			);
		});
	};

	var setCurrentCountry=function(country_name){
		current_country=country_name;
		$("#countryName").text(country_name);
		$('#hideB').click();
		getLocation();
		
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
		apiclient.getAllDataCountries(plotMarkers);
	};

	var hiddenCreate = function(){
    	var el = document.getElementById("selectedCountry"); 
    	el.style.display = (el.style.display == 'none') ? 'block' : 'none'; 
    }

	var hideDiv = function(){
		
        $('#hideB').click();

    };
	
	var start = function(){
		document.getElementById("aux_button").style.display = "none";
		$('#hideB').click();
		getCovids();
		
	};

	var getLocation=function(){
		alert("get location")
		apiclient.getLocation("colombia");
	};
	

	return {
		init: init,
		start: start,
		setCurrentCountry:setCurrentCountry,
		hiddenCreate: hiddenCreate,
		getCovids: getCovids,
		getLocation:getLocation
	}


})();