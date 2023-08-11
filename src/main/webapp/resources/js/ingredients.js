// 선호하는 재료 선택 관련 처리
const preferredIngredientButtons = document.querySelectorAll('.store-container1 .preferred');
const preferredSelectedList = document.getElementById('preferred-selected-list');
const maxPreferredSelection = 3;
let preferredSelectedIngredients = [];

preferredIngredientButtons.forEach(button => {
    button.addEventListener('click', () => {
        const ingredient = button.textContent;

        if (preferredSelectedIngredients.includes(ingredient)) {
            preferredSelectedIngredients = preferredSelectedIngredients.filter(item => item !== ingredient);
            button.classList.remove('selected');
        } else if (preferredSelectedIngredients.length < maxPreferredSelection) {
            preferredSelectedIngredients.push(ingredient);
            button.classList.add('selected');
        } else {
            alert(`최대 ${maxPreferredSelection}개까지 선택 가능합니다.`);
        }

        updateSelectedList(preferredSelectedList, preferredSelectedIngredients);
    });
});

// 선호하지 않는 재료 선택 관련 처리
const unpreferredIngredientButtons = document.querySelectorAll('.store-container2 .unpreferred');
const unpreferredSelectedList = document.getElementById('unpreferred-selected-list');
const maxUnpreferredSelection = 3;
let unpreferredSelectedIngredients = [];

unpreferredIngredientButtons.forEach(button => {
    button.addEventListener('click', () => {
        const ingredient = button.textContent;

        if (unpreferredSelectedIngredients.includes(ingredient)) {
            unpreferredSelectedIngredients = unpreferredSelectedIngredients.filter(item => item !== ingredient);
            button.classList.remove('selected');
        } else if (unpreferredSelectedIngredients.length < maxUnpreferredSelection) {
            unpreferredSelectedIngredients.push(ingredient);
            button.classList.add('selected');
        } else {
            alert(`최대 ${maxUnpreferredSelection}개까지 선택 가능합니다.`);
        }

        updateSelectedList(unpreferredSelectedList, unpreferredSelectedIngredients);
    });
});

function updateSelectedList(selectedList, selectedIngredients) {
    selectedList.innerHTML = '';
    selectedIngredients.forEach(ingredient => {
        const li = document.createElement('li');
        li.textContent = ingredient;
        selectedList.appendChild(li);
    });
}

function resetPreferredIngredients() {
    preferredSelectedIngredients = [];
    preferredIngredientButtons.forEach(button => {
        button.classList.remove('selected');
    });
    updateSelectedList(preferredSelectedList, preferredSelectedIngredients);
}

function resetUnpreferredIngredients() {
    unpreferredSelectedIngredients = [];
    unpreferredIngredientButtons.forEach(button => {
        button.classList.remove('selected');
    });
    updateSelectedList(unpreferredSelectedList, unpreferredSelectedIngredients);
}
