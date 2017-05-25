/**
 * 
 */
var auth = (function() {
	var _this = this;
	var ctx = $('#ctx').val(); 
	/*===========================================================================*/
	var login = function() {
		if(!_loginValid()) {
			return;
		}
		var url = ctx + '/auth/login/login';
		var params = {
			account	: $('#loginForm').find('#account').textbox("getValue"),
			password: $('#loginForm').find('#account').passwordbox("getValue")
		};
		$.post(url, params, function(data) {
			if(data.code==1) {
				window.location.href = data.url;
			} else {
				$('#errorMsg').text(data.msg);
			}
		})
	};
	/*===========================================================================*/
	var _loginValid = function() {
		if(!$('#loginForm').form('validate')) {
			return false;
		}
		return true;
	}
	
	/*===========================================================================*/
	_this.login = login;
	return _this;
	
})();