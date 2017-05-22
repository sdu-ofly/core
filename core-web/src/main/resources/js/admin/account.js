/**
 * 
 */
var account = (function() {
	var _this = this;
	var _addDialogWidth = 500;
	var _addDialogHeight = 150;
	var _curAccount = null;
	var ctx = $('#ctx').val(); 
	/*=============================================================*/
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月22日 下午1:08:46
	 * @Introduction	: 查询帐号列表
	 */
	var queryAccountList = function() {
		$('#accountForm').find('#accountGrid').datagrid("load", {
			condition	: $('#accountForm').find('#accountCondition').textbox("getValue")
		});
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 下午2:34:17
	 * @Introduction	: 新增帐号
	 */
	var addAccount = function() {
		var url = ctx + '/admin/account/addAccount';
		var params = {};
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveAccount
		}];
		OFLY.dialog("addAccountDialog", url, params, "新增", _addDialogWidth, _addDialogHeight, buttons);
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 下午11:39:44
	 * @Introduction	: 修改帐号信息
	 */
	var editAccount = function() {
		if(!_editAccountValid()) {
			return;
		}
		var url = ctx + '/admin/account/editAccount'
		var row = $('#accountForm').find('#accountGrid').datagrid('getSelected');
		var params = {
			id	: row.id	
		};
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveAccount
		}];
		OFLY.dialog("addAccountDialog", url, params, "修改", _addDialogWidth, _addDialogHeight, buttons);
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月22日 下午1:09:19
	 * @Introduction	: 删除帐号
	 */
	var deleteAccount = function() {
		if(!_deleteAccountValid()) {
			return;
		}
		var url = ctx + '/admin/account/deleteAccount';
		var row = $('#accountForm').find('#accountGrid').datagrid('getSelected');
		var params = {
			id	: row.id
		}
		OFLY.confirm("提示", "确认删除?", function() {
			$.post(url, params, function(data){
				OFLY.message(data.msg, function() {
					if(data.code == 1) {
						$('#accountForm').find('#accountGrid').datagrid("reload");
					}
				});
			});
		});
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月22日 下午1:09:36
	 * @Introduction	: 帐号列表单击事件
	 */
	var accountClick = function(index, row) {
		_curAccount = row;
		queryRelaRole();// 
		queryUnUseRoleList();
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月22日 下午1:10:08
	 * @Introduction	: 查询关联角色
	 */
	var queryRelaRole = function() {
		if(!_queryRelaRoleValid()) {
			return;
		}
		var url = ctx + '/admin/account/queryRelaRoleList';
		var params = {
			accountId : _curAccount.id
		};
		var condition = $('#accountForm').find('#accountRelaCondition').textbox('getValue');
		if(condition!=null && condition!="") {
			params['condition'] = condition
		}
		$('#accountForm').find('#relaRoleGrid').datagrid({
			url			: url,
			queryParams	: params
		});
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月22日 下午1:10:46
	 * @Introduction	: 查询未管理角色
	 */
	var queryUnUseRoleList = function() {
		if(!_queryRelaRoleValid()) {
			return;
		}
		var url = ctx + '/admin/account/queryUnUseRoleList';
		var params = {
			accountId : _curAccount.id
		};
		var condition = $('#accountForm').find('#accountUnRelaCondition').textbox('getValue');
		if(condition!=null && condition!="") {
			params['condition'] = condition
		}
		$('#accountForm').find('#unUseRoleGrid').datagrid({
			url			: url,
			queryParams	: params
		});
	}
	/*=============================================================*/
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 下午3:03:07
	 * @Introduction	: 保存帐号
	 */
	var _saveAccount = function() {
		if(!_saveAccountValid()) {
			return;
		}
		var url  = ctx + '/admin/account/saveAccount';
		var params = {
			account	: $('#addAccountForm').find('#account').textbox('getValue'),
			name	: $('#addAccountForm').find('#name').textbox('getValue'),
			password: $('#addAccountForm').find('#password').passwordbox('getValue')
		};
		var id = $('#addAccountForm').find('#id').val();
		if(id!=null && id!='') {
			params['id'] = id;
		}
		OFLY.confirm("提示", "确认保存?", function() {
			$.post(url, params, function(data) {
				OFLY.message(data.msg,function() {
					if(data.code == 1) {
						$('#accountForm').find('#accountGrid').datagrid("reload");
						OFLY.dialog.close("addAccountDialog");
					}
				});
			});
		});
		
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 下午11:39:19
	 * @Introduction	: 保存帐号验证
	 */
	var _saveAccountValid = function() {
		if(!$('#addAccountForm').form('validate')) {
			return false;
		}
		var passwordCheck = $('#addAccountForm').find('#passwordCheck').passwordbox('getValue');
		var password = $('#addAccountForm').find('#password').passwordbox('getValue');
		if((passwordCheck!=""||password!="")&&passwordCheck!=password) {
			OFLY.message("两次密码不一致");
			return false;
		}
		return true;
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 下午11:45:36
	 * @Introduction	: 选择帐号验证
	 */
	var _selectedAccountValid = function() {
		var row = $('#accountForm').find('#accountGrid').datagrid('getSelected');
		if(row==null) {
			OFLY.message('请先选择帐号');
			return false
		}
		return true;
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 下午11:38:50
	 * @Introduction	: 修改帐号验证
	 */
	var _editAccountValid = function() {
		return _selectedAccountValid();
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月21日 下午11:45:08
	 * @Introduction	: 删除帐号验证
	 */
	var _deleteAccountValid = function(){
		return _selectedAccountValid();
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月22日 上午12:36:28
	 * @Introduction	: 关联角色验证
	 */
	var _queryRelaRoleValid = function() {
		return _selectedAccountValid();
	}
	/*=============================================================*/
	/*=============================================================*/
	/*=============================================================*/
	
	_this.queryAccountList = queryAccountList;		// 查询帐号列表
	_this.addAccount = addAccount;					// 新增帐号
	_this.editAccount = editAccount;				// 修改帐号
	_this.deleteAccount = deleteAccount;			// 删除帐号
	_this.accountClick = accountClick;				// 单击帐号
	_this.queryRelaRole = queryRelaRole;			// 查询关联角色
	_this.queryUnUseRoleList = queryUnUseRoleList;	// 查询未关联角色
	return _this;
})();