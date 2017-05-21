/**
 * 角色管理
 */
var roles = (function() {
	var _this=this;
	var ctx = $('#ctx').val();
	var _curRole = null;
	var _curTreeNode = null;
	/*==================================================================*/
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:07:16
	 * @Introduction	: 双击资源树，查询未使用的资源信息
	 */
	var resourcesDblClick = function(event, treeId, treeNode) {
		_curTreeNode = treeNode;
		_queryUnUseResourceList();	// 查询未使用的资源信息
		
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:07:54
	 * @Introduction	: 查询角色列表
	 */
	var queryRoles = function() {
		$('#roleGrid').datagrid("load", {
			condition	: $('#roleCondition').textbox("getValue")
		});
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月20日 上午8:41:57
	 * @Introduction	: 新增角色
	 */
	var addRole = function() {
		var url = ctx +'/admin/roles/addRole';
		var params  = {};
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveRole
		}];
		OFLY.dialog("addRoleDialog", url, params, "新增", 500, 120, buttons);
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月20日 下午6:24:30
	 * @Introduction	: 修改角色
	 */
	var editRole = function() {
		if(!_editRoleValid()) {
			return;
		}
		var url = ctx +'/admin/roles/editRole';
		var params = {
			id	: $('#roleForm').find('#roleGrid').datagrid("getSelected").id
		}
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveRole
		}];
		OFLY.dialog("addRoleDialog", url, params, "修改", 500, 120, buttons);
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月20日 下午6:24:50
	 * @Introduction	: 删除角色
	 */
	var deleteRole = function() {
		if(!_deleteRoleValid()) {
			return;
		}
		var url = ctx + '/admin/roles/deleteRole'
		var params = {
			id	: $('#roleForm').find('#roleGrid').datagrid("getSelected").id
		} 
		OFLY.confirm("提示", "确认删除该数据?", function() {
			$.post(url, params, function(data) {
				OFLY.message(data.msg, function() {
					if(data.code == 1) {
						$('#roleForm').find('#roleGrid').datagrid("reload");
					}
				});
			})
		});
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:05:27
	 * @Introduction	: 单击角色,查询管理的资源和未使用的资源
	 */
	var roleClick = function(index, row) {
		_curRole = row;
		queryRelaResouece();// 
		_queryUnUseResourceList();
		
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月20日 下午11:00:42
	 * @Introduction	: 查询角色管理的资源信息
	 */
	var queryRelaResouece = function() {
		if(!_queryRelaResoueceValid()) {
			return;
		}
		var url = ctx + '/admin/roles/queryResoueceWithRole';
		var params = {
			roleId : _curRole.id
		};
		var condition = $('#roleRelaCondition').textbox('getValue');
		if(condition!=null && condition!="") {
			params['condition'] = condition
		}
		$('#relaResourceGrid').datagrid({
			url			: url,
			queryParams	: params
		});
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:08:21
	 * @Introduction	: 删除关联资源
	 */
	var deleteRelaResource = function(){
		if(!_deleteRelaResourceValid()) {
			return;
		}
		var url = ctx +'/admin/roles/deleteRelaResource';
		var rows = $('#relaResourceGrid').datagrid("getSelections");
		var ids = new Array();
		$.each(rows,function(index, item) {
			ids.push(item.roleResRelaId);
		});
		var params = {
			ids	: JSON.stringify(ids)
		};
		$.post(url, params, function(data){
			OFLY.message(data.msg,function() {
				if(data.code==1) {
					$('#roleForm').find('#relaResourceGrid').datagrid("reload");
					$('#roleForm').find('#unUseResourceGrid').datagrid("reload");
				}
			});
		});
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午11:40:49
	 * @Introduction	: 添加关联资源
	 */
	var addRelaResource = function() {
		if(!_addRelaResourceValid()) {
			return;
		}
		var url = ctx + '/admin/roles/addRelaResource';
		var arr = new Array();
		var roleId = _curRole.id;
		var rows = $('#roleForm').find('#unUseResourceGrid').datagrid("getSelections");
		$.each(rows, function(index, item) {
			arr.push({
				roleId		: roleId,
				resourceId	: item.id
			});
		});
		var params = {
			data	: JSON.stringify(arr)
		}
		$.post(url, params, function(data) {
			OFLY.message(data.msg, function() {
				if(data.code == 1) {
					$('#roleForm').find('#relaResourceGrid').datagrid("reload");
					$('#roleForm').find('#unUseResourceGrid').datagrid("reload");
				}
			});
		})
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月20日 下午11:33:13
	 * @Introduction	: 格式化是否
	 */
	var isFlagFormat = function(value, row, index) {
		if(value == 1){
			return '<span style="color: hsl(187, 100%, 42%);font-size: 18px;">√</span>';
		} else {
			return "";
		}
	}
	/*==================================================================*/
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:09:20
	 * @Introduction	: 保存角色
	 */
	var _saveRole = function() {
		if(!_saveRoleValid()) {
			return;
		}
		var url = ctx +'/admin/roles/saveRole';
		var params = {
			name	: $('#addRoleForm').find('#name').textbox("getValue"),
			value	: $('#addRoleForm').find('#value').textbox("getValue")
		};
		var id = $('#addRoleForm').find('#id').val();
		if(id!=null && id!="") {
			params['id'] = id
		}
		$.post(url, params, function(data) {
			OFLY.message(data.msg, function() {
				if(data.code == 1) {
					$('#roleForm').find('#roleGrid').datagrid("reload");
					OFLY.dialog.close("addRoleDialog");
				}
			});
		});
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:09:37
	 * @Introduction	: 保存角色验证
	 */
	var _saveRoleValid = function() {
		if(!$('#addRoleForm').form('validate')) {
			return false;
		}
		return true;
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:09:52
	 * @Introduction	: 验证是否选择了角色
	 */
	var _validSelectedRow = function() {
		if(_curRole==null) {
			OFLY.message("请先选择角色");
			return false;
		}
		return true;
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:10:35
	 * @Introduction	: 编辑角色验证
	 */
	var _editRoleValid = function() {
		return _validSelectedRow();
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:10:56
	 * @Introduction	: 删除角色验证
	 */
	var _deleteRoleValid = function() {
		return _validSelectedRow();
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:11:09
	 * @Introduction	: 查询关联资源验证
	 */
	var _queryRelaResoueceValid = function() {
		return _validSelectedRow;
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:12:51
	 * @Introduction	: 删除关联资源验证
	 */
	var _deleteRelaResourceValid = function() {
		var rows = $('#relaResourceGrid').datagrid("getSelections");
		if(rows==null || rows.length==0) {
			OFLY.message("请选择要删除的资源");
			return false;
		}
		return true;
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午11:45:08
	 * @Introduction	: 添加关联资源验证
	 */
	var _addRelaResourceValid = function() {
		var rows = $('#roleForm').find('#unUseResourceGrid').datagrid("getSelections");
		if(rows==null || rows.length==0) {
			OFLY.message("请选择要添加的资源");
			return false;
		}
		return true;
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 上午10:13:09
	 * @Introduction	: 查询还未使用的资源
	 */
	var _queryUnUseResourceList = function() {
		if(_curTreeNode==null){
			return;// 没有选择资源树分类
		}
		if(_curRole==null){
			return;// 没选角色不查询
		}
		var url = ctx + '/admin/roles/queryUnUseResourceList';
		var params = {
			roleId	: _curRole.id,
			parentId: _curTreeNode.id
		}
		$('#roleForm').find('#unUseResourceGrid').datagrid({
			url			: url,
			queryParams	: params
		});
	}
	
	/*======================抛出的方法===================================*/
	_this.resourcesDblClick = resourcesDblClick;	// 资源树双击事件
	_this.queryRoles = queryRoles;					// 查询
	_this.addRole = addRole;						// 新增
	_this.editRole = editRole;						// 修改
	_this.deleteRole = deleteRole;					// 删除
	_this.queryRelaResouece = queryRelaResouece;	// 管理资源查询
	_this.roleClick = roleClick;					// 角色管理单击事件
	_this.deleteRelaResource = deleteRelaResource;	// 删除管理资源
	_this.addRelaResource = addRelaResource;	// 删除管理资源
	_this.isFlagFormat = isFlagFormat;				// 【是|否】格式化
	/*==================================================================*/
	
	return _this;
})();