// JavaScript Document
var collections_xsl=
	'<?xml version="1.0" encoding="UTF-8"?>' +
	'<h3>Collections</h3>' + 
	'<table cellpadding=0 cellspacing=0 border=0>' +
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
	'<xsl:if test="position() mod 2 =1"> ' +
	'   <tr bgColor="#eeeeee"; onMouseOver="javascript:mouseOverTr(this);" onMouseOut="javascript:mouseOutTr(this);" onclick="javascript:queryWebserviceConnection({@id});">'+
	'		<td><xsl:value-of select="./Name"/></td>'+
    '  		<td><xsl:value-of select="./ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./GeoLocation/Country"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Province"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Latitude"/></td>'+
   	'   	<td><xsl:value-of select="./GeoLocation/Longitude"/></td>'+
  	'   	<td><xsl:value-of select="./GeoLocation/Altitude"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
   '<xsl:if test="position() mod 2 =0"> ' +
	'   <tr bgColor="#dddddd"; onMouseOver="javascript:mouseOverTr(this);" onMouseOut="javascript:mouseOutTr(this);" onclick="javascript:queryWebserviceConnection({@id});">'+
	'		<td><xsl:value-of select="./Name"/></td>'+
    '  		<td><xsl:value-of select="./ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./GeoLocation/Country"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Province"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Latitude"/></td>'+
   	'   	<td><xsl:value-of select="./GeoLocation/Longitude"/></td>'+
  	'   	<td><xsl:value-of select="./GeoLocation/Altitude"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
	
	'</xsl:for-each>'+
	'</table>';

var collection_xsl='<?xml version="1.0" encoding="UTF-8"?>' +
	'<h3>Collection</h3>' + 
	'<table cellpadding=0 cellspacing=0 border=0 width=600>' +
	'   <tr>'+
    '		<td class="field_name">ID</td><td class="field_value"><xsl:value-of select="CollectionResponse/Individual/@id"/></td>'+
	'	</tr><tr>' +	
    '  		<td class="field_name">Name</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/ScientificName/Simple"/></td>'+
	'	</tr><tr>' +		
	'  		<td class="field_name">Family</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/ScientificName/Family" /></td>'+
	'	</tr><tr>' +		 
    '  		<td class="field_name">Subfamily</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/ScientificName/Subfamily" /></td>' +
	'	</tr><tr>' +				
    '  		<td class="field_name">Genus</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/ScientificName/Genus" /></td>' +
	'	</tr><tr>' +				
    '  		<td class="field_name">Species</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/ScientificName/Species" /></td>' +
	'	</tr><tr>' +				
    '  		<td class="field_name">Subspecies</td class="field_value><td><xsl:value-of select="CollectionResponse/Individual/ScientificName/Subspecies" /></td>' +
	'	</tr>' +
	'   <tr>'+
    '  		<td class="field_name">Sex</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/Sex"/></td>'+
	'	</tr><tr>' +
	'  		<td class="field_name">Collection Date</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/CollectionDate"/></td>'+
	'	</tr><tr>' +
    '  		<td class="field_name">Country</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/GeoLocation/Country"/></td>'+
	'	</tr><tr>' +
	'   	<td class="field_name">Province</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/GeoLocation/Province"/></td>'+
	'	</tr><tr>' +	
    '   	<td class="field_name">Latitude</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/GeoLocation/Latitude"/></td>'+
	'	</tr><tr>' +	
   	'   	<td class="field_name">Longitude</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/GeoLocation/Longitude"/></td>'+
	'	</tr><tr>' +	
  	'   	<td class="field_name">Altitude</td><td class="field_value><xsl:value-of select="CollectionResponse/Individual/GeoLocation/Altitude"/></td>'+
 	'   </tr>' +
	'</table>' +
	'<br/><br/>' +
	'<b>Notes</b><br/>'+
	'<div style="width:600;">' +
	'<xsl:value-of select="CollectionResponse/Individual/Notes"/>' +
	'</div>' +
	'<br/><br/>' +
	'<b>Specimens</b>' +
	'<table cellpadding=0 cellspacing=0 border=0>' +
	'<tr>'+
	'<th><b>Id</b></th>' +
	'<th><b>Tissue</b></th>' +
	'<th><b>Location</b></th>'+
	'</tr>'+
	'<xsl:for-each select="CollectionResponse/Individual/Specimens/Specimen">' +
	'<xsl:if test="position() mod 2 =1"> ' +
	'   <tr bgColor="#eeeeee"' + 
    '		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./Tissue"/></td>'+
    '  		<td><xsl:value-of select="./Location"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
	'<xsl:if test="position() mod 2 =0"> ' +
	'   <tr bgColor="#dddddd"' + 
    '		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./Tissue"/></td>'+
    '  		<td><xsl:value-of select="./Location"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
	'</xsl:for-each>'+
	'</table>' +
	'<br/><br/>' +
	'<b>Images</b>' +
	'<table cellpadding=0 cellspacing=0 border=0>' +
	'<xsl:for-each select="CollectionResponse/Individual/Images/Image">' +
	'   <tr>'+
    '		<td><img src="http://www.nescent.org/images/rotate/{.}" /></td>'+
 	'   </tr>'+
	'</xsl:for-each>'+
	'</table>';

