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
  //�ж������Ƿ�Ϊ��
  if(num==""){
	  document.getElementById("two").value="";
      document.getElementById("eight").value="";
      document.getElementById("ten").value="";
      document.getElementById("sixteen").value="";
  }else{
	//2���Ƽ��
	  if(type==2){
		  var regex=/^-{0,1}[01]+[Bb]{0,1}$/;
		  if(regex.test(num)==false){
			  count=1;
		  }
	  }
	  //8���Ƽ��
	  if(type==8){
		  var regex=/^-{0,1}0{0,1}[0-7]+[Oo]{0,1}$/;
		  if(regex.test(num)==false){
			  count=1;
		  }
	  }
	  //10�����Ƽ��
	  if(type==10){
		  var regex=/^-{0,1}[0-9]+[Dd]{0,1}$/;
		  if(regex.test(num)==false){
			  count=1;
		  }
	  }
	  //16���Ƽ��
	  if(type==16){
		  var regex=/^-{0,1}(0X|0x){0,1}[0-9a-eA-E]+[Hh]{0,1}$/;
		  if(regex.test(num)==false){
			  count=1;
		  }
	  }
	  //���������ʽ�������ִ��
	  if(count==1){
		  document.getElementById("two").value="false";
	      document.getElementById("eight").value="false";
	      document.getElementById("ten").value="false";
	      document.getElementById("sixteen").value="false";
	  }
	  // alert(num+" "+type);
	  //������Ҫ�󣬽�ֵ�ύ�������
	  if(count==0){
		  var xmlhttp;
		  if (window.XMLHttpRequest)
		  {
		    // IE7+, Firefox, Chrome, Opera, Safari �����ִ�д���
		    xmlhttp=new XMLHttpRequest();
		  }
		  else
		  {
		    // IE6, IE5 �����ִ�д���
		    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		    if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		      var s=xmlhttp.responseText;
		      var retu=s.split(",");
		      //alert(s+" "+retu[0]+retu[1]+retu[2]+retu[3]);
		      //��������
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

