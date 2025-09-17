const listMember = () => {
    fetch('/member/list')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('memberTableBody');
            tbody.innerHTML = ''; // Clear existing rows

            data.forEach(member => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td>${member.id}</td>
                    <td>${member.name}</td>
                    <td>${member.phone}</td>
                    <td>${member.address}</td>
                    <td>${new Date(member.joinDate).toISOString().split('T')[0]}</td>
                    <td>${member.grade}</td>
                    <td>${member.city}</td>
                    <td>
                        <button onclick="editMember(${member.id})">Edit</button>
                        <button onclick="deleteMember(${member.id})">Delete</button>
                    </td>
                `;

                tbody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching members:', error));
}