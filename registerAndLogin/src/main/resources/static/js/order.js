document.addEventListener("DOMContentLoaded", function () {
    displayCartItems();

    const placeOrderBtn = document.getElementById("placeOrder");
    if (placeOrderBtn) {
        placeOrderBtn.addEventListener("click", placeOrder);
    }

    const viewOrderHistoryBtn = document.getElementById("viewOrderHistory");
    if (viewOrderHistoryBtn) {
        viewOrderHistoryBtn.addEventListener("click", fetchOrders);
    }
});

async function fetchOrders() {
    try {
        console.log("Fetching orders...");
        const response = await fetch("http://localhost:8080/order/getAllOrders", {
            method: "GET",
            credentials: "include"
        });

        if (!response.ok) {
            throw new Error("Failed to fetch orders. Status: " + response.status);
        }

        const orders = await response.json();
        console.log("Fetched orders:", orders);
        displayOrders(orders);
    } catch (error) {
        console.error("Error fetching orders:", error);
    }
}

function displayOrders(orders) {
    const ordersContainer = document.getElementById("orderList");
    if (!ordersContainer) {
        console.error("Error: #orderList element not found in HTML.");
        return;
    }

    ordersContainer.innerHTML = orders.length > 0 ? "" : "<p>No previous orders found.</p>";

    orders.forEach(order => {
        if (!order.orderItems) {
            console.error("Error: Order data missing 'orderItems'.", order);
            return;
        }

        // Handle null price values
        const orderPrice = order.orderPrice !== null ? `$${order.orderPrice.toFixed(2)}` : "Not available";

        const orderElement = document.createElement("div");
        orderElement.classList.add("order-item");
        orderElement.innerHTML = `
            <h3>Order #${order.id}</h3>
            <p>Date: ${new Date(order.orderDate).toLocaleString()}</p>
            <p><strong>Total Price:</strong> ${orderPrice}</p>
            <h4>Items:</h4>
            <ul>
                ${order.orderItems.map(item => `
                    <li>${item.product?.name || "Unknown"} - ${item.quantity}</li>
                `).join("")}
            </ul>
        `;
        ordersContainer.appendChild(orderElement);
    });
}

function displayCartItems() {
    const cartItemsContainer = document.getElementById("cartItems");
    if (!cartItemsContainer) return;

    const cart = JSON.parse(localStorage.getItem("cart")) || [];
    cartItemsContainer.innerHTML = cart.length > 0 ? "" : "<p>No items in the cart</p>";

    cart.forEach(item => {
        const cartItem = document.createElement("div");
        cartItem.classList.add("cart-item");
        cartItem.innerHTML = `
            <p>${item.name} - ${item.quantity}</p>
        `;
        cartItemsContainer.appendChild(cartItem);
    });
}

async function placeOrder() {
    const cart = JSON.parse(localStorage.getItem("cart")) || [];

    if (cart.length === 0) {
        alert("Please add products to the cart before placing an order.");
        window.location.href = "products.html";
        return;
    }

    const orderItems = cart.map(item => ({
        productId: item.id,
        quantity: item.quantity
    }));

    try {
        console.log("Placing order with items:", orderItems);
        const response = await fetch("http://localhost:8080/order/placeOrder", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify({ orderItems })
        });

        if (!response.ok) {
            throw new Error("Order placement failed. Status: " + response.status);
        }

        alert("Order placed successfully!");
        localStorage.removeItem("cart");  // Clear cart after order placement
        displayCartItems();  // Refresh cart display
        fetchOrders();  // Refresh order list
    } catch (error) {
        console.error("Error placing order:", error);
    }
}