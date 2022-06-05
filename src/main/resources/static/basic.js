let target;

$(document).ready(function () {
    // id 가 query 인 녀석 위에서 엔터를 누르면 execSearch() 함수를 실행하라는 뜻입니다.
    $('#close').on('click', function () {
        $('#container').removeClass('active');
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
            }
        }
    })
}

function addPost(post) {
    return `<div class="card">
            <header class="card-header">
                <p class="card-header-title">
                    ${post.title}
                </p>
            </header>
            <div class="card-content">
                <div class="content">
                    ${post.content}
                    <span>${post.userid}</span>
                    <br>
                    <time datetime="2016-1-1">${post.createdate}</time>
                </div>
            </div>
            <footer class="card-footer">
                <a href="#" class="card-footer-item" onclick="openComment(${post.id});">Comment</a>
                <a href="#" class="card-footer-item">Edit</a>
                <a href="#" class="card-footer-item">Delete</a>
            </footer>
        </div>`;
}

function addHTML(itemDto) {
    return `<div class="search-itemDto">
                <div class="search-itemDto-left">
                    <img src="${itemDto.image}" alt="">
                </div>
                <div class="search-itemDto-center">
                    <div>${itemDto.title}</div>
                    <div class="price">
                        ${numberWithCommas(itemDto.lprice)}
                        <span class="unit">원</span>
                    </div>
                </div>
                <div class="search-itemDto-right">
                    <img src="images/icon-save.png" alt="" onclick='addProduct(${JSON.stringify(itemDto)})'>
                </div>
            </div>`
}

function openComment(cidx) {
    $('#container').addClass('active');
    target = cidx;
}

function sendPost(){
    let title = $('#title').val();
    let content = $('#content').val();

    const req = {
        title : title,
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

function setComment() {

    let comment = $('#comment').val();
    if (comment === '') {
        alert('댓글을 입력해주세요');
        return;
    }
    $.ajax({
        type: "PUT",
        url: `/api/products/${targetId}`,
        contentType: "application/json",
        data: JSON.stringify({myprice: myprice}),
        success: function (response) {
            $('#container').removeClass('active');
            alert('성공적으로 등록되었습니다.');
            window.location.reload();
        }
    })
}