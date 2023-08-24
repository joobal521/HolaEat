var currentPage = 0;

function removeReview(element) {
    var reviewNo = element.parentNode.parentNode.id;

    $.ajax({
        url: "adminReview/delete/" + reviewNo,
        method: "DELETE",
        success: function (response) {
            // Handle success if needed
            alert("삭제하기: " + reviewNo);
        },
        error: function () {
            alert("삭제에 실패했습니다.");
        }
    });
}

function loadReviews(page) {
    $.ajax({
        url: "adminReviewList?page=" + page,
        success: function (response) {
            console.log(response);
            $(".admin-reviewList").empty();
            response['data'].forEach(review => {
                $(".admin-reviewList").append(
                    `<tr>
                        <td>${review.reviewNo}</td>
                        <td class="reviewTitle">${review.title}</td>
                        <td class="reviewUserId">${review.userId}</td>
                        <td class="reviewContent">${review.content}</td>
                        <td class="reviewContent">${review.reviewLike}</td>
                        <td>
                            <button class="removeBtn" data-id="${review.reviewNo}">삭제하기</button>
                        </td>
                    </tr>`
                );
            });
            currentPage = response.page;
        },
        error: function () {
            alert("로드에 실패했습니다.");
        }
    });
}

function preBtn() {
    if (currentPage > 0) {
        currentPage--;
        console.log(currentPage);
        loadReviews(currentPage);
    }
}

function nextBtn() {
    currentPage++;
    console.log(currentPage);
    loadReviews(currentPage);
}