const formSteps = document.querySelectorAll('.step');
const nextBtns = document.querySelectorAll('#nextBtn');
const backBtns = document.querySelectorAll('.backBtn'); // 이전 버튼들 선택
const calculateBtn = document.getElementById('calculate');

let currentStep = 0;

hideAllSteps();
showCurrentStep();

nextBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        currentStep++;
        if (currentStep < formSteps.length) {
            hideAllSteps();
            showCurrentStep();
        }
    });
});

// "이전" 버튼 클릭 이벤트 핸들러
backBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        currentStep--;
        if (currentStep >= 0) {
            hideAllSteps();
            showCurrentStep();
        }
    });
});

calculateBtn.addEventListener('click', () => {
    currentStep++;
    if (currentStep < formSteps.length) {
        hideAllSteps();
        showCurrentStep();
    }
});

function showCurrentStep() {
    formSteps[currentStep].style.display = 'block';
}

function hideAllSteps() {
    formSteps.forEach(step => {
        step.style.display = 'none';
    });
}
