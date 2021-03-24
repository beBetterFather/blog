<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 图片切换</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="${basePath!}/static/favicon.ico">
    <link href="${basePath!}/static/h+/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${basePath!}/static/h+/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${basePath!}/static/h+/css/animate.css" rel="stylesheet">
    <link href="${basePath!}/static/h+/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-7">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5></h5>
                    <div class="ibox-tools">
                       <#-- <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>-->
                        <a class="dropdown-toggle" data-toggle="dropdown" href="">
                            <i class="fa">选择年份</i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="${basePath!}/bb/images?age=2013">2011</a>
                            </li>
                            <li><a href="${basePath!}/bb/images?age=2013">2012</a>
                            </li>
                            <li><a href="${basePath!}/bb/images?age=2013">2013</a>
                            </li>
                            <li><a href="${basePath!}/bb/images?age=2014">2014</a>
                            </li>
                        </ul>
                      <#-- <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>-->
                    </div>
                </div>


                <div class="ibox-content ">
                    <div class="carousel slide" id="carousel2">
                        <ol class="carousel-indicators">
                            <#-- list 循环，循环体里面是map对象-->
                            <#list images as image>
                                <#list image?keys as itemKey>
                                    <#if itemKey == '0'>
                                        <li data-slide-to="${itemKey!}" data-target="#carousel2" class="active"></li>
                                    </#if>
                                    <#if itemKey != '0'>
                                        <li data-slide-to="${itemKey!}" data-target="#carousel2"></li>
                                    </#if>
                                </#list>
                            </#list>
                        </ol>
                        <div class="carousel-inner center">
                            <#list images as image>
                                <#list image?keys as itemKey>
                                    <#if itemKey == '0'>
                                        <div class="item active">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/wbb/${images[itemKey]!}">
                                            <div class="carousel-caption">
                                                <p></p>
                                            </div>
                                        </div>
                                    </#if>
                                    <#if itemKey != '0'>
                                        <div class="item ">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/wbb/${images[itemKey]!}">
                                            <div class="carousel-caption">
                                                <p></p>
                                            </div>
                                        </div>
                                    </#if>
                                </#list>
                            </#list>
                        </div>
                        <a data-slide="prev" href="#carousel2" class="left carousel-control">
                            <span class="icon-prev"></span>
                        </a>
                        <a data-slide="next" href="#carousel2" class="right carousel-control">
                            <span class="icon-next"></span>
                        </a>
                    </div>
                </div>
            </div>
        </div>

    </div>
   <#-- <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>多张切换</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="carousel.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="carousel.html#">选项1</a>
                            </li>
                            <li><a href="carousel.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="carousel slide" id="carousel3">
                        <div class="carousel-inner">
                            <div class="item gallery active left">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big1.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big2.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big3.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big1.jpg">
                                    </div>
                                </div>
                            </div>
                            <div class="item gallery next left">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big3.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big1.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big2.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big1.jpg">
                                    </div>
                                </div>
                            </div>
                            <div class="item gallery">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big2.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big3.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big1.jpg">
                                    </div>
                                    <div class="col-sm-6">
                                        <img alt="image" class="img-responsive" src="img/p_big2.jpg">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a data-slide="prev" href="carousel.html#carousel3" class="left carousel-control">
                            <span class="icon-prev"></span>
                        </a>
                        <a data-slide="next" href="carousel.html#carousel3" class="right carousel-control">
                            <span class="icon-next"></span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>-->
</div>


<#--    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div class="carousel slide" id="carousel3">
                            <div class="carousel-inner">
                                <div class="item gallery active left">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/wbb/earrings.jpg">
                                        </div>
                                       &lt;#&ndash; <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/wbb/gradulate.jpg">
                                        </div>&ndash;&gt;
                                        <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/wbb/lovely.jpg">
                                        </div>
                                        <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/wbb/weddingPhoto.jpg">
                                        </div>
                                    </div>
                                </div>
                                <div class="item gallery next left">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/wbb/gradulate.jpg">
                                        </div>
                                        &lt;#&ndash;<div class="col-sm-6">
                                           &lt;#&ndash; <img alt="image" class="img-responsive" src="${basePath!}/static/h+/img/p3.jpg">&ndash;&gt;
                                        </div>&ndash;&gt;
                                        <div class="col-sm-6">
                                            &lt;#&ndash;<img alt="image" class="img-responsive" src="${basePath!}/static/h+/img/p1.jpg">&ndash;&gt;
                                        </div>
                                        <div class="col-sm-6">
                                           &lt;#&ndash; <img alt="image" class="img-responsive" src="${basePath!}/static/h+/img/p2.jpg">&ndash;&gt;
                                        </div>
                                        <div class="col-sm-6">
                                           &lt;#&ndash; <img alt="image" class="img-responsive" src="${basePath!}/static/h+/img/p1.jpg">&ndash;&gt;
                                        </div>
                                    </div>
                                </div>
                                <div class="item gallery">
                                    <div class="row">
                                      &lt;#&ndash;  <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/h+/img/p_big2.jpg">
                                        </div>
                                        <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/h+/img/p_big3.jpg">
                                        </div>
                                        <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/h+/img/p_big1.jpg">
                                        </div>
                                        <div class="col-sm-6">
                                            <img alt="image" class="img-responsive" src="${basePath!}/static/h+/img/p_big2.jpg">
                                        </div>&ndash;&gt;
                                    </div>
                                </div>
                            </div>
                            <a data-slide="prev" href="carousel.html#carousel3" class="left carousel-control">
                                <span class="icon-prev"></span>
                            </a>
                            <a data-slide="next" href="carousel.html#carousel3" class="right carousel-control">
                                <span class="icon-next"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>-->

    <!-- 全局js -->
    <script src="${basePath!}/static/h+/js/jquery.min.js?v=2.1.4"></script>
    <script src="${basePath!}/static/h+/js/bootstrap.min.js?v=3.3.6"></script>
    <!-- 自定义js -->
    <script src="${basePath!}/static/h+/js/content.js?v=1.0.0"></script>

</body>

</html>
