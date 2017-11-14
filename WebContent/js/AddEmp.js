$(function() {
	// 给文本框绑定日历控件
	$('#empHiredate').datetimepicker({
		lang: 'ch',
		format: 'Y-m-d',
		formatDate: 'Y-m-d',
		timepicker: false
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
			type: 'GET',
			dataType: 'json',
			data: {}
		})
		.done(function(data) {

			//遍历json数组
			$.each(data, function(index, el) {

				var opt = '<option value = "' + el.jobId + '">' + el.jobName + '</option>';
				jobId.append(opt);

			});

		})
		.fail(function(xhr, errorStatus, errorText) {
			layer.msg('请求失败，' + errorText + ',' + xhr.status);
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

		//登录名（不能为空，最多10字符，必须是唯一）
		var empLoginName = $('#empLoginName');

		if(h.test(empLoginName.val())) {
			layer.msg('请输入汉字或英文登录名！');
			empLoginName.focus();
			return false;
		} else if(empLoginName.val() == '') {
			layer.msg('登录名不能为空！');
			empLoginName.focus();
			return false;
		} else if(empLoginName.val().length > 10) {
			layer.msg('登录名最多10字符！');
			empLoginName.focus();
			return false;
		}

		params = {
			empLoginName: empLoginName.val()
		}

		var checkLoginName = true;

		//发出ajax请求，调用后端代码
		$.ajax({
				url: baseUrl + '/CheckLoginNameServlet',
				type: 'POST',
				dataType: 'html',
				data: params,
				async: false //必须设置为同步执行
			})
			.done(function(data) {

				if(data == 'no') {
					layer.msg('登录名已被占用！');
					empLoginName.focus();
					checkLoginName = false; //设置标志为false
				}
			})
			.fail(function(xhr, errorStatus, errorText) {
				layer.msg('请求失败，' + errorText + ',' + xhr.status);
			});

		if(!checkLoginName)
			return false;

		//密码必须6-8位，字母数字下划线组成
		r = /^\w{6,8}$/;

		var empPwd = $('#empPwd');
		var checkPwd = $('#checkPwd');

		if(!r.test(empPwd.val())) {
			layer.msg('密码必须6-8位，字母数字下划线组成！');
			empPwd.focus();
			return false;
		} else if(empPwd.val() != checkPwd.val()) {
			layer.msg('密码输入错误！');
			return false;
		}

		//邮箱格式必须合法
		r = /^\w+@[a-z0-9_-]+(\.[a-z]{2,6}){1,2}$/;
		
		var empEmail = $('#empEmail');
		
		if (!r.test(empEmail.val())) {
			layer.msg('请输入正确的邮箱格式！');
			empPwd.focus();
			return false;
		}
		
		//电话号码格式必须合法
		var empPhone = $('#empPhone');
				
		r = /^1[345678]\d{9}$/;
		//var r2 = /^(0\d{3})-\d{7,8}$/;
		
		if (!r.test(empPhone.val())) {
			layer.msg('请输入正确的电话号码！');
			empPhone.focus();
			return false;
		}
		
		//工资最多6位整数，两位小数
		var empSalary = $('#empSalary');

		r = /^0|([1-9]\d{0,5}(\.\d{1,2})?)$/;

		if(!r.test(empSalary.val())) {
			layer.msg('工资格式不合法!');
			empSalary.focus();
			return false;
		}

		//入职日期不能为空
		var empHiredate = $('#empHiredate');

		if(empHiredate.val() == '') {
			layer.msg('请选择入职日期!');
			empHiredate.focus();
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
		
		if (empInfo.val() == '' ) {
			layer.msg('请输入不超过100个字符的内容!');
			empInfo.focus();
			return false;
		}else if(empInfo.val().length > 10) {
			layer.msg('最多输入100个字符!');
			empInfo.focus();
			return false;
		}

		return true;
	}

	//登录名失去焦点，进行验证
	$('#empLoginName').change(function() {

		if(this.value == '') {
			return;
		}

		layer.msg('正在检查用户名', {
			icon: 16
		});

		var params = {
			empLoginName: this.value
		}

		//发出ajax请求，调用后端代码
		$.ajax({
				url: baseUrl + '/CheckLoginNameServlet',
				type: 'POST',
				dataType: 'html',
				data: params
			})
			.done(function(data) {

				if(data == 'yes') {
					layer.msg('创建登录名成功！');
				} else {
					layer.msg('登录名已被占用！');
					$('#empLoginName').focus();
				}

			})
			.fail(function(xhr, errorStatus, errorText) {
				layer.msg('请求失败，' + errorText + ',' + xhr.status);
			});
	});

	//点击增加职务按钮
	$('#addBtn').click(function() {

		//调用表单验证函数
		if(!checkForm()) {
			return;
		}

		var params = $('#form1').serialize();

		//发出ajax请求，调用后端代码
		$.ajax({
				url: baseUrl + '/AddEmpServlet',
				type: 'POST',
				dataType: 'html',
				data: params
			})
			.done(function(data) {
				layer.msg('增加员工成功！');
				
				setTimeout(function() {
					location.href = 'QueryEmp.html';
				},200);
				
			})
			.fail(function(xhr, errorStatus, errorText) {
				layer.msg('请求失败，' + errorText + ',' + xhr.status);
			});
	});

});