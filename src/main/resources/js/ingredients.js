document.addEventListener("DOMContentLoaded", function() {
    const preferDropdown = document.getElementById("prefer");
    const dislikeDropdown = document.getElementById("dislike");
    const selectedIngredientsList = document.getElementById("selectedIngredientsList");
    const selectedUnIngredientsList = document.getElementById("selectedUnIngredientsList");

    function createListItem(text, list) {
        const newListItem = document.createElement("li");
        newListItem.textContent = text;

        const removeButton = document.createElement("button");
        removeButton.textContent = "X";
        removeButton.classList.add("remove-button");
        removeButton.addEventListener("click", function() {
            list.removeChild(newListItem);
        });

        newListItem.appendChild(removeButton);
        list.appendChild(newListItem);
    }

    preferDropdown.addEventListener("change", function() {
        const selectedOption = preferDropdown.options[preferDropdown.selectedIndex];
        if (selectedOption.value !== "") {
            const ingredientName = selectedOption.text;
            createListItem(ingredientName, selectedIngredientsList);
        }
    });

    dislikeDropdown.addEventListener("change", function() {
        const selectedOption = dislikeDropdown.options[dislikeDropdown.selectedIndex];
        if (selectedOption.value !== "") {
            const ingredientName = selectedOption.text;
            createListItem(ingredientName, selectedUnIngredientsList);
        }
    });
});

























