<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>添加问卷</title>

    <link rel="stylesheet" type="text/css" href="css/wenjuan_ht.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css">

    <script src="addWJ_js/jquery.min.js"></script>
    <script src="addWJ_js/index.js"></script>

</head>
<body>
<table class="table" style="background-color: #333333">
    <tr><td></td></tr>
    <tr class="container-fluid" align="center">
        <td class="col-xs-4"><a href="home.jsp"><h4 style="color: deepskyblue">首页</h4></a></td>
        <td class="col-xs-4"><a href="view_WJ.jsp"><h4 style="color: deepskyblue">所有问卷</h4></a></td>
        <td class="col-xs-4"><a href="viewMy_WJ.jsp?u_id=<%=session.getAttribute("Name")%>"><h4 style="color: deepskyblue">我的问卷</h4></a></td>
    </tr>
</table>
<div class="">
    <div align="center">
				<textarea name="w_title" class="form-control"
                          placeholder="请在这输入问卷标题"
                          style="text-align: center; width: 50%; margin: 0 auto; margin-top: 25PX; font-size: 20px; border: none;"></textarea>
        <!-- <input class="form-control" type="text" name="type"
        placeholder="请输入问卷类型"
        style="text-align: center; width: 50%; margin: 0 auto; margin-top: 25PX; font-size: 15px;" /> -->


    </div>
    <div class=" all_660">
        <div class="yd_box"></div>
        <div class="but" style="padding-top: 20px">

            截止时间：<input type="date" name="endtime"/>

            <!-- <input type="text" name="q_type"/> -->
            <select name="w_type">
                <option value="O">问卷类别</option>
                <option value="A类">A类</option>
                <option value="B类">B类</option>
                <option value="C类">C类</option>
                <option value="D类">D类</option>
                <option value="E类">E类</option>
            </select>
            <select id="addquerstions" class="addquerstions selectpicker" data-style="btn-info" name="">
                <option value="-1">添加问题</option>
                <option value="0">单选</option>
                <option value="1">多选</option>
                <option value="2">填空</option>
                 <option value="3">矩阵</option> 
            </select><label for="addquerstions"> </label> <input type="hidden" name="test"/>
            <button name="tijiao" class="btn btn-success" onclick="a();">发布</button>
        </div>
    </div>
    <!--选项卡区域  模板区域---------------------------------------------------------------------------------------------------------------------------------------->


    <div class="xxk_box">
        <div class="xxk_conn hide">
            <!--单选----------------------------------------------------------------------------------------------------------------------------------------->

            <div class="xxk_xzqh_box dxuan ">
					<textarea name="q_content" cols="" rows=""
                              class="input_wenbk btwen_text btwen_text_dx" placeholder="单选题目"></textarea>
                <div class="title_itram">
                    <div class="kzjxx_iteam">
                        <input name="" type="radio" value="" class="dxk"> <input
                            name="" type="text" class="input_wenbk" value=""
                            placeholder="选项"> <label> <input name=""
                                                             type="checkbox" value="" class="fxk"> <span>可填空</span>
                    </label> <a href="javascript:void(0);" class="5el_xm">删除</a>
                    </div>
                </div>
                <a href="javascript:void(0)" class="zjxx">增加选项</a>
                <!--完成编辑-->
                <div class="bjqxwc_box">
                    <a href="javascript:void(0);" class="qxbj_but">取消编辑</a> <a
                        href="javascript:void(0);" class="swcbj_but"> 完成编辑</a>
                </div>
            </div>

            <!--多选----------------------------------------------------------------------------------------------------------------------------------------->
            <div class="xxk_xzqh_box duoxuan hide">
					<textarea name="" cols="" rows=""
                              class="input_wenbk btwen_text btwen_text_duox" placeholder="多选题目"></textarea>
                <div class="title_itram">
                    <div class="kzjxx_iteam">
                        <input name="" type="checkbox" value="" class="dxk"> <input
                            name="" type="text" class="input_wenbk" value="选项"
                            placeholder="选项"> <label> <input name=""
                                                             type="checkbox" value="" class="fxk">
                        <span>可填空</span></label>
                        <a
                                href="javascript:void(0);" class="del_xm">删除</a>
                    </div>
                </div>
                <a href="javascript:void(0)" class="zjxx">增加选项</a>
                <!--完成编辑-->
                <div class="bjqxwc_box">
                    <a href="javascript:void(0);" class="qxbj_but">取消编辑</a> <a
                        href="javascript:void(0);" class="swcbj_but"> 完成编辑</a>
                </div>
            </div>
            <!-- 填空----------------------------------------------------------------------------------------------------------------------------------------->
            <div class="xxk_xzqh_box tktm hide">
					<textarea name="" cols="" rows=""
                              class="input_wenbk btwen_text btwen_text_tk" placeholder="答题区"></textarea>
                <!--完成编辑-->
                <div class="bjqxwc_box">
                    <a href="javascript:void(0);" class="qxbj_but">取消编辑</a> <a
                        href="javascript:void(0);" class="swcbj_but"> 完成编辑</a>

                </div>
            </div>
            <!-- 矩阵----------------------------------------------------------------------------------------------------------------------------------------->
            <div class="xxk_xzqh_box  hide">
                <div class="line_dl"></div>
                <div class="jztm">
						<textarea name="" cols="" rows="" class="input_wenbk btwen_text"
                                  placeholder="题目"></textarea>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tbody>
                        <tr valign="top">
                            <td width="135">
                                <h4 class="ritwenz_xx">左标题</h4> <textarea name="" cols=""
                                                                          rows="" class="leftbtwen_text"
                                                                          placeholder="例子：CCTV1，CCTV2，CCTV3"></textarea>
                            </td>
                            <td>
                                <h4 class="ritwenz_xx  ">
                                    右侧选项文字 <input type="radio" name="xz" value="0"
                                                  checked="checked" class="xzqk">单选<input type="radio"
                                                                                          value="1" name="xz"
                                                                                          class="xzqk">多选
                                </h4>
                                <div class="title_itram">
                                    <div class="kzjxx_iteam">
                                        <input name="" type="text" class="input_wenbk jzwent_input"
                                               value="选项" onBlur="if(!this.value)this.value='选项'"
                                               onClick="if(this.value&amp;&amp;this.value==='选项' )  this.value=''">
                                        <label> <input name="" type="checkbox" value=""
                                                       class="fxk"> <span>可填空</span></label> <a
                                            href="javascript:void(0);" class="del_xm">删除</a>
                                    </div>
                                    <div class="kzjxx_iteam">
                                        <input name="" type="text" class="input_wenbk jzwent_input"
                                               value="选项" onBlur="if(!this.value)this.value='选项'"
                                               onClick="if(this.value&amp;&amp;this.value=='选项' )  this.value=''">
                                        <label> <input name="" type="checkbox" value=""
                                                       class="fxk"> <span>可填空</span></label> <a
                                            href="javascript:void(0);" class="del_xm">删除</a>
                                    </div>
                                    <div class="kzjxx_iteam">
                                        <input name="" type="text" class="input_wenbk jzwent_input"
                                               value="选项" onBlur="if(!this.value)this.value='选项'"
                                               onClick="if(this.value&amp;&amp;this.value=='选项' )  this.value=''">
                                        <label> <input name="" type="checkbox" value=""
                                                       class="fxk"> <span>可填空</span></label> <a
                                            href="javascript:void(0);" class="del_xm">删除</a>
                                    </div>
                                </div>
                                <a href="javascript:void(0)" class="zjxx"
                                   style="margin-left: 0;">增加选项</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <!--完成编辑-->
                    <div class="bjqxwc_box">
                        <a href="javascript:void(0);" class="qxbj_but">取消编辑</a> <a
                            href="javascript:void(0);" class="swcbj_but"> 完成编辑</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div
        style="text-align: center; margin: 50px 0; font: normal 14px/24px 'MicroSoft YaHei';">
</div>

</body>
</html>