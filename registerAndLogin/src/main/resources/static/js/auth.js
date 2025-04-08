window.history.replaceState(null, "", window.location.href);
window.addEventListener("popstate", function () {
    window.history.replaceState(null, "", window.location.href);
});

const API_BASE_URL = "http://localhost:8080/auth";

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");

    if (loginForm) {
        loginForm.addEventListener("submit", (e) => {
            e.preventDefault();
            const userName = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            fetch(`${API_BASE_URL}/login`, {
                method: "POST",
                headers: { 
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ userName, password }),
                credentials: "include" // Ensures session cookies are sent
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => Promise.reject(text));
                }
                return response.text();
            })
            .then(data => {
                alert(data); 
				localStorage.setItem("isActive","yes");
                window.location.href = "products.html";
            })
            .catch(error => {
                console.error("Login error:", error);
                alert(error); 
            });
        });
    }

    if (registerForm) {
        registerForm.addEventListener("submit", (e) => {
            e.preventDefault();
            const userName = document.getElementById("newUsername").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("newPassword").value;

            fetch(`${API_BASE_URL}/register`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ userName, email, password })
            })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => Promise.reject(text));
                }
                return response.text();
            })
            .then(result => {
                alert(result); 
                window.location.href = "login.html";
            })
            .catch(error => {
                console.error("Registration error:", error);
                alert(error);
            });
        });
    }
});
