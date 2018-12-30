$(function() {
	$("#uploadFile").fileinput({
		theme : 'fa',
		language : 'zh',
		uploadUrl : "UpLoad",
		allowedPreviewTypes : [ 'image', 'html', 'text', 'video', 'audio' ],// 预览类型
		// allowedFileExtensions: ['jpg', 'gif', 'png'], //文件上传类型
		maxFileSize : 0,
		maxFileCount : 10
	});

	$('#uploadFile')
			.on(
					'filebatchuploadcomplete',
					function(event, files, extra) {
						$("#uploadModal").find(".modal-footer").find(".btn")
								.text("确定");
						console.log('File batch upload complete');
					});
	$('#uploadModal')
			.on(
					"hide.bs.modal",
					function() {
						if ($("#uploadModal").find(".modal-footer")
								.find(".btn").text() == "确定") {
							window.location.reload();
						}
					});

	$("#shareModal").on("hide.bs.modal", function() {
		window.location.reload();
	});

	$(".share").click(function() {
		$.get($(this).attr("url"), {}, handleShowLink);
	});
	var clipboard = new Clipboard('#copyLink');
	clipboard.on('success', function(e) {
		$("#copySuccess").show();
		e.clearSelection();
	});
	clipboard.on('error', function(e) {
		$("#copyError").show();
	});

	$("#confirmDeletion").click(
			function() {
				var fileNames = new Array();
				var paths = new Array();
				$("tbody input[type='checkbox']:checked").each(
						function() {
							fileNames.push($(this).parent().parent().parent()
									.find(".param .fileName").text());
							paths.push($(this).parent().parent().parent().find(
									".param .path").text());
						});
				$.ajax({
					url : 'DelFile',
					data : {
						"fileName" : fileNames,
						"path" : paths
					},
					dataType : "json",
					type : "POST",
					traditional : true,
					success : handleDelFiles
				});
			});
});
function handleShowLink(data) {
	$("#linkText").val(data);
	$("#shareModal").modal("show");
};

function handleDelFiles(data) {
	window.location.reload();
}
