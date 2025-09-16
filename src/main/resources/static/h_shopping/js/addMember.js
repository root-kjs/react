const addMember = async () => {
    const memberName = document.querySelector(".memberName").value;
    const memberPhone = document.querySelector(".memberPhone").value;
    const memberAddress = document.querySelector(".memberAddress").value;
    const memberGrade = document.querySelector(".memberGrade").value;
    const memberCity = document.querySelector(".memberCity").value;

    if (!memberName || !memberPhone || !memberAddress || !memberGrade || !memberCity) {
        alert("모든 필드를 입력해주세요.");
        return;
    }

    const memberData = {
        memberName,
        memberPhone,
        memberAddress,
        memberGrade,
        memberCity
    };

    fetch('/member/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(memberData)
    })
    .then(response => {
        if (response.ok) {
            alert("회원이 성공적으로 등록되었습니다.");
            window.location.href = '/h_shopping/member/list.jsp';
        } else {
            return response.json().then(data => {
                throw new Error(data.message || "회원 등록에 실패했습니다.");
            });
        }
    })
    .catch(error => {
        alert(`오류가 발생했습니다: ${error.message}`);
    });
}