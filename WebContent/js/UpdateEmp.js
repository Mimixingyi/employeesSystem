$(function() {

	//设置全局ajax，所有的请求都是同步请求
	$.ajaxSetup({
		async: false
	});

	//动态生成部门列表
	var deptId = $('#deptId');

	//发出ajax请求，调用后端代码
	$.ajax({
			url: baseUrl + '/QueryDeptJsonServlet',
			type: 'GET',
			dataType: 'json',
			data: {}
		})
		.done(function(data) {

			//遍历json数组
			$.each(data, function(index, el) {

				var opt = '<option value = "' + el.deptId + '">' + el.deptName + '</option>';
				deptId.append(opt);

			});

		})
		.fail(function(xhr, errorStatus, errorText) {
			layer.msg('请求失败，' + errorText + ',' + xhr.status);
	});

	//动态生成职务列表
	var jobId = $('#jobId');

	//发出ajax请求，调用后端代码
	$.ajax({
			url: baseUrl + '/QueryJobJsonServlet',
			type: 'POST',
			dataType: 'json',
			data: {}
		})
		.done(function(data) {
			
			//遍历json数组
			$.each(data.list, function(index, el) {

				var opt = '<option value = "' + el.jobId + '">' + el.jobName + '</option>';
				jobId.append(opt);

			});

		})
		.fail(function(xhr, errorStatus, errorText) {
			layer.msg('请求失败，' + errorText + ',' + xhr.status);
		});

	//根据id查询员工数据，填充到表单中显示
	var empId = $.getParam('empId');
	//获得传递页码
	var num = $.getParam('pageNum');
	//alert(num);

	$.getJSON(baseUrl + '/GetEmpServlet', {
			empId: empId
		}, function(data) {

			$('#empId').val(data.empId);
			$('#empName').val(data.empName);
			$('#empLoginName').val(data.empLoginName);
			$('#empEmail').val(data.empEmail);
			$('#empPhone').val(data.empPhone);
			$('#empSalary').val(data.empSalary);
			$('#empHiredate').val(data.empHiredate);
			$('#deptId').val(data.dept.deptId);
			$('#jobId').val(data.job.jobId);
			$('#empInfo').val(data.empInfo);

		})
		.error(function(xhr, errorStatus, errorText) {
			layer.msg('查询员工数据失败，' + errorText + ',' + xhr.status);
		});

	//表单验证函数
	function checkForm() {

		var r;
		var params;

		//验证职务名称
		var empName = $('#empName');
		var h = /^\d+$/;

		if(h.test(empName.val())) {
			layer.msg('请输入汉字或英文名称！');
			empName.focus();
			return false;
		} else if(empName.val() == '') {
			layer.msg('职务名称不能为空');
			empName.focus();
			return false;
		} else if(empName.val().length > 10) {
			layer.msg('职务名称最多10字符！');
			empName.focus();
			return false;
		}

		//电话号码格式必须合法
		var empPhone = $('#empPhone');

		r = /^1[345678]\d{9}$/;
		//var r2 = /^(0\d{3})-\d{7,8}$/;

		if(!r.test(empPhone.val())) {
			layer.msg('请输入正确的电话号码！');
			empPhone.focus();
			return false;
		}

		//工资最多6位整数，两位小数
		var empSalary = $('#empSalary');

		r = /^0|([1-9]\d{0,5}(\.\d{1,2})?)$/;

		if(!r.test(empSalary.val())) {
			console.log(empSalary);
			layer.msg('工资格式不合法!');
			empSalary.focus();
			return false;
		}

		//必须选择部门
		var deptId = $('#deptId');

		if(deptId.val() == '') {
			layer.msg('请选择部门!');
			deptId.focus();
			return false;
		}

		//必须选择职务
		var jobId = $('#jobId');

		if(jobId.val() == '') {
			layer.msg('请选择职务!');
			jobId.focus();
			return false;
		}

		//工资不能超过职务最高最低范围
		var checkSalary = true;
		var empSalary = $('#empSalary');

		params = {
			jobId: jobId.val()
		}

		//发出ajax请求，调用后端代码
		$.ajax({
				url: baseUrl + '/GetJobServlet',
				type: 'POST',
				dataType: 'json',
				data: params,
				async: false //必须设置为同步执行
			})
			.done(function(data) {

				if(parseFloat(empSalary.val()) < data.jobMinSal ||
					parseFloat(empSalary.val()) > data.jobMaxSal) {

					layer.msg('工资必须介于职务工资' + data.jobMinSal + '和' + data.jobMaxSal + '之间');

					checkSalary = false;

				}

			})
			.fail(function(xhr, errorStatus, errorText) {
				layer.msg('请求失败，' + errorText + ',' + xhr.status);
			});

		if(!checkSalary)
			return false;

		//员工介绍不能超过100字
		var empInfo = $('#empInfo');

		if(empInfo.val() == '') {
			layer.msg('请输入不超过100个字符的内容!');
			empInfo.focus();
			return false;
		} else if(empInfo.val().length > 10) {
			layer.msg('最多输入100个字符!');
			empInfo.focus();
			return false;
		}

		return true;
	}

	//点击修改按钮
	$('#UpdateBtn').click(function() {

		//调用表单验证函数
		if(!checkForm()) {
			return;
		}

		var params = $('#form1').serialize();

		//发出ajax请求，调用后端代码
		$.ajax({
				url: baseUrl + '/UpdateEmpServlet',
				type: 'POST',
				dataType: 'html',
				data: params
			})
			.done(function(data) {
				
				layer.msg('修改员工成功！');
				
				setTimeout(function() {
					
					//父窗口刷新,跳转到指定页码画面
					window.parent.location.href = 'QueryEmp.html?pageNum=' + num;
					//调用父窗口的关闭层语句
					window.parent.layer.closeAll();
			
				},500);
			})
			.fail(function(xhr, errorStatus, errorText) {
				layer.msg('请求失败，' + errorText + ',' + xhr.status);
			});
	});

});