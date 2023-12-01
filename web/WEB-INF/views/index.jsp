<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Personal - Start Bootstrap Theme</title>
    <!-- Favicon-->
    <link rel="icon" type="/image/x-icon" href="${pageContext.request.contextPath}assets/favicon.ico" />
    <!-- Custom Google font-->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@100;200;300;400;500;600;700;800;900&amp;display=swap" rel="stylesheet" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <style>
        #coordination-position {
            position: absolute;
            left: 0;
            display: none;
            padding: 5px;
            background-color: #000;
            color: #fff;
            font-size: 12px; }
        #login-form{
            display: inline-block;
        }

        h1{
            margin-bottom: 5px;
        }
    </style>
</head>
<body class="d-flex flex-column h-100" style="margin:20px">

<h1>hello, this is image coordinates system</h1>
<div>
    <form class="m-3" id="login-form" action = "user/login" method="post" style="margin-bottom: 10px">
        <input class="form-control mb-3" name="username" id="username" type="text"
               placeholder="아이디를 입력해주세요"
               aria-label="Search">
        <input class="form-control mb-3" name="password" id="password" type="text"
               placeholder="비밀번호를 입력해주세요"
               aria-label="Search">
        <input class="btn btn-primary h-75" type="submit" value="전송">
    </form><button id="register-button">가입</button>

    <form class="m-3" id="register-form" action = "user/register" method="post" style="display: none; margin-bottom: 10px">
        <input class="form-control mb-3" name="username" id="register-id" type="search"
               placeholder="가입할 아이디를 입력해주세요"
               aria-label="Search">
        <input class="form-control mb-3" name="password" id="register-password" type="search"
               placeholder="가입할 비밀번호를 입력해주세요"
               aria-label="Search">
        <input class="form-control mb-3" name="name" id="register-name" type="search"
               placeholder="가입할 이름을 입력해주세요"
               aria-label="Search">
        <input class="form-control mb-3" name="nickname" id="register-nickname" type="search"
               placeholder="가입할 닉네임을 입력해주세요"
               aria-label="Search">
        <input class="btn btn-primary h-75" type="submit" value="전송">
    </form>
</div>


<div class="container">
    <div class="image-upload" id="image-upload">

        <form method="post" enctype="multipart/form-data">
            <div class="button">
                <label for="chooseFile">
                    👉 CLICK HERE! 👈
                </label>
            </div>
            <input type="file" id="chooseFile" name="chooseFile" accept="image/*" onchange="loadFile(this)">
        </form>

        <div class="fileContainer">
            <div class="fileInput">
                <p>FILE NAME: </p>
                <p id="fileName"></p>
            </div>
            <div class="buttonContainer">
                <div class="submitButton" id="submitButton">SUBMIT</div>
            </div>
        </div>
    </div>
    <h3 id="coordination-position">here is coordination position!</h3>
    <div class="image-show" id="image-show"></div>
</div>
<script lang="javascript">
    function loadFile(input) {
        let file = input.files[0];	//선택된 파일 가져오기

        //미리 만들어 놓은 div에 text(파일 이름) 추가
        let name = document.getElementById('fileName');
        name.textContent = file.name;

        //새로운 이미지 div 추가
        let newImage = document.createElement("img");
        newImage.setAttribute("class", 'img');

        //이미지 source 가져오기
        newImage.src = URL.createObjectURL(file);

        newImage.style.width = "70%";
        newImage.style.height = "70%";
        newImage.style.visibility = "hidden";   //버튼을 누르기 전까지는 이미지를 숨긴다
        newImage.style.objectFit = "contain";

        //이미지를 image-show div에 추가
        let container = document.getElementById('image-show');
        container.appendChild(newImage);

        let submit = document.getElementById('submitButton');
        submit.onclick = showImage;     //Submit 버튼 클릭시 이미지 보여주기

        function showImage() {
            let newImage = document.getElementById('image-show').lastElementChild;
            const coordinate_position = document.querySelector('#coordination-position');

            //이미지는 화면에 나타나고
            newImage.style.visibility = "visible";
            newImage.addEventListener('click', (e)=>{
                coordinate_position.style.display = 'block';
                coordinate_position.style.left = e.clientX + 'px';
                coordinate_position.style.top = e.clientY - coordinate_position.offsetHeight - 10 + 'px';
                coordinate_position.textContent = "x: {"+e.offsetX +"} y: {"+ e.offsetY+"}";
            })


            //이미지 업로드 버튼은 숨겨진다
            document.getElementById('image-upload').style.visibility = 'hidden';

            document.getElementById('fileName').textContent = null;     //기존 파일 이름 지우기
        }
    }

    $(document).ready(function(){
        $('#register-button').click(function(){
            $("#login-form").css('display', 'none')
            $("#register-button").css('display', 'none')
            $("#register-form").show();
        });
    });
</script>
</body>
</html>
