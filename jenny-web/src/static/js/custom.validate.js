//手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

//验证IMG是否上传图片
jQuery.validator.addMethod("isImg", function(value, element) {
	return value != "";
}, "请上传图片");

//身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
  return this.optional(element) || isIdCardNo(value);    
}, "请输入正确的身份证号码。");

//手机号与邮箱同时验证
jQuery.validator.addMethod("isMobileOrEmail", function(value, element) {   
	return isMobile(value) || isEmail(value);
}, "请输入正确的身份证号码。");

//选择器是否选择
jQuery.validator.addMethod("isSelect", function(value, element) {   
	return value != "0";
}, "");

//判断是否为空
jQuery.validator.addMethod("isEmpty", function(value, element) {  
	var empty = /^\s*$/;
	return !empty.test(value);
}, "");

//验证手机和固定电话  this.optional(element) || (length == 11 && mobile.test(value));
jQuery.validator.addMethod("isMobileOrTelephone", function(value, element) {
	    var mobile = $.trim($("#telephone").val());  
	    var isMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;  
	    var isPhone = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;
	    //如果为1开头则验证手机号码  
	    if (mobile.substring(0, 1) == 1  && isMobile.test(mobile)) {  
	        if (!isMobile.exec(mobile) && mobile.length != 11) {  
	            return false;  
	        }  
	    }  
	    //如果为0开头则验证固定电话号码  
	    else if (mobile.substring(0, 1) == 0) {  
	        if (!isPhone.test(mobile)) {  
	            return false;  
	        }  
	    }  
	    //否则全部不通过  
	    else {  
	        return false;  
	    }  
	    return true;  
}, "请正确填写您的手机号码");

//验证邮编
jQuery.validator.addMethod("isZipCode", function(value, element) {   
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮编");

//密码 最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
jQuery.validator.addMethod("isPassword", function(value, element) {
	if (value.length == 0) {
		return true;
	}
    var reg1 = /[~!@#$%^&*()_+{}:<>?*-.`,.\/;\[\]=]{1}/;
    var reg2 = /[a-z]+/;
    var reg3 = /[A-Z]+/;
    var reg4 = /[0-9]+/;
    var tel = /[\s\S]{8,18}/;
    return this.optional(element) && tel.test(value)
	    || (reg1.test(value) && reg3.test(value) && reg4.test(value))
	    || (reg1.test(value) && reg2.test(value) && reg4.test(value))
	    || (reg1.test(value) && reg2.test(value) && reg3.test(value));
}, "密码至少包含三种以上大写字母，小写字母，数字，特殊字符8~18位字符");

//日期
jQuery.validator.addMethod("isDate", function(value, element) {   
    var tel = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确的日期格式");


//手机号码验证规则
function isMobile(num){
	var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
	return num.length == 11 && mobile.test(num);
}

//邮箱验证规则
function isEmail(value){
	var email = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return email.test(value);
}

//身份证号码的验证规则
function isIdCardNo(num){ 
	var len = num.length, re; 
	if (len == 15) 
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/); 
	else if (len == 18) 
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/); 
	else {
		return false;
	} 
	var a = num.match(re); 
	if (a != null) 
	{ 
		if (len==15) 
		{ 
			var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]); 
			var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
		} 
		else 
		{ 
			var D = new Date(a[3]+"/"+a[4]+"/"+a[5]); 
			var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
		} 
		if (!B) {
			return false;
		} 
	} 
	if(!re.test(num)){
		return false;
	}
	return true; 
} 