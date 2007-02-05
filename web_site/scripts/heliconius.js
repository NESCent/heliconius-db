/*
JavaScript for Heliconius Website, Jan. 2007
This javascript file definies important calsses, functions and variables that help to perform AJAX-related operations, such as retrieving data from heliconius 
web services, renderring XML data using XSTL and so on.
*/

var net = new Object();
net.READY_STATE_UNINITIALIZED=0;
net.READY_STATE_LOADING=1;
net.READY_STATE_LOADED=2;
net.READY_STATE_INTERACTIVE=3;
net.READY_STATE_COMPLETE=4;

net.ContentLoader=function(method,url,param,onload,onerror){
	this.method=method;
	this.url=url;
	this.req=null;
	this.parameters=param;
	this.onload=onload;
	this.onerror=(onerror)?onerror:this.defaultErrorHandler;
	this.loadXMLDoc();
}

net.ContentLoader.prototype={
	loadXMLDoc:function(){
		if(window.XMLHttpRequest){
			this.req=new XMLHttpRequest();
		}else if(window.ActiveXObject) {
			this.req=new ActiveXObject("Microsoft.XMLHTTP");
		}
		else{
			alert("Can not create XMLHttpRequest");
		}
		
		if(this.req){
			try{
				var loader=this;
				this.req.onreadystatechange=function(){
					loader.onReadyState.call(loader);
				}
				
				this.req.open(this.method,this.url,true);
				
				if(this.parameters && this.parameters.length>0)
				{
					this.req.send(this.parameters);
				}
				else
				{
					this.req.send(null);
				}
			}catch(err)
			{
				this.onerror.call(this);
			}
		}
	},
	
	onReadyState:function(){
		var req=this.req;
		var ready=req.readyState;
		if(ready==net.READY_STATE_COMPLETE){
			var httpStatus=req.status;
			if(httpStatus==200 || httpStatus==0){
				this.onload.call(this);
			}else{
				this.onerror.call(this);
			}
		}
	},
			
			

	defaultErrorHandler:function(){
		alert("Error fetching data!"
			  +"\n\nreadyState:"+this.req.readyState
			  +"\nstatus: "+this.req.status
			  +"\nheaders: "+this.req.getAllResponseHeaders());
	}
}


