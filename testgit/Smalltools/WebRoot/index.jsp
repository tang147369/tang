<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>小工具</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/main.css" />
		<script type="text/javascript" src="js/main.js" ></script>
	</head>
	<body>
		<!--导航栏--->
			<nav class="navbr navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#collapse-h">
							<span class="sr-only">切换导航</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">小工具使用平台</a>
					</div>
					<div class="collapse navbar-collapse" id="collapse-h">
						<ul class="nav navbar-nav">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">算法
									<b class="caret"></b>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">排序算法</a></li>
									<li class="divider"></li>
									<li><a href="#">查找算法</a></li>
									<li class="divider"></li>
									<li><a href="#">分治法</a></li>
									<li class="divider"></li>
									<li><a href="#">动态规划法</a></li>
									<li class="divider"></li>
									<li><a href="#">贪心算法</a></li>
								</ul>
							</li>
							<li><a href="#">个人作品</a></li>
							<li><a href="#">留言</a></li>
						</ul>
					</div>
				</div>
			</nav>
		<!--内容-->
		<div class="container">
			<div id="contents"  class="row" style="margin-top: 100px;">
				<!--左边导航栏-->
				<div class="contents-left-main hidden-xs  col-sm-2 col-md-2 " id="contents-left-m">
					<div class="contents-left panel-group" id="accordion">
						<div class="panel panel-default" style="margin-top:0px;border-top:hidden;border-bottom:hidden;">
							<a href="#" class="list-group-item" data-toggle="collapse" data-parent="#accordion" data-target="#a1"><span class="glyphicon glyphicon-plus"></span><span class="sf">插入排序</span></a>
						    <div id="a1" class="collapse">
						    	<a href="#" class="list-group-item"><span class="sf">直接插入排序</span></a>
						   	    <a href="#" class="list-group-item"><span class="sf">折半插入排序</span></a>
						   	    <a href="#" class="list-group-item"><span class="sf">希尔排序</span></a>
						    </div>
					    </div>
					    <div class="panel panel-default" style="margin-top:0px;border-top:hidden;border-bottom:hidden">
							<a href="#" class="list-group-item" data-toggle="collapse" data-parent="#accordion"  data-target="#a2"><span class="glyphicon glyphicon-plus"></span><span class="sf">选择排序</span></a>
							<div id="a2" class="collapse">
								<a href="#" class="list-group-item"><span class="sf">冒泡排序</span></a>
						   	    <a href="#" class="list-group-item"><span class="sf">快速排序</span></a>
							</div>
						</div>
						<div class="panel panel-default" style="margin-top:0px;border-top:hidden;border-bottom:hidden">
							<a href="#" class="list-group-item" data-toggle="collapse" data-parent="#accordion"  data-target="#a3"><span class="glyphicon glyphicon-plus"></span><span class="sf">交换排序</span></a>
							<div id="a3" class="collapse">
								<a href="#" class="list-group-item"><span class="sf">直接选择排序</span></a>
						   	    <a href="#" class="list-group-item"><span class="sf">堆排序</span></a>
							</div>
						</div>
						<a href="#" class="list-group-item" data-toggle="collapse" data-parent="#accordion"><span class="glyphicon glyphicon-okglyphicon glyphicon-ok"></span><span class="sf">归并排序</span></a>
						<a href="#" class="list-group-item" data-toggle="collapse" data-parent="#accordion"><span class="glyphicon glyphicon-ok"></span><span class="sf">基数排序</span></a>
					</div>
				</div>
				<!--右边内容 tab-pane后面加fade有淡入淡出的效果 in类显示初始内容-->
				<div class="contents-right col-sm-10 col-md-10">
					<ul id="myTab" class="nav nav-tabs">
						<li class="active">
							<a style="color: black;" href="#bubbleSort" data-toggle="tab">冒泡排序</a>
						</li>
						<li><a style="color: black;" href="#test" data-toggle="tab">时间效率测试</a></li>
						<li class="visible-xs"><a style="color: black;" href="#" data-toggle="tab">排序方式</a></li>
					</ul>
					<!--标签页-->
					<div id="myTabContent" class="tab-content">
						<!--排序算法说明标签页-->
						<div class="tab-pane fade in active" id="bubbleSort">
							<div class="panel panel-default">
								<div class="panel-heading"><h4>冒泡排序</h4></div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">1.排序思路</div>
								<div class="panel-body">
									冒泡排序也称为气泡排序，是一种典型的交换排序方法，其基本思想是：通过无序区中相邻元素间关键字的比较和位置的交换，使关键字最小的
									元素如气泡一般逐渐往上"漂浮"，直至水面，整个算法是从最下面的元素开始，对每两个相邻的关键字进行比较，且使关键字较小的元素换至关键字
									较大的元素之上，使得经过一趟冒泡排序后，关键字最小的元素到达最上端；接着，再在剩下的元素中找关键字次小的元素，并把它换到第二个位置上。
									依次类推，一直到所有元素都有序为止。
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">2.排序算法</div>
							</div>
					冒泡排序的算法如下：
