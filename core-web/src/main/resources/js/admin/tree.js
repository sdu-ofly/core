/**
 * @Author			: Logan
 * @Create Time		: 2017年5月11日 上午10:00:56
 * @Introduction	: 树结构数据
 */
var tree = (function() {
	var _this = this;
	var ctx = $('#ctx').val();
	var _curResourcesNode = null;
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月11日 上午10:50:12
	 * @Introduction	: 初始化admin导航树
	 */
	var initAdminNavigation = function(id,options) {
		var setting = {
				callback: {
					onDblClick	: options.onDblClick
				},
				view	: {
					showLine	: false
				}
			};
			var zNodes =[
				{ id:'ztree_resources',name:'资源'},
				{ id:'ztree_roles',name:'角色'},
				{ id:'ztree_account',name:'帐号'}
			]
			$.fn.zTree.init($('#'+id), setting, zNodes);
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月11日 下午1:11:46
	 * @Introduction	: 初始化资源树
	 */
	var initResourcesTree = function(id,options) {
		//初始化右键菜单
		_initRightMenus();
		var setting = {
				async	: {
					enable		: true,
					autoParam	: ['id'],
					url			: ctx+'/tree/queryResources'
				},
				callback: {
					onDblClick	: options.resourcesDblClick,
					onRightClick: _resourcesRightMenus
				},
				view	: {
					showLine	: false
				}
			};
			$.fn.zTree.init($('#'+id), setting);
	}
	var _refreshNode = function(id) {
		var zTree = $.fn.zTree.getZTreeObj(id);
		var parentNode = zTree.getNodeByTId(_curResourcesNode.parentTId);  
		zTree.reAsyncChildNodes(parentNode, 'refresh');
	}
	
	var _initRightMenus = function() {
		$('#mm').menu('appendItem', {
			text: '新增',
			iconCls: 'icon-add',
			onclick: _addResourcesNode
		});
		$('#mm').menu('appendItem', {
			text: '修改',
			iconCls: 'icon-edit',
			onclick: _editResourcesNode
		});
		$('#mm').menu('appendItem', {
			text: '删除',
			iconCls: 'icon-cancel',
			onclick: _deleteResourcesNode
		});
	}
	var _resourcesRightMenus = function(event, treeId, treeNode) {
		_curResourcesNode = treeNode;
		$('#mm').menu('show', {    
			left: event.pageX,
			top: event.pageY    
		});
	}
	var _addResourcesNode = function(event) {
		var url = ctx+"/tree/addResourcesNode";
		var params = {
			parentId	: _curResourcesNode.id,
			parentName	: _curResourcesNode.name
		}
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveResourcesNode
		}]
		OFLY.dialog("addResourcesNode", url, params, "新增", 450, 120, buttons);
	}
	
	var _editResourcesNode = function() {
		var url = ctx+"/tree/editResourcesNode";
		var params = {
			id	: _curResourcesNode.id	
		}
		var buttons = [{
			text	: '保存',
			iconCls	: 'icon-save',
			plain	: 'true',
			handler	: _saveResourcesNode
		}]
		OFLY.dialog("addResourcesNode", url, params, "修改", 450, 120, buttons);
	}
	
	
	var _saveResourcesNode = function() {
		if(!_saveResourcesNodeValid()) {
			return;
		}
		var url = ctx + '/tree/saveResourcesNode';
		if($('#resourceNodeForm').find("#id").val()) {// 修改
			var params = {
				id			: $('#resourceNodeForm').find("#id").val(),
				name		: $('#resourceNodeForm').find('#name').textbox('getValue')
			};
		} else {//新增
			var params = {
				parentId	: $('#resourceNodeForm').find("#parentId").val(),
				name		: $('#resourceNodeForm').find('#name').textbox('getValue')
			};
		}
		
		OFLY.confirm("确认框","确认保存?",function() {
			$.post(url, params, function(data) {
				OFLY.message(data.msg, function() {
					if(data.code==1) {
						_refreshNode("tree");
						OFLY.dialog.close("addResourcesNode");
					}
				});
				
			})
		});
	}
	
	var _deleteResourcesNode = function() {
		_hasResourcesChildNode(function() {
			var url = ctx +'/tree/deleteResourcesNode';
			var params = {
				id	: 	_curResourcesNode.id
			};
			OFLY.confirm("确认框","确认删除?", function() {
				$.post(url, params, function(data){
					OFLY.message(data.msg, function() {
						if(data.code == 1) {
							_refreshNode("tree");
						}
					});
				});
			});
		});
	}
	var _hasResourcesChildNode = function(fn) {
		var url = ctx + '/tree/hasResourcesChildNode';
		var params = {
			id	: 	_curResourcesNode.id
		};
		$.post(url, params, function(flag) {
			if(flag) {//存在下级数据
				OFLY.message('有下级数据,不能删除');
			} else {
				fn.call(this);
			}
		});
		
	}
	
	var _saveResourcesNodeValid = function() {
		if(!$("#resourceNodeForm").form('validate')) {
			return false;
		}
		return true;
	}
	
	_this.initAdminNavigation = initAdminNavigation;
	_this.initResourcesTree = initResourcesTree;
	return _this;
})();