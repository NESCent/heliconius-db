/*
JavaScript for Heliconius Website, Jan. 2007
This javascript file definies important classes, functions and variables that help to perform AJAX-related operations, such as retrieving data from heliconius web services, renderring XML data using XSTL and so on.
*/

/* collections html */
var collections;

/* collection html */
var collection;

/* hybrids html */
var hybrids;

/* hybrid html */
var hybrid;

/* broods html */
var broods;

/* brood html */
var brood;


/* url of the web service */
var ws_url="http://localhost/heliconius_ws/rest/";  

/* image folder */
var image_url="http://www.nescent.org/images/rotate/";  


/* The div obejct to show results */
var showResultObject; 

/* function to set show_result_object */
function setShowResultObject(obj)
{
	showResultObject=obj;	
}


// class to hold web service information 

function WebService(serviceName, xsltName, handlerName, pageName)
{
	this.webServiceName=serviceName;
	this.xsltName=xsltName;
	this.resultHandler=handlerName;
	this.pageName=pageName;
	
	this.getPageName=getPageName;
	this.setPageName=setPageName;
	this.getWebServiceName=getWebServiceName;
	this.getXsltName=getXsltName;
	this.setWebServiceName=setWebServiceName;
	this.setXsltName=setXsltName;
	this.getResultHandler=getResultHandler;
	this.setResultHandler=setResultHandler;
	
	
	function getPageName()
	{
		return this.pageName;
	}
	
	function setPageName(myVal)
	{
		return this.pageName=myVal;
	}
	
	function getResultHandler()
	{
		return this.resultHandler;
	}

	function getWebServiceName()
	{
		return this.webServiceName;
	}
	
	function getXsltName()
	{
		return this.xsltName;
	}
	
	function setResultHandler(myVal)
	{
		this.resultHandler=myVal;
	}
	
	function setWebServiceName(myVal)
	{
		this.webServiceName=myVal;
	}
	
	function setXsltName(myVal)
	{
		this.xsltName=myVal;
	}
}

// class to hold all web services and support get/set/add functions 
function WebServices()
{
	this.services=new Array();
	this.services[0]=new WebService("collections","collections_xsl",handleResults,"collections.html");
	this.services[1]=new WebService("collection","collection_xsl",handleResults,"collection.html");
	this.services[2]=new WebService("hybrids","hybrids_xsl",handleResults,"hybrids.html");
	this.services[3]=new WebService("hybrid","hybrid_xsl",handleResults,"hybrid.html");
	this.services[4]=new WebService("broods","broods_xsl",handleResults,"broods.html");
	this.services[5]=new WebService("brood","brood_xsl",handleResults,"brood.html");
	
	this.contains=contains;
	this.getServiceByName=getServiceByName;
	this.getServiceByIndex=getServiceByIndex;
	this.setServiceByName=setServiceByName;
	this.setServiceByIndex=setServiceByIndex;
	this.addService=addService;
	
	function contains(name)
	{
		for(var i=0;i<this.services.length;i++)
		{
			if(this.services[i].getWebServiceName()==name)
				return true;
		}
		
		return false;
	}
	
	function getServiceByName(name)
	{
		for(var i=0;i<this.services.length;i++)
		{
			if(this.services[i].getWebServiceName()==name)
				return this.services[i];
		}
		
		return null;	
	}
	
	function getServiceByIndex(index)
	{
		if(index>-1 && index<this.services.length)
			return this.services[index];
		
		return null;	
	}
	
	function setServiceByName(name, service)
	{
		for(var i=0;i<this.services.length;i++)
		{
			if(this.services[i].getWebServiceName()==name)
				this.services[i]=service;
		}
	}
	
	function setServiceByIndex(index,service)
	{
		if(index>-1 && index<this.services.length)
			this.services[index]=service;
	}
	
	function addService(service)
	{
		this.services[this.services.length]=service;
	}
} //end of WebServices class

// create services 
var webServices=new WebServices();
var current_service;

