<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<base href="<%=basePath%>">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>问卷调查</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="author" content=""/>

    <!-- Animate.css -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="css/icomoon.css">
    <!-- Themify Icons-->
    <link rel="stylesheet" href="css/themify-icons.css">
    <!-- Bootstrap  -->
    <link rel="stylesheet" href="css/bootstrap.css">

    <!-- Magnific Popup -->
    <link rel="stylesheet" href="css/magnific-popup.css">

    <!-- Owl Carousel  -->
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">

    <!-- Theme style  -->
    <link rel="stylesheet" href="css/style.css">

    <!-- Modernizr JS -->
    <script src="js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="js/respond.min.js"></script>
    <!--[endif]-->

</head>
<body>
<div class="gtco-loader"></div>

<div id="page">


    <div class="page-inner">
        <nav class="gtco-nav" role="navigation">
            <div class="gtco-container">

                <div class="row">
                    <div class="col-sm-4 col-xs-12">
                        <div id="gtco-logo">
                            <a href="#">在线问卷网</a><br>
                            <a href="#">SurveyOnline.com</a>
                        </div>
                    </div>
                    <div class="col-xs-8 text-right menu-1">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/wenjuan/get"><b>参与调查</b></a></li>

                            <li class="has-dropdown">
                                <a href="#" style=""><b>个人中心</b></a>
                                <ul class="dropdown">
                                    <li><a href="${pageContext.request.contextPath}/wenjuan/aaa?u_id=<%=session.getAttribute("id")%>">我的问卷</a></li>
                                    <li><a href="#">我的信息</a></li>
                                    <li><a href="login1.jsp">切换用户</a></li>
                                    <form action="logout.action" method="post">
                                        <li><a href="">注销</a></li>
                                    </form>
                                </ul>
                            </li>

                            <li class="btn-cta"><a href="addWJ.jsp"><span><b>创建问卷</b></span></a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </nav>

        <header id="gtco-header" class="gtco-cover" role="banner"
                style="background-image: url(img/bg6.png);height: 400px;">


        </header>

        <div class="gtco-section border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
                        <p>在线问卷网致力于为您带来最好的用户体验</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="b.jsp?id=10" class="fh5co-project-item">
                            <figure>
                                <div class="overlay">
                                    <i class="ti-plus"></i>
                                </div>
                                <img src="img/h1.png" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>食堂满意度调查问卷</h2>
                                <p>6道题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;100份答卷</p>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="b.jsp?id=4" class="fh5co-project-item">
                            <figure>
                                <div class="overlay">
                                    <i class="ti-plus"></i>
                                </div>
                                <img src="img/h2.png" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>可口可乐产品市场占有率调查</h2>
                                <p>6道题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;80份答卷</p>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="b.jsp?id=9" class="fh5co-project-item">
                            <figure>
                                <div class="overlay">
                                    <i class="ti-plus"></i>
                                </div>
                                <img src="img/h3.png" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>关于大学生上网情况的调查</h2>
                                <p>5道题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;70份答卷</p>
                            </div>
                        </a>
                    </div>

                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="b.jsp?id=8" class="fh5co-project-item">
                            <figure>
                                <div class="overlay">
                                    <i class="ti-plus"></i>
                                </div>
                                <img src="img/h4.png" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>当代大学生恋爱观调查</h2>
                                <p>6道题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;120份答卷</p>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="b.jsp?id=5" class="fh5co-project-item">
                            <figure>
                                <div class="overlay">
                                    <i class="ti-plus"></i>
                                </div>
                                <img src="img/h5.png" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>饿了么外卖客户满意度调查</h2>
                                <p>6道题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;90份答卷</p>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-4 col-md-4 col-sm-6">
                        <a href="stay.jsp" class="fh5co-project-item">
                            <figure>
                                <div class="overlay">
                                    <i class="ti-plus"></i>
                                </div>
                                <img src="img/h6.png" alt="Image" class="img-responsive">
                            </figure>
                            <div class="fh5co-text">
                                <h2>关于iPhone市场占有率的调查问卷</h2>
                                <p>18道题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;60份答卷</p>
                              </div>  
                        </a>
                    </div>

                </div>
            </div>
        </div>


        <footer id="gtco-footer" role="contentinfo">
            <div class="gtco-container">
                <div class="row row-p	b-md">

                    <div class="col-md-4">
                        <div class="gtco-widget">
                            <h3>关于<span class="footer-logo">本网站</span>
                            </h3>
                            <p>问卷调查系统是由2016级java04班<br>刘洪雨 张峻箐 姜世擘 严宏逸 完成</p>
                        </div>
                    </div>

                    <div class="col-md-4 col-md-push-1">
                        <div class="gtco-widget">
                            <h3>链接</h3>
                            <ul class="gtco-footer-links">
                                <li><a href="#">知识库</a></li>
                                <li><a href="#">职业生涯</a></li>
                                <li><a href="#">新闻</a></li>
                                <li><a href="#">服务条款</a></li>
                                <li><a href="#">隐私政策</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="gtco-widget">
                            <h3>取得联系</h3>
                            <ul class="gtco-quick-contact">
                                <li><a href="#"><i class="icon-phone"></i> +178 639 27840</a></li>
                                <li><a href="#"><i class="icon-mail2"></i> info@</a></li>
                                <li><a href="#"><i class="icon-chat"></i> 即时聊天</a></li>
                            </ul>
                        </div>
                    </div>

                </div>

                <div class="row copyright">
                    <div class="col-md-12">
                        <p class="pull-left">
                            <small class="block">QDU&copy; 16级java04班 </small>
                        </p>
                        <p class="pull-right">


                        <ul class="gtco-social-icons pull-right">
                            <li><a href="#"><i class="icon-twitter"></i></a></li>
                            <li><a href="#"><i class="icon-facebook"></i></a></li>
                            <li><a href="#"><i class="icon-linkedin"></i></a></li>
                            <li><a href="#"><i class="icon-dribbble"></i></a></li>
                        </ul>
                        </p>
                    </div>
                </div>

            </div>
        </footer>
    </div>

</div>

<div class="gototop js-top">
    <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
</div>

<!-- jQuery -->
<script src="js/jquery.min.js"></script>
<!-- jQuery Easing -->
<script src="js/jquery.easing.1.3.js"></script>
<!-- Bootstrap -->
<script src="js/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="js/jquery.waypoints.min.js"></script>
<!-- Carousel -->
<script src="js/owl.carousel.min.js"></script>
<!-- countTo -->
<script src="js/jquery.countTo.js"></script>
<!-- Magnific Popup -->
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/magnific-popup-options.js"></script>
<!-- Main -->
<script src="js/main.js"></script>
</body>
</html>
