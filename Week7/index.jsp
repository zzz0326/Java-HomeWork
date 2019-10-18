<%--
  Created by IntelliJ IDEA.
  User: 57041
  Date: 2019/10/17
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

天气预报:
选择你想查看的日期
<select id="date">
  <option value="2019-10-18">2019-10-18</option>
  <option value="2019-10-19">2019-10-19</option>
  <option value="2019-10-20">2019-10-20</option>
  <option value="2019-10-21">2019-10-21</option>
  <option value="2019-10-22">2019-10-22</option>
  <option value="2019-10-23">2019-10-23</option>
  <option value="2019-10-24">2019-10-24</option>
  <option value="2019-10-25">2019-10-25</option>
  <option value="2019-10-26">2019-10-26</option>
  <option value="2019-10-27">2019-10-27</option>
  <option value="2019-10-28">2019-10-28</option>
  <option value="2019-10-29">2019-10-29</option>
</select>
<button type="button" onclick="sun()">提交</button>
<textarea id="weather"></textarea>
</html>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <script type="text/javascript">
  function sun() {
    var myselcet = document.getElementById("date");
    var index = myselcet.selectedIndex;
    var need = myselcet.options[index].value;
    //alert(need);
    $.ajax
    ({
      url: "http://localhost:8080/Weather_war_exploded/hi/say.do",
      dataType: "json",
      type: "post",
      data: {
        "date": need
        //传走下拉框中的值
      },
      success:function(data){
        alert('success!');
        var ss = JSON.stringify(data);
        //转义
        document.getElementById("weather").value=ss;
      },
      error:function(data){
        alert('failed!');

        document.write(data);
      }
    });
  }



</script>