var net = new Object();
net.READY_STATE_UNINITIALIZED=0;
net.READY_STATE_LOADING=1;
net.READY_STATE_LOADED=2;
net.READY_STATE_INTERACTIVE=3;
net.READY_STATE_COMPLETE=4;

function ContentLoader(method,url,param,onload,onerror){
	this.method=method;
	this.url=url;
	this.req=null;
	this.parameters=param;
	this.onload=onload;
	this.onerror=(onerror)?onerror:this.defaultErrorHandler;
	this.loadXMLDoc();
}

ContentLoader.prototype={
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
				
				if(this.parameters && this.parameters.length>0)
				{
					this.req.open(this.method,this.url+"?"+this.parameters,true);
				}
				else
				{
					this.req.open(this.method,this.url,true);
				}
				this.req.send(null);
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
		var msg="Error fetching data!"
			  +"\n\nreadyState:"+this.req.readyState
			  +"\nstatus: "+this.req.status
			  +"\nheaders: "+this.req.getAllResponseHeaders();
		showErrorMessage(msg);	  
			  
	}
}


function sendQuery(wsName, param)
{
	showLoadingPage();
	current_service=webServices.getServiceByName(wsName)
	if(current_service==null)
	{
		alert("Web service not found for: " + wsName);
		return;
	}
	
	var url=ws_url+wsName;
	var method="GET";
	var loader=new ContentLoader(method,url,param,current_service.getResultHandler());
}



function handleResults()
{
	try
  	{
		var mainFrame=window.parent.main_frame;
		
		var xml = xmlParse(this.req.responseText);
		var xslt = xmlParse(eval(current_service.getXsltName()));
		
		var html = xsltProcess(xml, xslt);
		if(current_service.getWebServiceName()=="collections")
		{
			collections= html;
			mainFrame.location=current_service.getPageName();
		}
		else if(current_service.getWebServiceName()=="collection")
		{
			collection= html;
			mainFrame.location=current_service.getPageName();
		}
		else if(current_service.getWebServiceName()=="hybrids")
		{
			hybrids= html;
			mainFrame.location=current_service.getPageName();
			
		}
		else if(current_service.getWebServiceName()=="hybrid")
		{
			hybrid= html;
			mainFrame.location=current_service.getPageName();
			
		}
		else if(current_service.getWebServiceName()=="broods")
		{
			broods= html;
			mainFrame.location=current_service.getPageName();
			
		}
		else if(current_service.getWebServiceName()=="brood")
		{
			brood= html;
			mainFrame.location=current_service.getPageName();
			
		}
	}
	catch(err)
  	{
		var txt="Error description: " + err.description;
		showErrorMessage(txt);
  	}
}


function showLoadingPage()
{
	var html="<div style='vertical-align:center; text-align:center;'>";
	html+="<br/><br/><br/><br/><div style='background-color:#CCCCCC; width:300px;height: 50px;border: 1px solid #777;'>";
	html+="<table style='width:290px; background-color:#CCCCCC;'><tr><td style='width:50px; background-color:#CCCCCC;'><img src='images/butterfly.gif' /></td><td  style='background-color:#CCCCCC;'>";
	html+="Loading data</td></tr></table>";
	html+="</div>";
	html+="</div>";
	showResultObject.innerHTML = html;
}

function showErrorMessage(msg)
{
	var html="<div style='vertical-align:center; text-align:center;'>";
	html+="<br/><br/><br/><br/><div style='background-color:#CCCCCC; width:300px;border: 1px solid #777;'>";
	html+="<table style='width:290px; background-color:#CCCCCC;'><tr><td style='width:50px; background-color:#CCCCCC;'><img src='images/error.jpg' /></td><td  style='background-color:#CCCCCC;'>";
	html+="<b>Error</b><br/>"+msg+"</td></tr></table>";
	html+="</div>";
	html+="</div>";
	showResultObject.innerHTML = html;
	showResultObject.innerHTML = html;	
}


