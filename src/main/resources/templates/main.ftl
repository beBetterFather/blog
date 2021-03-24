<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>首页--layui后台管理模板 2.0</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${basePath!}/static/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${basePath!}/static/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-bg-green">
		<@shiro.hasRole name="admin">
			<div id="nowTime" value="管理员用户"></div>
		</@shiro.hasRole>
		<@shiro.hasRole name="level1">
			<div id="nowTime" value="一星级用户"></div>
		</@shiro.hasRole>
		<@shiro.hasRole name="superVIP">
			<div id="nowTime" value="超超级用户"></div>
		</@shiro.hasRole>
	</blockquote>

    <script type="text/javascript" src="${basePath!}/static/layui/layui.js"></script>
	<script type="text/javascript" src="${basePath!}/static/js/main.js"></script>
</body>
</html>