/**
 * 
 */
	window.onload = function(){
		var aNodes = document.getElementsByTagName("a");
		for(var i =0;i<aNodes.length;i++){
			aNodes[i].onclick = function(){
				var request = createXmlhttp();
				var method = "GET";
				var url = this.href;
				request.open(method,url);
				request.send(null);
				request.onreadystatechange = function(){
					if(request.readyState == 4){
						if(request.status == 200 || request.status == 304){
							document.getElementById("show").innerHTML = request.responseText;
						}
					}
				}
				return false;
			}
		}
	}
	
	function createXmlhttp(){ 
		if(window.XMLHttpRequest){ 
	    	xmlhttp = new XMLHttpRequest(); 
	  		if (xmlhttp.overrideMimeType){ 
	   			xmlhttp.overrideMimeType("text/xml"); 
	  		} 
		} 
		else if(window.ActiveXObject){ 
	  		try
	  		{ 
	   			xmlhttp = new ActiveXObject("Msxml2.XMLHTTP"); 
	  		}catch(e)
	  		{ 
	  			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); 
	  		} 
		} 
		if(!xmlhttp){ 
	 	 	window.alert("Your broswer not support XMLHttpRequest!"); 
		} 
		return xmlhttp; 
	}