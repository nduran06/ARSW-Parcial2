var apiclient = (function () {
	var url = "http://https://hidden-thicket-36590.herokuapp.com/rapidapi"; 

	return {
		getAllDataCountries: function (callback) {
			var urlApp = url + "/covids/countries";
			$.ajax({
				url: urlApp,
				type: "GET",
				success: function (res) {
					callback(res);
				},
				error: function (err) {
					alert("Error :"+err);
				}
			});
		},

		getCountryData: function (country, callback) {
			var urlApp = url + "/covids/country?country="+country;
			$.ajax({
				url: urlApp,
				type: "GET",
				success: function (res) {
					callback(res);
				},
				error: function (err) {
					alert("Error :"+err);
				}
			});
		}, 

		getLocation: function (name, callback) {
			var urlApp = url + "/covids/location?name="+name;
			$.ajax({
				url: urlApp,
				type: "GET",
				success: function (res) {
					callback(res);
				},
				error: function (err) {
					alert("Error :"+err);
				}
			});
		}

	};
})();