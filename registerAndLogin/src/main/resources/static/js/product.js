document.addEventListener("DOMContentLoaded", function () {
    fetchProducts();
    displayCartItems();

    const goToOrdersBtn = document.getElementById("goToOrders");
    if (goToOrdersBtn) {
        goToOrdersBtn.addEventListener("click", function () {
			displayCartItems();
            window.location.href = "order.html"; 
        });
    }
});

function fetchProducts() {
    fetch("http://192.168.33.89:8080/product/get")
        .then(response => response.json())
        .then(products => {
            displayProducts(products);
        })
        .catch(error => console.error("Error fetching products:", error));
}

function displayProducts(products) {
    const productList = document.getElementById("productList");
    productList.innerHTML = "";
    products.forEach(product => {
        const productCard = document.createElement("div");
        productCard.classList.add("product-card");
        productCard.innerHTML = `
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>Price: $${product.price.toFixed(2)}</p>
            <button onclick="addToCart(${product.id}, '${product.name}')">Add to Cart</button>
        `;
        productList.appendChild(productCard);
    });
}

function addToCart(productId, productName) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    const productIndex = cart.findIndex(item => item.id === productId);

    if (productIndex !== -1) {
        cart[productIndex].quantity += 1;
    } else {
        cart.push({ id: productId, name: productName, quantity: 1 });
    }

    localStorage.setItem("cart", JSON.stringify(cart));
    displayCartItems();
}

function displayCartItems() {
    console.log("Updating cart display...");

    const cartItemsContainer = document.getElementById("cartItems");
    if (!cartItemsContainer) {
        console.error("cartItems element not found in HTML!");
        return;
    }

    const cart = JSON.parse(localStorage.getItem("cart")) || [];
    console.log("Current Cart Data:", cart);

    cartItemsContainer.innerHTML = cart.length > 0 ? "" : "<p>Cart is empty</p>";

    cart.forEach(item => {
        console.log("Processing item:", item);
        const cartItem = document.createElement("div");
        cartItem.classList.add("cart-item");
        cartItem.innerHTML = `<p>${item.name} - ${item.quantity}</p>`;
        cartItemsContainer.appendChild(cartItem);
    });
}