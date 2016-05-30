	//院1级联动修改显示
function atype(){
	var oid = document.getElementById('oid').value;
	var html = "";
	$.post("typel", {
			Action : "post",
			id : "test001"
		}, function(data, textStatus) {
			if (data.length > 0) {
					
					html += '<select id="accountCatalog" name="accountCatalog" >';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];
						if(oid==newdata.organizationId){
							html += '<option value="' + newdata.organizationId
								+ '" selected="selected" >' + newdata.organizationDwmc
								+ '</option>';
						}else{
							html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';
						}
						

					}

					html += '</select>'

					document.getElementById("tt").innerHTML = html;
					document.getElementById("tname").innerHTML = "选择组织机构";
				
			}

		}, "json");
	
}

	//院二级联动修改显示（ID反向查询显示）
function atype1(){
	var oid = document.getElementById('oid').value;
	var atype = document.getElementById('atype').value;
	var url = document.getElementsByName("accountType")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
	var html = "";
	var sid = "";
	$.post("fxtypel", {
			Action : "post",
			id : oid
		}, function(data, textStatus) {
			if (data.length > 0) {
					
					html += '<select id="typeone" name="typeone" onchange="CUrltype()">';
				
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];
						
						if(atype==1 && oid != 'test001' && i ==1 && urlpoing==4){
						html += '<option value="test001" >航天科工集团</option>';
						}
						html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';
						
					}
					sid = data[0].organizationId;
					html += '</select>'

					document.getElementById("tt").innerHTML = html;
					document.getElementById("tname").innerHTML = "选择组织机构";
					xjtype(sid);
			}
			
		}, "json");
	
	
	
}

		//院二级联动修改显示（底层）
function xjtype(sid){
	var url = document.getElementsByName("accountType")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
	var oid = document.getElementById('oid').value;
	var html = "";
	if(urlpoing==4){//培训机构
		$.post("typel1", {
			Action : "post",
			id : sid
		}, function(data, textStatus) {
			if (data.length > 0) {
					
					html += '<select id="accountCatalog" name="accountCatalog" onchange="PXBtype()">';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];
						if(oid==newdata.organizationId){
							html += '<option value="' + newdata.organizationId
								+ '" selected="selected" >' + newdata.organizationDwmc
								+ '</option>';
						}else{
							html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';
						}
						

					}

					html += '</select>'

					document.getElementById("tt1").innerHTML = html;
					document.getElementById("tname1").innerHTML = "选择底层组织机构";
				
			}

		}, "json");
	}else{
		$.post("DCtypel", {
			Action : "post",
			id : sid
		}, function(data, textStatus) {
			if (data.length > 0) {
					
					html += '<select id="accountCatalog" name="accountCatalog" >';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];
						if(oid==newdata.organizationId){
							html += '<option value="' + newdata.organizationId
								+ '" selected="selected" >' + newdata.organizationDwmc
								+ '</option>';
						}else{
							html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';
						}
						

					}

					html += '</select>'

					document.getElementById("tt1").innerHTML = html;
					document.getElementById("tname1").innerHTML = "选择底层组织机构";
				
			}

		}, "json");
	}
	
}
		
function FXPXBtype() {
	var oid = document.getElementById('oid').value;
	var cid = document.getElementById('cid').value;
	var html = "";
	var bid="";

		$.post("FXBCtypel", {
			Action : "post",
			id:oid,
			bid:cid
		}, function(data, textStatus) {
			if (data.length > 0) {
					html += '<select id="accountAdmin" name="accountAdmin" onchange="BCtype()">';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.classresourceId
								+ '" >' + newdata.classresourceName
								+ '</option>';

					}

					html += '</select>'
					bid=data[0].classresourceId;
					document.getElementById("tt2").innerHTML = html;
					document.getElementById("tname2").innerHTML = "选择培训班";
				FXBCtype(bid);
			}else{
				alert("无培训班");
			}

		}, "json");

	

}

function FXBCtype(n) {
		var html = "";
		var cid = document.getElementById('cid').value;
		var uid = document.getElementById('accountId').value;
		$.post("BCtypel1", {
			Action : "post",
			id : n,
			uid : uid
		}, function(data, textStatus) {
			if (data.length > 0) {
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];
						if(newdata.tranclassType=="selected"){
							html +='<input id="bc" name="bc" type="checkbox"  checked  value="'+newdata.tranclassId+'">'+newdata.tranclassName+'<br>';
						}else{
							html +='<input id="bc" name="bc" type="checkbox"  value="'+newdata.tranclassId+'">'+newdata.tranclassName+'<br>';
						}
						
						
					}
					document.getElementById("tt3").innerHTML = html;
					document.getElementById("tname3").innerHTML = "选择班次";
				
			}else{
				alert("无班次");
			}

		}, "json");

	

}
		