$(function() {

	var jobId = $.getParam('jobId');
	var num = $.getParam('pageNum');

	$.getJSON(baseUrl + '/GetJobServlet', {
			jobId: jobId
		}, function(data) {
			//console.log(data);
			$('#jobId').val(data.jobId);
			$('#jobName').val(data.jobName);
			$('#jobMinSal').val(data.jobMinSal);
			$('#jobMaxSal').val(data.jobMaxSal);
		})
		.error(function(xhr, errorStatus, errorText) {
			layer.msg('查询职务数据失败，' + errorText + ',' + xhr.status);
		});
		
	
	$('#UpdateBtn').click(function() {
		
		var jobMinSal = $('#jobMinSal');
		var jobMaxSal = $('#jobMaxSal');
		
		
		if(jobMinSal.val() > jobMaxSal.val() ) {
					
			layer.msg('请输入正确工资金额！');
			return;		
		}

		var params = $('#form1').serialize();
	
		$.ajax({
			type:"POST",
			url:baseUrl + "/UpdateJobServlet",
			dataType:"html",
			data:params
		})
		.done(function(data) {
			
			layer.msg('修改职务信息成功！');
			
			setTimeout(function() {
				
				window.parent.location.href = 'QueryJob.html?pageNum=' + num;
				window.parent.layer.close();
				
			},500);
			
		})
		.error(function(xhr,errorStatus,errorText) {
			console.log('请求失败，' + errorText + ',' + xhr.status);
		});
		
	});
		
});