<pre>void BubbleSort(RecType R[],int n)
{
  int i,j;
  RecType tmp;
  for(i=0;i&lt;n-1;i++)
  {
    for(j=n-1;j&gt;i;j--)		//比较，找出本躺关键字最小的元素
    if(R[j].key&lt;R[j-1].key)
    {
      tmp=R[j];			//R[j]与R[j-1]进行交换，将关键字最小的元素前移
      R[j]=R[j-1];
      R[j-1]=tmp;
    }
  }
}
</pre>
							<div>
								例题：设待排序的表有10个元素，其关键字为{9，8，7，6，5，4，3，2，1，0}。说明采用冒泡排序法进行排序的过程？	
								<span class="h-span"  data-toggle="collapse" data-target="#pspan">查看解析</span>
								<!--例题解析-->
								<div class="panel panel-default collapse" id="pspan" style="background: rgb(245,245,245);">
									<div class="panel-body">
										解：其排序过程如下所示。每次从无序区中冒出一个关键字最小的元素（用红色表示）并将其定位。
										<ul class="list-unstyled">
											<li>初始关键字 9 8 7 6 5 4 3 2 1 0</li>       
											<li>i=0 <span style="color: red;">0</span> 9 8 7 6 5 4 3 2 1</li>      
										    <li>i=1 0 <span style="color: red;">1</span> 9 8 7 6 5 4 3 2</li> 
									        <li>i=2 0 1 <span style="color: red;">2</span> 9 8 7 6 5 4 3</li> 
									        <li>i=3 0 1 2 <span style="color: red;">3</span> 9 8 7 6 5 4</li> 
										    <li>i=4 0 1 2 3 <span style="color: red;">4</span> 9 8 7 6 5</li> 
										    <li>i=5 0 1 2 3 4 <span style="color: red;">5</span> 9 8 7 6</li>
										    <li>i=6 0 1 2 3 4 5 <span style="color: red;">6</span> 9 8 7</li>
										    <li>i=7 0 1 2 3 4 5 6 <span style="color: red;">7</span> 9 8</li>
										    <li>i=8 0 1 2 3 4 5 6 7 <span style="color: red;">8</span> 9</li>
										</ul>
									</div>
								</div>
							</div>	
							<!--追加内容-->
							<div>
								<br />
								补充：
								<P>在有些情况下，在第i(i&lt;n-1)趟就已经排好序了，但算法仍执行后面几趟的比较。
								      实际上，一旦算法中某一趟比较时没有出现任何元素交换，说明已排好序了，就可以结束本算法。
								   为此，改进冒泡排序算法如下：
								</P>
								<pre>
void BubbleSort1(RecType R[],int n)
{
  int i,j;
  bool exchange;
  RecType tmp;
  for(i=0;i&lt;n-1;i++)
  {
  	exchange=false;
  	for(j=n-1;j&gt;i;j--)
  	{
  	  if(R[j].key&lt;R[j-1].key)
  	  {
  	  	tmp=R[j];
  	  	R[j]=R[j-1];
  	  	exchange=true;
  	  }
  	  if(!exchange)
  	    return;
  	}
  }
}</pre>
							</div>
						</div>
						<!--时间效率测试标签页-->
						<br/>
						<div class="tab-pane fade in" id="test">
							<div class="panel panel-default">
								<div class="panel-heading">在生成前的输入框输入要生成的随机数的位数，再点击生成按钮即可生成待排序随机数;生成后点击排序即可。</div>
							</div>
							<div class="row">
								<div class="col-md-5">
									<textarea class="form-control" rows="6" readonly="readonly"></textarea>
								</div>
								<div class="col-md-2 ">
									<br  />
									<form action="Time_efficiency" method="post" role="form">
										<div class="input-group input-group-sm">
									            <input type="text" name="num" class="form-control">
									            <span class="input-group-btn">
									            	<button class="btn btn-info" type="submit">生成</button>
									            </span>
								        </div>
							         </form>
							        <br/>
							        <p style="text-align:right;"><button type="button" class="btn btn-info btn-sm hidden">排序</button></p>
								</div>
								<div class="col-md-5">
									<textarea class="form-control" rows="6" readonly="readonly"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--底部-->
		<div id="footer">
			<div class="container bottom">
				<div class="row">
					
				</div>
			</div>
		</div>
		<script type="text/javascript" src="js/jquery.js" ></script>
		<script type="text/javascript" src="js/bootstrap.min.js" ></script>
	</body>
</html>

