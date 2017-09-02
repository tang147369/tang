function icon(){
	var now = document.getElementById("accordion");
	var main = document.getElementById("contents-left-m");
	if(now.style.display=="block"){
		now.style.display="none";
	}else{
		now.style.display="block";
	}
}
function conversion(num,type)
{
  var num=num;
  var type=type;
  var count=0;
  //锟叫讹拷锟斤拷锟斤拷锟角凤拷为锟斤拷
  if(num==""){
	  document.getElementById("two").value="";
      document.getElementById("eight").value="";
      document.getElementById("ten").value="";
      document.getElementById("sixteen").value="";
  }else{
	//2锟斤拷锟狡硷拷锟�
	  if(type==2){
		  var regex=/^-{0,1}[01]+[Bb]{0,1}$/;
		  if(regex.test(num)==false){
			  count=1;
		  }
	  }
	  //8锟斤拷锟狡硷拷锟�
	  if(type==8){
		  var regex=/^-{0,1}0{0,1}[0-7]+[Oo]{0,1}$/;
		  if(regex.test(num)==false){
			  count=1;
		  }
	  }
	  //10锟斤拷锟斤拷锟狡硷拷锟�
	  if(type==10){
		  var regex=/^-{0,1}[0-9]+[Dd]{0,1}$/;
		  if(regex.test(num)==false){
			  count=1;
		  }
	  }
	  //16锟斤拷锟狡硷拷锟�
	  if(type==16){
		  var regex=/^-{0,1}(0X|0x){0,1}[0-9a-eA-E]+[Hh]{0,1}$/;
		  if(regex.test(num)==false){
			  count=1;
		  }
	  }
	  //锟斤拷锟斤拷锟斤拷锟斤拷锟绞斤拷锟斤拷锟斤拷锟斤拷执锟斤拷
	  if(count==1){
		  document.getElementById("two").value="false";
	      document.getElementById("eight").value="false";
	      document.getElementById("ten").value="false";
	      document.getElementById("sixteen").value="false";
	  }
	  // alert(num+" "+type);
	  //锟斤拷锟斤拷锟斤拷要锟襟，斤拷值锟结交锟斤拷锟斤拷锟斤拷锟�
	  if(count==0){
		  var xmlhttp;
		  if (window.XMLHttpRequest)
		  {
		    // IE7+, Firefox, Chrome, Opera, Safari 锟斤拷锟斤拷锟街达拷写锟斤拷锟�
		    xmlhttp=new XMLHttpRequest();
		  }
		  else
		  {
		    // IE6, IE5 锟斤拷锟斤拷锟街达拷写锟斤拷锟�
		    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		    if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		      var s=xmlhttp.responseText;
		      var retu=s.split(",");
		      //alert(s+" "+retu[0]+retu[1]+retu[2]+retu[3]);
		      //锟斤拷锟斤拷锟斤拷锟斤拷
		      document.getElementById("two").value=retu[0];
		      document.getElementById("eight").value=retu[1];
		      document.getElementById("ten").value=retu[2];
		      document.getElementById("sixteen").value=retu[3];
		    }
		  }
		  xmlhttp.open("POST","../Tools",true);
		  xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		  xmlhttp.send("num="+num+"&type="+type+"&toolName=conversion");
	  }
  }
}
function choooseImg(){
	var path=document.getElementById("choose").value;
	alert(path)
	var newPath=path.replace(/\\/g,"/");
	document.getElementById("imgChoosed").style.src="newPath";
	alert(newPath);
}
//鐐瑰嚮鎻愪氦鏃舵椂
function btnToSub(){
	var dbUrl = document.getElementById("dbUrlId").value;
	var user = document.getElementById("userId").value;
	var pwd = document.getElementById("pwdId").value;
	var dbName = document.getElementById("dbNameId").value;
	var table = document.getElementById("tableId").value;
	//alert(dbUrl+" "+user+" "+pwd+" "+dbName+" "+table);
	if(dbUrl!="" && user!="" && pwd!="" && dbName!="" && table!=""){
		document.getElementById("dbToExcelSub").type="submit";
	}else{
		alert("输入不能为空！");
	}
}

