/**
 * @Author			: Logan
 * @Create Time		: 2017年5月10日 下午5:03:00
 * @Introduction	: 
 */
var admin = (function() {
	var _this = this;
	var TABS_ID = "admin_tabs";
	var ctx = $('#ctx').val();
	
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月10日 下午5:05:49
	 * @Introduction	: 初始化admin
	 */
	var init = function() {
		_initTree();
	};
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月10日 下午5:05:49
	 * @Introduction	: 初始化树
	 */
	var _initTree = function() {
		var setting = {
			callback: {
				onDblClick	: _treeDblClick,
				onRightClick: _treeRightClick
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
		$.fn.zTree.init($('#tree'), setting, zNodes);
	}
	/**
	 * @Author			: Logan
	 * @Create Time		: 2017年5月10日 下午9:36:16
	 * @Introduction	: 双击导航树
	 */
	var treeDblClick = function(event, treeId, treeNode) {
		var allTabs = $('#'+TABS_ID).tabs("tabs");
		var isTabExist = false;
		var curTabIndex = -1;//-1表示不存在
		$.each(allTabs,function(index, item) {
			if(treeNode.id == item.panel("options").id) {
				isTabExist = true;
				curTabIndex = index;
				return false;
			}
		})
		if(!isTabExist) {// 不存在
			var tabFunc = treeNode.id == 'ztree_resources'?'/admin/resources/init':
						  treeNode.id == 'ztree_roles'?'/admin/roles/init':
						  treeNode.id == 'ztree_account'?'/admin/account/init':'';
			
			$('#'+TABS_ID).tabs('add',{
				id		: treeNode.id,
				title	: treeNode.name,
				content	: '<iframe width="100%" height="100%" scrolling="no" frameborder="0" src="'+ctx+tabFunc+'"></iframe>',
				height	: $('#'+TABS_ID).parent().height(),
				closable: true
			});
		} else {		// 已存在，选中该tab
			$('#'+TABS_ID).tabs("select", curTabIndex);
		}
	}
	var refresh = function() {
		var url = ctx + '/admin/refresh';
		var params = {};
		$.post(url, params, function(data){
			OFLY.message(data.msg);
		});
	}
	
	_this.init = init;	// 初始化Admin
	_this.treeDblClick = treeDblClick;	// 初始化Admin
	_this.refresh = refresh;	// 刷新权限
	return _this;
})();


