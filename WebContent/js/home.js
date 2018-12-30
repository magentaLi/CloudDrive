$(function() {
	if ($("#loginError")[0]) {
		$("#login").modal("show");
	}
	if ($("#registerError")[0]) {
		$("#signup").modal("show");
	}
	$("#login").on("hide.bs.modal", function() {
		window.location.href = "Home";
	});
	$("#signup").on("hide.bs.modal", function() {
		window.location.href = "Home";
	});
})
