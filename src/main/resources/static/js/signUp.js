function signUp() {
    const login = document.querySelector("#login").value;
    const password = document.querySelector("#password").value;
    const email = document.querySelector("#email").value;
    const error = document.querySelector("#error");

    error.innerHTML = "";

    fetch("http://localhost:8080/api/v1/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept-Type": "application/json"
        },
        body: JSON.stringify({
            login,
            password,
            email
        })
    }).then(async (res) => {
        const data = await res.json();

        if(res.status === 201) {
            window.location.assign('/signin');
        } else {
            console.log(data);
            const {errors} = data;
            errors.forEach(err => {
                const p = document.createElement("p");
                p.innerHTML = err.message;
                error.appendChild(p);
            });
        }
    })
}