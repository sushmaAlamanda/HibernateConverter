<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title> Login Page | RMR</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/public/img/logoimage.jpg">
	<!--STYLESHEET-->
	<!--=================================================-->
	<!--Roboto Slab Font [ OPTIONAL ] -->
	<!--<link href="http://fonts.googleapis.com/css?family=Roboto+Slab:400,300,100,700" rel="stylesheet">-->
	<!--<link href="http://fonts.googleapis.com/css?family=Roboto:500,400italic,100,700italic,300,700,500italic,400" rel="stylesheet">-->
	<!--Bootstrap Stylesheet [ REQUIRED ]-->
	<script src="${pageContext.request.contextPath}/resources/public/js/jquery_3.2.1_jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/public/bootstrap/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/public/css/fontawesomeitalic.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/public/css/fontawesome.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/public/css/bootstrap.min.css" rel="stylesheet">
	<!--Jasmine Stylesheet [ REQUIRED ]-->
	<link href="${pageContext.request.contextPath}/resources/public/css/style.css" rel="stylesheet">
	<!--Font Awesome [ OPTIONAL ]-->
	<link href="${pageContext.request.contextPath}/resources/public/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<!--Switchery [ OPTIONAL ]-->
	<link href="${pageContext.request.contextPath}/resources/public/plugins/switchery/switchery.min.css" rel="stylesheet">
	<!--Bootstrap Select [ OPTIONAL ]-->
	<link href="${pageContext.request.contextPath}/resources/public/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet">
	<!--Demo [ DEMONSTRATION ]-->
	<link href="${pageContext.request.contextPath}/resources/public/css/demo/jasmine.css" rel="stylesheet">
	<!--SCRIPT-->
	<!--=================================================-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/css/jquery.toast.min.css">
	<script type="text/javascript"> 
		var server_url = ""; 
	</script>
	
	<!--Page Load Progress Bar [ OPTIONAL ]-->
	<link href="${pageContext.request.contextPath}/resources/public/plugins/pace/pace.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/resources/public/plugins/pace/pace.min.js"></script>
	<!-- Jquery validations -->
	<link href="${pageContext.request.contextPath}/resources/public/plugins/jquery-validation-master/css/screen.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/resources/public/js/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/publicjs/jquery.magnific-popup.js" type="text/javascript"></script> 
	<!-- End Jquery validations -->
	<link href="${pageContext.request.contextPath}/resources/public/plugins/keyboard/jquery-ui.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/public/plugins/keyboard/keyboard.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/resources/public/plugins/keyboard/jquery.keyboard.js"></script>
	<script src="${pageContext.request.contextPath}/resources/public/plugins/keyboard/jquery-ui.min.js"></script>
	<script>
		function c(val){
			document.getElementById("d").value=val;
		}
		function v(val){
			document.getElementById("d").value+=val;
		}
		function e(){
			try{ 
			  c(eval(document.getElementById("d").value)) 
			}catch(e){
				c('Error') 
			}
		}	
	</script>
	<link href="${pageContext.request.contextPath}/resources/public/css/logincss/font-awesome.css" rel="stylesheet"> 
	<link href="${pageContext.request.contextPath}/resources/public/css/logincss/popup-box.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${pageContext.request.contextPath}/resources/public/css/logincss/style.css" rel='stylesheet' type='text/css' />
	<link href="${pageContext.request.contextPath}/resources/public/css/logincss/style1.css" rel='stylesheet' type='text/css' />
	<link href="${pageContext.request.contextPath}/resources/public/css/fontopensans.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/public/css/fontquestrial.css" rel="stylesheet">
</head>
<style type="text/css">
#login_form{ margin-left:25px;}
.ui-keyboard{margin-top:50px !important;}
</style>
<!--TIPS-->
<!--You may remove all ID or Class names which contain "demo-", they are only used for demonstration. -->
<body style="overflow:hidden; margin:0;">