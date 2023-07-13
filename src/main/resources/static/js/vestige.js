function sendit(){
    const name = document.getElementById('name');
    const email = document.getElementById('email');
    const phone = document.getElementById('phone');
    // const content = document.getElementById('content');

    //정규식
    const expNameText = /^[가-힣a-zA-Z]+$/;
    const expPhoneText = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    const expEmailText = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    // const expContentText = /^[a-zA-Z0-9ㄱ-힣]+$/;

    if(!expNameText.test(name.value)){
        alert('이름 형식을 확인하세요\n한글,영어만 입력가능합니다');
        name.focus();
        return false;
    }

    if(!expEmailText.test(email.value)){
        alert('이메일 형식을 확인해주세요');
        email.focus();
        return false;
    }

    if(!expPhoneText.test(phone.value)){
        alert('"-" 하이픈 넣어서 작성해주세요')
        phone.focus();
        return false;
    }

    return true;
}
// if(!expContentText.test(content.value)){
//     alert('내용을 입력해주세요')
//     content.focus();
//     return false;
// }
