function userAddForm() {
	var obj = document.forms[0];
	obj.action = 'user_add_form.html';
	obj.method = 'post';
	obj.submit();
}

function userModifyForm() {
	var obj = document.forms[0];
	var flag = false;
	var usrId = '';

	if (typeof (obj.usrId) != 'undefined') {
		if (typeof (obj.usrId.length) != 'undefined') {
			for (var i = 0; i < obj.usrId.length; i++) {
				if (obj.usrId[i].checked == true) {
					usrId = obj.usrId[i].value;
					flag = true;
				}
			}
		} else {
			if (obj.usrId.checked == true) {
				usrId = obj.usrId.value;
				flag = true;
			}
		}
	} else {
		alert("Please add record before modify.");
		return;
	}

	if (flag == true) {
		obj.hidUsrId.value = usrId;
		obj.action = 'user_modify_form.html';
		obj.method = 'post';
		obj.submit();
	} else {
		alert("Please select the record");
	}

}

function userDelete() {
	var obj = document.forms[0];
	var flag = false;
	var usrId = '';

	if (typeof (obj.usrId) != 'undefined') {
		if (typeof (obj.usrId.length) != 'undefined') {
			alert("True " + obj.usrId.length);
			for (var i = 0; i < obj.usrId.length; i++) {
				if (obj.usrId[i].checked == true) {
					usrId = obj.usrId[i].value;
					flag = true;
				}
			}
		} else {
			if (obj.usrId.checked == true) {
				usrId = obj.usrId.value;
				flag = true;
			}
		}
	} else {
		alert("Please add record before modify.");
		return;
	}

	if (flag == true) {
		obj.hidUsrId.value = usrId;
		obj.action = 'user_delete.html';
		obj.method = 'post';
		obj.submit();
	} else {
		alert("Please select the record");
	}
}