var hybrids_xsl=
	'<?xml version="1.0" encoding="UTF-8"?>' +
	'<h3>Natural Hybrids</h3>' + 
	'<table cellpadding=0 cellspacing=0 border=0>' +
	'<tr>'+
	'<th><b>Id</b></th>' +
	'<th><b>Parent 1</b></th>' +
	'<th><b>Parent 2</b></th>' +
	'<th><b>Country</b></th>'+
	'<th><b>Province</b></th>'+
	'<th><b>Latitude</b></th>'+
	'<th><b>Longitude</b></th>'+
	'<th><b>Altitude</b></th>'+

	'</tr>'+
	'<xsl:for-each select="HybridsResponse/Individuals/Individual">' +
	'<xsl:if test="position() mod 2 =1"> ' +
	'   <tr bgColor="#eeeeee" onMouseOver="javascript:mouseOverTr(this);" onMouseOut="javascript:mouseOutTr(this);" onclick="javascript:queryWebserviceHybrid({@id});">'+
	'		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./ScientificName2/Simple"/></td>'+
    '  		<td><xsl:value-of select="./GeoLocation/Country"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Province"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Latitude"/></td>'+
   	'   	<td><xsl:value-of select="./GeoLocation/Longitude"/></td>'+
  	'   	<td><xsl:value-of select="./GeoLocation/Altitude"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
   '<xsl:if test="position() mod 2 =0"> ' +
	'   <tr bgColor="#dddddd" onMouseOver="javascript:mouseOverTr(this);" onMouseOut="javascript:mouseOutTr(this);" onclick="javascript:queryWebserviceHybrid({@id});">'+
	'		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./ScientificName2/Simple"/></td>'+
    '  		<td><xsl:value-of select="./GeoLocation/Country"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Province"/></td>'+
    '   	<td><xsl:value-of select="./GeoLocation/Latitude"/></td>'+
   	'   	<td><xsl:value-of select="./GeoLocation/Longitude"/></td>'+
  	'   	<td><xsl:value-of select="./GeoLocation/Altitude"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
	'</xsl:for-each>'+
	'</table>';

