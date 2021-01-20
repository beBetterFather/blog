<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 布局</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="${basePath!}/static/favicon.ico">
    <link href="${basePath!}/static/h+/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${basePath!}/static/h+/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Morris -->
    <link href=${basePath!}/static/h+/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <link href="${basePath!}/static/h+/css/animate.css" rel="stylesheet">
    <link href="${basePath!}/static/h+/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content text-center p-md">

                    <h2><span class="text-navy">考勤折线图</span>
                    </h2>

                </div>

                <#--<div class="ibox-content text-center p-md">
                    <div class="m-t-md">
                        <div class="p-md ">
                            <a href="index_v5.html" target="_blank">
                                <img class="img-responsive img-shadow" src="${basePath!}/static/h+/img/index_4.jpg" alt="">
                            </a>
                        </div>
                    </div>
                </div>-->

                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>饼状图</h5>
                    <div class="ibox-content">
                        <div class="flot-chart">
                            <div class="flot-chart-content" id="flot-pie-chart"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  <#--  <div class="row">
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-content text-center p-md">

                    <h4 class="m-b-xxs">上下式布局（可选布局）<span class="label label-primary">新</span></h4>
                    <p>可选择的配置选项</p>
                    <span class="simple_tag">滚动导航栏(Scroll navbar)</span>
                    <span class="simple_tag">顶部固定导航栏(Top fixed navbar)</span>
                    <span class="simple_tag">盒式布局(Boxed layout)</span>
                    <span class="simple_tag">滚动页脚(Scroll footer)</span>
                    <span class="simple_tag">固定页脚(Fixed footer)</span>
                    <div class="m-t-md">
                        <p>点击以下图片查看效果：</p>
                        <div class="p-lg ">
                            <a href="index_v5.html" target="_blank">
                                <img class="img-responsive img-shadow" src="${basePath!}/static/h+/img/index_4.jpg" alt="">
                            </a>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-content text-center p-md">

                    <h4 class="m-b-xxs">左右式布局（经典布局）</h4>
                    <p>可选择的配置选项</p>
                    <span class="simple_tag">折叠菜单(Collapse menu)</span>
                    <span class="simple_tag">滚动菜单栏(Scroll navbar)</span>
                    <span class="simple_tag">固定顶部菜单栏(Top fixed navbar)</span>
                    <span class="simple_tag">盒式布局(Boxed layout)</span>
                    <div class="m-t-md">
                        <p>点击以下图片查看效果：</p>
                        <div class="p-lg">
                            <a href="javascript:;">
                                <img class="img-responsive img-shadow" src="${basePath!}/static/h+/img/index.jpg" alt="">
                            </a>
                        </div>
                    </div>
                </div>
            </div>

        </div>


    </div>-->


</div>


<!-- 全局js -->
<script src="${basePath!}/static/h+/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath!}/static/h+/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->
<script src="${basePath!}/static/h+/js/content.js?v=1.0.0"></script>

<!-- Flot -->
<script src="${basePath!}/static/h+/js/plugins/flot/jquery.flot.js"></script>
<script src="${basePath!}/static/h+/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="${basePath!}/static/h+/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="${basePath!}/static/h+/js/plugins/flot/jquery.flot.pie.js"></script>

<!-- Flot demo data -->
<script src="${basePath!}/static/h+/js/demo/flot-demo.js"></script>


</body>

</html>
