var app = (function () {

	var map;
	var markersList;
	var markers;
	var bounds;
	var current_country;
	var current_deaths;
	var current_confimed;
	var current_recovered;


	var getTable = function (covids) {

		$("#covidsTableBody").empty();

		covids.map(function (covid) {
			$("#covidsTableBody").append(
				"<tr> <td onclick=app.setCurrentCountry('"+covid.name+"','"+covid.deaths+
				"','"+covid.confirmed+"','"+covid.recovered+"')>" + covid.name + "</td>"+ 
				"<td>" + covid.deaths + "</td>" +
				"<td>" + covid.confirmed + "</td>" +
				"<td>" + covid.recovered + "</td>" +
				"</tr>"
			);
		});
	};


	var getTableRegion = function (covids) {

		$("#covidsRegionTableBody").empty();

		covids.map(function (covid) {
			$("#covidsRegionTableBody").append(
				"<tr> <td>"+ covid.province + "</td>"+ 
				"<td>" + covid.deaths + "</td>" +
				"<td>" + covid.confirmed + "</td>" +
				"<td>" + covid.recovered + "</td>" +
				"</tr>"
			);
		});
	};

	var getTableCurrentCountry = function () {
		$("#covidsCountryTableBody").empty();

		$("#covidsCountryTableBody").append(
			"<tr> <td>Num Deaths</td>" +
			"<td>" + current_deaths + "</td>" +
			"</tr>" +
			"<tr> <td>Num Infected</td>" +
			"<td>" + current_confimed + "</td>" +
			"</tr>" +
			"<tr> <td>Num Cured</td>" +
			"<td>" + current_recovered + "</td>" +
			"</tr>"
		);

	};

	var setCurrentCountry=function(country_name, deaths, confirmed, recovered){
		current_country=country_name;
		current_deaths=deaths;
		current_confimed=confirmed;
		current_recovered=recovered;
		$("#countryName").text(country_name);
		$('#hideB').click();
		getLocation(current_country);
		getTableCurrentCountry();
		getCovidsCountry(current_country);
		
	};

	var getCovidsCountry = function (countryName) {
		clearMarkers();
		alert(countryName);
		apiclient.getCountryData(countryName, getTableRegion);
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

	

	var getCovids = function () {
		clearMarkers();
		apiclient.getAllDataCountries(getTable);
	};

	var hiddenCreate = function(){
    	var el = document.getElementById("selectedCountry"); 
    	el.style.display = (el.style.display == 'none') ? 'block' : 'none'; 
    }

	
	var start = function(){
		document.getElementById("aux_button").style.display = "none";
		$('#hideB').click();
		getCovids();
		init();
		
	};

	var clearMarkers = function () {
		if (markersList) {
			markersList.forEach(function (marker) {
				marker.setMap(null);
			});
		}

	};

	var plot = function (ms) {
		var covids=JSON.parse(ms);
		var latlng=covids[0].latlng;
		alert(latlng)
		
		markers = [];
		bounds = new google.maps.LatLngBounds();

		var position = new google.maps.LatLng(latlng[0], latlng[1]);
		markers.push(
			new google.maps.Marker({
				position: position,
				map: map,
				animation: google.maps.Animation.DROP
			})
		);

		bounds.extend(position);
		markersList = markers;
		map.fitBounds(bounds);


	};

	var getLocation=function(name){
		apiclient.getLocation(name, plot);
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