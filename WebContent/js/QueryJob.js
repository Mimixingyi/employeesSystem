$(function() {

			//分页变量
			var recordCount; //总记录数
			var pageCount; //共多少页
			var pageSize = 3; //每页多少条
			var pageNum = $.getParam('pageNum'); //当前页

			getPage();

			function getPage() {

				var params = {
					pageNum: pageNum,
					pageSize: pageSize
				}

				$.ajax({
						url: baseUrl + "/QueryJobJsonServlet",
						type: "post",
						dateType: "json",
						data: params
					})
					.done(function(data) {

						//拼接Html字符串
						var html;

						var dataTable = $('#dataTable tbody');

						var list = JSON.parse(data).list; //获得数据集合

						//清空原始内容
						dataTable.empty();

						//遍历数据集合
						$.each(list, function(index, item) {

							html = '<tr>';
							html += '	<td>' + item.jobId + '</td>';
							html += '	<td>' + item.jobName + '</td>';
							html += '	<td>' + item.jobMinSal + '</td>';
							html += '	<td>' + item.jobMaxSal + '</td>';
							html += '	<td>';
							html += '		<input type="button" data-id ="' + item.jobId + '" value="修改" class="btn btn-primary btn-sm update-btn" />';
							html += '		<input type="button" data-id ="' + item.jobId + '" value="删除" class="btn btn btn-danger btn-sm del-btn" />';
							html += '	</td>';
							html += '</tr>';

							dataTable.append(html);
						});
						//设置分页信息
						recordCount = JSON.parse(data).pager.recordCount;
						pageSize = JSON.parse(data).pager.pageSize;
						pageCount = JSON.parse(data).pager.pageCount;
						pageNum = JSON.parse(data).pager.pageNum;

						$('#pagerInfo').html('共' + recordCount + '条数据，' + pageNum + '/' + pageCount + '页');

						//动态显示分页按钮
						if(pageNum == 1) {
							/*$('#firstBtn').hide();
							$('#prevBtn').hide();*/

							$('#firstBtn').addClass('btn disabled');
							$('#prevBtn').addClass('btn disabled');
						} else {
							$('#firstBtn').removeClass('btn disabled');
							$('#prevBtn').removeClass('btn disabled');
						}

						if(pageNum == pageCount) {
							$('#nextBtn').hide();
							$('#lastBtn').hide();
						} else {
							$('#nextBtn').show();
							$('#lastBtn').show();
						}

						//动态生成页码按钮
						var start = pageNum - 2;
						var end = pageNum + 2;

						if(pageNum <= 3) {
							start = 1;
							end = 5;
						}

						if(pageNum >= pageCount - 2) {
							end = pageCount;
						}

						if(pageCount < 5) {
							end = pageCount;
						}

						$('.pagenum-btn').remove(); //删除原始数据

						for(var i = start; i <= end; i++) {
							var html = $('<li><a href="javascript:;" class="pagenum-btn">' + i + '</a></li>');

							//当前页码高亮
							if(i == pageNum) {
								$(html).addClass('active');
							}

							$('#nextBtn').parent().before(html);

						}
					}).error(function(xhr, errorStatus, errorText) {
						layer.msg('请求失败，' + xhr.status + ',' + errorText);
					});
			}

			//首页按钮单击
			$('#firstBtn').click(function() {
				pageNum = 1;
				getPage();
			});

			//上一页按钮单击
			$('#prevBtn').click(function() {
				pageNum--;
				getPage();
			});

			//下一页按钮单击
			$('#nextBtn').click(function() {
				pageNum++;
				getPage();
			});

			//末页按钮单击
			$('#lastBtn').click(function() {
				pageNum = pageCount;
				getPage();
			});

			//利用事件委托加页码按钮单击事件
			$('#pagerNav').on('click', '.pagenum-btn', function(e) {

				pageNum = $(this).text(); //获得便签文字
				//alert(1111);
				getPage();
			});

			//利用事件委托添加删除按钮操作
			$('#dataTable').on('click', '.del-btn', function() {

				if(!window.confirm('确定要删除此员工数据吗？')) {
					return;
				}

				var jobId = $(this).attr('data-id');
				var that = this;

				//console.log(empId);

				$.post(baseUrl + '/DelJobServlet', {
						jobId: jobId
					}, function(e) {
						layer.msg('删除成功');
						setTimeout(function() {
							getData();
						}, 100);
						//删除前端数据行
						$(that).parents('tr').remove();
					})
					.error(function(xhr, errorStatus, errorText) {
						layer.msg('请求失败，' + xhr.status + ',' + errorText);
					});
			});

			//利用事件委托添加详情按钮功能
			$('#dataTable').on('click', '.update-btn', function() {

				//获得当前员工id
				var jobId = $(this).attr('data-id');
				//alert(pageNum);
				
				// 弹出员工详情模态层
				layer.open({
					title: '职务详情',
					type: 2,
					area: ['620px', '600px'], //宽高
					content: 'UpdateJob.html?jobId=' + jobId + '&pageNum=' + pageNum
				});

			});
		});