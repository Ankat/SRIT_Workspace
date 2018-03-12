function submit() {
	var obj = document.forms[0];
	var flag = false;
	var usrEmail = '';
	var usrPassword = '';

	if ((typeof (obj.usrEmail) != 'undefined') && (typeof (obj.usrPassword) != 'undefined')) {
		usrEmail = obj.usrEmail.value.trim();
		usrPassword = obj.usrPassword.value.trim();

		if (usrEmail == '' || usrEmail == null) {
			alert('Please enter email.');
			flag = false;
			return;
		} else {
			flag = true;
		}

		if (usrPassword == '' || usrPassword == null) {
			alert('Please enter password.');
			flag = false;
			return;
		} else {
			flag = true;
		}
	}

	if (flag == true) {
		obj.action = 'checkLogin.html';
		obj.method = 'POST';
		obj.submit();
	}
}

function signUp() {
	var obj = document.forms[0];
	obj.action = 'register_new_user_form.html';
	obj.method = 'post';
	obj.submit();

}

document.getElementById('email').onkeydown = function(e) {
	if (e.keyCode == 13) {
		fnSubmit();
	}
};