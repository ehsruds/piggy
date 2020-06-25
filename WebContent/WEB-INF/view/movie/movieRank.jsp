<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(window).on("load", function() {

		//페이지 로딩 완료 후, 순위가져오기 함수 실행함 
		getRank();
	});

	//순위가져오기
	function getRank() {

		//Ajax 호출
		$.ajax({
			url : "/movie/getRank.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {

				var movie_rank = "";

				for (var i = 0; i < json.length; i++) {
					movie_rank += (json[i].img + "위 | ");
					movie_rank += (json[i].title + " | ");
					movie_rank += (json[i].m_url + " | ");

				}

				$('#movie_rank').html(movie_rank);
			}
		})

	}
</script>
</head>
<body>
	<h1>영화 순위</h1>
	<hr />
	<div id="movie_rank"></div>
	<br />
	<hr />
</body>
</html>


