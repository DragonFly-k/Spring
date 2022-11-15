function addScooter() {
    const token = window.localStorage.getItem("token");
    const model = document.getElementById('model');
    const price = document.getElementById('price');

    fetch('/api/v1/admin/addScooter', {
        method: 'POST',
        headers: {
            "Authorization": `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            model: model.value,
            price: parseFloat(price.value),
        })
    }).then(async (response) => {
        if(response.ok) {
            model.value = '';
            price.value = '';
            alert('Scooter was added!')
        } else {
            const data = await response.json();
            console.error("Errors", data.errors);
        }
    })
}