// // 선호하는 재료 선택 관련 처리
// const preferredIngredientButtons = document.querySelectorAll('.store-container1 .preferred');
// const preferredSelectedList = document.getElementById('selectedIngredientId'); // 변경된 ID 사용
// const maxPreferredSelection = 3;
// let preferredSelectedIngredients = [];
//
// preferredIngredientButtons.forEach(button => {
//     button.addEventListener('click', () => {
//         const ingredient = button.textContent;
//
//         if (preferredSelectedIngredients.includes(ingredient)) {
//             preferredSelectedIngredients = preferredSelectedIngredients.filter(item => item !== ingredient);
//             button.classList.remove('selected');
//         } else if (preferredSelectedIngredients.length < maxPreferredSelection) {
//             preferredSelectedIngredients.push(ingredient);
//             button.classList.add('selected');
//         } else {
//             alert(`최대 ${maxPreferredSelection}개까지 선택 가능합니다.`);
//         }
//
//         updateSelectedList(preferredSelectedList, preferredSelectedIngredients);
//     });
// });
//
// // 데이터 전송
// function sendDataToServer(data) {
//     fetch('http://localhost:8081/savePreferredIngredients', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(data),
//     })
//         .then(response => response.text())
//         .then(result => {
//             console.log('Data sent successfully.', result);
//         })
//         .catch(error => {
//             console.error('Failed to send data.', error);
//         });
// }
//
// // 사용자 ID와 재료 ID 목록 전송
// var userId = '<%= session.getAttribute("log") %>';
// var selectedIngrIds = [/* populate with selected ingredient IDs */];
//
// var data = {
//     user_id: userId,
//     ingr_ids: selectedIngrIds,
// };
//
// sendDataToServer(data); // 서버로 데이터 전송
//
//
// // 사용자 세션에서 user_id 가져오는 함수
// // 세션에서 user_id 값을 가져오는 함수
// function getUserIDFromSession() {
//     // 세션에서 user_id 값을 추출하는 방법에 따라서 구현 (예: 쿠키, 로컬 스토리지, API 호출 등)
//     // 아래는 예시로 쿠키에서 user_id 값을 가져오는 코드입니다.
//     var cookies = document.cookie.split(';');
//     for (var i = 0; i < cookies.length; i++) {
//         var cookie = cookies[i].trim();
//         if (cookie.startsWith('user_id=')) {
//             return cookie.substring('user_id='.length, cookie.length);
//         }
//     }
//     return null;
// }
//
// // 선호 재료 저장 버튼 클릭 처리
// function savePreferredIngredient() {
//
//     // 선택된 재료 버튼 엘리먼트를 가져옴
//     const selectedIngredientsButtons = document.querySelectorAll('.store-container1 .preferred.selected');
//
//     // 선택된 재료명을 추출하여 배열로 저장
//     const selectedIngredients = Array.from(selectedIngredientsButtons).map(button => button.textContent);
//
//     // 선택된 재료명으로 ingr_id를 찾아내는 로직을 구현
//     const selectedIngrIds = selectedIngredients.map(ingrName => {
//         // ingrNames 배열에서 ingrName과 일치하는 항목 찾기
//         const selectedIngr = ingrNames.find(item => item === ingrName);
//         if (selectedIngr) {
//             // ingrNames 배열의 인덱스를 이용하여 ingrIds 배열에서 인그리드 찾기
//             return ingrIds[ingrNames.indexOf(selectedIngr)];
//         }
//     });
//
//     var userId = getUserIDFromSession();
//
//     // 기타 로직 및 데이터 생성
//     var data = {
//         user_id: userId,
//         ingr_ids: [null]  // 여기에 실제 데이터 추가
//     };
//
//     console.log({userId});
//     console.log({selectedIngrIds});
//
//     // 서버로 데이터 전송
//     sendDataToServer(data);
// }
//
//
// // 선호하지 않는 재료 선택 관련 처리
// const unpreferredIngredientButtons = document.querySelectorAll('.store-container2 .unpreferred');
// const unpreferredSelectedList = document.getElementById('unpreferred-selected-list');
// const maxUnpreferredSelection = 3;
// let unpreferredSelectedIngredients = [];
//
// unpreferredIngredientButtons.forEach(button => {
//     button.addEventListener('click', () => {
//         const ingredient = button.textContent;
//
//         if (unpreferredSelectedIngredients.includes(ingredient)) {
//             unpreferredSelectedIngredients = unpreferredSelectedIngredients.filter(item => item !== ingredient);
//             button.classList.remove('selected');
//         } else if (unpreferredSelectedIngredients.length < maxUnpreferredSelection) {
//             unpreferredSelectedIngredients.push(ingredient);
//             button.classList.add('selected');
//         } else {
//             alert(`최대 ${maxUnpreferredSelection}개까지 선택 가능합니다.`);
//         }
//
//         updateSelectedList(unpreferredSelectedList, unpreferredSelectedIngredients);
//     });
// });
//
// function updateSelectedList(selectedList, selectedIngredients) {
//     selectedList.innerHTML = '';
//     selectedIngredients.forEach(ingredient => {
//         const li = document.createElement('li');
//         li.textContent = ingredient;
//         selectedList.appendChild(li);
//     });
// }
//
//
// function resetPreferredIngredients() {
//     preferredSelectedIngredients = [];
//     preferredIngredientButtons.forEach(button => {
//         button.classList.remove('selected');
//     });
//     updateSelectedList(preferredSelectedList, preferredSelectedIngredients);
// }
//
// function resetUnpreferredIngredients() {
//     unpreferredSelectedIngredients = [];
//     unpreferredIngredientButtons.forEach(button => {
//         button.classList.remove('selected');
//     });
//     updateSelectedList(unpreferredSelectedList, unpreferredSelectedIngredients);
// }
//
//
// function findIngredientIdByName(ingredientName) {
//     // 재료 이름을 기반으로 해당 재료의 ID를 찾는 로직을 구현해야 합니다.
//     // ingrNames 배열에서 ingredientName과 일치하는 객체를 찾고 그 객체의 ingrId를 반환할 수 있습니다.
//     const selectedIngr = ingrNames.find(item => item.ingrName === ingredientName);
//     return selectedIngr ? selectedIngr.ingrId : null;
// }
//
//
//
//
//
//
//
// // 카테고리 버튼 클릭 처리
// const categoryButtons = document.querySelectorAll('.category button');
//
// categoryButtons.forEach(button => {
//     button.addEventListener('click', () => {
//         if (button.classList.contains('selected')) {
//             // 버튼이 이미 선택된 상태일 경우, 배경색을 없애고 선택 해제
//             button.classList.remove('selected');
//
//             // 여기에 선택된 카테고리에 따른 추가 동작을 구현하세요.
//             // 예: 선택한 카테고리에 해당하는 내용 숨기기 또는 기타 처리
//         } else {
//             // 다른 버튼들의 배경색을 초기화
//             categoryButtons.forEach(btn => {
//                 btn.classList.remove('selected');
//             });
//
//             // 선택한 버튼의 배경색을 변경
//             button.classList.add('selected');
//
//             // 여기에 선택된 카테고리에 따른 추가 동작을 구현하세요.
//             // 예: 선택한 카테고리에 해당하는 내용 표시 또는 기타 처리
//         }
//     });
// });
//
// document.addEventListener('DOMContentLoaded', () => {
//     const savePreferredButton = document.querySelector('.save_prefer');
//
//     savePreferredButton.addEventListener('click', () => {
//         if (preferredSelectedIngredients.length === 0) {
//             alert('선호하는 재료를 선택해주세요.');
//             return;
//         }
//
//         const userId = getUserIDFromSession();
//         const selectedIngrIds = preferredSelectedIngredients.map(ingrName => findIngredientIdByName(ingrName));
//
//         const data = {
//             user_id: userId,
//             ingr_ids: selectedIngrIds,
//         };
//
//         sendDataToServer(data);
//
//         alert('선호하는 재료가 저장되었습니다.');
//     });
// });
//
//
//
//
