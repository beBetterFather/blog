<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 联系人</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="${basePath!}/static/favicon.ico">
    <link href="${basePath!}/static/h+/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${basePath!}/static/h+/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${basePath!}/static/h+/css/animate.css" rel="stylesheet">
    <link href="${basePath!}/static/h+/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <#list users as user>
           <#-- <dd data-menu="${user.trueName!}"><a href="javascript:;">
&lt;#&ndash;                    <i class="layui-icon" data-icon="${user.icon!}">${key.icon!}</i>&ndash;&gt;
                    <cite>${user.trueName!}</cite></a>
            </dd>-->
            <div class="col-sm-4">
                <div class="contact-box">
                  <#--  <a href="profile.html">-->
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="${basePath!}/static/h+/img/${user.image!}">
                                <div class="m-t-xs font-bold">
                                    <#if user.online == "1">
                                        <td class="client-status">
                                            <span class="label label-primary">在线</span>
                                        </td>
                                        </br>
                                        <a href="${basePath!}/admin/guster/forceLogout?userName=${user.userName!}">
                                            <td class="client-status" onclick="">
                                                <span class="label alert-danger">强制下线</span>
                                            </td>
                                        </a>
                                    </#if>
                                    <#if user.online == "0">
                                        <td class="client-status">
                                            <span class="label label-warning">离线</span>
                                        </td>
                                    </#if>
                                    <#if user.online == "2">
                                        <td class="client-status">
                                            <span class="label label-danger">会话过期</span>
                                        </td>
                                    </#if>
                                    <#if user.online == "3">
                                        <td class="client-status">
                                            <span class="label label-danger">登录失败</span>
                                        </td>
                                    </#if>
                                </div>

                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>${user.trueName!}</strong></h3>
                            <p><i class="fa fa-map-marker"></i> ${user.provinceName!}·${user.cityName!}</p>
                            <address>
                                <strong>${user.orgName!}</strong>
                                <br>E-mail:${user.email!}<br>
                                Weibo:<a href="">${user.weibo!}</a><br>
                                <abbr title="Phone">Tel:</abbr>${user.phone!}
                            </address>
                        </div>
                        <div class="clearfix"></div>
                  <#--  </a>-->
                </div>
            </div>
        </#list>
    </div>
</div>

<!-- 全局js -->
<script src="${basePath!}/static/h+/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath!}/static/h+/js/bootstrap.min.js?v=3.3.6"></script>



<!-- 自定义js -->
<script src="${basePath!}/static/h+/js/content.js?v=1.0.0"></script>

<script>
    $(document).ready(function () {
        $('.contact-box').each(function () {
            animationHover(this, 'pulse');
        });
    });
</script>


</body>

</html>
