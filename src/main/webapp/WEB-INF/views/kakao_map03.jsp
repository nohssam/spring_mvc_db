<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=XXXXXXXXXXX"></script>
<script type="text/javascript">
	navigator.geolocation.getCurrentPosition(function(position) {
		var lat = position.coords.latitude;   // 위도
		var lon = position.coords.longitude;  // 경도
		
	    document.getElementById("lat").innerHTML = lat;
	    document.getElementById("lon").innerHTML = lon;
	    geo_map(lat,lon);
	});
</script>
</head>
<body>
<h1> 카카오 지도(내 위치 : 마커, 인포윈도우) </h1>

<h3> 위도 : <span id="lat"></span>,  경도 : <span id="lon"></span> </h3>
<!-- 지도를 표시할 div 입니다 -->
<div id="map" style="width:800px;height:350px;"></div>

<script>
	function geo_map(lat, lon) {
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
		
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(lat, lon); 

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		var iwContent = '<div style="padding:5px;"><a href="http://ictedu.co.kr" target="_blank">한국ICT 인재개발원</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		    iwPosition = new kakao.maps.LatLng(lat, lon); //인포윈도우 표시 위치입니다

		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
		    position : iwPosition, 
		    content : iwContent 
		});
		  
		// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
		infowindow.open(map, marker); 
		
		
		
		
		
		
	}
</script>
</body>
</html>















