/**
 * 
 * id:验证代码；只能输入字母数字或—_
 * int:只能输入正整数
 * int_0:只能输入非0正整数
 * money:只能输入最多两位小数
 */

function getContextPath() {
	var contextPath = document.location.pathname; 
	var index =contextPath.substr(1).indexOf("/"); 
	contextPath = contextPath.substr(0,index+1);
	delete index; 
	return contextPath; 
} 

function validatePK(rules,className,pkFieldName){
	var pkField= $("#"+pkFieldName);
	var url = getContextPath() + "/base/ajaxpage.jsp?objectName=IDCheck&objectEvent=CheckID";
	url = url +"&className="+className + "&id=" +pkField.val().replace(/(^\s*)|(\s*$)/g,'');
	url = url +"&C="+Math.random();
	$.get(url, function(result){
		result = result.replace(/\r\n/g,'');
		if(result == "Y"){
			var tip = Tips.duplicatePK;
			showTips(pkFieldName,pkField,tip);
			return false;
		}else{
			$("#"+rules.formName).submit();
		}
	 });
}

function validateForm(rules,className, pkField){
	if(!validateField(rules))
		return false;
	if(className !=null && className != ""){
		validatePK(rules,className, pkField);
	}else
		$("#"+rules.formName).submit();
}
function validateField(rules){
	for(var k in rules){
		if(k!="formName"){
			if(!tipValidate($("#"+k),k,rules[k])){
				return false;			
			}
		}
	}
	return true;
}
function tipValidate(obj,key,rule){
 		var v = $("#"+key).val();
 		v = v.replace(/(^\s*)|(\s*$)/g,'');
 		$("#"+key).val(v);
 		var type = rule.type;
 		var minLength = rule.minLength;
 		var maxLength = rule.maxLength;
 		
 		if(!validateLength(key,obj,v,minLength,maxLength))
 			return false;
 		
 		if(type!=null){
 			if(type == "id"){
 				if(!validateId(key,obj,v))
 					return false;
 			}
 			else if(type == "int"){
 				if(!validateInt(key,obj,v))
 					return false;
 			}
 			else if(type== "int_0"){
 				if(!validateIntnotZero(key,obj,v))
 					return false;
 			}else if(type == "money"){
 				if(!validateMoney(key,obj,v))
 					return false;
 			}
 			else if(type=="english"){
 				if(!validateEnglish(key,obj,v))
 					return false;
 			}
 		}
 		return true;
 	}
 	function validateLength(key,obj, v, minLength, maxLength){
 		if(minLength != null && v.length<minLength){
 			var tip = Tips.minLength + minLength;
 			showTips(key, obj, tip);
 			return false;
 		}
 		
 		if(maxLength != null && v.length>maxLength){
 			var tip = Tips.maxLength + maxLength;
 			showTips(key, obj, tip);
 			return false;
 		}
 		hideTips(key, obj);
 		return true;
 	}
 	
 	function validateId(key,obj, v){
 		var id = /^[a-zA-Z0-9_-]{1,}$/;
 		if(!id.test(v)){
 			var tip = Tips.id;
 			showTips(key, obj, tip);
 			return false;
 		}
 		
 		hideTips(key, obj);
 		return true;
 	}
 	function validateInt(key,obj,v){
 		var id = /^[0-9]*$/;
 		if(!id.test(v)){
 			var tip = Tips.integer;
 			showTips(key, obj, tip);
 			return false;
 		}
 		
 		hideTips(key, obj);
 		return true;
 	}
 	function validateIntnotZero(key,obj,v){
 		var id = /^\+?[1-9][0-9]*$/;
 		if(!id.test(v)){
 			var tip = Tips.integer_0;
 			showTips(key, obj, tip);
 			return false;
 		}
 		
 		hideTips(key, obj);
 		return true;
 	}
 	function validateMoney(key,obj,v){
 		var id = /^(\d+(\.\d{1,2})?){0,1}$/;
 		if(!id.test(v)){
 			var tip = Tips.money;
 			showTips(key, obj, tip);
 			return false;
 		}
 		
 		hideTips(key, obj);
 		return true;
 	}
 	
 	function validateEnglish(key,obj,v){
 		var id = /^[a-zA-Z0-9_\$#& -]*$/;
 		if(!id.test(v)){
 			var tip = Tips.englishOnly;
 			showTips(key, obj, tip);
 			return false;
 		}
 		
 		hideTips(key, obj);
 		return true;
 	}
 	
 	function showTips(key,obj,tip){
 		var tp = $("#TIP_"+key);
 		if(tp.length<=0)
 			obj.after($("<div id=\"TIP_"+key+"\" class=\"validateRed\">"+ tip+"</div>"));
 		else
 			tp.text(tip);
 	}
 	function hideTips(key, obj){
 		var tp = $("#TIP_"+key);
 		if(tp.length>0)
 			tp.remove();
 	}