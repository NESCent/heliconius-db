// JavaScript Document

var nav=navigator.appName;

var oldColor;
function mouseOverTr(obj)
{
	oldColor=obj.bgColor;
	obj.bgColor="#fbf2ba";
	if(nav == "Microsoft Internet Explorer")
		obj.style.cursor ="hand";
	else
		obj.style.cursor ="pointer";
}

function mouseOutTr(obj)
{
	obj.bgColor=oldColor;
	obj.style.cursor ="default";
}