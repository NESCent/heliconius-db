// JavaScript Document
var collections_xsl=
	'<?xml version="1.0" encoding="UTF-8"?>' +
	'<table cellpadding=0 cellspacing=0 border=0>' +
	'<caption>Collections</caption>' + 
	'<tr>'+
	'<th><b>Id</b></th>' +
	'<th><b>Name</b></th>' +
	'<th><b>Country</b></th>'+
	'<th><b>Province</b></th>'+
	'<th><b>Latitude</b></th>'+
	'<th><b>Longitude</b></th>'+
	'<th><b>Altitude</b></th>'+
	'</tr>'+
	'<xsl:for-each select="CollectionsResponse/Individuals/Individual">' +
	'   <tr align="right">'+
    '		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./GeoLocation/Country"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Province"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Latitude"/></td>'+
   	'   	<td><xsl:value-of select="./GeoLocation/Longitude"/></td>'+
  	'   	<td><xsl:value-of select="./GeoLocation/Altitude"/></td>'+
 	'   </tr>'+
	'</xsl:for-each>'+
	'</table>';

