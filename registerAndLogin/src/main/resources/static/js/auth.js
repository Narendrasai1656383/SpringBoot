window.history.replaceState(null, "", window.location.href);
window.addEventListener("popstate", function () {
    window.history.replaceState(null, "", window.location.href);
});

const API_BASE_URL = "http://localhost:8080/auth";

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");

    if (loginForm) {
        loginForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const userName = document.getElementById("username").value;
            const password = document.getElementById("password").value;
            
            const response = await fetch(`${API_BASE_URL}/login`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ userName, password })
            });

            if (response.ok) {
                alert("Login successful!");
                window.location.href = "products.html";
            } else {
                alert("Invalid credentials");
            }
        });
    }

    if (registerForm) {
        registerForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const userName = document.getElementById("newUsername").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("newPassword").value;

            const response = await fetch(`${API_BASE_URL}/register`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ userName, email, password })
            });

            const result = await response.text();
            alert(result);
            if (response.ok) {
                window.location.href = "login.html";
            }
        });
    }
});
