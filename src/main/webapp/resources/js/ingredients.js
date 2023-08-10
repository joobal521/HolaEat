// 선호하는 재료와 선호하지 않는 재료 관련 함수
const preferredSelectedIngredients = [];
const unpreferredSelectedIngredients = [];

function setupIngredientSelection(btnContainers, ingredientsContainers, selectedIngredients, updateFunction, oppositeSelectedIngredients) {
    btnContainers.forEach((btnContainer, index) => {
        const showIngredientsBtn = btnContainer.querySelector('button');
        const ingredientsContainer = ingredientsContainers[index];

        showIngredientsBtn.addEventListener('click', () => {
            ingredientsContainer.style.display = 'block'; // 보이도록 변경
        });

        const selectedList = ingredientsContainer.querySelector('.selected-ingredients ul');

        const ingredientButtons = ingredientsContainer.querySelectorAll('.store-item button');

        ingredientButtons.forEach(button => {
            button.addEventListener('click', () => {
                const ingredient = button.textContent;

                if (selectedIngredients.length < 3 || selectedIngredients.includes(ingredient)) {
                    if (!selectedIngredients.includes(ingredient)) {
                        selectedIngredients.push(ingredient);
                        button.classList.add('selected'); // 배경색 변경

                        // 선호하지 않는 재료 목록에서 해당 재료 삭제 및 숨기기
                        const oppositeButton = findOppositeButton(ingredient);
                        if (oppositeButton) {
                            oppositeSelectedIngredients.splice(oppositeSelectedIngredients.indexOf(ingredient), 1);
                            oppositeButton.classList.remove('selected'); // 배경색 원래대로
                            oppositeButton.parentElement.style.display = 'none'; // 부모 요소를 숨기기
                            updateUnpreferredSelectedList();
                        }
                    } else {
                        const index = selectedIngredients.indexOf(ingredient);
                        if (index !== -1) {
                            selectedIngredients.splice(index, 1);
                            button.classList.remove('selected'); // 배경색 원래대로

                            // 선호하지 않는 재료 목록에서 해당 재료 보이게 하기
                            const oppositeButton = findOppositeButton(ingredient);
                            if (oppositeButton) {
                                oppositeButton.parentElement.style.display = 'flex'; // 부모 요소를 보이도록 변경
                                updateUnpreferredSelectedList();
                            }
                        }
                    }

                    updateFunction();
                } else {
                    alert('최대 3개까지만 선택할 수 있습니다.');
                }
            });
        });
    });
}

// 선택한 재료에 해당하는 선호하지 않는 버튼을 찾는 함수
function findOppositeButton(ingredient) {
    const unpreferredButtons = document.querySelectorAll('.unpreferred');
    for (const button of unpreferredButtons) {
        if (button.textContent === ingredient) {
            return button;
        }
    }
    return null;
}

// 나머지 코드는 동일합니다.



// 첫 번째 선호하는 재료 목록과 관련된 코드
const preferredBtnContainers = document.querySelectorAll('.ingredients .btn-container1');
const preferredIngredientsContainers = document.querySelectorAll('.ingredients .store-container1');

setupIngredientSelection(
    preferredBtnContainers,
    preferredIngredientsContainers,
    preferredSelectedIngredients,
    updatePreferredSelectedList,
    unpreferredSelectedIngredients // 선호하지 않는 재료 목록의 선택된 재료 배열
);

// 두 번째 선호하지 않는 재료 목록과 관련된 코드
const unpreferredBtnContainers = document.querySelectorAll('.ingredients .btn-container2');
const unpreferredIngredientsContainers = document.querySelectorAll('.ingredients .store-container2');

setupIngredientSelection(
    unpreferredBtnContainers,
    unpreferredIngredientsContainers,
    unpreferredSelectedIngredients,
    updateUnpreferredSelectedList,
    preferredSelectedIngredients // 선호하는 재료 목록의 선택된 재료 배열
);

function removeFromOtherSelectedIngredients(ingredient) {
    const otherSelectedIngredients = preferredSelectedIngredients === selectedIngredients
        ? unpreferredSelectedIngredients
        : preferredSelectedIngredients;

    const index = otherSelectedIngredients.indexOf(ingredient);
    if (index !== -1) {
        otherSelectedIngredients.splice(index, 1);
        updateFunction();
    }
}


// 함수: 재료 선택 목록 업데이트
function updatePreferredSelectedList() {
    const preferredSelectedList = document.querySelector('.ingredients .store-container1 + .selected-ingredients ul');
    updateSelectedList(preferredSelectedList, preferredSelectedIngredients);
}

function updateUnpreferredSelectedList() {
    const unpreferredSelectedList = document.querySelector('.ingredients .store-container2 + .selected-ingredients ul');
    updateSelectedList(unpreferredSelectedList, unpreferredSelectedIngredients);
}

function updateSelectedList(listElement, selectedIngredients) {
    listElement.innerHTML = ''; // 기존 목록 초기화

    selectedIngredients.forEach(ingredient => {
        const listItem = document.createElement('li');
        listItem.textContent = ingredient;
        listElement.appendChild(listItem);
    });
}
