$(function() {
	request('get', '', '/sysuser/userCache', 'sys', function(result) {
		if (!result.data.portrait) {
			result.data.portrait = '../assets/demo/avatar/profile.png';
		}
		$('#portrait').attr('src', result.data.portrait);
		$('#userName').html(result.data.userName);
		$('#email').html(result.data.email);
		$('#mobile').html(result.data.mobile);
		$('#birthday').html(result.data.birthday);
		$('#gander').html(result.data.gander);
		
		var handle = {
			success: function(response, newValue) {
				var obj = {};
				obj[this.id] = newValue;
	            saveProfile(obj);
	        }
		}
		$('#email').editable(handle);
		$('#mobile').editable(handle);
		$('#birthday').editable(handle);
		$('#gender').editable({
			prepend: "未选择",
			source: [{
					value: true,
					text: '男'
				},
				{
					value: false,
					text: '女'
				}
			],
			display: function(value, sourceData) {
				var colors = {
						"": "gray",
						true: "green",
						false: "blue"
					},
					elem = $.grep(sourceData, function(o) {
						return o.value == value;
					});
				if(elem.length) {
					$(this).text(elem[0].text).css("color", colors[value]);
				} else {
					$(this).empty();
				}
			},
			success: function(response, newValue) {
				var obj = {};
				obj[this.id] = newValue;
	            saveProfile(obj);
	        }
		});
//		$('#intro').editable(handle);
	});
	
	$('#cropperPortrait').on('shown.bs.modal', function(event) {
		var modal = $(this);
		modal.find('#image').attr('src', $('#portrait').attr('src'));
		cropper();
	});
});

function upload() {
	var fileUploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		multi_selection : multiSelect,
		browse_button : btnId,
		flash_swf_url : '/static/lib/plupload/Moxie.swf',
		silverlight_xap_url : '/static/lib/plupload/Moxie.xap',
		url : 'hdfs/upload',
		max_file_size : '5mb',
		resize : resize || {},
		filters : [ {
			title : "Image files",
			extensions : "jpg,jpeg,gif,png"
		} ],
		unique_names : true,
		init : {
			PostInit: function() {
			},
			FilesAdded: function(uploader, files) {
				uploader.start();
			},
			
			BeforeUpload:function(up, file){
				set_upload_param(up, file);
			},
		
			UploadProgress: function(up, file) {
				console.log(file.percent);
			},
		
			FileUploaded: function(up, file, info) {
			},
		
			Error: function(up, err) {
			}
		}
	});
}

function cropper() {
	var options = {
		dragMode: 'move',
		aspectRatio: 1/1,
		autoCropArea: 1,
		preview: ".img-preview",
		ready: function(){
			$(window).resize();
		}
	},
	$image = $("#image").cropper(options),
	URL = window.URL || window.webkitURL,
	uploadedImageType = 'image/png',
	uploadedImageURL, imageBase64;
	$('.btn-group,.modal-footer').on('click', '[data-method]', function () {
		var $this = $(this), data = $this.data(), result,
			cropper = $image.data('cropper'), cropped;
		if ($this.prop('disabled') || $this.hasClass('disabled')) {
			return;
		}
		if (cropper && data.method) {
			data = $.extend({}, data);
			cropped = cropper.cropped;
			switch (data.method) {
			case 'rotate':
				if (cropped && options.viewMode > 0) {
					$image.cropper('clear');
				}
				break;
			case 'getCroppedCanvas':
				if (uploadedImageType === 'image/jpeg') {
					if (!data.option) {
						data.option = {};
					}
					data.option.fillColor = '#fff';
				}
				break;
			}
			result = $image.cropper(data.method, data.option, data.secondOption);
			switch (data.method) {
			case 'rotate':
				if (cropped && options.viewMode > 0) {
					$image.cropper('crop');
				}
				break;
			case 'scaleX':
			case 'scaleY':
				$(this).data('option', -data.option);
				break;
			case 'getCroppedCanvas':
				if (result) {
					imageBase64 = result.toDataURL(uploadedImageType);
					$('#portrait').attr('src', imageBase64);
					$('#cropperPortrait').modal('hide');
					saveProfile({"portrait": imageBase64});
				}
				break;
			}
		}
	});
	$("#inputImage").change(function(){
		var files = this.files, file;
		if (!$image.data('cropper')) {
			return;
		}
		if (files && files.length) {
			file = files[0];
			if (/^image\/\w+$/.test(file.type)) {
				uploadedImageType = file.type;
				uploadedImageURL = URL.createObjectURL(file);
				$image.cropper('destroy').attr('src', uploadedImageURL).cropper(options);
				$(this).val('');// 清理下，方便下次选择
			} else {
				js.showMessage('请选择一个图片文件。');
			}
		}
		$(this).blur();
	});
	$(window).resize(function(){
		$('.img-container').height($(window).height()-50);
	}).resize();
}

function saveProfile(formData) {
	request('put', formData, '/sysuser/update/profile', 'sys');
}
