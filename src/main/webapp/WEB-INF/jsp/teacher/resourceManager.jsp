<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>系统信息查看</title>
<jsp:include page="/WEB-INF/include/meta.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/css-jquery.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/adaptor.jsp"></jsp:include>
<jsp:include page="/WEB-INF/include/redirectPage.jsp"></jsp:include>
</head>
<body>
	<section class="content-header">
		<h1>资源管理</h1>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb" id="path">
					<li id="0"><i class="fa fa-home"></i> Home</li>
				</ol>
			</div>
			<div class="col-md-12" id="tableDiv">
				<div id="toolbar">
					<button id="newFolder" class="btn btn-primary">
						<i class="fa fa-folder"></i> 新建文件夹
					</button>
					<button id="uploadFiles" class="btn btn-primary">
						<i class="fa fa-upload"></i> 上传文件
					</button>
				</div>
				<table id="resourceTable"
					class="table table-bordered table-striped dataTable" role="grid">
				</table>
			</div>
			<div class="col-md-12" id="previewOperateDiv" style="padding-bottom: 10px;">
				<div id="toolbar">
					<button id="previewBtn" class="btn btn-primary">
						<i class="fa fa-eye"></i> 预览
					</button>
					<button id="downloadBtn" class="btn btn-primary">
						<i class="fa fa-download"></i> 下载
					</button>
				</div>
			</div>
			<div class="col-md-12" id="previewDiv">
				<div class="box box-success">
					<div class="box-header ui-sortable-handle" style="cursor: move;">
						<i class="fa fa-comments-o"></i>
						<h3 class="box-title">评论</h3>
					</div>
					<div class="slimScrollDiv"
						style="position: relative; overflow: hidden; width: auto; height: 500px;">
						<div class="box-body chat" style="overflow: hidden; width: auto; height: 500px; overflow-y:auto;"
						 id="commentDiv">
						</div>
					</div>
					<!-- /.chat -->
					<div class="box-footer">
						<div class="input-group">
							<input class="form-control" placeholder="说句话吧。。。" id="comContent">
							<div class="input-group-btn">
								<button type="button" class="btn btn-success" id="comSave">
									<i class="fa fa-plus"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<div class="modal" id="resourceModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="resourceTitle"></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="resourceForm"
						action="../resource/resource!update.action">
						<input type="hidden" name="resource.resTeaId"
							value="${teacher.teaId }" /> <input type="hidden"
							id="resourceId" name="resource.resId" /> <input type="hidden"
							id="resourceType" name="resource.resType" value="folder" />
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2" id="labFile">文件名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="resourceName"
										name="resource.resName" placeholder="新文件名">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="resourceSave" class="btn btn-primary">保存</button>
					<button type="button" id="resourceCancel" class="btn btn-default"
						data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="newFolderModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">新建文件夹</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="newFolderForm"
						action="../resource/resource!save.action">
						<input type="hidden" name="resource.resTeaId"
							value="${teacher.teaId }" /> <input type="hidden"
							name="resource.resState" value="1" /> <input type="hidden"
							name="resource.resParId" id="resourceParId" /> <input
							type="hidden" name="resource.resType" id="resourceTypeFolder"
							value="folder" />
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">新文件夹名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="resource.resName"
										placeholder="新文件夹名">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="newFolderSave" class="btn btn-primary">保存</button>
					<button type="button" id="newFolderCancel" class="btn btn-default"
						data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="uploadFilesModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">上传文件</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="uploadFilesForm" method="post"
						enctype="multipart/form-data"
						action="../resource/resource!saveFiles.action">
						<input type="hidden" name="resource.resTeaId"
							value="${teacher.teaId }" /> <input type="hidden"
							name="resource.resState" value="1" /> <input type="hidden"
							name="resource.resParId" id="resourceParId2" />
						<div class="box-body">
							<div class="form-group">
								<label class="col-sm-2">选择文件</label>
								<div class="col-sm-10">
									<input type="file" name="fff" multiple />
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" id="uploadFilesSave" class="btn btn-primary">保存</button>
					<button type="button" id="uploadFilesCancel"
						class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="movePathModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">移动文件</h4>
				</div>
				<input type="hidden" id="resourceIdMove" />
				<div class="modal-body">
					<div class="box-body">
						<div class="form-group">
							<label class="col-sm-2">选择路径</label>
							<div class="col-sm-10">
								<div id="movePathTree"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="movePathSave" class="btn btn-primary">保存</button>
					<button type="button" id="movePathCancel" class="btn btn-default"
						data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="videoDiv">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<video id="video" width="100%" controls="controls">您的浏览器不支持此种视频格式。</video> 
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="audioDiv">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<audio id="audio" src="" width="100%">您的浏览器不支持此种音频格式。</audio>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="imageDiv">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<img id="image" src="" width="100%">
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal" id="textDiv">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<p id="text"></p>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var resParId = 0 ;
	var res;
	$(function(){
		initTable();
		$('#resourceParId').val(resParId);
		$("#previewDiv").hide();
		$("#previewOperateDiv").hide();
	})
	function initTable() {  
	    //先销毁表格  
	    $("#resourceTable").bootstrapTable('destroy');  
	    //初始化表格,动态从服务器加载数据  
	    $("#resourceTable").bootstrapTable({
	    	toolbar:"#toolbar",
	        method: "get",  //使用get请求到服务器获取数据  
	        url:"../resource/resource!page.action", 
	        //url:"/STRMS/test.json",
	        striped: true,  //表格显示条纹  
	        //pagination: true, //启动分页 	 
	        //pageSize: 10,  //每页显示的记录数  
	        ///pageNumber:1, //当前第几页  
	        search: true,  //是否启用查询  
	        //showColumns: true,  //显示下拉框勾选要显示的列  
	        //showRefresh: true,  //显示刷新按钮  
	        sidePagination: "server", //表示服务端请求  
	        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
	        //设置为limit可以获取limit, offset, search, sort, order  
	        queryParamsType : "undefined",   
	        queryParams: function queryParams(params) {   //设置查询参数
	            var param = {
 	                'resource.resName':params.searchText,
	                'resource.resParId':resParId,
	                'resource.resTeaId':'${teacher.teaId }'
	            };
	        	return param;
	        },  
	        columns: [{  
	            field: 'resName',
	            title: '文件名'
	        },{  
	            field: 'resSize',
	            title: '文件大小',
	            formatter: sizeFormatter
	        },{ 
	            field: 'resUploadTime',
	            title: '上传时间'
	        },{ 
	            field: 'resState',
	            title: '文件状态',
	            formatter: stateFormatter
	        },{
	            field: 'resId',
	            title: '操作',
	            align: 'left',
	            events: operateEvents,
	            formatter: operateFormatter
	        }],  
	        onLoadSuccess: function(){  //加载成功时执行
	        },  
	        onLoadError: function(){  //加载失败时执行 
	        },
	        //onClickRow:function(){}
	        onDblClickCell:function(field, value, row){
				addPath(row.resId,row.resName);
		        if(row.resType=="folder"){
					resParId  = row.resId;
					$("#resourceTable").bootstrapTable('refresh');
				}else{
					res = row;
					$("#tableDiv").hide();
					$("#previewDiv").show();
					$("#previewOperateDiv").show();
					loadComment();
				}
	        }
		});
	}
	
	function operateFormatter(value, row, index) {
	   var s="";
	  		s = s + '<a class="rename">重命名</a>';
	   if(row.resType!="folder"){
	   		s = s + ' <a class="move">移动 </a>';
	   }
	   s = s + ' <a class="remove">删除</a>';
	   if(row.resType!="folder"){
		   s = s + ' <a class="download">下载</a>';
		   if(row.resState=="10"){
			   s = s + ' <a class="canel-download">取消开放下载</a>';
		   }else if(row.resState =="1" || row.resState =="3"){
			   s = s + ' <a class="apply">开放下载</a>';
		   }else if(row.resState =="2"){
			   s = s + ' <a class="canel-apply">取消申请</a>';
		   }
	   }
	   return [s].join('');
	}
	function sizeFormatter(value, row, index) {
		if(row.resType!="folder"){
			var danwei ="B";
			if(value > 1024){
				value = value/1024;
				danwei ="KB";
			}
			if(value > 1024){
				value = value/1024;
				danwei ="MB";
			}
			if(value > 1024){
				value = value/1024;
				danwei ="GB";
			}
		   return [parseFloat(value).toFixed(2)+danwei].join('');
		}
		
	}
	function stateFormatter(value, row, index) {
		var level="";
		if(value =="10"){
			level = "开放下载";
		}else if(value =="1" || value =="3"){
			level = "";
		}else if(value =="2"){
			level = "审核中";
		//}else if(value =="3"){
		//	level = "审核不通过";
		}
		return [level].join('');
	}
	window.operateEvents = {
	   'click .rename': function (e, value, row, index) {
		   renameResource(row.resId,row.resName);
	   },
	   'click .move': function (e, value, row, index) {
		   moveResource(row.resId);
	   },
	   'click .remove': function (e, value, row, index) {
		   updateResource(row.resId,0);
	   },
	   'click .canel-download': function (e, value, row, index) {
		   updateResource(row.resId,1);
	   },
	   'click .apply': function (e, value, row, index) {
		   updateResource(row.resId,2);
	   },
	   'click .canel-apply': function (e, value, row, index) {
		   updateResource(row.resId,1);
	   },
	   'click .download': function (e, value, row, index) {
		   downloadResource(row.resId);
	   }
	};
	
	function renameResource(resId,resName){
		if (resId != null || resId != '') {
			$("#resourceTitle").html("重命名");
			$("#resourceId").val(resId);
			$("#resourceName").val(resName);
		}
		$('#resourceModal').modal('show');
	}
	
	$("#resourceSave").click(function() {
		$("#resourceForm").ajaxSubmit(function(data) {
			if(testData(data)) {
				bootbox.alert("操作成功");
				($("#resourceForm"))[0].reset();
		        $("#resourceTable").bootstrapTable('refresh');
			}
		})
		$('#resourceModal').modal('hide');
	});

	$("#resourceCancel").click(function() {
		($("#resourceForm"))[0].reset();
	});
	
	function moveResource(resId){
		$('#resourceIdMove').val(resId);
		$('#movePathModal').modal('show');
		$.ajax({
			url : '../resource/resource!folderTree.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			dataType : 'json',
			data:{
				'resource.resTeaId':'${teacher.teaId }'
			},
			success : function(data, textStatus, jqXHR) {
				var content =  data.content;
				var $tree = $('#movePathTree').treeview({
					data : content,
					backColor: 'green'
				})
			}
		})
	}

	function downloadResource(resId){
		window.location.href='../resource/resource!downloadFile.action?resource.resId='+resId;
	}

	$("#movePathSave").click(function() {
		var arr = $('#movePathTree').treeview('getSelected');
		var resId = $('#resourceIdMove').val();
		if(arr.length==0){
			bootbox.alert("请选择一个文件夹");
		}
		$.ajax({
			url : '../resource/resource!update.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			data : {
				'resource.resId' : resId,
				'resource.resParId' : arr[0].id
			},	
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.result == "success") {
					bootbox.alert("操作成功");
			        $("#resourceTable").bootstrapTable('refresh');
				} else {
					bootbox.alert("操作失败");
				}
			}
		})
		$('#movePathModal').modal('hide');
	});
	function updateResource(resId,state){
		$.ajax({
			url : '../resource/resource!update.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			data : {
				'resource.resId' : resId,
				'resource.resState' : state
			},	
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.result == "success") {
					bootbox.alert("操作成功");
			        $("#resourceTable").bootstrapTable('refresh');
				} else {
					bootbox.alert("操作失败");
				}
			}
		})
	}
	
	$('#newFolder').click(function(){
		$('#resourceParId').val(resParId);
		$('#newFolderModal').modal('show');
	})
	
	$('#uploadFiles').click(function(){
		$('#resourceParId2').val(resParId);
		$('#uploadFilesModal').modal('show');
	})
	
	$("#uploadFilesSave").click(function() {
		$("#uploadFilesForm").ajaxSubmit(function(data) {
			if(testData(data)) {
				bootbox.alert("操作成功");
				($("#uploadFilesForm"))[0].reset();
		        $("#resourceTable").bootstrapTable('refresh');
			}
		})
		$('#uploadFilesModal').modal('hide');
	});

	$("#uploadFilesCancel").click(function() {
		($("#uploadFilesForm"))[0].reset();
	});
	

	$("#newFolderSave").click(function() {
		$("#newFolderForm").ajaxSubmit(function(data) {
			if(testData(data)) {
				bootbox.alert("操作成功");
				($("#newFolderForm"))[0].reset();
		        $("#resourceTable").bootstrapTable('refresh');
			}
		})
		$('#newFolderModal').modal('hide');
	});

	$("#newFolderCancel").click(function() {
		($("#newFolderForm"))[0].reset();
	});
	
	function addPath(id,name){
        //<li id="0"><a href="#" onclick="reloadByPath(this)"><i class="fa fa-home"></i> Home</a></li>
        var oldPath = $("#path li:last-child").html();
        var newPath = '<a href="#" onclick="reloadByPath(this)">' + oldPath + '</a>';
        $("#path li:last-child").html(newPath);
        $("#path").append('<li id="'+id+'">'+name+'</li>');
	}

	function reloadByPath(obj){
		$("#tableDiv").show();
		$("#previewDiv").hide();
		$("#previewOperateDiv").hide();
	    var arr = $("#path li");
	    var state = false;
	    resParId = obj.parentNode.id;
	    for(var i=0;i<arr.length;i++){
		    if(state==true){
		    	arr[i].remove();
			}
		    if(arr[i].id == resParId){
			    state = true;
			}
		}
		//去掉a标签
        $("#path li:last-child").html($("#path li:last-child a").html());
	    $("#resourceTable").bootstrapTable('refresh');
	}

	var videoType = new Array("OGG","MP4","BMW","MP3");
	var imageType = new Array("JPG","JPEG","PNG");
	var textType = new Array("TXT","JAVA","CSS","JS","JSON");
	
	$('#previewBtn').click(function(){
		var url = '../resource/resource!downloadFile.action?resource.resId='+res.resId;
		///resource/resource!d"ownload.action?resource.resId=62
		for(var i=0;i<videoType.length;i++){
			if(res.resType == videoType[i]){
				$('#videoDiv').modal('show');
				$('#video').attr('src',url);
				return;
			}
		}
		for(var i=0;i<imageType.length;i++){
			if(res.resType == imageType[i]){
				$('#imageDiv').modal('show');
				$('#image').attr('src',url);
				return;
			}
		}
		for(var i=0;i<textType.length;i++){
			if(res.resType == textType[i]){
				$('#textDiv').modal('show');
				loadText(url);
				return;
			}
		}
		bootbox.alert("此类文件不支持预览！");
	});
	
	$('#downloadBtn').click(function(){
		window.location.href='../resource/resource!downloadFile.action?resource.resId='+res.resId;
	});
	
	function loadText(url){
	    $('#text').text('');           
		$.ajax({  
	        type: "get",  
	        url: url,  
	        dataType: "text",  
	        timeout: 100000, //超时时间：100秒  
	        success: function(data){//1m的文本                   
	                    if(data.length>1024000)  
	                        {  
	                            var originLength=data.length;  
	                            data=data.substring(0,1024000);                               
	                            replaceAndAppendData(data,$("#textareaShowTextOfProduct"),10240,function(){  
	                                $("#textareaShowTextOfProduct").text("<br/>...............省略"+(originLength-data.length)+"个长度的内容<br/>");  
	                            });  
	  
	                        }else{  
	                            replaceAndAppendData(data,$("#textareaShowTextOfProduct"),10240);  
	                        }  
	                 },  
	        error:function(XMLHttpRequest, textStatus, errorThrown){  
	         }  
	    });  
	}
	var replaceAndAppendDataRe;  
    /** 
    *替换相关符号，并将之追加在dom节点上 
    *count表示将数据分为多少份 
    *countLength,每一次处理的数据量 
    *callBack执行完毕之后的回调函数 
    **/  
    function replaceAndAppendData(data,obj,countLength,callBack){  
        if(countLength<0) countLength=10240;  
        if(countLength>51200) countLength=51200;  
        //var countLength=data.length/count;//每一份的长度  
        var startStr=0;//当前开始的字符串  
        replaceAndAppendDataRe=function(){
			if(startStr<data.length){  
			    var tmpData=data.substring(startStr,startStr+countLength);  
			    var startT=new Date().getTime(); 
			    tmpData=tmpData.replace(/\n/g,"<br>");  
                tmpData=tmpData.replace(/[ ]/g," ");
			    var endT=new Date().getTime();  
			    var spaceT=endT-startT;  
			    if(spaceT<15) spaceT=15;  
			    if(spaceT>250){  
			        countLength=String.parseInt(countLength*0.75+"");  
			    }  
			    $('#text').append(tmpData);               
			    setTimeout("replaceAndAppendDataRe()",spaceT);  
            }else{  
                if(typeof callBack!='undefined'&&callBack!=null){  
                    callBack();  
                }  
            }     
                startStr=startStr+countLength;  
        };  
        replaceAndAppendDataRe();  
    }  
    /* 
    <div class="item">
	<img src="../dist/img/user2-160x160.jpg" alt="user image"
		class="offline">
	<p class="message">
		<a href="#" class="name"> <small
			class="text-muted pull-right"><i class="fa fa-clock-o"></i>
				5:30</small> Susan Doe
		</a> I would like to meet you to discuss the latest news about the
		arrival of the new theme. They say it is going to be one the
		best themes on the market
	</p> */
	

	$('#comSave').click(function(){
		var content = $('#comContent').val();
		if(content==""){
			bootbox.alert("请输入内容!");
		}
		$.ajax({
			url : '../resource/comment!save.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			data : {
				'comment.comResId' : res.resId,
				'comment.comContent' : content,
			},	
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.result == "success") {
					bootbox.alert("评论成功");
					loadComment();
				} else {
					bootbox.alert("操作失败");
				}
			}
		})		
	});
	
	function loadComment(){
		$.ajax({
			url : '../resource/comment!getListByComResId.action',
			type : 'POST', //GET
			async : true, //或false,是否异步
			data : {
				'comment.comResId' : res.resId
			},	
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.result == "success") {
					$('#commentDiv').html('');
					var content = data.content;
					console.log(content)
					for(var i=0;i<content.length;i++){
						var comment = content[i];
						var str = '';
						str += '<div class="item">';
						str += '<img src="../dist/img/user2-160x160.jpg" alt="user image" class="offline">';
						str += '<p class="message">';
						str += '<span class="name">';
						str += '<small class="text-muted pull-right"><i class="fa fa-clock-o"></i>'+content[i].comDate+'</small>';
						str += content[i].comRoleName + '</span>';
						str += content[i].comContent+ '</p></div>';
						$('#commentDiv').append(str);
					}
				}
			}
		})
	}

    /* 
    <div class="item">
	<img src="../dist/img/user2-160x160.jpg" alt="user image"
		class="offline">
	<p class="message">
		<a href="#" class="name"> <small
			class="text-muted pull-right"><i class="fa fa-clock-o"></i>
				5:30</small> Susan Doe
		</a> I would like to meet you to discuss the latest news about the
		arrival of the new theme. They say it is going to be one the
		best themes on the market
	</p> */
</script>
</html>
