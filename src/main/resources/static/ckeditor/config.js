/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    //图片上传请求路径
    config.filebrowserImageUploadUrl = "/back/image/fileUpload";

    //超链接文件上传请求路径(没生效，后续待测试)
    //config.filebrowserUploadUrl = "/back/image/urlUpload"

	//工具栏“图像”中预览区域显示内容
	config.image_previewText = '图片最大宽度最好设置为760px';
};
