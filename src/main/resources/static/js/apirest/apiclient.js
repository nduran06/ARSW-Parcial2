var apiclient = (function () {
	var url = "http://localhost:8080/rapidapi"; 

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

		getLocation: function (name) {
			var urlApp = url + "/covids/location?name="+name;
			$.ajax({
				url: urlApp,
				type: "GET",
				success: function (res) {
					alert(res)
					alert(JSON.parse(res))
					//callback(res);
				},
				error: function (err) {
					alert("Error :"+err);
				}
			});
		}

	};
})();