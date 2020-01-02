	$(function() {	//flexigrid表格设置
	    $("#flex").flexigrid({
		        url : 'gradeList.action',	//ajax方式对应的url地址
		        dataType : 'json',	//请求数据格式
		        colModel : [{
			            display : 'ID',
			            name : 'id',
			            width : 40,	//得加上 要不IE报错
			            sortable : true,	//是否可被排序
			            align : 'center',
			            hide : true    //是否隐藏该列
		        	},{
			            display : '用户名',
			            name : 'userName',
			            width : 100,
			            sortable : true,
			            align : 'center'
		            }, {
		            	display : '类目名',
		            	name : 'categoryName',
		            	width : 140,
		            	sortable : false,
		            	align : 'center',
		            	hide : false    //是否隐藏该列
		            }, {
			            display : '成绩',
			            name : 'grade',
			            width : 100,
			            sortable : true,
			            align : 'center'
		            }, {
		            	display : '答题数',
		            	name : 'count',
		            	width : 100,
		            	sortable : false,
		            	align : 'center',
		            	hide : false    //是否隐藏该列
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
		        sortname : 'id',		//按那一列排序
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
	            
		        width : 520,	
		        height : 260,
		        title : '类目成绩列表'
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
				action="gradeAdd.action";
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
			    $('#form input[name="grade_id"]').val(data[0]);
			    $('#form select[name="user_id"]').val(data[1]);
			    $('#form select[name="category_id"]').val(data[2]);
			    $('#form input[name="grade.grade"]').val(data[3]);
			    $('#form input[name="grade.total"]').val(data[4]);
			    action="gradeUpdate.action";
			    $("#usermsg").jqmShow();
			    break;
			    
			 case '删除' :
				selected_count = $('.trSelected', grid).length;
			    if (selected_count == 0) {
				    alert('请至少选择一条记录!');
				    return;
			    }
			    var ids = '';
			    $('.trSelected td:nth-child(2) div', grid).each(function(i) {
				        if (i)
					        ids += ',';
				        ids += $(this).text();
			        })
			    if (confirm("确定删除所选成绩信息?")) {
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
	        url : 'gradeDelete.action',
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