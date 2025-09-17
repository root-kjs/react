const listSales = () => {
    fetch('/api/sales')
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector("#salesBody");
            let html = '';

            data.forEach(sale => {
                html += `
                    <tr>
                        <td>${sale.id}</td>
                        <td>${sale.productName}</td>
                        <td>${sale.quantity}</td>
                        <td>${sale.price}</td>
                        <td>${new Date(sale.date).toISOString().split('T')[0]}</td>
                        <td>
                            <button onclick="editSale(${sale.id})">Edit</button>
                            <button onclick="deleteSale(${sale.id})">Delete</button>
                        </td>
                    </tr>
                `;
                tbody.innerHTML = html;
            });
        })
        .catch(error => console.error('Error fetching sales:', error));
}
document.addEventListener('DOMContentLoaded', listSales);
const editSale = (id) => {
    window.location.href = `/h_shopping/sales/update.jsp?id=${id}`;
}