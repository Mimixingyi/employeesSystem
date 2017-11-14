$(function(){
	
	var checkLogin = true;
	var empLoginName = $('#empLoginName');
	var empPwd = $('#empPwd');
	
	//打开网页，判断当前有没有本地存储用户名和头像数据
	if (localStorage.getItem('empLoginName')) {
		empLoginName.val(localStorage.getItem('empLoginName'));
	}
	//打开网页会显示默认头像
	if (localStorage.getItem('empPic')) {
		console.log(localStorage.getItem("empPic"));
		$('#header_pic .wrapper').css('background','url(' + localStorage.getItem("empPic") + ')');
	}
		
	$('#login-btn').click(function() {

		if (empLoginName.val() == 'null') {
			layer.msg('登录名不可以为空!');
			empLoginName.focus();
			checkLogin = false; 
		}else if (empPwd.val() == '') {
			layer.msg('密码不可以为空!');
			empPwd.focus();
			checkLogin = false; 
		}
		
		var params = {
			empLoginName:empLoginName.val(),
			empPwd:empPwd.val()
		}
		
		//发出ajax请求
		$.ajax({
			url:baseUrl + "/LoginServlet",
			type:"post",
			dateType:'json',
			data:params,
			async:false
		})
		.done(function(data) {
		
			if (data == 1) {
				layer.msg('登录名不存在 !');
				empLoginName.focus();
				//checkLogin = false; 
			}else if(data == 2) {
				layer.msg('密码错误!');
				empPwd.focus();
				//checkLogin = false; 
			}else if(data == 3) {
				//checkLogin = true; 
				layer.msg('登陆成功！');
				
				//记录用户名到本地存储
				localStorage.setItem("empLoginName",empLoginName.val());
				
				/*如果用户有头像数据，把头像数据保存到本地存储(没有头像数据，登录时则清空原来头像数据)*/
				$.get(baseUrl + '/GetEmpPicServlet',function(data) {
					
					if (data && data != 'null') {
						localStorage.setItem('empPic',data);
						
						location.href = 'main.html';
					}else {
						localStorage.removeItem('empPic');
					}
					
					location.href = 'main.html';
					
				})
				.error(function(xhr, errorStatus, errorText) {
					layer.msg('请求失败，' + errorText + ',' + xhr.status);
				});
			}
		})
		.fail(function(xhr, errorStatus, errorText) {
			layer.msg('请求失败，' + errorText + ',' + xhr.status);
		});
		
		//return checkLogin;
	});

});