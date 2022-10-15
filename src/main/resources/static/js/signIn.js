function signIn() {
    const login = document.querySelector("#login").value;
    const password = document.querySelector("#password").value;
    const error = document.querySelector("#error");

    error.innerHTML = "";

    fetch("http://localhost:8080/api/v1/auth/signin", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept-Type": "application/json"
        },
        body: JSON.stringify({
            login,
            password
        })
    }).then(async (res) => {
        const data = await res.json();

        if(res.status === 200) {
            const {accountId, token} = data;
            window.localStorage.setItem("accountId", accountId);
            window.localStorage.setItem("token", token);
            window.location.assign('/');
        } else {
            const {errors} = data;
            error.innerHTML = errors;
        }
    })
}