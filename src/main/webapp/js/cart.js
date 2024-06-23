document.addEventListener('DOMContentLoaded', () => {
    const products = [
        { id: 1, name: 'Product 1', price: 50, photo: 'https://via.placeholder.com/150' },
        { id: 2, name: 'Product 2', price: 60, photo: 'https://via.placeholder.com/150' },
        { id: 3, name: 'Product 3', price: 70, photo: 'https://via.placeholder.com/150' },
        { id: 4, name: 'Product 4', price: 80, photo: 'https://via.placeholder.com/150' },
        { id: 5, name: 'Product 5', price: 90, photo: 'https://via.placeholder.com/150' },
        { id: 6, name: 'Product 6', price: 100, photo: 'https://via.placeholder.com/150' },
        { id: 7, name: 'Product 7', price: 110, photo: 'https://via.placeholder.com/150' },
        { id: 8, name: 'Product 8', price: 120, photo: 'https://via.placeholder.com/150' },
        { id: 9, name: 'Product 9', price: 130, photo: 'https://via.placeholder.com/150' },
        { id: 10, name: 'Product 10', price: 140, photo: 'https://via.placeholder.com/150' },
    ];

    const productList = document.getElementById('product-list');

    products.forEach(product => {
        const productItem = document.createElement('div');
        productItem.classList.add('product-item');
        productItem.innerHTML = `
            <img src="${product.photo}" alt="${product.name}">
            <h3>${product.name}</h3>
            <p class="price">$${product.price}</p>
            <button class="add-to-cart" data-id="${product.id}">Add to Cart</button>
        `;
        productList.appendChild(productItem);
    });

    const cart = [];

    document.querySelectorAll('.add-to-cart').forEach(button => {
        button.addEventListener('click', (e) => {
            const productId = parseInt(e.target.dataset.id);
            const product = products.find(p => p.id === productId);
            cart.push(product);
            alert(`${product.name} added to cart!`);
            localStorage.setItem('cart', JSON.stringify(cart));
        });
    });
});
