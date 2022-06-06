let target;

$(document).ready(function () {

    $('#comment-close').on('click', function () {
        $('#comment-container').removeClass('active');
    })


    $('#content-close').on('click', function (){
        $('#content-container').removeClass('active');
    })

    $('.nav div.nav-see').on('click', function () {
        $('div.nav-see').addClass('active');
        $('div.nav-search').removeClass('active');

        $('#see-area').show();
        $('#search-area').hide();
    })
    $('.nav div.nav-search').on('click', function () {
        $('div.nav-see').removeClass('active');
        $('div.nav-search').addClass('active');

        $('#see-area').hide();
        $('#search-area').show();
    })

    $('#see-area').show();
    $('#search-area').hide();

    if ($('#admin').length === 1) {
        showProduct(true);
    } else {
        showProduct();
    }

    $('#comments').hide();
})

function showProduct() {
    $.ajax({
        type: 'GET',
        url: `/api/posts`,
        success: function (response) {
            // $('#post-container').empty();
            console.log(response);
            for (let i = 0; i < response.length; i++) {
                let post = response[i];
                let tempHtml = addPost(post);
                $('#post-container').append(tempHtml);
                $(`#${post.id}-editarea`).hide();
            }
        }
    })
}

function addPost(post) {
    let username = post.username;
    let date = new Date(post.modifiedAt)
    let time = time2str(date)

    return `<div class="card">
            <header class="card-header">
                <p class="card-header-title">
                    ${post.title}
                </p>
                <span>by ${post.username}</span>
            </header>
            <div class="card-content">
                <div class="${post.id}-content">
                    ${post.content}
                    <br>
                    <time style="float: right" datetime="2016-1-1">${time}</time>
                </div>
            </div>
            <footer class="card-footer">
                <a href="#" id="comment-btn" class="card-footer-item" onclick="openComment(${post.id});">Comment</a>
                <a href="#" class="card-footer-item" onclick="updatePost(${post.id})">Edit</a>
                <a href="#" class="card-footer-item" onclick="deletePost(${post.id})">Delete</a>
            </footer>
        </div>`;
}

function time2str(date) {
    let today = new Date()
    let time = (today - date) / 1000 / 60  // 분

    if (time < 60) {
        return parseInt(time) + "분 전"
    }
    time = time / 60  // 시간
    if (time < 24) {
        return parseInt(time) + "시간 전"
    }
    time = time / 24
    if (time < 7) {
        return parseInt(time) + "일 전"
    }
    return `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일`
}

function openComment(id) {
    $('#nav').hide();
    $('#post-container').empty();
    fetch(`/api/post/${id}`, {
        method: "GET"
    }).then((res) => res.json())
        .then((res) => {
            let post = res;
            let tempHtml = addPost(post);
            $('#post-container').append(tempHtml);
            $('#comment-btn').hide();
            $('#comments').show();
        })

    target = id;
}
function updatePost(id){
    $('#content-container').addClass('active');
    $('#content-id').val(id);
}

function processUpdate(){
    let id = $('#content-id').val();
    let content = $("#update-content").val();
    let req = {
        content : content
    }

    fetch(`/api/post/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(req),
    })
        .then((res) => res.json())     // then은 서버에서 응답한 데이터
        .then((res) => {
            console.log(res);
            window.location.reload();
        })
        .catch((err) => {
            console.error(err);
        })
}

function deletePost(id){

    fetch(`/api/post/${id}`,{
        method : "DELETE"
    }).then(response=>response.json())
        .then(data => {
            window.location.reload();
        })
        .catch(error=>console.log(error));
}

function sendPost() {
    let title = $('#title').val();
    let content = $('#content').val();

    const req = {
        title: title,
        content: content
    }

    fetch("/api/post", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(req),
    })
        .then((res) => res.json())     // then은 서버에서 응답한 데이터
        .then((res) => {
            console.log(res);
            window.location.reload();
        })
        .catch((err) => {
            console.error(err);
        })
}

function setComment() {
    let content = $('#comment-input').val();
    if (content === '') {
        alert('댓글을 입력해주세요');
        return;
    }
    let username = $('#username').text();

    let req = {
        id : target,
        username : username,
        content : content
    }


    fetch("/api/post", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(req),
    })
        .then((res) => res.json())     // then은 서버에서 응답한 데이터
        .then((res) => {
            console.log(res);
            window.location.reload();
        })
        .catch((err) => {
            console.error(err);
        })
}