$(function() {

	$("#confirmDeletion").click(
			function() {
				var keys = new Array();
				$("tbody input[type='checkbox']:checked").each(
						function() {
							keys.push($(this).parent().parent().parent().find(
									".param .key").text());
						});
				$.ajax({
					url : 'DelShare',
					data : {
						"keys" : keys
					},
					dataType : "json",
					type : "POST",
					traditional : true,
					success : handleDelShares
				});
			});
});

function handleDelShares(data) {
	window.location.reload();
}
