/**
 * 
 */
	$(function(){
//		$("a").click(function(){
//			var url = this.href;
//			var args = {"time": new Date()};
//			$("#showsingle").load(url,args);
//			return false;
//		});
		
		//.live('click',function()
		$("#show").on('click',".single",function(){
			var url = "AjaxServlet";
			$(show).find("td").each(function(){
				$(this).attr("style","background-color:#FFFFFF;");
			})
			$(this).parent().attr("style","background-color:#FFFF77;");
			//alert($(this).html());
			var args = {"time": new Date(),"id":$(this).html()};
			
			$.post(url,args,function(data){
				getcontent(data);
				//getalluser(data);
			},"json")
			return false;
		});
		
		$("#showdb").click(function(){
			var url = "AjaxServlet";
			var args = {"time": new Date()};
			$("#show").attr("style","float: left;height:645px;overflow:auto;overflow-x: hidden;");
			
			$.post(url,args,function(data){
				//getcontent(data);
				getalluser(data);
			},"json")
			return false;
		});
	})
	
	function getcontent(data){
		//<table border="1" cellpadding="2" cellspacing="1" style="margin: 0 auto">
		$("#showsingle").empty();
		$("#showsingle").attr("style","float: left;height:645px;overflow:auto;overflow-x: hidden;");
		$("#showsingle").append("<table id='showsingletable' border='1' cellpadding='2' cellspacing='1' style='background-color:#FFFF77;margin: 0 auto'></table>");
		//"caller_id_name":"1008","destination_number":"666666666666","start_stamp":"2016-10-28 15:26:23","answer_stamp":"2016-10-28 15:26:23",
		//"end_stamp":"2016-10-28 15:26:27","duration":4,"billsec":4,"hangup_cause":"NORMAL_CLEARING"
		//<td align="center">会议室名称</td>
		$("#showsingletable").append("<tr><td align='center'>主叫方</td>"+
				"<td align='center'>被叫方</td>"+
				"<td align='center'>发起时间</td>"+
				"<td align='center'>接通时间</td>"+
				"<td align='center'>结束时间</td>"+
				"<td align='center'>呼叫时长</td>"+
				"<td align='center'>通话时长</td>"+
				"<td align='center'>挂断方式</td></tr>");
		$(data).each(function(){
			$("#showsingletable").append("<tr><td align='center'>"+this.caller_id_name +"</td>"+
					"<td align='center'>"+this.destination_number+"</td>"+
					"<td align='center'>"+this.start_stamp+"</td>"+
					"<td align='center'>"+this.answer_stamp+"</td>"+
					"<td align='center'>"+this.end_stamp+"</td>"+
					"<td align='center'>"+this.duration+"</td>"+
					"<td align='center'>"+this.billsec+"</td>"+
					"<td align='center'>"+this.hangup_cause+"</td></tr>");
		})
//		for(var i=0;i<data.length;i++){
//			$("#showtable").append("<tr><td align='center'>"+data[i].caller_id_name +"</td>"+
//					"<td align='center'>"+data[i].destination_number+"</td>"+
//					"<td align='center'>"+data[i].start_stamp+"</td>"+
//					"<td align='center'>"+data[i].answer_stamp+"</td>"+
//					"<td align='center'>"+data[i].end_stamp+"</td>"+
//					"<td align='center'>"+data[i].duration+"</td>"+
//					"<td align='center'>"+data[i].billsec+"</td>"+
//					"<td align='center'>"+data[i].hangup_cause+"</td></tr>");
//		}
	}
	
	function getalluser(data){
		$("#show").empty();
		$("#showsingle").empty();
		$("#show").append("<table id='showtable' border='1' cellpadding='2' cellspacing='1' style='margin: 0 left'></table>");
		$("#showtable").append("<tr><td align='center'>主叫用户</td></tr>");
		$(data).each(function(){
			//<td><a href="ddd.jsp">ddd</a></td>
			$("#showtable").append("<tr><td align='center'>"+"<a href='AjaxServlet' class='single'>"+this.username+"</a>"+"</td></tr>");
		})
	}