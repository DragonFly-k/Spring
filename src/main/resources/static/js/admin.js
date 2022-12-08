function addScooter() {
    const token = window.localStorage.getItem("token");
    const model = document.getElementById('model').value;
    const price = document.getElementById('price').value;

    fetch("http://localhost:8080/api/v1/admin/addScooter/"+model+"/"+price, {
        method: 'POST',
        headers: { "Authorization": `Bearer ${token}`},
    }).then(async (response) => {
        if(response.ok) {
            window.location.reload();
            alert('Scooter was added!')
        } else {
            const data = await response.json();
            alert(data.message);
            console.error("Errors", data.errors);
        }
    })
}

function deleteScooter() {
   const token = window.localStorage.getItem("token");
   const model = document.getElementById('model').value;

   fetch("http://localhost:8080/api/v1/admin/deleteScooter/" + model, {
       method: 'DELETE',
       headers: {"Authorization": `Bearer ${token}`}
   }).then(response => {
   console.log(response)
       if(response.ok) {
           window.location.reload();
           alert('Scooter was deleted!')
       } else {
           const data = response.json();
              alert(data.message);
           console.error("Errors", data.errors);
       }
   })
}

function updScooter() {
    const token = window.localStorage.getItem("token");
    const model = document.getElementById('model').value;
    const price = document.getElementById('price').value;

    fetch("http://localhost:8080/api/v1/admin/updScooter/"+model+"/"+price, {
        method: 'PUT',
        headers: { "Authorization": `Bearer ${token}`},
    }).then(async (response) => {
        if(response.ok) {
            window.location.reload();
            alert('Scooter was updated!')
        } else {
            const data = await response.json();
            alert(data.message);
            console.error("Errors", data.errors);
        }
    })
}