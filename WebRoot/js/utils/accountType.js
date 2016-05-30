var mark1 = 0;
function CUrl() {
	document.getElementById("tt").innerHTML = "";
	document.getElementById("tname").innerHTML = "";
	document.getElementById("tt1").innerHTML = "";
    document.getElementById("tname1").innerHTML = "";
    document.getElementById("tt2").innerHTML = "";
    document.getElementById("tname2").innerHTML = "";
    document.getElementById("tt3").innerHTML = "";
    document.getElementById("tname3").innerHTML = "";
	var url = document.getElementsByName("accountType")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
	var atype = document.getElementById('atype').value;
	if (urlpoing == 2 || urlpoing == 3||urlpoing==4) {
		var html = "";

		$.post("typel", {
			Action : "post",
			id : "test001"
		}, function(data, textStatus) {
			if (data.length > 0) {
				if (urlpoing == 2) {
					html += '<select id="accountCatalog" name="accountCatalog" >';
					html += '<option value="" >请选择</option>';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';

					}

					html += '</select>'

					document.getElementById("tt").innerHTML = html;
					document.getElementById("tname").innerHTML = "选择组织机构";
				} else {
					html += '<select id="typeone" name="typeone" onchange="CUrltype()">';
					html += '<option value="" >请选择</option>';
					if(atype==1 && urlpoing==4){
						html += '<option value="test001" >航天科工集团</option>';
					}
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';

					}

					html += '</select>'

					document.getElementById("tt").innerHTML = html;
					document.getElementById("tname").innerHTML = "选择组织机构";
				}
			}

		}, "json");

	}

}

function CUrltype() {
	document.getElementById("tt1").innerHTML = "";
    document.getElementById("tname1").innerHTML = "";
	document.getElementById("tt2").innerHTML = "";
    document.getElementById("tname2").innerHTML = "";
    document.getElementById("tt3").innerHTML = "";
    document.getElementById("tname3").innerHTML = "";
	var url = document.getElementsByName("typeone")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
	
	var urls = document.getElementsByName("accountType")[0];
	var urlpoings = urls.options[urls.options.selectedIndex].value;
		var html = "";
		if(urlpoings==4){//培训机构
			$.post("typel1", {
			Action : "post",
			id : urlpoing
		}, function(data, textStatus) {
			if (data.length > 0) {
					html += '<select id="accountCatalog" name="accountCatalog" onchange="PXBtype()">';
					html += '<option value="" >请选择</option>';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';

					}

					html += '</select>'

					document.getElementById("tt1").innerHTML = html;
					document.getElementById("tname1").innerHTML = "选择底层组织机构";
			
			}

		}, "json");
		}else{
			$.post("DCtypel", {
			Action : "post",
			id : urlpoing
		}, function(data, textStatus) {
			if (data.length > 0) {
					html += '<select id="accountCatalog" name="accountCatalog" >';
					html += '<option value="" >请选择</option>';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';

					}

					html += '</select>'

					document.getElementById("tt1").innerHTML = html;
					document.getElementById("tname1").innerHTML = "选择底层组织机构";
			
			}

		}, "json");
		}
		

	

}


function PXBtype() {
	document.getElementById("tt2").innerHTML = "";
    document.getElementById("tname2").innerHTML = "";
    document.getElementById("tt3").innerHTML = "";
    document.getElementById("tname3").innerHTML = "";
	var url = document.getElementsByName("accountCatalog")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
		var html = "";

		$.post("BCtypel", {
			Action : "post",
			id : urlpoing
		}, function(data, textStatus) {
			if (data.length > 0) {
					html += '<select id="accountAdmin" name="accountAdmin" onchange="BCtype()">';
					html += '<option value="" >请选择</option>';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.classresourceId
								+ '" >' + newdata.classresourceName
								+ '</option>';

					}

					html += '</select>'
					mark1=1;
					document.getElementById("tt2").innerHTML = html;
					document.getElementById("tname2").innerHTML = "选择培训班";
				
			}else{
				mark1=0;
				alert("无培训班");
			}

		}, "json");

	

}

function BCtype() {
	document.getElementById("tt3").innerHTML = "";
    document.getElementById("tname3").innerHTML = "";
	var url = document.getElementsByName("accountAdmin")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
		var html = "";

		$.post("BCtypel1", {
			Action : "post",
			id : urlpoing,
			uid : 0
		}, function(data, textStatus) {
			if (data.length > 0) {
				
					for ( var i = 0; i < data.length; i++) {
						
						var newdata = data[i];

						html +='<input id="bc" name="bc" type="checkbox"  value="'+newdata.tranclassId+'">'+newdata.tranclassName+'<br>';

					}
					document.getElementById("tt3").innerHTML = html;
					document.getElementById("tname3").innerHTML = "选择班次";
				
			}else{
				alert("无班次");
			}

		}, "json");

	

}