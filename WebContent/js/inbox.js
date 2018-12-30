$(function() {
	$("#inboxUpload").fileinput({
		theme : 'fa',
		language : 'zh',
		uploadUrl : "UpLoad",
		allowedPreviewTypes : [ 'image', 'html', 'text', 'video', 'audio' ],// 预览类型
		// allowedFileExtensions: ['jpg', 'gif', 'png'], //文件上传类型
		maxFileSize : 0,
		maxFileCount : 10,
		uploadExtraData : function() {
			var obj = {};
			$('form').find('.form-control-inline').each(function() {
				var id = $(this).attr('id'), val = $(this).val();
				obj[id] = val;
			});
			obj["path"] = $("#inboxName").text();
			obj["userName"] = $("#inboxName").attr("user");
			return obj;
		}
	});

	$("#mkInbox").click(function() {
		$.get("CreateInbox", {
			inboxName : $("#inboxName").val(),
			illustrate : $("#illustrate").val()
		}, handleShowLink);
	});
	$('#inboxLinkModal').on("hide.bs.modal", function() {
		window.location.href = "InboxManage";
	});
	var clipboard = new Clipboard('#copyLink');
	clipboard.on('success', function(e) {
		$("#copySuccess").show();
		e.clearSelection();
	});

	clipboard.on('error', function(e) {
		$("#copyError").show();
	});
	$('[data-toggle="popover"]').popover();

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
	$(".delInbox").click(function() {
		var key = $(this).next().find(".key").text();
		var inboxName = $(this).next().find(".inboxName").text();
		$("#subkey").val(key);
		$("#subInboxName").val(inboxName);
		$("#delInboxModal").modal("show");
	});
});

function handleShowLink(results) {
	var json = jQuery.parseJSON(results);
	if (json.message != null) {
		$("#createInboxInfo strong").text(json.message);
		$("#createInboxInfo").show();
	} else {
		$("#newFolder").modal("hide");
		$("#linkText").val(json.link);
		$("#inboxLinkModal").modal("show");
	}
};

function handleDelFiles(data) {
	window.location.reload();
}
