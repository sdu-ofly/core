var resources = (function(){
	var _this = this;
	var _width = 500;
	var _height = 200;
	var ctx = $('#ctx').val();
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月11日 下午10:01:55
	 * @Introduction	: 资源节点双击
	 */
	var resourcesDblClick = function(event, treeId, treeNode) {
		if(treeNode) {
			//修改datagrid查询的默认值
			var url =$('#resourcesForm').find('#datagrid').datagrid("options").url;
			$('#resourcesForm').find('#parentId').val(treeNode.id);
			$('#resourcesForm').find('#parentName').val(treeNode.name);
			if(!url){
				$('#resourcesForm').find('#datagrid').datagrid({
					url			: ctx +'/admin/resources/queryList',
					queryParams	: {
						parentId	: treeNode.id,
						condition	: $('#resourcesForm').find('#condition').val()
					}
				});
			} else {
				$('#resourcesForm').find('#datagrid').datagrid('load', {
					parentId	: $('#resourcesForm').find('#parentId').val(),
					condition	: $('#resourcesForm').find('#condition').val()
						
				});
			}
		}
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月12日 上午12:16:18
	 * @Introduction	: 查询功能资源列表
	 */
	var queryList = function() {
		$('#resourcesForm').find('#datagrid').datagrid('load', {
			parentId	: $('#resourcesForm').find('#parentId').val(),
			condition	: $('#resourcesForm').find('#condition').val()
		});
	}
	var isMenuFormat = function(value, row, index) {
		if(value == 1){
			return '<span style="color: hsl(187, 100%, 42%);font-size: 18px;">√</span>';
		} else {
			return "";
		}
	}
	var addResource = function() {
		if(!$('#resourcesForm').find('#parentId').val()) {
			OFLY.message("请先选择要保存的资源文件夹");
			return;
		}
		
		var url= ctx + '/admin/resources/addResource';
		var params = {
			parentId	: $('#resourcesForm').find('#parentId').val(),
			parentName	: $('#resourcesForm').find('#parentName').val()
		};
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveResource
		}];
		OFLY.dialog("addResourceDialog", url, params, "新增", _width, _height, buttons);
	}
	var editResource = function() {
		if(!_editResourceValid()) {
			return;
		}
		var row = $('#resourcesForm').find('#datagrid').datagrid("getSelected");
		var url = ctx + '/admin/resources/editResource';
		var params = {
			id			: row.id,
			parentId	: $('#resourcesForm').find('#parentId').val(),
			parentName	: $('#resourcesForm').find('#parentName').val()
		};
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveResource
		}];
		OFLY.dialog("addResourceDialog", url, params, "新增", _width, _height, buttons);
	}
	var _editResourceValid = function() {
		var  selectedRows = $('#resourcesForm').find('#datagrid').datagrid("getSelections");
		if(selectedRows==null || selectedRows.length==0) {
			OFLY.message("请选择一条数据");
			return false;
		} else 
		if(selectedRows.length>1){
			OFLY.message("只能选择提条数据");
			return false;
		}
		return true;
	}
	var deleteResource = function() {
		if(!_deleteResourceValid()) {
			return;
		}
		var ids = new Array();
		var rows = $('#resourcesForm').find('#datagrid').datagrid('getSelections');
		$.each(rows,function(index, item) {
			ids.push(item.id);
		});
		var url = ctx + '/admin/resources/deleteResource';
		var params = {
			ids	: JSON.stringify(ids)
		};
		var msg = '';
		if(rows.length ==1) {
			
	    
			msg = '确认删除[<span style="font-size: 16px;color: hsl(0, 100%, 50%);">'+rows[0].name+'</span>]资源?';
		} else {
			msg = '确认删除[<span style="font-size: 16px;color: hsl(0, 100%, 50%);">'+rows.length+'</span>]条资源';
		}
		OFLY.confirm('提示框', msg, function() {
			$.post(url, params, function(data) {
				OFLY.message(data.msg, function() {
					if(data.code == 1) {
						$('#resourcesForm').find('#datagrid').datagrid("reload");
					}
				});
			})
		});
		
	}
	
	var _deleteResourceValid = function() {
		var rows = $('#resourcesForm').find('#datagrid').datagrid('getSelections');
		if(rows == null || rows.length==0) {
			OFLY.message('请选择一些准备删除的数据');
			return false;
		}
		return true;
	}
	
	var _saveResource = function() {
		if(!_saveResourceValid()) {
			return;
		}
		var url = ctx + '/admin/resources/saveResource';
		var params = {
			id			: $('#addResourceForm').find('#id').val(),
			parentId	: $('#addResourceForm').find('#parentId').val(),
			name		: $('#addResourceForm').find('#name').textbox("getValue"),
			url			: $('#addResourceForm').find('#url').textbox("getValue"),
			isMenu		: $('#addResourceForm').find('input[name="isMenu"]:checked').val(),
			auth		: $('#addResourceForm').find('input[name="auth"]:checked').val()
		}
		OFLY.confirm('提示框', '确认保存?', function() {
			$.post(url, params, function(data){
				OFLY.message(data.msg, function() {
					if(data.code == 1) {
						$('#resourcesForm').find('#datagrid').datagrid("reload");
						OFLY.dialog.close("addResourceDialog");
					}
				});
			});
		});
	}
	var _saveResourceValid = function() {
		if(!$('#addResourceForm').form('validate')) {
			return false;
		}
		return true;
	}
	
	_this.resourcesDblClick = resourcesDblClick;	// 双击资源
	_this.queryList = queryList;					// 查询
	_this.isMenuFormat = isMenuFormat;				// 格式化是否菜单项
	_this.addResource = addResource;				// 新增
	_this.editResource = editResource;				// 修改
	_this.deleteResource = deleteResource;			// 删除
	return _this;
})();