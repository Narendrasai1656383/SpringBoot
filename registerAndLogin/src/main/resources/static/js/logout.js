document.addEventListener("DOMContentLoaded", () => {
	const logoutForm=document.getElementById("logoutForm");
	if (logoutForm) {
	        logoutForm.addEventListener("submit", (e) => {
	            e.preventDefault();
	            fetch(`http://localhost:8080/auth/logout`, {
	                method: "GET",
	                credentials: "include" 
	            })
	            .then(response => {
	                if (!response.ok) {
	                    return response.text().then(text => Promise.reject(text));
	                }
	                return response.text();
	            })
	            .then(data => {
	                alert(data); 
					localStorage.clear();
	                window.location.href = "index.html";
	            })
	            .catch(error => {
	                console.error("Login error:", error);
	                alert(error); 
					window.location.href = "index.html";
	            });
	        });
	    }
	})