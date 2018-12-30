$(function() {
	$('#CheckAll').click(function() {
		if (this.checked) {
			$('tbody :checkbox').prop('checked', true);
		} else {
			$('tbody :checkbox').prop('checked', false);
		}
	});

	$('tbody :checkbox').click(function() {
		var checkArry = document.getElementsByName("subBox");
		for (var i = 0; i < checkArry.length; i++) {
			if (checkArry[i].checked == false) {
				$('#CheckAll').prop('checked', false);
				return;
			}
		}
		$('#CheckAll').prop('checked', true);
	});

	$('table :checkbox').click(function() {
		var checkArry = document.getElementsByName("subBox");
		for (var i = 0; i < checkArry.length; i++) {
			if (checkArry[i].checked == true) {
				$(".checkShow").show();
				return;
			}
		}
		$(".checkShow").hide();
	});

	$("tbody tr").bind({
		mouseover : function() {
			$(this).find(".disabled").show();
		},
		mouseout : function() {
			$(this).find(".disabled").hide();
		}
	});
})