var hybrid_xsl='<?xml version="1.0" encoding="UTF-8"?>' +
	'<h3>Natural Hybrid</h3>' + 
	'<table>' +
	'   <tr>'+
    '		<td class="field_name">ID</td><td class="field_value"><xsl:value-of select="HybridResponse/Individual/@id"/></td>'+
	'	</tr><tr>' +	
    '  		<td class="field_name">Genus/Species 1</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/ScientificName/Genus"/>&nbsp;<xsl:value-of select="HybridResponse/Individual/ScientificName/Species"/></td>'+
	'	</tr><tr>' +		
	'  		<td class="field_name">Subspecies 1</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/ScientificName/Subspecies" /></td>'+
	'	</tr><tr>' +		 
        '  		<td class="field_name">Genus/Species 2</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/ScientificName2/Genus"/>&nbsp;<xsl:value-of select="HybridResponse/Individual/ScientificName2/Species"/></td>'+
	'	</tr><tr>' +		
	'  		<td class="field_name">Subspecies 2</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/ScientificName2/Subspecies" /></td>'+
	'	</tr>' +
	'   <tr>'+
    '  		<td class="field_name">Sex</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/Sex"/></td>'+
	'	</tr><tr>' +
	'  		<td class="field_name">Collection Date</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/CollectionDate"/></td>'+
	'	</tr><tr>' +
    '  		<td class="field_name">Country</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/GeoLocation/Country"/></td>'+
	'	</tr><tr>' +
	'   	<td class="field_name">Province</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/GeoLocation/Province"/></td>'+
	'	</tr><tr>' +	
    '   	<td class="field_name">Latitude</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/GeoLocation/Latitude"/></td>'+
	'	</tr><tr>' +	
   	'   	<td class="field_name">Longitude</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/GeoLocation/Longitude"/></td>'+
	'	</tr><tr>' +	
  	'   	<td class="field_name">Altitude</td><td class="field_value><xsl:value-of select="HybridResponse/Individual/GeoLocation/Altitude"/></td>'+
 	'   </tr>' +
	'</table>' +
	'<br/><br/>' +
	'<div style="width:600;">' +
	'<xsl:value-of select="CollectionResponse/Individual/Notes"/>' +
	'</div>' +
	'<xsl:value-of select="HybridResponse/Individual/Notes"/>' +
	'<br/><br/>' +
	'<b>Specimens</b>' +
	'<table cellpadding=0 cellspacing=0 border=0>' +
	'<tr>'+
	'<th><b>Id</b></th>' +
	'<th><b>Tissue</b></th>' +
	'<th><b>Location</b></th>'+
	'</tr>'+
	'<xsl:for-each select="HybridResponse/Individual/Specimens/Specimen">' +
	'<xsl:if test="position() mod 2 =1"> ' +
	'   <tr bgColor="#eeeeee"' + 
    '		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./Tissue"/></td>'+
    '  		<td><xsl:value-of select="./Location"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
	'<xsl:if test="position() mod 2 =0"> ' +
	'   <tr bgColor="#dddddd"' + 
    '		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./Tissue"/></td>'+
    '  		<td><xsl:value-of select="./Location"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
	'</xsl:for-each>'+
	'</table>' +
	'<br/><br/>' +
	'<b>Images</b>' +
	'<table cellpadding=0 cellspacing=0 border=0>' +
	'<xsl:for-each select="HybridResponse/Individual/Images/Image">' +
	'   <tr>'+
    '		<td><img src="http://www.nescent.org/images/rotate/{.}" /></td>'+
 	'   </tr>'+
	'</xsl:for-each>'+
	'</table>';
	
var broods_xsl=
	'<?xml version="1.0" encoding="UTF-8"?>' +
	'<h3>Broods</h3>' + 
	'<table cellpadding=0 cellspacing=0 border=0>' +
	'<tr>'+
	'<th><b>Brood Number</b></th>' +
	'<th><b>Type of Cross</b></th>'+
	'<th><b>Father</b></th>' +
	'<th><b>Mother</b></th>' +
	'<th><b>Number of Eggs</b></th>'+
	'<th><b>Number of Adults</b></th>'+
	'</tr>'+
	'<xsl:for-each select="BroodsResponse/Broods/Brood">' +
	'<xsl:if test="position() mod 2 =1"> ' +
	'   <tr bgColor="#eeeeee" onMouseOver="javascript:mouseOverTr(this);" onMouseOut="javascript:mouseOutTr(this);" onclick="javascript:queryWebserviceBrood({@id});">'+
	'		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./CrossType"/></td>'+	
    '  		<td><xsl:value-of select="./Father/ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./Mother/ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./NumberOfEggs"/></td>'+
    '   	<td><xsl:value-of select="./NumberOfAdults"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
   '<xsl:if test="position() mod 2 =0"> ' +
		'   <tr bgColor="#dddddd" onMouseOver="javascript:mouseOverTr(this);" onMouseOut="javascript:mouseOutTr(this);" onclick="javascript:queryWebserviceBrood({@id});">'+
	'		<td><xsl:value-of select="./@id"/></td>'+
    '  		<td><xsl:value-of select="./CrossType"/></td>'+	
    '  		<td><xsl:value-of select="./Father/ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./Mother/ScientificName/Simple"/></td>'+
    '  		<td><xsl:value-of select="./NumberOfEggs"/></td>'+
    '   	<td><xsl:value-of select="./NumberOfAdults"/></td>'+
 	'   </tr>'+
	'</xsl:if>' +
	'</xsl:for-each>'+
	'</table>';
	
