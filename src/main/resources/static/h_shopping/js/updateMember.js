const updateMember = async () => {
    const memberId = document.querySelector(".memberId").value;
    const name = document.querySelector(".name").value;
    const phone = document.querySelector(".phone").value;
    const address = document.querySelector(".address").value;
    const grade = document.querySelector(".grade").value;
    const rejectArea = document.querySelector(".rejectArea").value;

    if (!name || !phone || !address || !grade) {
        alert('모든 필드를 입력해주세요.');
        return;
    }

    const memberData = {
        memberId,
        name,
        phone,
        address,
        grade,
        rejectArea
    };

    fetch('/member/update' + memberId, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(memberData)
    })
    .then(response => {
        if (response.ok) {
            alert('회원 정보가 성공적으로 수정되었습니다.');
            window.location.href = '/h_shopping/member/list.jsp';
        } else {
            return response.json().then(data => {
                throw new Error(data.message || '회원 정보 수정에 실패했습니다.');
            });
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert(error.message);
    });
}   