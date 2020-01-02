	$(function() {	//flexigrid表格设置
	    $("#flex").flexigrid({
		        url : 'questionList.action',	//ajax方式对应的url地址
		        dataType : 'json',	//请求数据格式
		        colModel : [{
			            display : 'ID',
			            name : 'id',
			            width : 40,	//得加上 要不IE报错
			            sortable : true,	//是否可被排序
			            align : 'center',
			            hide : true    //是否隐藏该列
		        	}, {
			            display : '类目',
			            name : 'categoryName',
			            width : 120,
			            sortable : false,
			            align : 'left'
		            }, {
			            display : '题目',
			            name : 'title',
			            width : 180,
			            sortable : false,
			            align : 'left'
		            }, {
		            	display : '选项1',
		            	name : 'option1',
		            	width : 60,
		            	sortable : false,
		            	align : 'left',
		            	hide : false    //是否隐藏该列
		            }, {
		            	display : '选项2',
		            	name : 'option2',
		            	width : 60,
		            	sortable : false,
		            	align : 'left',
		            	hide : false    //是否隐藏该列
		            }, {
		            	display : '选项3',
		            	name : 'option3',
		            	width : 60,
		            	sortable : false,
		            	align : 'left',
		            	hide : false    //是否隐藏该列
		            }, {
		            	display : '选项4',
		            	name : 'option4',
		            	width : 60,
		            	sortable : false,
		            	align : 'left',
		            	hide : false    //是否隐藏该列
		            }, {
			            display : '答案',
			            name : 'result',
			            width : 20,
			            sortable : true,
			            align : 'center'
		            }, {
		            	display : '分值',
		            	name : 'score',
		            	width : 20,
		            	sortable : true,
		            	align : 'center'
		            }, {
		            	display : '解析',
		            	name : 'analy',
		            	width : 150,
		            	sortable : true,
		            	align : 'left'
		            }],
		        buttons : [{			//按钮组
			            name : '添加',		//按钮名称
			            bclass : 'add',	
			            onpress : test	//点击后处理方法
		            }, {
			            separator : true	//设置分割线
		            }, {
			            name : '修改',
			            bclass : 'edit',
			            onpress : test
		            }, {
			            separator : true
		            }, {
			            name : '删除',
			            bclass : 'delete',
			            onpress : test
		            }, {
			            separator : true
		            }],

			    usepager : true,	//是否使用分页
		        sortname : 'id',	//按那一列排序
		        sortorder : 'desc',	//排序方式
		        rp : 10,	//每页默认显示数量
		        
		        useRp : true,	//是否可动态设置每页显示数量
		        striped : true,	//是否显示斑纹效果
		        resizable : true,	//是否可伸缩
		        nowrap : true, // 是否不换行
		        checkbox: true,//是否显示第一列的checkbox
		        showTableToggleBtn : false,	//是否显示表开关
		        
		        errormsg : '发生错误',	//错误提示信息
		        nomsg : '无相关记录',	//无结果提示信息
		        procmsg : '正在处理,请稍候 ... ',	//正在处理的提示信息
		        pagestat : '显示 {from}-{to} 行,共 {total} 行',	// 显示当前页和总页面的样式
		        
		        autoload : true,		// 自动加载 
		        minwidth : 30, 	// 列的最小宽度   
	            minheight : 80,	// 列的最小高度   
	            
		        width : 850,	
		        height : 260,
		        title : '考题列表'
	        });

	
	//定义全局变量,用于在点击相应按钮时动态提交
	var action="";
	//点击表格中按钮时执行方法
	function test(com, grid) {
		 switch (com) {
		 
			 case '添加' :
				$("#form input[type='text']").each(function() {
				        $(this).val("");	//将id为form的input标签中带有[]中属性的标签置空
			        });
				action="questionAdd.action";
				$("#usermsg").jqmShow();//显示id为usermsg的标签
			    break;
				
			 case '修改' :
				selected_count = $('.trSelected', grid).length;
				if (selected_count == 0) {
				    alert('请至少选择一条记录!');
				    return;
			    }
			    if (selected_count > 1) {
				    alert('只能同时修改一条记录!');
				    return;
			    }
			    data = new Array();
			    $('.trSelected td', grid).each(function(i) {
				        data[i] = $(this).children('div').text();
			        });
			    $('#form input[name="question_id"]').val(data[0]);
			    $('#form select[name="category_id"]').val(data[1]);
			    $('#form input[name="question.title"]').val(data[2]);
			    $('#form input[name="question.option1"]').val(data[3]);
			    $('#form input[name="question.option2"]').val(data[4]);
			    $('#form input[name="question.option3"]').val(data[5]);
			    $('#form input[name="question.option4"]').val(data[6]);
			    $('#form input[name="question.result"]').val(data[7]);
			    $('#form input[name="question.score"]').val(data[8]);
			    $('#form input[name="question.analy"]').val(data[9]);
			   
			    action="questionUpdate.action";
			    $("#usermsg").jqmShow();
			    break;
			    
			 case '删除' :
				selected_count = $('.trSelected', grid).length;
			    if (selected_count == 0) {
				    alert('请至少选择一条记录!');
				    return;
			    }
			    var names = '';
			    $('.trSelected td:nth-child(5) div', grid).each(function(i) {
				        if (i)
					        names += ',';
				        names += $(this).text();
			        });
			    var ids = '';
			    $('.trSelected td:nth-child(2) div', grid).each(function(i) {
				        if (i)
					        ids += ',';
				        ids += $(this).text();
			        })
			    if (confirm("确定删除考题[" + names + "]?")) {
			    	deleteCategory(ids);
			    }
			    break;
		 }
	}
		
	//为submit按钮添加点击事件
	$("#submit").click(function(){
		$.ajax({
	        url : action,
	        data : $("#form").serialize(),
	        type : 'POST',
	        dataType : 'text',
	        success : function(rs) {
	        	alert(rs);
		    	$('#usermsg').jqmHide();
		        $('#flex').flexReload();
			}
		});
	})
   
  	//删除方法
	function deleteCategory(ids) {
    $.ajax({
	        url : 'questionDelete.action',
	        data : {idstr : ids},	//发送到服务器的数据。必须为 Key/Value 格式。
	        type : 'POST',
	        dataType : 'text',
	        success : function(rs) {
	        	alert(rs);
		        $('#flex').flexReload();//表格重载
	        }
        });
   }
	
});
	
	
	//初始化jqmodal弹出框并设置参数
	$( function() {   
		  $('#usermsg').jqm({
					trigger : '',// 触发
					modal : true,// 限制输入的对话
					overlay : 50,// 遮罩程度%
					// ajax: '@href',//ajax请求
					// ajaxText:'',//ajax返回内容
					// target : '',//目标元素
				  })
				  .jqmAddClose('.close')//添加触发关闭的selector
				  .jqDrag('.drag');// 添加拖拽的selector //jquery版本不同会影响效果
			//	  .jqmAddTrigger(''); //添加触发的selector
		});