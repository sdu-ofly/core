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
					url			: ctx +'/resources/queryList',
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
	
	var addResource = function() {
		var url= ctx + '/resources/addResource';
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
		var url = ctx + '/resources/editResource';
		var params = {
			id	: '1'
		};
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveResource
		}];
		OFLY.dialog("addResourceDialog", url, params, "新增", _width, _height, buttons);
	}
	var deleteResource = function() {
		debugger;
	}
	
	var _saveResource = function() {
		if(!_saveResourceValid()) {
			return;
		}
		var url = ctx + '/resources/saveResource';
		var params = {
			parentId	: $('#addResourceForm').find('#parentId').val(),
			name		: $('#addResourceForm').find('#name').textbox("getValue"),
			url			: $('#addResourceForm').find('#url').textbox("getValue"),
			isMenu		: $('#addResourceForm').find('input[name="isMenu"]:checked').val()
		}
		$.post(url, params, function(data){
			OFLY.message(data.msg, function() {
				if(data.code == 1) {
					$('#resourcesForm').find('#datagrid').datagrid("reload");
					OFLY.dialog.close("addResourceDialog");
				}
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
	_this.addResource = addResource;				// 新增
	_this.editResource = editResource;				// 修改
	_this.deleteResource = deleteResource;			// 删除
	return _this;
})();