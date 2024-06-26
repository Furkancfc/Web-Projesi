document.addEventListener('DOMContentLoaded', () => {
    const cartItems = [];
    const cartItemsContainer = document.getElementById('cart-items');
    const totalPriceElement = document.getElementById('total-price');

    function updateCart() {
        cartItemsContainer.innerHTML = '';
        let total = 0;

        cartItems.forEach(item => {
            const cartItem = document.createElement('div');
            cartItem.classList.add('cart-item');
            cartItem.innerHTML = `
                <img src="${item.photo}" alt="${item.name}">
                <h3>${item.name}</h3>
                <p class="price">$${item.price.toFixed(2)}</p>
            `;
            cartItemsContainer.appendChild(cartItem);
            total += item.price;
        });

        totalPriceElement.innerText = `Total: $${total.toFixed(2)}`;
    }

    document.querySelectorAll('.add-to-cart').forEach(button => {
        button.addEventListener('click', () => {
            const randomProduct = {
                id: 1,
                name: 'Random Product',
                price: 99.99,
                photo: 'https://via.placeholder.com/150'
            };
            cartItems.push(randomProduct);
            updateCart();
        });
    });
});
