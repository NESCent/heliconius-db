// JavaScript Document
var collections_xsl='<?xml version="1.0" encoding="UTF-8"?>' +
	'<table><caption>Collections</caption><tr><td><b>Id</b></td><td><b>Name</b></td>' +
	'<td><b>Country</b></td>'+
	'<td><b>Province</b></td>'+
	'<td><b>Latitude</b></td>'+
	'<td><b>Longitude</b></td>'+
	'<td><b>Altitude</b></td>'+
	'</tr>'+
	'<xsl:for-each select="CollectionsResponse/Individuals/Individual">' +
	'   <tr align="right">'+
    '	<td><xsl:value-of select="./@id"/></td>'+
      '  <td><xsl:value-of select="./ScientificName/Simple"/></td>'+
      '  <td><xsl:value-of select="./GeoLocation/Country"/></td>'+
     '   <td><xsl:value-of select="./GeoLocation/Province"/></td>'+
    '    <td><xsl:value-of select="./GeoLocation/Latitude"/></td>'+
   '     <td><xsl:value-of select="./GeoLocation/Longitude"/></td>'+
  '      <td><xsl:value-of select="./GeoLocation/Altitude"/></td>'+
 '   </tr>'+
'</xsl:for-each>'+
'</table>';

