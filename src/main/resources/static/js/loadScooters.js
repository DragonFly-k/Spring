const token = window.localStorage.getItem("token");
const accountId = window.localStorage.getItem("accountId");
const scooterId = window.localStorage.getItem("scooterId");
const scooterContent = document.getElementById("scooters-content");
let myRent;

if (token) {
    if(scooterId) scooterInfo(scooterId);
    else {
        fetch(`/api/v1/account/rent?accountId=${accountId}`, {
            headers: {
                'Accept-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        }).then(async (response) => {
            if(response.ok) {
                const data = await response.json();
                if(data && data.scooter) {
                    window.localStorage.setItem("scooterId", data.scooter.id);
                    myRent = data;
                    scooterInfo(data.scooter.id);
                } else {
                    scooterList();
                }
            } else scooterList();
        })
    }
} else {
    requestAuth();
}

function requestAuth() {
    scooterContent.innerHTML = "";
    const div = document.createElement("div");
    div.appendChild((() => {
        const header = document.createElement('h1')
        header.innerHTML = "Please sign in or create account firstly";
        return header;
    })())
    scooterContent.appendChild(div);
}

function scooterList() {
    scooterContent.innerHTML = "";
    fetch('/api/v1/scooter/list', {
        headers: {
            'Accept-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    }).then(async (response) => {
        if(response.ok) {
            const data = await response.json() || [];
            const availabilityResponse = await fetch('/api/v1/scooter/availabilityList', {
                headers: {
                    'Accept-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });
            const availabilityData = await availabilityResponse.json();
            if(data.length) {
                data.forEach(scooter => scooterContent.appendChild(scooterComponent(scooter, availabilityData)))
            }
        } else if (response.status === 403) {
            requestAuth()
        }
    })
}

async function scooterInfo(id) {
    scooterContent.innerHTML = "";
    const getByIdResponse = await fetch(`/api/v1/scooter/get?id=${id}`, {
        headers: {
            'Accept-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })
    if(!getByIdResponse.ok) {
        scooterList();
        return;
    }
    const scooter = await getByIdResponse.json();
    const container = document.createElement('div');
    container.appendChild((() => {
        const header = document.createElement('h1');
        header.innerHTML = "Scooter Info";
        return header;
    })());

    container.appendChild((() => {
        const p = document.createElement('p');
        p.innerHTML = `Scooter Model: ${scooter.model}`;
        return p;
    })());

    container.appendChild((() => {
        const p = document.createElement('p');
        p.innerHTML = `Price per minute: ${scooter.price}`;
        return p;
    })());

    const availabilityResponse = await fetch(`/api/v1/account/rent?accountId=${accountId}`, {
        headers: {
            'Accept-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });

    if (availabilityResponse.ok) {
        const data = await availabilityResponse.json();
        if(data && data.rentTime) {
            container.appendChild((() => {
                const p = document.createElement('p');
                p.innerHTML = `Rent Date: ${new Date(data.rentTime).toLocaleDateString()}`;
                return p;
            })());

            container.appendChild((() => {
                const p = document.createElement('p');
                p.innerHTML = `Rent Time: ${new Date(data.rentTime).toLocaleTimeString()}`;

                return p;
            })());
        } else {
            scooterList();
        }
    } else scooterList();

    container.appendChild((() => {
        const button = document.createElement('button');
        button.innerHTML = "Stop";
        button.className = "btn btn-success";
        button.onclick = async () => {
            window.localStorage.removeItem("scooterId");
            await fetch("/api/v1/scooter/rent", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify({
                    accountId,
                    scooterId: scooter.id
                })
            })
            scooterList();
        }

        return button;
    })());

    scooterContent.appendChild(container);
}

function scooterComponent(scooter, availabilityData) {
    const container = document.createElement('div');
    container.className = "scooter-card";
    container.appendChild((() => {
        const image = document.createElement('img');
        image.src = "/img/sc.png";
        image.width = 250;

        return image;
    })())
    const div = document.createElement("div");
    div.appendChild((() => {
        const header = document.createElement('h1');
        header.innerHTML = scooter.model;

        return header;
    })());
    div.appendChild((() => {
        const price = document.createElement('p');
        price.innerHTML = `Price per minute: ${scooter.price.toFixed(2)}$`;

        return price;
    })());
    div.appendChild((() => {
        const button = document.createElement('button');
        button.innerHTML = 'Take this!';
        button.className = "btn btn-primary";
        button.onclick = async () => {
            window.localStorage.setItem("scooterId", scooter.id);
            await fetch("/api/v1/scooter/rent", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify({
                    accountId,
                    scooterId: scooter.id
                })
            })
            scooterInfo(scooter.id);
        }

        if(availabilityData.find(availability => availability.scooter.id === scooter.id)) {
            button.disabled = true;
        }

        return button;
    })());

    container.appendChild(div);
    return container;
}