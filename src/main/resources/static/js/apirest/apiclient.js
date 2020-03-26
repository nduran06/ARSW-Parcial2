var apiclient = (function () {
	var url = "https://localhost:8080/rapidapi"; 

	return {
		getAllData: function (callback) {
			var urlApp = url + "/covids";
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