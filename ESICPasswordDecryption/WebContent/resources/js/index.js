function fnKeyDown(event) {
	if (event.keyCode == '13') {
		fnSubmit();
	}
}

function fnSubmit() {
	var obj = document.frm;
	var username = obj.username.value;
	var flag = false;

	if (username == "" || username == null) {
		flag = false;
		alert("Please enter username");
		return;
	} else {
		flag = true;
	}

	if (flag) {
		var contextPath = getContextPath();
		var url = contextPath + "/fetch_userdetails.html";
		var params = {
			username : username
		};

		$.post(url, params, function(data, status) {
			if (status == 'success') {
				document.getElementById("result").innerHTML = data;
			}
		});

	}
}
