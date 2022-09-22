let eVideo = document.getElementById('video-1');
let eVideo2 = document.getElementById('video-2');
let eVideo3 = document.getElementById('video-3');

let ePlayVideo = document.getElementById('play');

eVideo.addEventListener('click',function () {
    let genderHtml = '<iframe width="711" height="400" src="https://www.youtube.com/embed/RSwgdGr_sAw" title="[S6] Doraemon Tập 300 - Trải Nghiệm Khách Sạn Thời Kỳ Đồ Đá - Tiếng Việt" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>';
    ePlayVideo.innerHTML = genderHtml;
});

eVideo2.addEventListener('click',function () {
    let genderHtml = '<iframe width="711" height="400" src="https://www.youtube.com/embed/KCSkDlGKqYs" title="[S9] Doraemon - Tập 447 - Giày Khiêu Vũ - Nàng Chồn Thích Doraemon - Hoạt Hình Tiếng Việt" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>';
    ePlayVideo.innerHTML = genderHtml;
});
eVideo3.addEventListener('click',function () {
    let genderHtml = '<iframe width="711" height="400" src="https://www.youtube.com/embed/Ccc_gcftpDI" title="[S9] Doraemon - Tập 445 - Kẹo Dạy Bảo - Kèn Xua Đuổi - Hoạt Hình Tiếng Việt" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>';
    ePlayVideo.innerHTML = genderHtml;
});