var brood_xsl=
	'<?xml version="1.0" encoding="UTF-8"?>' +
	'<h3>Brood</h3>' + 
	'<table cellpadding="0" cellspacing="0" border="0" width="500">' +
	'<tr>'+
	'<td align="left"><div id="mother_box">Maternal Grand Mother <br/>ID: <xsl:value-of  select="BroodResponse/Brood/MotherBrood/Mother/@id" /><br/><xsl:value-of  select="BroodResponse/Brood/MotherBrood/Mother/ScientificName/Simple" /></div>	'+
	'<br /><div id="father_box">Maternal Grand Father<br/>ID: <xsl:value-of  select="BroodResponse/Brood/MotherBrood/Father/@id" /><br/><xsl:value-of  select="BroodResponse/Brood/MotherBrood/Father/ScientificName/Simple" /></div></td>' +
	'<td align="right"><div id="mother_box">Paternal Grand Mother<br/>ID: <xsl:value-of  select="BroodResponse/Brood/FatherBrood/Mother/@id" /><br/><xsl:value-of  select="BroodResponse/Brood/FatherBrood/Mother/ScientificName/Simple" /></div>	'+
	'<br /><div id="father_box">Paternal Grand Father<br/>ID: <xsl:value-of  select="BroodResponse/Brood/FatherBrood/Father/@id" /><br/><xsl:value-of  select="BroodResponse/Brood/FatherBrood/Father/ScientificName/Simple" /></div></td>' +
	'</tr>'+
	'</table>' +
	'<br/><br/>' +
	'<table cellpadding=0 cellspacing=0 border=0 width="500">' +
	'<tr>'+
	'<td align="center" width="100%">' +
	'<table width="100%">' +
	'<td align="right" width="20%">' +
	'<img src="images/right_point.jpg" /></td>' +
	'<td align="right" width="30%">' +
	'<div id="mother_box">Mother<br/>ID: <xsl:value-of  select="BroodResponse/Brood/Mother/@id" /><br/><xsl:value-of  select="BroodResponse/Brood/Mother/ScientificName/Simple" /></div>	'+
	'</td>' +
	'<td align="left" width="30%">' +
	'<div id="father_box">Father<br/>ID: <xsl:value-of  select="BroodResponse/Brood/Father/@id" /><br/><xsl:value-of  select="BroodResponse/Brood/Father/ScientificName/Simple" /></div>' +
	'</td>' +
	'<td align="left" width="20%">' +
	'<img src="images/left_point.jpg" /></td>' +
	'</tr>' +
	'<table>' +
	'</td>' +
	'</tr>'+
	'</table>' +
	'<br/><br/>' +
	'<table cellpadding=0 cellspacing=0 border=0 width="500">' +
	'<tr>'+
	'<td align="center" width="100%"><img src="images/down_point.jpg" /></td>'+
	'</tr>'+
	'<tr>'+
	'<td align="center" width="100%">Offspring</td>'+
	'</tr>'+
	'<tr>'+
	'<td align="center" width="80%"><hr/></td>'+
	'</tr>'+
	'</table>' +
	'<br/><br/>' +
	'<xsl:for-each select="BroodResponse/Brood/Offsprings/Individual">' +
	
	'<div id="offspring_box">'+
	'ID: <xsl:value-of select="./@id"/><br/>'+
	'Name: <xsl:value-of select="./ScientificName/Simple"/><br/>'+
	'</div><br/>'+
	'</xsl:for-each>'+
	'</table>';